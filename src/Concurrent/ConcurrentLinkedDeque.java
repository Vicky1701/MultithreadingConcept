package Concurrent;

import java.util.concurrent.ConcurrentLinkedDeque;

class ConcurrentLinkedDequeExample {

    private final ConcurrentLinkedDeque<Integer> deque = new ConcurrentLinkedDeque<>();

    public void addFirst(Integer value) {
        System.out.println(Thread.currentThread().getName() + " - Adding first: " + value);
        deque.addFirst(value);
    }

    public void addLast(Integer value) {
        System.out.println(Thread.currentThread().getName() + " - Adding last: " + value);
        deque.addLast(value);
    }

    public void pollFirst() {
        Integer value = deque.pollFirst();
        System.out.println(Thread.currentThread().getName() + " - Polled first: " + value);
    }

    public void pollLast() {
        Integer value = deque.pollLast();
        System.out.println(Thread.currentThread().getName() + " - Polled last: " + value);
    }

    public static void main(String[] args) {
        ConcurrentLinkedDequeExample example = new ConcurrentLinkedDequeExample();

        Runnable adder = () -> {
            for (int i = 1; i <= 5; i++) {
                example.addFirst(i);
                example.addLast(i * 10);
            }
        };

        Runnable poller = () -> {
            for (int i = 1; i <= 5; i++) {
                example.pollFirst();
                example.pollLast();
            }
        };

        Thread adderThread = new Thread(adder, "Adder Thread");
        Thread pollerThread = new Thread(poller, "Poller Thread");

        adderThread.start();
        pollerThread.start();
    }
}



/**
 * Interview Questions and Answers:
 * 
 * Q1: What is a ConcurrentLinkedDeque in Java?
 * A1: ConcurrentLinkedDeque is a thread-safe, non-blocking, doubly-linked deque (double-ended queue) that allows concurrent access and modification from multiple threads without requiring explicit synchronization.
 * 
 * Q2: How does ConcurrentLinkedDeque achieve thread safety?
 * A2: It uses a non-blocking algorithm and atomic operations (e.g., Compare-And-Swap) to ensure thread-safe operations on its nodes without locking.
 * 
 * Q3: What are the key features of ConcurrentLinkedDeque?
 * A3: Key features include:
 *    - Thread-safe operations for concurrent access.
 *    - Non-blocking implementation for high performance.
 *    - Doubly-linked structure to efficiently add/remove elements from both ends.
 * 
 * Q4: When would you use ConcurrentLinkedDeque over other deques?
 * A4: Use ConcurrentLinkedDeque when:
 *    - You need a deque with thread-safe, non-blocking behavior.
 *    - The application requires frequent additions/removals from both ends in a concurrent environment.
 * 
 * Q5: Can ConcurrentLinkedDeque store null elements?
 * A5: No, ConcurrentLinkedDeque does not allow null elements. Attempting to insert a null element will result in a NullPointerException.
 * 
 * Q6: How is ConcurrentLinkedDeque different from LinkedBlockingDeque?
 * A6: 
 *    - **Blocking vs Non-blocking**: LinkedBlockingDeque blocks threads when the deque is full or empty, while ConcurrentLinkedDeque is non-blocking.
 *    - **Capacity**: ConcurrentLinkedDeque is unbounded, while LinkedBlockingDeque can be bounded or unbounded.
 *    - **Performance**: ConcurrentLinkedDeque offers better performance in highly concurrent scenarios.
 * 
 * Q7: What are some key methods provided by ConcurrentLinkedDeque?
 * A7: Common methods include:
 *    - `addFirst(E e)`: Inserts the specified element at the front.
 *    - `addLast(E e)`: Inserts the specified element at the back.
 *    - `pollFirst()`: Retrieves and removes the first element, or returns `null` if empty.
 *    - `pollLast()`: Retrieves and removes the last element, or returns `null` if empty.
 *    - `peekFirst()`: Retrieves the first element without removing it.
 *    - `peekLast()`: Retrieves the last element without removing it.
 * 
 * Q8: Is ConcurrentLinkedDeque suitable for a producer-consumer scenario?
 * A8: Not directly, as it is non-blocking. For producer-consumer scenarios where blocking behavior is needed, LinkedBlockingDeque is more appropriate.
 * 
 * Q9: How does ConcurrentLinkedDeque handle concurrency?
 * A9: It uses atomic variables for its head and tail pointers and relies on CAS (Compare-And-Swap) operations to ensure safe and efficient updates to the deque structure.
 * 
 * Q10: What is the time complexity of basic operations in ConcurrentLinkedDeque?
 * A10: Most basic operations (e.g., adding or removing elements) have O(1) time complexity due to its doubly-linked structure.
 * 
 * Q11: How does ConcurrentLinkedDeque perform under high contention?
 * A11: It performs well in high-contention scenarios due to its non-blocking and lock-free design, reducing thread contention compared to blocking implementations.
 * 
 * Q12: What are the common use cases for ConcurrentLinkedDeque?
 * A12: 
 *    - Task scheduling where tasks can be added or removed from both ends.
 *    - Real-time systems requiring a highly concurrent double-ended queue.
 *    - Maintaining a history of recent events or requests in concurrent environments.
 */