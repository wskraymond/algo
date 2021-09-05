package com.mine.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * You are given an array people where people[i] is the weight of the ith person,
 *  and an infinite number of boats where each boat can carry a maximum weight of limit.
 *  Each boat carries at most two people at the same time,
 *  provided the sum of the weight of those people is at most limit.
 *
 * Return the minimum number of boats to carry every given person.
 *
 *
 * Input: people = [3,2,2,1], limit = 3
 * Output: 3
 * Explanation: 3 boats (1, 2), (2) and (3)
 *
 * optimal structure:
 *  0: n-1 set{u1,vk | score: 2, <=limit} or {u1 : score: 1 , <=limit}
 *  1: n-2 or n-1  set{u2,vk | score: 2, <=limit} or {u2 : score: 1 , <=limit}
 *  2: n-3, n-2 , n-1 =>total=n
 *  n: ...
 *  => exponential ? not sure
 *
 *  Greedy Choice:
 *  Match the heaviest with the lightest to minimize the number of boats as much as possible
 *  Proof : if match with other than lightest or vice versa
 *                 1. possible unmatch for heavier items(> limit)
 *
 *  Solution: sort the array and match head(i) and tail(j) until i=j.
 *                    - i: to be put in the boat
 *                    - j: to be pair with the ith item if wi+wj <= limit
 */
public class BoatsToSavePpl {
    public int numRescueBoats(int[] people, int limit) {
        //lightest to heaviest
        Arrays.sort(people); //nlogn

        //Heaviest to lightest
        /*Arrays.sort(Arrays.stream(people).boxed().toArray(Integer[]::new),
                Collections.reverseOrder());*/

        int result=0;
        for(int i=0,j=people.length-1; i<=j;j--){ //n
            if(i!=j
                && people[i] + people[j] <=limit){
                i++;
            }
            result++;
        }

        return result; //O(nlogn)
    }
}
