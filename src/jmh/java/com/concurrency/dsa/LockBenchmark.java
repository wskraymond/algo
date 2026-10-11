package com.concurrency.dsa;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ArraySpinLock vs ReentrantLock(true): lock + small critical section + unlock.
 * Keep threads <= ring capacity (16) and <= physical cores: a FIFO spin lock collapses
 * when the next waiter in line is descheduled.
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(2)
public class LockBenchmark {

    interface SimpleLock {
        void lock();
        void unlock();
    }

    static final class ArrayLock implements SimpleLock {
        private final ArraySpinLock l = new ArraySpinLock(16);
        public void lock() { l.lock(); }
        public void unlock() { l.unlock(); }
    }

    static final class FairReentrant implements SimpleLock {
        private final ReentrantLock l = new ReentrantLock(true);
        public void lock() { l.lock(); }
        public void unlock() { l.unlock(); }
    }

    @Param({"array", "reentrantFair"})
    String impl;

    @Param({"0", "50"})
    int csWork; // Blackhole.consumeCPU tokens spent inside the critical section

    SimpleLock lock;
    long counter; // plain: guarded by the lock

    @Setup(Level.Trial)
    public void setup() {
        switch (impl) {
            case "array": lock = new ArrayLock(); break;
            case "reentrantFair": lock = new FairReentrant(); break;
            default: throw new IllegalArgumentException(impl);
        }
        counter = 0;
    }

    private long op() {
        lock.lock();
        try {
            if (csWork > 0) {
                Blackhole.consumeCPU(csWork);
            }
            return ++counter;
        } finally {
            lock.unlock();
        }
    }

    private long nestedOp() {
        lock.lock();
        try {
            return op(); // second, reentrant acquisition
        } finally {
            lock.unlock();
        }
    }

    @Benchmark @Threads(1)
    public long threads1() { return op(); }

    @Benchmark @Threads(2)
    public long threads2() { return op(); }

    @Benchmark @Threads(4)
    public long threads4() { return op(); }

    @Benchmark @Threads(8)
    public long threads8() { return op(); }

    @Benchmark @Threads(1)
    public long nested1() { return nestedOp(); }

    @Benchmark @Threads(4)
    public long nested4() { return nestedOp(); }

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder().include(LockBenchmark.class.getSimpleName()).build();
        new Runner(opt).run();
    }
}
