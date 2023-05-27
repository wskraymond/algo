package com.mine.product.MaxFXPair;

import java.util.HashMap;
import java.util.Map;

public class MaxFXPair_bf {
    /**
     * Maximize the FX amount in conversion
     * from fromCcy to toCcy
     *
     * if cannot convert, then return -1.
     *
     * Test case/Edge cases
     *      1. A <-> B <-> C <-> A
     *          a) for from A to C
     *              i) A -> B -> C
     *                  - it can lead to a cycle: A -> B -> C -> A -> C
     *              ii) A -> C
     *          b) for from B to A
     *              i) B -> A
     *                  -> it can lead to multiply 1/rate And backward of the fx fair conversion
     *              ii) B -> C -> A
     *
     *      2. A <> B
     *      3. null
     *
     *
     * Question to ask/clarify
     *      1. 3 lettters for currency
     *      2. a graph with a cycle
     *          - FX Pair is bidirectional
     *          - sth makes sense during multiplication
     *      3. reduce the problem to shortest path problem
     *          i) by log the weight
     *          2) negate the weight
     *
     * which approach to choose
     *      1. backtracking <- why exponential time
     *      2. BF   <- why it can be applied to it.
     *      3. distrake <- explain why it doesn't work
     *
     *
     * @param fxPairs map{"A=B", 10.0}
     * @param fromCcy
     * @param toCcy
     * @return
     */
    public double maxProduct(Map<String, Double> fxPairs, String fromCcy, String toCcy){
        /**
         * Recurrence Relations:
         *      f(v, k) = max{f(v, k-1), f(u, k-1) * cost(u,v)} | for any u to v , if k>=0
         *
         * Base Case:
         *      f(s, 0) = 1
         *
         * Goal:
         *     f(v, n-1)
         */
        Map<String, Double> dp_k_1 = new HashMap<>();
        //init to -Inf
        for(String pair: fxPairs.keySet()){
            String[] tmp = pair.split("=");
            dp_k_1.put(tmp[0], Double.MIN_VALUE);
            dp_k_1.put(tmp[1], Double.MIN_VALUE);
        }

        //edge case
        if(!dp_k_1.containsKey(fromCcy)
            || !dp_k_1.containsKey(toCcy)){
            return -1;
        }

        //base case
        dp_k_1.put(fromCcy, 1d);
        Map<String, Double> dp_k = new HashMap<>(dp_k_1);

        /*
        in this bidirectional graph, why do we still use at most n - 1 edges ?
        for X -> B -> A -> path Y -> A -> B -> C,
             Assume A -> path Y -> A is positive cycle ( decreasing multiplication)
                - rate = 1/r1
             then B -> A and A-> B will offset each other
                = rate = 1
             Thus, x-> B -> C (given rate = 1/R)
                must be superior than X -> B -> A -> path Y -> A -> B -> C
                    - 1/R x 1/r1

         Eventually, we should never use both direction of the same edge in a path which be inferior
         */
        int n  = dp_k.size();
        for(int k=1; k<=n-1;k++){
            for(Map.Entry<String, Double> fxPair: fxPairs.entrySet()){
                String[] tmp = fxPair.getKey().split("=");
                String ccy1 = tmp[0];
                String ccy2 = tmp[1];

                //forward edge
                double forwardRate = fxPair.getValue();
                double prevForward = dp_k_1.get(ccy1);
                double nextForward = dp_k.get(ccy2);
                if(prevForward>0) {
                    dp_k.put(ccy2, Math.max(nextForward, prevForward * forwardRate));
                }

                //backward edge
                double backwardRate = 1/fxPair.getValue();
                double prevBackward = dp_k_1.get(ccy2);
                double nextBackward = dp_k.get(ccy1);
                if(prevBackward>0) {
                    dp_k.put(ccy1, Math.max(nextBackward, prevBackward * backwardRate));
                }
            }

            dp_k_1 = new HashMap<>(dp_k);
        }

        double result = dp_k.getOrDefault(toCcy, -1d);
        return result!=Integer.MIN_VALUE ? result : -1;
    }
}
