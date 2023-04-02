package com.mine.stack.parenthesis;

/**
 * Brute force thinking
 * We can generate all 2^(2^n) sequences of '(' and ')' characters.
 * Then, we will check if each one is valid.
 *
 * Backtracking Approach:
 * let's only add them when we know it will remain a valid sequence.
 * We can do this by keeping track of the number of opening
 * and closing brackets we have placed so far.
 *
 *
 * We can start an opening bracket
 *     if we still have one (of n) left to place.
 * And we can start a closing bracket
 *     if it would not exceed the number of opening brackets
 *
 * Problem :
 *
 * Given n pairs of parentheses,
 * write a function to generate all combinations of well-formed parentheses.
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 *
 * Example 2:
 *
 * Input: n = 1
 * Output: ["()"]
 *
 */
public class GenerateParenthesis {
}
