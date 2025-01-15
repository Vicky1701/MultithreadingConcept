package LocksAndSemaphoresConcepts;

import java.util.concurrent.locks.StampedLock;

/**
 * Example of StampedLock usage with detailed comments and clear System.out messages.
 */
class StampedLockExample {

    private final StampedLock stampedLock = new StampedLock();
    private int sharedData = 0;

    /**
     * Simulates reading the shared resource using a StampedLock in a read lock mode.
     */
    public void readResource() {
        long stamp = stampedLock.readLock(); // Acquire a read lock
        try {
            System.out.println(Thread.currentThread().getName() + " - Acquired read lock. Reading data: " + sharedData);
        } finally {
            System.out.println(Thread.currentThread().getName() + " - Releasing read lock.");
            stampedLock.unlockRead(stamp); // Release the read lock
        }
    }

    /**
     * Simulates writing to the shared resource using a StampedLock in a write lock mode.
     */
    public void writeResource(int value) {
        long stamp = stampedLock.writeLock(); // Acquire a write lock
        try {
            System.out.println(Thread.currentThread().getName() + " - Acquired write lock. Updating data to: " + value);
            sharedData = value;
        } finally {
            System.out.println(Thread.currentThread().getName() + " - Releasing write lock.");
            stampedLock.unlockWrite(stamp); // Release the write lock
        }
    }

    public static void main(String[] args) {
        StampedLockExample example = new StampedLockExample();

        // Define tasks for reading and writing the resource
        Runnable readTask = example::readResource;
        Runnable writeTask = () -> example.writeResource((int) (Math.random() * 100));

        // Create and start threads for reading and writing
        for (int i = 1; i <= 3; i++) {
            Thread reader = new Thread(readTask, "Reader Thread " + i);
            reader.start();
        }

        for (int i = 1; i <= 2; i++) {
            Thread writer = new Thread(writeTask, "Writer Thread " + i);
            writer.start();
        }
    }
}

/**
 * Interview Questions and Answers:
 * 
 * Q1: What is a StampedLock in Java?
 * A1: A StampedLock is a type of lock introduced in Java 8 that supports three modes of locking: read, write, and optimistic read. It provides better throughput under high contention compared to traditional locks.
 * 
 * Q2: What are the key methods of StampedLock?
 * A2: The key methods are:
 *     - readLock(): Acquires a read lock.
 *     - writeLock(): Acquires a write lock.
 *     - tryOptimisticRead(): Attempts an optimistic read without locking.
 *     - validate(long stamp): Checks if the optimistic read is still valid.
 *     - unlockRead(long stamp): Releases a read lock.
 *     - unlockWrite(long stamp): Releases a write lock.
 * 
 * Q3: What is optimistic reading in StampedLock?
 * A3: Optimistic reading is a lightweight, non-blocking way to read data. A thread can read without acquiring a full lock, but it must validate the stamp to ensure data consistency.
 * 
 * Q4: What happens if a thread holding an optimistic read finds the data inconsistent?
 * A4: The thread should fallback to acquiring a read or write lock to ensure data consistency.
 * 
 * Q5: What are the advantages of using StampedLock?
 * A5: StampedLock offers higher throughput and reduced contention compared to ReentrantReadWriteLock, especially in scenarios with more reads than writes.
 * 
 * Q6: What are the limitations of StampedLock?
 * A6: StampedLock does not support reentrant locks and cannot detect deadlocks. Developers need to use it carefully to avoid issues.
 * 
 * Q7: Can StampedLock be used in place of ReentrantReadWriteLock?
 * A7: Yes, but only if the application does not require reentrant behavior and can handle the added complexity of using StampedLock.
 */
