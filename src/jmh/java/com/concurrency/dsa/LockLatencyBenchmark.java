package com.concurrency.dsa;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Latency (SampleTime) of lock + small critical section + unlock.
 * JMH reports p0 / p50 / p90 / p95 / p99 / p99.9 / p99.99 / p100 per benchmark.
 *
 * Machine: Apple M4 Pro = 8 performance + 4 efficiency cores = 12 logical CPUs.
 *   threads4  : well inside the P-cores
 *   threads8  : = P-cores
 *   threads12 : = all cores
 *   threads16 / threads24 : oversubscribed, a FIFO spin lock may convoy when the
 *                           next waiter in line is descheduled -> watch p99.9 / max.
 *
 * ArraySpinLock ring capacity MUST be >= number of competing threads, otherwise two
 * threads share a slot. 32 covers up to 24 threads (capacity must be 2^n).
 *
 * macOS cannot pin threads, results are noisy. For the real low-latency story run this on
 * Linux with isolcpus + taskset / Java-Thread-Affinity, and numactl on multi-socket boxes.
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.SampleTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 3)
@Fork(2)
public class LockLatencyBenchmark {

    static final int RING_CAPACITY = 32;

    interface SimpleLock {
        void lock();
        void unlock();
    }

    static final class ArrayLock implements SimpleLock {
        private final ArraySpinLock l = new ArraySpinLock(RING_CAPACITY);
        public void lock() { l.lock(); }
        public void unlock() { l.unlock(); }
    }

    static final class ReentrantLockImpl implements SimpleLock {
        private final ReentrantLock l;
        ReentrantLockImpl(boolean fair) { this.l = new ReentrantLock(fair); }
        public void lock() { l.lock(); }
        public void unlock() { l.unlock(); }
    }

    @Param({"array", "reentrantFair", "reentrantUnfair"})
    String impl;

    @Param({"0", "50"})
    int csWork; // Blackhole.consumeCPU tokens spent inside the critical section

    SimpleLock lock;
    long counter; // plain: guarded by the lock

    @Setup(Level.Trial)
    public void setup() {
        switch (impl) {
            case "array":           lock = new ArrayLock(); break;
            case "reentrantFair":   lock = new ReentrantLockImpl(true); break;
            case "reentrantUnfair": lock = new ReentrantLockImpl(false); break;
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

    @Benchmark @Threads(4)  public long threads4()  { return op(); }
    @Benchmark @Threads(8)  public long threads8()  { return op(); }
    @Benchmark @Threads(12) public long threads12() { return op(); }
    @Benchmark @Threads(16) public long threads16() { return op(); }
    @Benchmark @Threads(24) public long threads24() { return op(); }

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder().include(LockLatencyBenchmark.class.getSimpleName()).build();
        new Runner(opt).run();
    }
}
