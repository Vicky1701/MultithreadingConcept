package LocksAndSemaphoresConcepts;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Example of Lock with Condition interface with detailed comments and clear System.out messages.
 */
class LockWithConditionExample {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean readyToConsume = false;
    private int sharedData = 0;

    /**
     * Producer method that writes data and signals the consumer.
     */
    public void produce(int value) {
        lock.lock(); // Acquire the lock
        try {
            while (readyToConsume) {
                System.out.println(Thread.currentThread().getName() + " - Waiting for consumer to consume.");
                condition.await(); // Wait until the data is consumed
            }
            sharedData = value;
            readyToConsume = true;
            System.out.println(Thread.currentThread().getName() + " - Produced data: " + value);
            condition.signal(); // Signal the consumer
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " - Interrupted during production.");
        } finally {
            lock.unlock(); // Release the lock
        }
    }

    /**
     * Consumer method that reads data produced by the producer.
     */
    public void consume() {
        lock.lock(); // Acquire the lock
        try {
            while (!readyToConsume) {
                System.out.println(Thread.currentThread().getName() + " - Waiting for producer to produce.");
                condition.await(); // Wait until data is produced
            }
            System.out.println(Thread.currentThread().getName() + " - Consumed data: " + sharedData);
            readyToConsume = false;
            condition.signal(); // Signal the producer
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " - Interrupted during consumption.");
        } finally {
            lock.unlock(); // Release the lock
        }
    }

    public static void main(String[] args) {
        LockWithConditionExample example = new LockWithConditionExample();

        // Producer task
        Runnable producerTask = () -> {
            for (int i = 1; i <= 5; i++) {
                example.produce(i);
            }
        };

        // Consumer task
        Runnable consumerTask = () -> {
            for (int i = 1; i <= 5; i++) {
                example.consume();
            }
        };

        // Start producer and consumer threads
        Thread producer = new Thread(producerTask, "Producer Thread");
        Thread consumer = new Thread(consumerTask, "Consumer Thread");

        producer.start();
        consumer.start();
    }
}

/**
 * Interview Questions and Answers:
 * 
 * Q1: What is the Condition interface in Java?
 * A1: The Condition interface is used with a Lock to provide thread communication similar to the Object class's wait(), notify(), and notifyAll() methods but with more control and flexibility.
 * 
 * Q2: How do you create a Condition object?
 * A2: A Condition object is created using the newCondition() method of the Lock interface, typically from a ReentrantLock instance.
 *     Example: Condition condition = lock.newCondition();
 * 
 * Q3: What are the key methods of the Condition interface?
 * A3: Key methods include:
 *     - await(): Causes the current thread to wait until signaled.
 *     - signal(): Wakes up one waiting thread.
 *     - signalAll(): Wakes up all waiting threads.
 * 
 * Q4: What are the differences between Condition and Object's wait/notify?
 * A4: Differences include:
 *     - Condition is tied to a Lock, whereas wait/notify are tied to a synchronized block.
 *     - Condition provides multiple waiting queues, improving flexibility.
 * 
 * Q5: What happens if signal() is called when no thread is waiting?
 * A5: If signal() is called and no threads are waiting, the signal is ignored.
 * 
 * Q6: What are some use cases for the Condition interface?
 * A6: Use cases include:
 *     - Producer-consumer problems.
 *     - Multi-threaded communication where specific conditions need to be met before a thread proceeds.
 * 
 * Q7: Can a thread call await() without holding the lock?
 * A7: No, calling await() without holding the associated lock will throw an IllegalMonitorStateException.
 */
