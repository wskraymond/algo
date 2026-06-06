package com.practice.fundamental.j8weapons;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class RemoteService {
    private static final Random random = new Random();

    public static void delay() {
        int delay = 500 + random.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    enum Currency { EUR, USD, GBP }
    static class ExchangeService {
         private static final Map<String, Double> RATES = Map.of(
                "EUR_USD", 1.09,
                "USD_EUR", 0.92,
                "EUR_GBP", 0.86,
                "GBP_USD", 1.27
        );

         /** Simulates a remote exchange-rate lookup (~1 s). */
        public static double getRate(Currency from, Currency to) {
            delay();
            return RATES.getOrDefault(from + "_" + to, 1.0);
        }

    }


    static class Discount {
        public enum Code {
            NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

            private final int percentage;

            Code(int percentage) {
                this.percentage = percentage;
            }
        }

        public static String applyDiscount(Quote quote) {
            return quote.getShopName() + " price is " +
                    Discount.apply(quote.getPrice(),
                            quote.getDiscountCode());
        }

        public static double applyDiscountValue(Quote quote) {
            return Discount.apply(quote.getPrice(),
                    quote.getDiscountCode());
        }

         /** Simulates a remote service to apply a discount code (~1 s). */

        private static double apply(double price, Code code) {
            delay();
            return price * (100 - code.percentage) / 100;
        }

    }

    static class Shop {
        private String name;
        private final Currency currency;
        public Shop(String name, Currency currency) {
            this.name = name;
            this.currency = currency;
        }

        public String getName() {
            return name;
        }

        public Currency getCurrency() {
            return currency;
        }

        /**
         * Synchronous price with embedded discount code.
         * Returns "ShopName:price:CODE"  (Listing 11.14 format).
         */
        public String getPrice(String product) {
            double price = calculatePrice(product);
            Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
            return String.format("%s:%.2f:%s:%s", name, price, code, currency);
        }

        /** Simulates a long-running operation to calculate the price. */
        private double calculatePrice(String product) {
            delay();
            return random.nextDouble() * product.charAt(0) + product.charAt(1);
        }

        /**
         * manual CompletableFuture with
         * completeExceptionally for proper error propagation.
         */
        public CompletableFuture<Double> getPriceAsync(String product) {
            CompletableFuture<Double> futurePrice = new CompletableFuture<>();
            new Thread(() -> {
                try {
                    double price = calculatePrice(product);
                    futurePrice.complete(price);
                } catch (Exception ex) {
                    futurePrice.completeExceptionally(ex);   // Listing 11.6
                }
            }).start();
            return futurePrice;
        }

    }

}

class Quote {

    private final String shopName;
    private final double price;
    private final RemoteService.Discount.Code discountCode;
    private final RemoteService.Currency currency;

    public Quote(String shopName, double price, RemoteService.Discount.Code code, RemoteService.Currency currency) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = code;
        this.currency = currency;
    }

    public static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        RemoteService.Discount.Code code = RemoteService.Discount.Code.valueOf(split[2]);
        RemoteService.Currency currency = RemoteService.Currency.valueOf(split[3]);
        return new Quote(shopName, price, code, currency);
    }

    public String getShopName() { return shopName; }
    public double getPrice() { return price; }
    public RemoteService.Discount.Code getDiscountCode() { return discountCode; }
    public RemoteService.Currency getCurrency() { return currency; }
}

public class BestPriceFinder {
    private static final List<RemoteService.Shop> shops = Arrays.asList(
            new RemoteService.Shop("BestPrice",      RemoteService.Currency.USD),
            new RemoteService.Shop("LetsSaveBig",    RemoteService.Currency.EUR),
            new RemoteService.Shop("MyFavoriteShop", RemoteService.Currency.GBP),
            new RemoteService.Shop("BuyItAll",       RemoteService.Currency.GBP),
            new RemoteService.Shop("ShopEasy",       RemoteService.Currency.EUR)
    );


    Executor executor = new ThreadPoolExecutor(
            shops.size(),
            100,
            1000l,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>()
    );

    public List<String> findPrices_using_stream(String product) {
        return shops.parallelStream()
                .map(shop -> shop.getPrice(product))
                .collect(toList());
    }

    public List<String> findPrices(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> shop.getPrice(product), executor))
                        .map(future -> future.thenApply(Quote::parse))
                        .map(future -> future.thenCompose(quote ->
                                CompletableFuture.supplyAsync(
                                        () -> RemoteService.Discount.applyDiscount(quote), executor)))
                        .collect(toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    /**
     * getPrice()          supplyAsync   → async I/O, needs its own thread
     * Quote::parse        thenApply     → pure transform, no I/O, stay on same thread
     * discount + rate     thenCompose   → need quote value to build futures
     *   ├── discount      supplyAsync   → async I/O ─┐
     *   └── getRate       supplyAsync   → async I/O ─┴─► thenCombine → merge results
     *
     * @param product
     * @return
     */
    public Stream<CompletableFuture<String>> findPricesInUSD(String product) {
        return shops.stream()
                // Step 1 – async: fetch "ShopName:price:CODE:CURRENCY"
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(product), executor))
                // Step 2 – sync: String → Quote (no I/O)
                .map(f -> f.thenApply(Quote::parse))
                // Step 3 – for each Quote, launch discount + rate IN PARALLEL
                .map(f -> f.thenCompose(quote ->
                        CompletableFuture.supplyAsync(() -> RemoteService.Discount.applyDiscountValue(quote), executor)
                                .thenCombine(
                                        CompletableFuture.supplyAsync(
                                                () -> RemoteService.ExchangeService.getRate(quote.getCurrency(), RemoteService.Currency.USD), executor),
                                        (discountedPrice, rate) -> String.format(
                                                "%s price is %.2f USD (from %s, rate=%.2f)",
                                                quote.getShopName(), discountedPrice * rate,
                                                quote.getCurrency(), rate))));
    }



    public Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(
                                () -> RemoteService.Discount.applyDiscount(quote), executor)));
    }


    public static void main(String[] args) {
        // Example of manual CompletableFuture usage
        shops.stream().map(shop-> shop.getPriceAsync("myPhone"))
                .map(f->f.whenCompleteAsync((result, ex) -> {
                    System.out.println("Price fetched: " + result);
                    if (ex != null) {
                        System.out.println("Error fetching price: " + ex.getMessage());
                    } else {
                        System.out.println("Price fetched: " + result);
                    }
                }));


        BestPriceFinder finder = new BestPriceFinder();
        // Register thenAccept on each future
        finder.findPricesStream("myPhone").map(f -> f.thenAccept(System.out::println));

        // Wait for all with allOf
        long start = System.nanoTime();
        long finalStart = start;
        CompletableFuture[] futures = finder.findPricesInUSD("myPhone")
                .map(f -> f.thenAccept(
                        s -> System.out.println(s + " (done in " +
                                ((System.nanoTime() - finalStart) / 1_000_000) + " msecs)")))
                .toArray(size -> new CompletableFuture[size]);

        //wait for the first with anyOf
        start = System.nanoTime();
        long finalStart1 = start;
        CompletableFuture.anyOf(futures).thenAccept(s -> System.out.println(s + " (done in " +
                ((System.nanoTime() - finalStart1) / 1_000_000) + " msecs)")).join();

        CompletableFuture.allOf(futures).thenAccept(
                s -> System.out.println(s + " (done in " +
                        ((System.nanoTime() - finalStart) / 1_000_000) + " msecs)")).join();
        System.out.println("All shops have now responded in "
                + ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }
}
