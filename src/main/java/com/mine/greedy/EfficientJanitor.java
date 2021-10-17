package com.mine.greedy;

import java.util.Collections;
import java.util.List;

public class EfficientJanitor {
    /**
     * Input: a list of waste (plastic bags)
     * Condition: total weight for each trip <= 3.0 pounds
     * Output: the minimum number of trip that janitor has to make
     *
     * Constraints :
     *   1<= n <=1000
     *   1.01 <= weight <= 3.0
     *   limit <= 3.0 pounds
     *
     * @param weight
     * @return
     */
    public int efficientJanitor(List<Float> weight){
        Collections.sort(weight);

        int result = 0;
        //assume: input is ArrayList
        // 1.01 + 1.01 + 1.01 = 3.03 > limit=3
        // 3/2 = 1.5
        // Thus, the maximum number of bags must be 2
        for(int i=0, j=weight.size()-1; i<=j; j--){
            if(i!=j
                && Double.compare(weight.get(i) + weight.get(j), 3.0f) <=0) {
                i++;
            }
            result++;
        }

        return result;
    }
}
