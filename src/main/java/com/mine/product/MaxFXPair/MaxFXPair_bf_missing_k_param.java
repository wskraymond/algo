package com.mine.product.MaxFXPair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxFXPair_bf_missing_k_param {
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
         *      f(s, v, k) = max{f(s,u, k-1) * cost(u,v)} | for any u to v , if k>=1
         *
         * Base Case:
         *      f(s,s) = 1
         * Goal:
         *     f(s, d)
         */
        Map<String, Double> dp = new HashMap<>();

        //init to -Inf
        fxPairs.keySet().forEach(pair->{
            String[] tmp = pair.split("=");
            dp.put(tmp[0], Double.MIN_VALUE);
            dp.put(tmp[1], Double.MIN_VALUE);
        });

        //edge case
        if(!dp.containsKey(fromCcy)
            || !dp.containsKey(toCcy)){
            return -1;
        }

        //base case
        dp.put(fromCcy, 1d);

        int n  = dp.size();
        for(int k=1; k<=n-1;k++){
            for(Map.Entry<String, Double> fxPair: fxPairs.entrySet()){
                String[] tmp = fxPair.getKey().split("=");
                String ccy1 = tmp[0];
                String ccy2 = tmp[1];

                //forward edge
                double forwardRate = fxPair.getValue();
                double prevForward = dp.get(ccy1);
                double nextForward = dp.get(ccy2);
                if(prevForward>0) {
                    dp.put(ccy2, Math.max(nextForward, prevForward * forwardRate));
                }

                //backward edge
                double backwardRate = 1/fxPair.getValue();
                double prevBackward = dp.get(ccy2);
                double nextBackward = dp.get(ccy1);
                if(prevBackward>0) {
                    dp.put(ccy1, Math.max(nextBackward, prevBackward * backwardRate));
                }
            }
        }

        double result = dp.getOrDefault(toCcy, -1d);
        return result!=Integer.MIN_VALUE ? result : -1;
    }
}
