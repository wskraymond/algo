package com.practice.conc.safeinit;

import com.mine.anno.ThreadSafe;

public class SafeInit_with_HRB {
    /*
        using a shared variable guarded by a lock or
        a shared volatile variable ensures that reads and writes of that variable are ordered by happens‐before.

        -----------------HRB is a stronger promise of visibility and ordering than made by safe publication.----------------------
        ------------------HRB is a sort of "concurrency assembly language" --------------------------------------------------------
        ------------------But Safe publication operates at a level closer to that of your program's design ---------------------------
        When X is safely published from A to B, the safe publication guarantees visibility of the state of X, but not of the state of
        other variables A may have touched. But if A putting X on a queue happens‐before B fetches X from that queue, not only
        does B see X in the state that A left it (assuming that X has not been subsequently modified by A or anyone else), but B
        sees everything A did before the handoff (again, subject to the same caveat).[
     */
}

@ThreadSafe
class SafeLazyInitialization {
    private static Resource resource;
    public synchronized static Resource getInstance() {
        if (resource == null)
            resource = new Resource();
        return resource;
    }
}

/**
 * Static initializers are run by the JVM at class initialization time,
 * after class loading but before the class is used by any thread.
 * Because the JVM acquires a lock during initialization
 * and this lock is acquired by each thread at least once to ensure that
 * the class has been loaded, memory writes made during static initialization are automatically visible to all threads.
 *
 * Thus statically initialized objects require no explicit synchronization either
 *      - during construction
 *      - or when being referenced.
 *
 * However, this applies only to the "as‐constructed state"
 *      - if the object is mutable, synchronization is still required by
 *          - both readers and writers to make subsequent modifications visible and to avoid data corruption.
 */
@ThreadSafe
class EagerInitialization {
    private static Resource resource = new Resource(); //"as‐constructed state"
    public static Resource getResource() { return resource; }
}

@ThreadSafe
class LazyInitResourceFactory {
    /**
     * with the JVM's lazy class loading to create a lazy initialization technique
     *      - that does not require synchronization on the common code path.
     *
     * The lazy initialization holder class idiom uses a class
     *      - whose only purpose is to initialize the Resource.
     *
     * The JVM defers initializing the ResourceHolder class until it is actually used,
     *      - and because the Resource is initialized with a static initializer,
     *          - no additional synchronization cost is needed.
     */
    private static class ResourceHolder {
        public static Resource resource = new Resource(); //lazy initialization
    }

    public static Resource getResource() {
        return ResourceHolder.resource ;
    }
}
