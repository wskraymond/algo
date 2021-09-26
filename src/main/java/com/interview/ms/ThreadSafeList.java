package com.interview.ms;

import java.util.ArrayList;
import java.util.List;

public class ThreadSafeList {
    private final List<String> l = new ArrayList<>();

    public synchronized void  add(String s) {
        l.add(s);
    }

    public synchronized List<String> get(){
        List<String> copy = new ArrayList<>(l.size());
        for(String s:l){
            copy.add(s);
        }

        return copy;
    }

    public void main(String[] args){
        ThreadSafeList a = new ThreadSafeList();
        //thread B

    }
}
