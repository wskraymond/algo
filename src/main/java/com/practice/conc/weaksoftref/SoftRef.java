package com.practice.conc.weaksoftref;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class SoftRef {
    /**
     * Simple soft-reference based cache example.
     *
     * Use case implemented:
     * 1. Caching: store large objects with soft references so the JVM can reclaim them under memory pressure.
     * 2. Memory-sensitive applications: objects are recreated via a loader when GC cleared the soft reference.
     * 3. Resource management: inexpensive recreation strategy via a loader function.
     */
    public static class SoftCache<K, V> {
        private final Map<K, SoftReference<V>> map = new ConcurrentHashMap<>();
        private final Function<? super K, ? extends V> loader;

        public SoftCache(Function<? super K, ? extends V> loader) {
            this.loader = loader;
        }

        public SoftCache() {
            this.loader = null;
        }

        public void put(K key, V value) {
            map.put(key, new SoftReference<>(value));
        }

        public V get(K key) {
            SoftReference<V> ref = map.get(key);
            V value = (ref == null) ? null : ref.get();
            if (value == null && loader != null) {
                value = loader.apply(key);
                if (value != null) {
                    put(key, value);
                }
            }
            return value;
        }
    }
}



