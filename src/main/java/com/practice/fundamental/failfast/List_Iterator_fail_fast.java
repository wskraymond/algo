package com.practice.fundamental.failfast;

import java.util.*;
import java.util.concurrent.*;

public class List_Iterator_fail_fast {
    public static List<Integer> removeEventNumber(List<Integer> immutable){
        List<Integer> numbers = new ArrayList<>(immutable);
        Iterator<Integer> it = numbers.iterator();
        while(it.hasNext()){
            Integer num = it.next();
            if(num%2==0){
                it.remove();
            }
        }

        return numbers;
    }

    public static List<Integer> removeEventNumber2(List<Integer> immutable){
        List<Integer> numbers = new ArrayList<>(immutable);
        numbers.removeIf(num->num%2==0);

        return numbers;
    }

    public static List<Integer> removalFailsDueToSkip(List<Integer> immutable){
        List<Integer> numbers = new ArrayList<>(immutable);
        for(int i=0;i<numbers.size();i++){ //no iterator in use
            if(numbers.get(i)%2==0){
                numbers.remove(i);
            }
        }

        return numbers;
    }

    public static List<Integer> removalFailsDueToConcurrentModificationExp(List<Integer> immutable) throws ConcurrentModificationException {
        List<Integer> numbers = new ArrayList<>(immutable);
        for(Integer num:numbers){ //internal iterator in use
            if(num%2==0){
                numbers.remove(num); //internal iterator in use
            }
        } //next iteration will throw CME

        return numbers;
    }

    public static List<Integer> removalFailsDueToConcurrentModificationExpWithMultiThread(List<Integer> immutable) throws ConcurrentModificationException, InterruptedException {
        List<Integer> numbers = new ArrayList<>(immutable);
        Runnable task = ()->{
            Iterator<Integer> it = numbers.iterator();
            while(it.hasNext()){
                Integer num = it.next();
                if(num%2==0){
                    it.remove();
                }
            }
        };

        ExecutorService ex = new ThreadPoolExecutor(2,2, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2));
        ex.execute(task);
        ex.execute(task);
        ex.awaitTermination(2, TimeUnit.SECONDS);

        return numbers;
    }

    public static void main(String[] args){
//        List<Integer> input = Arrays.asList(1,3,5,4,2);
        List<Integer> input = Arrays.asList(1, 4, 2, 3);
        List<Integer> output = removeEventNumber(input);
        System.out.println(output);
        List<Integer> output2 = removalFailsDueToSkip(input);
        System.out.println(output2);

        try {
            List<Integer> output3 = removalFailsDueToConcurrentModificationExp(input);
            System.out.println(output3);
        } catch (ConcurrentModificationException e) {
            System.out.println(e);
        }

        try {
            List<Integer> output4 = removalFailsDueToConcurrentModificationExpWithMultiThread(input);
            System.out.println(output4);
        } catch (ConcurrentModificationException | InterruptedException e) {
            System.out.println(e);
        }
    }
}
