package com.practice.conc.weaksoftref;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

public class WeakRef_canonicalization {
    Map<ImmutableObject, WeakReference<ImmutableObject>> map = new WeakHashMap<>();

    ImmutableObject canonicalize(ImmutableObject io) {
        synchronized (map) {
            WeakReference<ImmutableObject> ref = map.get(io);
            ImmutableObject canonical = (ref == null) ? null : ref.get();
            if (canonical == null) {
                map.put(io, new WeakReference<>(io));
                canonical = io;
            }
            return canonical;
        }
    }

}

class ImmutableObject {
    private final String value;

    public ImmutableObject(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmutableObject that = (ImmutableObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
