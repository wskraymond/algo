package com.practice.conc.weakconsistent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DelegatingVehicleTracker {
    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        locations = new ConcurrentHashMap<String, Point>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    /**
     * Notice that we've changed the behavior of
     * the vehicle tracker class slightly; while the monitor version returned a snapshot of the locations, the delegating version
     * returns an unmodifiable but "live" view of the vehicle locations. This means that if thread A calls getLocations and
     * thread B later modifies the location of some of the points, those changes are reflected in the Map returned to thread A.
     * As we remarked earlier, this can be a benefit (more up‐to‐date data) or a liability (potentially inconsistent view of the
     * fleet), depending on your requirements.
     *
     * @return
     */
    public Map<String, Point> getLocations_live() {
        return unmodifiableMap;
    }

    /**
     * If an unchanging view of the fleet is required, getLocations could instead return a shallow copy of the locations map.
     * Since the contents of the Map are immutable, only the structure of the Map, not the contents, must be copied
     *
     * @return
     */
    public Map<String, Point> getLocations_shallow() {
        return Collections.unmodifiableMap(new HashMap<String, Point>(locations));
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null)
            throw new IllegalArgumentException(
                    "invalid vehicle name: " + id);
    }
}

class Point {
    public final int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
