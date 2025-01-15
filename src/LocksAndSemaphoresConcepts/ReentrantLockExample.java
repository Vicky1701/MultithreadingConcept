package LocksAndSemaphoresConcepts;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

/**
 * Example of Reentrant Lock usage with detailed comments and logging.
 */
class ReentrantLockExample {

    private final Lock lock = new ReentrantLock(); // A ReentrantLock instance
   // private static final Logger logger = Logger.getLogger(ReentrantLockExample.class.getName()); // Logger for better understanding

    /**
     * Prints numbers from 1 to 5 with proper locking mechanism to ensure thread safety.
     */
    public void printNumbers() {
        // Acquire the lock before accessing shared resources
        lock.lock();
        try {
            for (int i = 1; i <= 5; i++) {
                // Log the current thread name and the number being printed
                //logger.info(Thread.currentThread().getName() + " - Printing: " + i);
                System.out.println(Thread.currentThread().getName() + " - " + i);
            }
        } finally {
            // Always release the lock in a finally block to avoid deadlocks
            lock.unlock();
            //logger.info(Thread.currentThread().getName() + " - Lock released");
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();

        // Define tasks for multiple threads
        Runnable task = example::printNumbers;

        // Create and start threads
        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");

        t1.start();
        t2.start();
    }
}

/**
 * Interview Questions and Answers:
 * 
 * Q1: What is a ReentrantLock in Java?
 * A1: ReentrantLock is a lock implementation provided by the java.util.concurrent.locks package. It allows threads to acquire the lock multiple times if the lock is already held by the same thread, hence the term "reentrant." It provides more control and flexibility compared to synchronized blocks, such as the ability to try locking, timed locking, and interruptible locking.
 * 
 * Q2: How is ReentrantLock different from synchronized blocks?
 * A2: The main differences are:
 *    - ReentrantLock provides explicit lock and unlock methods, whereas synchronized is implicit.
 *    - ReentrantLock supports fairness policies, tryLock(), and lockInterruptibly().
 *    - ReentrantLock must be released explicitly, while synchronized automatically releases locks when the thread exits the synchronized block/method.
 * 
 * Q3: What happens if a thread forgets to unlock a ReentrantLock?
 * A3: Forgetting to unlock a ReentrantLock can lead to deadlocks, where other threads waiting for the lock are blocked indefinitely. To avoid this, always use the lock in a try-finally block to ensure proper release.
 * 
 * Q4: How does ReentrantLock improve performance over synchronized?
 * A4: ReentrantLock can improve performance in scenarios with high contention because it allows non-blocking attempts to acquire the lock (tryLock) and enables fairness policies. It is also more flexible for advanced use cases like interruptible locking.
 * 
 * Q5: What is the purpose of the tryLock() method?
 * A5: The tryLock() method allows a thread to attempt to acquire the lock without blocking. If the lock is not available, it immediately returns false, allowing the thread to perform other tasks instead of waiting.
 * 
 * Q6: Can ReentrantLock be used with Condition variables?
 * A6: Yes, ReentrantLock can create Condition variables using the newCondition() method. These can be used for advanced thread coordination, similar to wait() and notify() with synchronized blocks.
 * 
 * Q7: How do you make a ReentrantLock fair?
 * A7: You can create a fair ReentrantLock by passing true to its constructor: new ReentrantLock(true). A fair lock grants access to threads in the order they requested it (FIFO).
 * 
 * Q8: Is ReentrantLock reentrant by default?
 * A8: Yes, ReentrantLock is reentrant by default, meaning a thread can acquire the same lock multiple times as long as it releases it the same number of times.
 */
