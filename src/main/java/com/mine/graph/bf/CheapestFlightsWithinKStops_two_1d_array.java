package com.mine.graph.bf;

import java.util.Arrays;

public class CheapestFlightsWithinKStops_two_1d_array {
    /**
     * There are n cities connected by some number of flights.
     * You are given an array flights where flights[i] = [fromi, toi, pricei]
     * indicates that there is a flight from city fromi to city toi with cost pricei.
     *
     * You are also given three integers src, dst, and k,
     * return the cheapest price from src to dst
     *  - with at most k stops (k stops exclude src, dst nodes)
     * If there is no such route, return -1.
     *
     *
     * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]],
     *        src = 0, dst = 2, k = 1
     * Output: 200
     * Explanation: The graph is shown.
     * The cheapest price from city 0 to city 2 with at most 1 stop costs 200,
     * as marked red in the picture.
     *
     * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]],
     *        src = 0, dst = 2, k = 0
     * Output: 500
     * Explanation: The graph is shown.
     * The cheapest price from city 0 to city 2 with at most 0 stop costs 500,
     * as marked blue in the picture.
     *
     * Constraints:
     *
     * 1 <= n <= 100
     * 0 <= flights.length <= (n * (n - 1) / 2)
     * flights[i].length == 3
     * 0 <= fromi, toi < n
     * fromi != toi
     * 1 <= pricei <= 104
     * There will not be any multiple flights between two cities.
     * 0 <= src, dst, k < n
     * src != dst
     * 
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param k
     * @return
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        /*
         0. At most K stops = At most k stops between src and dst
                        = At most [k + 2 (src&dst) - 1] Edges
                        = At most k + 1 edges
                        = At most X edges

         1. Recursion Relations:
                f(v,x) = min{ f(v, x-1), f(u, x-1) + w(u,v) | for all u to v }
         2. Base case:
                f(src, 0) = 0
         3. Our Goal:
                f(dst, X=k+1)
         */

        int[] dp_k_1 = new int[n];
        Arrays.fill(dp_k_1, Integer.MAX_VALUE);
        dp_k_1[src] = 0;
        int[] dp_k = dp_k_1.clone();

        for(int i=1;i<k+2;i++){
            for(int[] flight:flights){
                final int prev = flight[0];
                final int next = flight[1];
                final int price = flight[2];

                if(dp_k_1[prev]!=Integer.MAX_VALUE){
                    dp_k[next] = Math.min(dp_k[next], dp_k_1[prev] + price);
                }
            }

            dp_k_1 = dp_k.clone();
        }

        return dp_k[dst]!=Integer.MAX_VALUE ? dp_k[dst] : -1;
    }
}
