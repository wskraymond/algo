package com.mine.design;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/min-stack/
 *
 * Design a stack that supports push, pop, top, and retrieving
 * the minimum element in constant time.
 *
 * Implement the MinStack class:
 *
 *     MinStack() initializes the stack object.
 *     void push(int val) pushes the element val onto the stack.
 *     void pop() removes the element on the top of the stack.
 *     int top() gets the top element of the stack.
 *     int getMin() retrieves the minimum element in the stack.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * Output
 * [null,null,null,null,-3,null,0,-2]
 *
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 *
 *
 *
 * Constraints:
 *
 *     -231 <= val <= 231 - 1
 *     Methods pop, top and getMin operations will always be called on non-empty stacks.
 *     At most 3 * 104 calls will be made to push, pop, top, and getMin.
 *
 */
public class MinStack {
    private final Deque<Integer> valStack;
    private final Deque<Integer> minStack;

    /**
     * Methods pop, top and getMin operations
     * will always be called on non-empty stacks.
     */
    public MinStack() {
        valStack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int val) { //O(1)
        valStack.push(val);
        int min = minStack.isEmpty() ? val : Math.min(minStack.peek(), val);
        minStack.push(min);
    }

    public void pop() { //O(1)
        valStack.pop();
        minStack.pop();
    }

    public int top() { //O(1)
        return valStack.peek();
    }

    public int getMin() { //O(1)
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
