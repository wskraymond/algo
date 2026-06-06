package com.practice.conc.safeinit;


import com.practice.anno.NotThreadSafe;
import com.practice.anno.ThreadSafe;

public class DoubleCheckedLocking_anti_pattern {
    /**
     *
     */
}

@NotThreadSafe
class DoubleCheckedLocking {
    private static Resource resource;

    /**
     *  it is possible to see a current value of the reference but stale values for the object's state, meaning that the object could be
     *  seen to be in an invalid or incorrect state
     * @return
     */
    public static Resource getInstance() {
        if (resource == null) { // it is possible for a thread to see a partially constructed Resource.
            synchronized (DoubleCheckedLocking.class) {
                if (resource == null)
                    resource = new Resource();
            }
        }
        return resource;
    }
}

/**
 * DCL to work if resource is made volatile,
 * and the performance impact of this is small since volatile reads are usually only slightly more expensive than nonvolatile reads.
 *
 * --------------but why is still Anti pattern (but thread-safe) ---------------------------------------------
 * origin:  lazy initialization without paying the synchronization penalty on the common code path.
 * However, this is an idiom whose utility has largely passed ‐ the forces that motivated it (slow uncontended
 * synchronization, slow JVM startup) are no longer in play, making it less effective as an optimization.
 */
@ThreadSafe
class DoubleCheckedLocking_with_volatile {
    private static volatile Resource resource;

    public static Resource getInstance() {
        if (resource == null) { // it is possible for a thread to see a partially constructed Resource.
            synchronized (DoubleCheckedLocking.class) {
                if (resource == null)
                    resource = new Resource();
            }
        }
        return resource;
    }
}




