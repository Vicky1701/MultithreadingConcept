package LocksAndSemaphoresConcepts;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Example of ReadWriteLock usage with detailed comments and clear System.out messages.
 */
class ReadWriteLockExample {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(); // A ReadWriteLock instance
    private int sharedResource = 0; // A shared resource to demonstrate ReadWriteLock

    /**
     * Reads the value of the shared resource. Multiple threads can access this simultaneously.
     */
    public void readResource() {
        lock.readLock().lock(); // Acquire the read lock
        try {
            System.out.println(Thread.currentThread().getName() + " - Reading value: " + sharedResource);
        } finally {
            lock.readLock().unlock(); // Release the read lock
            System.out.println(Thread.currentThread().getName() + " - Read lock released.");
        }
    }

    /**
     * Writes a new value to the shared resource. Only one thread can access this at a time.
     */
    public void writeResource(int value) {
        lock.writeLock().lock(); // Acquire the write lock
        try {
            System.out.println(Thread.currentThread().getName() + " - Writing value: " + value);
            sharedResource = value;
        } finally {
            lock.writeLock().unlock(); // Release the write lock
            System.out.println(Thread.currentThread().getName() + " - Write lock released.");
        }
    }

    public static void main(String[] args) {
        ReadWriteLockExample example = new ReadWriteLockExample();

        // Define tasks for reading and writing
        Runnable readTask = example::readResource;
        // Runnable readTask = () -> example.readResource();--> both are same above line and this line

        Runnable writeTask = () -> example.writeResource((int) (Math.random() * 100));

        // Create and start threads
        Thread writer = new Thread(writeTask, "Writer Thread");
        Thread reader1 = new Thread(readTask, "Reader Thread 1");
        Thread reader2 = new Thread(readTask, "Reader Thread 2");

        writer.start();
        reader1.start();
        reader2.start();
    }
}

/**
 * Interview Questions and Answers:
 * 
 * Q1: What is a ReadWriteLock in Java?
 * A1: ReadWriteLock is a lock implementation provided by java.util.concurrent.locks. It has two types of locks:
 *     - Read Lock: Allows multiple threads to read a shared resource simultaneously.
 *     - Write Lock: Allows only one thread to write to the shared resource and prevents other threads from reading or writing while writing is in progress.
 * 
 * Q2: When should you use a ReadWriteLock?
 * A2: Use ReadWriteLock when you have a shared resource that is read frequently but written to infrequently. This allows better concurrency and performance by letting multiple threads read simultaneously.
 * 
 * Q3: Can a thread acquire a read lock if another thread holds a write lock?
 * A3: No, a thread cannot acquire a read lock while a write lock is held by another thread. The write lock ensures exclusive access.
 * 
 * Q4: What happens if a thread holding a read lock tries to acquire a write lock?
 * A4: If a thread holding a read lock tries to acquire a write lock, it can lead to a deadlock. To prevent this, you should always release the read lock before attempting to acquire a write lock.
 * 
 * Q5: How does ReadWriteLock improve performance?
 * A5: ReadWriteLock improves performance by allowing multiple threads to read a shared resource simultaneously, reducing contention when read operations dominate.
 * 
 * Q6: Can ReadWriteLock be reentrant?
 * A6: Yes, ReadWriteLock can be reentrant. A thread that holds a read lock or write lock can reacquire it if needed.
 * 
 * Q7: What is the difference between synchronized and ReadWriteLock?
 * A7: The main differences are:
 *     - synchronized blocks both read and write operations for all threads.
 *     - ReadWriteLock allows multiple threads to read simultaneously but only one thread to write.
 * 
 * Q8: How do you create a ReadWriteLock instance in Java?
 * A8: Use the ReentrantReadWriteLock class: 
 *     ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
 */
