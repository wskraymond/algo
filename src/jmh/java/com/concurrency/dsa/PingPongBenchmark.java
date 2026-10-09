package com.concurrency.dsa;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Control;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.SampleTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Group)
public class PingPongBenchmark {

    @Param({"spsc", "abq"})
    public String impl;

    private Queues.Q ping, pong;
    private static final Integer MSG = 42;

    @Setup(Level.Iteration)
    public void setup() {
        ping = impl.equals("spsc") ? new Queues.SpscQ(1024) : new Queues.JcQ(1024);
        pong = impl.equals("spsc") ? new Queues.SpscQ(1024) : new Queues.JcQ(1024);
    }

    @Benchmark
    @Group("rtt")
    @GroupThreads(1)
    public void pinger(Control control) {
        while (!control.stopMeasurement && !ping.offer(MSG)) Thread.onSpinWait();
        while (!control.stopMeasurement && pong.poll() == null) Thread.onSpinWait();
    }

    @Benchmark
    @Group("rtt")
    @GroupThreads(1)
    public void ponger(Control control) {
        Integer v = null;
        while (!control.stopMeasurement && (v = ping.poll()) == null) Thread.onSpinWait();
        if (v != null) while (!control.stopMeasurement && !pong.offer(v)) Thread.onSpinWait();
    }
}
