package Concurrent;

import java.util.concurrent.ConcurrentHashMap;

class ConcurrentHashMapExample {

    private final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    public void putData(String key, Integer value) {
        System.out.println(Thread.currentThread().getName() + " - Putting: (" + key + ", " + value + ")");
        map.put(key, value);
    }

    public void readData(String key) {
        Integer value = map.get(key);
        System.out.println(Thread.currentThread().getName() + " - Reading key: " + key + ", value: " + value);
    }

    public static void main(String[] args) {
        ConcurrentHashMapExample example = new ConcurrentHashMapExample();

        Runnable writer = () -> {
            for (int i = 1; i <= 5; i++) {
                example.putData("Key" + i, i);
            }
        };

        Runnable reader = () -> {
            for (int i = 1; i <= 5; i++) {
                example.readData("Key" + i);
            }
        };

        Thread writerThread = new Thread(writer, "Writer Thread");
        Thread readerThread = new Thread(reader, "Reader Thread");

        writerThread.start();
        readerThread.start();
    }
}


/**
 * Interview Questions and Answers:
 * 
 * Q1: What is ConcurrentHashMap in Java?
 * A1: ConcurrentHashMap is a thread-safe implementation of the Map interface. It allows concurrent read and write operations without requiring explicit synchronization by the developer.
 * 
 * Q2: How does ConcurrentHashMap achieve thread safety?
 * A2: ConcurrentHashMap uses fine-grained locks and CAS (Compare-And-Swap) operations to ensure thread safety. It divides the map into segments or buckets, reducing contention by locking only the affected part during updates.
 * 
 * Q3: Can ConcurrentHashMap store null keys or null values?
 * A3: No, ConcurrentHashMap does not allow null keys or null values. Attempting to insert a null key or value will result in a NullPointerException.
 * 
 * Q4: What is the default concurrency level of ConcurrentHashMap?
 * A4: In Java 7, the default concurrency level was 16, meaning 16 segments for locking. From Java 8 onward, ConcurrentHashMap uses a different internal structure (using buckets), and the concurrency level is dynamically managed.
 * 
 * Q5: What are the advantages of ConcurrentHashMap over HashMap?
 * A5: ConcurrentHashMap provides built-in thread safety for concurrent access. In contrast, HashMap is not thread-safe and requires external synchronization, which can degrade performance in multithreaded environments.
 * 
 * Q6: What happens if multiple threads try to update the same key in ConcurrentHashMap?
 * A6: The operation is thread-safe. ConcurrentHashMap ensures that only one thread updates the value for the same key at a time.
 * 
 * Q7: What is the difference between ConcurrentHashMap and Hashtable?
 * A7: 
 *    - **Performance**: ConcurrentHashMap has better performance due to finer-grained locking compared to Hashtable, which uses a single lock for the entire map.
 *    - **Null Values**: ConcurrentHashMap does not allow null keys or values, while Hashtable allows null values but not null keys.
 * 
 * Q8: What are the key methods provided by ConcurrentHashMap?
 * A8: Some key methods are:
 *    - `putIfAbsent(K key, V value)`: Adds a key-value pair if the key is not already present.
 *    - `compute(K key, BiFunction)`: Atomically computes a value for the given key.
 *    - `forEach()`: Performs a given action for each entry in the map.
 *    - `replace(K key, V oldValue, V newValue)`: Replaces a value only if it matches the existing value.
 * 
 * Q9: Can ConcurrentHashMap replace a `synchronized` block for map operations?
 * A9: Yes, ConcurrentHashMap eliminates the need for external synchronization when multiple threads access or modify the map concurrently.
 * 
 * Q10: How does ConcurrentHashMap differ from Collections.synchronizedMap()?
 * A10: 
 *    - `Collections.synchronizedMap()` wraps a regular map with synchronization, providing thread safety at the cost of blocking all operations.
 *    - ConcurrentHashMap provides better scalability with its non-blocking reads and finer-grained locks for updates.
 * 
 * Q11: What is the internal structure of ConcurrentHashMap in Java 8?
 * A11: In Java 8, ConcurrentHashMap is implemented using a combination of arrays and linked lists. For high concurrency, buckets can turn into trees (red-black trees) to improve performance for large data.
 * 
 * Q12: Is ConcurrentHashMap suitable for highly concurrent environments?
 * A12: Yes, ConcurrentHashMap is well-suited for scenarios with a high level of concurrent read and write operations due to its efficient locking mechanisms.
 * 
 * Q13: What are some common use cases for ConcurrentHashMap?
 * A13: 
 *    - Caching frequently accessed data in multi-threaded applications.
 *    - Storing configurations or settings that need to be accessed concurrently.
 *    - Maintaining real-time counts, such as tracking metrics or logs.
 */
