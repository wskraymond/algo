package com.concurrency.dsa;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventPoller;
import com.lmax.disruptor.RingBuffer;
import org.jctools.queues.SpscArrayQueue;

import java.util.concurrent.ArrayBlockingQueue;

final class Queues {
    private Queues() {}

    interface Q {
        boolean offer(Integer e);
        Integer poll();
    }

    static final class SpscQ implements Q {
        private final SPSCRingBuffer<Integer> q;
        SpscQ(int capacity) { q = new SPSCRingBuffer<>(capacity); }
        @Override public boolean offer(Integer e) { return q.offer(e); }
        @Override public Integer poll() { return q.poll(); }
    }

    static final class AbqQ implements Q {
        private final ArrayBlockingQueue<Integer> q;
        AbqQ(int capacity) { q = new ArrayBlockingQueue<>(capacity); }
        @Override public boolean offer(Integer e) { return q.offer(e); }
        @Override public Integer poll() { return q.poll(); }
    }

    static final class JcQ implements Q {
        private final SpscArrayQueue<Integer> q;
        JcQ(int capacity) { q = new SpscArrayQueue<>(capacity); }
        @Override public boolean offer(Integer e) { return q.offer(e); }
        @Override public Integer poll() { return q.poll(); }
    }

    static final class DisruptorQ implements Q {
        static final class Event { Integer value; }

        private final RingBuffer<Event> rb;
        private final EventPoller<Event> poller;
        private Integer out; // 只有 consumer 線程存取
        private final EventPoller.Handler<Event> handler = (ev, seq, endOfBatch) -> {
            out = ev.value;
            return false; // 一次只處理一筆
        };

        DisruptorQ(int capacity) {
            rb = RingBuffer.createSingleProducer(Event::new, capacity, new BusySpinWaitStrategy());
            poller = rb.newPoller();
            rb.addGatingSequences(poller.getSequence());
        }

        @Override public boolean offer(Integer e) {
            if (!rb.hasAvailableCapacity(1)) return false;
            long s = rb.next();
            try {
                rb.get(s).value = e;
            } finally {
                rb.publish(s);
            }
            return true;
        }

        @Override public Integer poll() {
            out = null;
            try {
                poller.poll(handler);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            return out;
        }
    }

    static final class AgronaQ implements Q {
        private final org.agrona.concurrent.OneToOneConcurrentArrayQueue<Integer> q;
        AgronaQ(int capacity) { q = new org.agrona.concurrent.OneToOneConcurrentArrayQueue<>(capacity); }
        @Override public boolean offer(Integer e) { return q.offer(e); }
        @Override public Integer poll() { return q.poll(); }
    }


    static Q create(String impl, int capacity) {
        return switch (impl) {
            case "spsc" -> new SpscQ(capacity);
            case "jctools" -> new JcQ(capacity);
            case "disruptor" -> new DisruptorQ(capacity);
            case "abq" -> new AbqQ(capacity);
            case "agrona" -> new AgronaQ(capacity);
            default -> throw new IllegalArgumentException(impl);
        };
    }
}
