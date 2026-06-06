package com.practice.fundamental.j8weapons;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CountingWords_3_ways {
    public int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }

    private int countWords(Stream<Character> stream){
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }

    public int countWordsSequential(String s) {
        Stream<Character> stream = IntStream.range(0, s.length()).mapToObj(s::charAt);
        return countWords(stream);
    }

    /**
     * Incorrect way to count words in parallel because it may split the stream in the middle of a word, leading to incorrect counts.
     * @param s
     * @return
     */
    public int countWordsParallel_random_split(String s) {
        Stream<Character> stream = IntStream.range(0, s.length()).mapToObj(s::charAt);
        return countWords(stream.parallel());
    }

    public int countWordsParallel_with_splitIterator(String s) {
        Spliterator<Character> spliterator = new WordCounterSpliterator(s);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        return countWords(stream);
    }

    public static void main(String[] args) {
        String s = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        CountingWords_3_ways counter = new CountingWords_3_ways();
        System.out.println("Iterative count: " + counter.countWordsIteratively(s));
        System.out.println("Sequential stream count: " + counter.countWordsSequential(s));
        System.out.println("Parallel stream (random split) count: " + counter.countWordsParallel_random_split(s));
        System.out.println("Parallel stream (with custom spliterator) count: " + counter.countWordsParallel_with_splitIterator(s));
    }
}
