package com.mine.math;


/**
 * https://leetcode.com/problems/optimal-division/solution/
 *
 *
 * Input: [1000,100,10,2]
 * Output: "1000/(100/10/2)"
 * Explanation:
 * 1000/(100/10/2) = 1000/((100/10)/2) = 200
 * However, the bold parenthesis in "1000/((100/10)/2)" are redundant,
 *
 * Other cases:
 * 1000/(100/10)/2 = 50
 * 1000/(100/(10/2)) = 50
 * 1000/100/10/2 = 0.5
 * 1000/100/(10/2) = 2
 */

/**
 *
 *  Using some simple math we can find the easy solution of this problem.
 *  Consider the input in the form of [a,b,c,d],
 *  now we have to set priority of operations to maximize a/b/c/d.
 *  We know that to maximize fraction p/qp/q, qq(denominator) should be minimized.
 *  So, to maximize a/b/c/da/b/c/d we have to first minimize b/c/d.
 *  Now our objective turns to minimize the expression b/c/d.
 *
 * There are two possible combinations of this expression, b/(c/d) and (b/c)/d.
 *
 * b/(c/d)        (b/c)/d = b/c/d
 * (b*d)/c        b/(d*c)
 * d/c            1/(d*c)
 * Obviously, d/c > 1/(d*c)d/c>1/(d∗c) for d>1d>1.
 *
 * You can see that second combination will always be less than first one for numbers greater than 11.
 * So, the answer will be a/(b/c/d).
 * Similarly for expression like a/b/c/d/e/f... answer will be a/(b/c/d/e/f...).
 *
 *
 *
 */
public class OptimalDivision {

    public String optimalDivision(int[] nums) {
        T t = optimal(nums, 0, nums.length - 1, "");
        return t.max_str;
    }
    class T {
        float max_val, min_val;
        String min_str, max_str;
    }
    public T optimal(int[] nums, int start, int end, String res) {
        T t = new T();
        if (start == end) {
            t.max_val = nums[start];
            t.min_val = nums[start];
            t.min_str = "" + nums[start];
            t.max_str = "" + nums[start];
            return t;
        }
        t.min_val = Float.MAX_VALUE;  // set Max to min_val
        t.max_val = Float.MIN_VALUE;  // set Min to max_val
        t.min_str = t.max_str = "";
        for (int i = start; i < end; i++) {
            T left = optimal(nums, start, i, "");
            T right = optimal(nums, i + 1, end, "");
            if (t.min_val > left.min_val / right.max_val) {
                t.min_val = left.min_val / right.max_val;
                t.min_str = left.min_str + "/" + (i + 1 != end ? "(" : "") + right.max_str + (i + 1 != end ? ")" : "");
            }
            if (t.max_val < left.max_val / right.min_val) {
                t.max_val = left.max_val / right.min_val;
                t.max_str = left.max_str + "/" + (i + 1 != end ? "(" : "") + right.min_str + (i + 1 != end ? ")" : "");
            }
        }
        return t;
    }
}
