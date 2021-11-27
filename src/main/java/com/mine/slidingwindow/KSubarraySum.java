package com.mine.slidingwindow;

/**
 * https://practice.geeksforgeeks.org/problems/k-subarray-sum/0/?page=2&query=page2
 *
 *
 * Given an integer array arr of size N and two integers K and M, the task is to find M largest sums of K sized subarrays.
 *
 * Input:
 * 1. The first line of the input contains a single integer T denoting the number of test cases. The description of T test cases follows.
 * 2. The first line of each test case contains three integers N, K, and M.
 * 3. The next line contains N space-separated integers.
 *
 *
 * Output: For each test case, print M space-separated integers (Print the sums from the maximum to minimum)
 *
 * Constraints:
 * 1. 1 <= T <= 100
 * 2. 1 <= K <= N <= 104
 * 3. 1 <= M <= N - K + 1
 * 4. 1 <= arr[i] <= 105
 *
 * Example:
 * Input:
 * 2
 * 5 2 3
 * 10 11 10 11 12
 * 5 5 1
 * 1 2 3 4 5
 *
 *
 * Output:
 * 23 21 21
 * 15
 *
 * Explanation:
 * Test Case 1: 2 sized subarray sum are {21, 21, 21, 21, 23},
 * We need to print 3 largest sums from maximum to minimum
 *
 */
public class KSubarraySum {
}
