package com.practice.conc.safeinit;

import com.mine.anno.NotThreadSafe;

public class UnSafeInit_without_HBR {
    /**
     * Suppose thread A is the first to invoke getInstance. It sees that resource is null, instantiates a new Resource, and
     * sets resource to reference it. When thread B later calls getInstance, it might see that resource already has a non‐null
     * value and just use the already constructed Resource. This might look harmless at first, but there is no happens‐before
     * ordering between the writing of resource in A and the reading of resource in B.
     *
     * Since neither thread used synchronization, B could possibly see A's actions in
     * a different order than A performed them. So even though A initialized the Resource before setting resource to
     * reference it, B could see the write to resource as occurring before the writes to the fields of the Resource. B could thus
     * see a partially constructed Resource that may well be in an invalid stateand whose state may unexpectedly change
     * later.
     */
}

@NotThreadSafe
class UnsafeLazyInitialization {
    private static Resource resource;
    public static Resource getInstance() {
        if (resource == null)
            resource = new Resource(); // unsafe publication
        return resource;
    }
}

class Resource {
    String name;
    Resource() {
        /*
        The Resource constructor changes the fields of the freshly allocated Resource from their default values (written by the
        Object constructor) to their initial values.
         */
        name = "Resource";
    }
}
