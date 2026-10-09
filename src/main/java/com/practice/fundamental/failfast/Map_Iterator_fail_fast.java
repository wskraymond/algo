package com.practice.fundamental.failfast;

import java.util.*;

public class Map_Iterator_fail_fast {
    public static Map<String,Integer> removeEvenEntry(Map<String,Integer> input){
        Map<String, Integer> res = new HashMap<>(input);
        Iterator<Map.Entry<String,Integer>> it = res.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Integer> e = it.next();
            if(e.getValue().intValue()%2==0){
                it.remove();
            }
        }

        return res;
    }

    public static Map<String,Integer> removalFailsDueToSkip(Map<String,Integer> input){
        Map<String, Integer> res = new HashMap<>(input);
        for(Map.Entry<String,Integer> entry:res.entrySet()){
            if(entry.getValue()%2==0){
                res.remove(entry.getKey());
            }
        }

        return res;
    }

    public static Map<String,Integer> removalUsingMultiThread(Map<String, Integer> input) throws InterruptedException {
        Map<String, Integer> map = new HashMap<>(input);

        // Thread 1: iterates the map
        Thread t1 = new Thread(() -> {
            try {
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    System.out.println(entry.getKey() + "=" + entry.getValue());
                    Thread.sleep(10); // slow down to increase race chance
                }
            } catch (ConcurrentModificationException e) {
                System.out.println("CME thrown in t1!");
                throw e;
            } catch (InterruptedException ignored) {}
        });

        // Thread 2: removes from the same map
        Thread t2 = new Thread(() -> {
            map.remove("b"); // directly modifies modCount
            map.remove("d");
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        return map;
    }

    public static void main(String[] args){
        Map<String, Integer> input = Map.ofEntries( //immutable map without limit on no. of entries
                Map.entry("a", 1),
                Map.entry("d", 4),
                Map.entry("b", 2),
                Map.entry("c", 3)
        );

        Map<String, Integer> output = removeEvenEntry(input);
        System.out.println(output);

        // This demonstrates the fail-fast behavior - it throws ConcurrentModificationException
        try {
            Map<String, Integer> output2 = removalFailsDueToSkip(input);
            System.out.println(output2);
        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException caught - cannot remove from map while iterating!");
            System.out.println("Exception message: " + e);
        }

        try {
            Map<String, Integer> output3 = removalUsingMultiThread(input);
            System.out.println(output3);
        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException caught - cannot remove from map while iterating!");
            System.out.println("Exception message: " + e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
