package com.practice.lc;

import java.util.ArrayDeque;
import java.util.Deque;

public class QInTwoStack {
    private final Deque<Integer> pushStack;
    private final Deque<Integer> popStack;

    public QInTwoStack() {
        pushStack = new ArrayDeque<>();
        popStack = new ArrayDeque<>();
    }

    public void push(int x) {
        pushStack.push(x);
    }

    public int pop() {
        if(popStack.isEmpty()){
            while(!pushStack.isEmpty()){
                popStack.push(pushStack.pop());
            }
        }

        return popStack.pop();
    }

    public int peek() {
        if(popStack.isEmpty()){
            while(!pushStack.isEmpty()){
                popStack.push(pushStack.pop());
            }
        }

        return popStack.peek();
    }

    public boolean empty() {
        return Math.max(pushStack.size(), popStack.size())==0;
    }
}
