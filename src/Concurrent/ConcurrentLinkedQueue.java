package Concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;

class ConcurrentLinkedQueueExample {

    private final ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

    public void addElement(Integer value) {
        System.out.println(Thread.currentThread().getName() + " - Adding: " + value);
        queue.add(value);
    }

    public void pollElement() {
        Integer value = queue.poll();
        System.out.println(Thread.currentThread().getName() + " - Polled: " + value);
    }

    public static void main(String[] args) {
        ConcurrentLinkedQueueExample example = new ConcurrentLinkedQueueExample();

        Runnable producer = () -> {
            for (int i = 1; i <= 5; i++) {
                example.addElement(i);
            }
        };

        Runnable consumer = () -> {
            for (int i = 1; i <= 5; i++) {
                example.pollElement();
            }
        };

        Thread producerThread = new Thread(producer, "Producer Thread");
        Thread consumerThread = new Thread(consumer, "Consumer Thread");

        producerThread.start();
        consumerThread.start();
    }
}

/**
 * Interview Questions and Answers:
 * 
 * Q1: What is a ConcurrentLinkedQueue in Java?
 * A1: ConcurrentLinkedQueue is a thread-safe, non-blocking, singly-linked queue that implements the `Queue` interface and is designed for high-concurrency scenarios.
 * 
 * Q2: How does ConcurrentLinkedQueue achieve thread safety?
 * A2: It uses a non-blocking algorithm and atomic operations (e.g., Compare-And-Swap) for enqueue and dequeue operations, ensuring thread safety without locking.
 * 
 * Q3: What are the key features of ConcurrentLinkedQueue?
 * A3: Key features include:
 *    - Thread-safe and non-blocking operations.
 *    - Based on a lock-free singly-linked structure.
 *    - Unbounded queue (no capacity restrictions).
 * 
 * Q4: How does ConcurrentLinkedQueue differ from BlockingQueue?
 * A4: 
 *    - **Blocking vs Non-blocking**: ConcurrentLinkedQueue is non-blocking, while BlockingQueue can block threads when full or empty.
 *    - **Usage**: ConcurrentLinkedQueue is used for high-performance concurrent operations, while BlockingQueue is used in producer-consumer scenarios.
 * 
 * Q5: Can ConcurrentLinkedQueue store null elements?
 * A5: No, ConcurrentLinkedQueue does not allow null elements. Adding a null element throws a NullPointerException.
 * 
 * Q6: What are some key methods provided by ConcurrentLinkedQueue?
 * A6: Common methods include:
 *    - `offer(E e)`: Adds the specified element to the tail of the queue.
 *    - `poll()`: Retrieves and removes the head of the queue, or returns `null` if the queue is empty.
 *    - `peek()`: Retrieves the head of the queue without removing it, or returns `null` if the queue is empty.
 *    - `size()`: Returns the number of elements in the queue (not constant-time).
 * 
 * Q7: Is ConcurrentLinkedQueue suitable for producer-consumer scenarios?
 * A7: Not directly, as it is non-blocking. For scenarios requiring blocking behavior, `BlockingQueue` implementations like `ArrayBlockingQueue` or `LinkedBlockingQueue` are more appropriate.
 * 
 * Q8: How does ConcurrentLinkedQueue perform under high contention?
 * A8: It performs well under high contention due to its lock-free design, reducing thread contention and providing better scalability.
 * 
 * Q9: What is the time complexity of basic operations in ConcurrentLinkedQueue?
 * A9: Most basic operations like `offer()`, `poll()`, and `peek()` have O(1) time complexity due to the efficient singly-linked structure.
 * 
 * Q10: What are the use cases for ConcurrentLinkedQueue?
 * A10: Common use cases include:
 *    - Task queuing in concurrent applications.
 *    - Maintaining a queue of events or messages in real-time systems.
 *    - High-throughput systems requiring non-blocking, thread-safe queues.
 * 
 * Q11: How does ConcurrentLinkedQueue handle concurrency?
 * A11: It uses atomic variables for its head and tail pointers and CAS (Compare-And-Swap) operations to safely update the queue structure without locking.
 * 
 * Q12: What happens if two threads simultaneously try to add elements to a ConcurrentLinkedQueue?
 * A12: Both threads succeed in adding their elements due to the non-blocking algorithm, which ensures correct and efficient updates to the queue.
 * 
 * Q13: How does the `size()` method of ConcurrentLinkedQueue work?
 * A13: The `size()` method traverses the entire queue to count the elements, making it O(n) in time complexity. It may not reflect the exact size in a concurrent scenario.
 * 
 * Q14: Can ConcurrentLinkedQueue be used in single-threaded applications?
 * A14: While it can be used, it may not be optimal. Simpler queue implementations like `LinkedList` or `ArrayDeque` are better suited for single-threaded use cases.
 */


















