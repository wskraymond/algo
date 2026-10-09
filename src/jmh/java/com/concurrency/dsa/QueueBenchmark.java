package com.concurrency.dsa;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.infra.Control;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 10, time = 1)
@Fork(value = 3, jvmArgsAppend = {
        "--add-exports=java.base/jdk.internal.vm.annotation=ALL-UNNAMED",
        "-XX:-RestrictContended"})
@State(Scope.Group)
public class QueueBenchmark {

    @Param({"spsc", "abq"})
    public String impl;

    @Param({"1024", "65536"})
    public int capacity;

    private Queues.Q queue;

    private static final Integer MSG = 42;

    @Setup(Level.Iteration)
    public void setup() {
        queue = impl.equals("spsc") ? new Queues.SpscQ(capacity) : new Queues.JcQ(capacity);
    }

    @Benchmark
    @Group("pingThroughput")
    @GroupThreads(1)
    public void produce(Control control) {
        final Queues.Q q = queue;
        while (!control.stopMeasurement && !q.offer(MSG)) {
            Thread.onSpinWait();
        }
    }

    @Benchmark
    @Group("pingThroughput")
    @GroupThreads(1)
    public void consume(Control control, Blackhole bh) {
        final Queues.Q q = queue;
        Integer v = null;
        while (!control.stopMeasurement && (v = q.poll()) == null) {
            Thread.onSpinWait();
        }
        if (v != null) {
            bh.consume(v);
        }
    }

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(QueueBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }

}
