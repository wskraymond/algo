package com.mine.product;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxFXPair_backtracking {
    /**
     * Maximize the FX amount in conversion
     * from fromCcy to toCcy
     *
     * if cannot convert, then return -1.
     *
     * Test case/Edge cases
     *      1. A <-> B <-> C <-> A ,
     *      given that A->B->C is increasing multiplication and C -> A is decreasing multiplication
     *          a) for from A to C
     *              i) A -> B -> C
     *                  - it can lead to a cycle: A -> B -> C -> A -> C with decreasing cycle
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
        return backtrack(fxPairs, new HashSet<>(), fromCcy, toCcy);
    }

    private double backtrack(Map<String, Double> fxPairs, Set<String> visit, String fromCcy, String toCcy){
        double max = -1;
        for(Map.Entry<String, Double> entry: fxPairs.entrySet()){
            String fxPair = entry.getKey();
            if(visit.contains(fxPair)){
                continue;
            }

            String[] tmp = fxPair.split("=");
            String ccy1 = tmp[0]; //entry.getKey().substring(0, 3);
            String ccy2 = tmp[1]; //entry.getKey().substring(4, 7);

            if(ccy1.equals(fromCcy)){
                double rate = entry.getValue();
                if(ccy2.equals(toCcy)){
                    max = Math.max(max, rate);
                } else {
                    visit.add(fxPair);
                    max = Math.max(max, rate*backtrack(fxPairs, visit, ccy2, toCcy));
                    visit.remove(fxPair);
                }
            } else if(ccy2.equals(fromCcy)){
                double rate = 1/entry.getValue();
                if(ccy1.equals(toCcy)){
                    max = Math.max(max, rate);
                } else {
                    visit.add(fxPair);
                    max = Math.max(max, rate*backtrack(fxPairs, visit, ccy1, toCcy));
                    visit.remove(fxPair);
                }
            }
        }

        return max;
    }
}
