package time_based_key_value_store;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.TreeSet;

record ValueWithTime(String value, int timestamp){}

public class TimeMap {
    Map<String, NavigableSet<ValueWithTime>> timeMap = new HashMap<>();

    public void set(String key, String value, int timestamp) {
        ValueWithTime valueWithTime = new ValueWithTime(value, timestamp);
        timeMap.compute(key, (k, v) -> {
            if (v == null) {
                NavigableSet<ValueWithTime> valueWithTimeSortedSet = new TreeSet<>(Comparator.comparingInt(ValueWithTime::timestamp));
                valueWithTimeSortedSet.add(valueWithTime);
                return valueWithTimeSortedSet;
            } else {
                v.add(valueWithTime);
                return v;
            }
        });
    }

    public String get(String key, int timestamp) {
        return Optional.ofNullable(timeMap.get(key))
                .map(values -> values.floor(new ValueWithTime(null, timestamp)))
                .map(ValueWithTime::value)
                .orElse("");
    }

    public static void main(String[] args) {
        TimeMap testMap = new TimeMap();
//        ["TimeMap","set","set","get","get","get","get","get"]
//        [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
        testMap.set("love","high",10);
        testMap.set("love","low",20);
        System.out.println(testMap.get("love", 5));
        System.out.println(testMap.get("love", 10));
    }
}
