package Concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class BlockingQueueExamples {

    /**
     * Demonstrates usage of ArrayBlockingQueue.
     */
    public static void arrayBlockingQueueExample() {
        System.out.println("=== ArrayBlockingQueue Example ===");
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);

        try {
            queue.put(1); // Adding element to the queue
            System.out.println("Added: 1");
            queue.put(2);
            System.out.println("Added: 2");
            queue.put(3);
            System.out.println("Added: 3");

            System.out.println("Attempting to add another element (blocks until space is available)");

            // This will block since the queue is full
            new Thread(() -> {
                try {
                    queue.put(4);
                    System.out.println("Added: 4 after space became available.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();

            Thread.sleep(1000); // Simulate time for other operations
            System.out.println("Removed: " + queue.take()); // Removes element
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Demonstrates usage of LinkedBlockingQueue.
     */
    public static void linkedBlockingQueueExample() {
        System.out.println("=== LinkedBlockingQueue Example ===");
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

        try {
            queue.put("A");
            System.out.println("Added: A");
            queue.put("B");
            System.out.println("Added: B");
            queue.put("C");
            System.out.println("Added: C");

            System.out.println("Queue content: " + queue);

            System.out.println("Removed: " + queue.take()); // Removes element
            System.out.println("Queue content after removal: " + queue);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Demonstrates usage of PriorityBlockingQueue.
     */
    public static void priorityBlockingQueueExample() {
        System.out.println("=== PriorityBlockingQueue Example ===");
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();

        queue.add(20);
        System.out.println("Added: 20");
        queue.add(5);
        System.out.println("Added: 5");
        queue.add(15);
        System.out.println("Added: 15");

        System.out.println("Queue content (prioritized): " + queue);

        try {
            System.out.println("Removed: " + queue.take());
            System.out.println("Queue content after removal: " + queue);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        arrayBlockingQueueExample();
        linkedBlockingQueueExample();
        priorityBlockingQueueExample();
    }
}

/**
 * Interview Questions and Answers:
 * 
 * Q1: What is a BlockingQueue in Java?
 * A1: BlockingQueue is an interface in Java that represents a thread-safe queue that blocks the operations of threads if the queue is full (for producers) or empty (for consumers).
 * 
 * Q2: What are the common implementations of BlockingQueue?
 * A2: The common implementations are:
 *     - ArrayBlockingQueue
 *     - LinkedBlockingQueue
 *     - PriorityBlockingQueue
 * 
 * Q3: How does ArrayBlockingQueue differ from LinkedBlockingQueue?
 * A3: ArrayBlockingQueue is backed by a fixed-size array, making it bounded, while LinkedBlockingQueue is backed by a linked structure and can be optionally bounded or unbounded.
 * 
 * Q4: What is the difference between PriorityBlockingQueue and other BlockingQueue implementations?
 * A4: PriorityBlockingQueue orders its elements based on their natural ordering or a custom Comparator, while other BlockingQueue implementations maintain insertion order.
 * 
 * Q5: What happens if you try to add an element to a full BlockingQueue?
 * A5: For bounded BlockingQueues like ArrayBlockingQueue, the `put()` method blocks until space becomes available, while the `offer()` method can return `false` or timeout.
 * 
 * Q6: What is the default capacity of LinkedBlockingQueue if none is specified?
 * A6: The default capacity is Integer.MAX_VALUE.
 * 
 * Q7: Can PriorityBlockingQueue handle null elements?
 * A7: No, PriorityBlockingQueue does not allow null elements and throws a NullPointerException if attempted.
 * 
 * Q8: How does thread-safety work in BlockingQueue implementations?
 * A8: BlockingQueue implementations use internal locks or other synchronization mechanisms to ensure thread-safe operations for concurrent producers and consumers.
 * 
 * Q9: What is the main difference between BlockingQueue and non-blocking queues?
 * A9: BlockingQueue methods like `put()` and `take()` block until they can complete their operations, while non-blocking queues return immediately.
 * 
 * Q10: Can we use BlockingQueue in a producer-consumer scenario?
 * A10: Yes, BlockingQueue is specifically designed for producer-consumer scenarios as it handles synchronization and waiting internally.
 */
