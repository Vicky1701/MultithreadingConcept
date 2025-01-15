package Concurrent;

import java.util.concurrent.CopyOnWriteArrayList;

class CopyOnWriteArrayListExample {

    private final CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

    public void addElement(Integer value) {
        System.out.println(Thread.currentThread().getName() + " - Adding: " + value);
        list.add(value);
    }

    public void iterateList() {
        System.out.println(Thread.currentThread().getName() + " - Iterating list: " + list);
        for (Integer value : list) {
            System.out.println(Thread.currentThread().getName() + " - Value: " + value);
        }
    }

    public static void main(String[] args) {
        CopyOnWriteArrayListExample example = new CopyOnWriteArrayListExample();

        Runnable writer = () -> {
            for (int i = 1; i <= 5; i++) {
                example.addElement(i);
            }
        };

        Runnable reader = () -> {
            example.iterateList();
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
 * Q1: What is a CopyOnWriteArrayList in Java?
 * A1: CopyOnWriteArrayList is a thread-safe variant of `ArrayList` where all mutative operations like `add()`, `set()`, or `remove()` result in a new copy of the underlying array being created.
 * 
 * Q2: How does CopyOnWriteArrayList achieve thread safety?
 * A2: It achieves thread safety by creating a new array for every write operation, ensuring that read operations can safely access the original array without synchronization.
 * 
 * Q3: When should CopyOnWriteArrayList be used?
 * A3: It is ideal for scenarios where:
 *    - Read operations significantly outnumber write operations.
 *    - Iterators are used frequently, and thread safety is required without external synchronization.
 * 
 * Q4: What are the key features of CopyOnWriteArrayList?
 * A4: Key features include:
 *    - Thread-safe without explicit synchronization.
 *    - Immutable snapshot iterators.
 *    - Higher memory usage due to copying the array on write operations.
 *    - Suitable for read-intensive operations.
 * 
 * Q5: How does CopyOnWriteArrayList handle concurrent read and write operations?
 * A5: While one thread is modifying the list (creating a new copy), other threads can still read from the original array without being blocked.
 * 
 * Q6: Can CopyOnWriteArrayList store null elements?
 * A6: Yes, `CopyOnWriteArrayList` allows null elements.
 * 
 * Q7: What are some common methods provided by CopyOnWriteArrayList?
 * A7: Common methods include:
 *    - `add(E e)`: Adds an element to the list (creates a new array).
 *    - `set(int index, E element)`: Replaces the element at the specified position (creates a new array).
 *    - `remove(Object o)`: Removes the first occurrence of the specified element (creates a new array).
 *    - `iterator()`: Returns an immutable snapshot iterator.
 * 
 * Q8: How does the iterator of CopyOnWriteArrayList work?
 * A8: The iterator provides a snapshot of the list at the time of its creation. It does not reflect any modifications made to the list after the iterator was created.
 * 
 * Q9: Is CopyOnWriteArrayList efficient for write-heavy operations?
 * A9: No, it is not efficient for write-heavy operations due to the overhead of creating a new copy of the array for every write operation.
 * 
 * Q10: What happens when you modify a CopyOnWriteArrayList while iterating?
 * A10: The iterator does not throw a `ConcurrentModificationException`. Instead, the modifications do not affect the iterator as it operates on a snapshot of the list.
 * 
 * Q11: How does CopyOnWriteArrayList differ from synchronized collections like `Collections.synchronizedList`?
 * A11: 
 *    - CopyOnWriteArrayList uses a copy-on-write strategy, while synchronized collections use explicit synchronization.
 *    - Iterators of CopyOnWriteArrayList are fail-safe, while those of synchronized collections are fail-fast.
 * 
 * Q12: What are the drawbacks of CopyOnWriteArrayList?
 * A12: Drawbacks include:
 *    - High memory usage during write operations.
 *    - Not suitable for write-intensive use cases.
 *    - Slower write performance compared to other thread-safe collections.
 * 
 * Q13: What is the time complexity of operations in CopyOnWriteArrayList?
 * A13: 
 *    - Read operations: O(1) (constant time).
 *    - Write operations: O(n) (due to copying the array).
 * 
 * Q14: Can CopyOnWriteArrayList be used in multi-threaded scenarios where frequent writes are needed?
 * A14: No, it is not recommended for frequent writes as each write operation involves creating a new copy of the array.
 * 
 * Q15: What are the use cases for CopyOnWriteArrayList?
 * A15: Common use cases include:
 *    - Caching frequently read but infrequently updated data.
 *    - Iterating over a collection in multi-threaded environments.
 *    - Storing configuration settings that are read often but updated rarely.
 */
