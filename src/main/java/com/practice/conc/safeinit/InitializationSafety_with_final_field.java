package com.practice.conc.safeinit;


import com.practice.anno.NotThreadSafe;
import com.practice.anno.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

public class InitializationSafety_with_final_field {
    /**
     * Initialization safety guarantees that for properly constructed objects, all threads will see the correct values of final fields
     * that were set by the constructor, regardless of how the object is published. Further, any variables that can be reached
     * through a final field of a properly constructed object (such as the elements of a final array or the contents of a HashMap
     * referenced by a final field) are also guaranteed to be visible to other threads.
     */

    /**
     * -------------------------Rule for final field --------------------
     * => Writes that initialize variables reachable through final fields are not reordered with operations following the post‐construction freeze.
     *
     * All writes to final fields made by the constructor, as well as to any variables reachable through
     * those fields, become "frozen" when the constructor completes, and any thread that obtains a reference to that object is
     * guaranteed to see a value that is at least as up to date as the frozen value.
     *
     */

}

@ThreadSafe
class SafeStates {
    private final Map<String, String> states;
    public SafeStates() {
        states = new HashMap<String, String>();
        states.put("alaska", "AK");
        states.put("alabama", "AL");
        states.put("wyoming", "WY");
    }
    public String getAbbreviation(String s) {
        return states.get(s);   //thread-safe, only constructor can modify the content in a object referenced through final field
    }
}

/**
 * Initialization safety makes visibility guarantees only for the values that are reachable through final fields as of the time
 * the constructor finishes. For values reachable through non‐final fields, or values that may change after construction, you
 * must use synchronization to ensure visibility.
 */
@NotThreadSafe
class UnSafeStates {
    private final Map<String, String> states;
    public UnSafeStates() {
        states = new HashMap<String, String>();
        states.put("alaska", "AK");
        states.put("alabama", "AL");
        states.put("wyoming", "WY");
    }
    public String getAbbreviation(String s) {
        return states.get(s);   //modifier violates the initialization Safety Guarantees
    }

    /**
     *  if any method other than the constructor modified its contents, initialization safety would not be strong enough to safely
     *  access SafeStates without synchronization
     * @param s
     * @param v
     */
    public void modify(String s, String v){
        this.states.put(s, v);  //other thread can modify it
    }
}