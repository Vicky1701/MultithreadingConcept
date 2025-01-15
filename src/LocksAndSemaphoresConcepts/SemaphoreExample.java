package LocksAndSemaphoresConcepts;

import java.util.concurrent.Semaphore;

/**
 * Example of Semaphore usage with detailed comments and clear System.out messages.
 */
class SemaphoreExample {

    private final Semaphore semaphore = new Semaphore(2); // Semaphore with 2 permits

    /**
     * Simulates accessing a shared resource that is limited by the number of permits.
     */
    public void accessResource() {
        try {
            System.out.println(Thread.currentThread().getName() + " - Attempting to acquire permit...");
            semaphore.acquire(); // Acquire a permit
            System.out.println(Thread.currentThread().getName() + " - Permit acquired. Accessing resource.");

            // Simulate work with the resource
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " - Interrupted while accessing resource.");
        } finally {
            System.out.println(Thread.currentThread().getName() + " - Releasing permit.");
            semaphore.release(); // Release the permit
        }
    }

    public static void main(String[] args) {
        SemaphoreExample example = new SemaphoreExample();

        // Define a task for accessing the resource
        Runnable task = example::accessResource;

        // Create and start threads
        for (int i = 1; i <= 5; i++) {
            Thread thread = new Thread(task, "Thread " + i);
            thread.start();
        }
    }
}

/**
 * Interview Questions and Answers:
 * 
 * Q1: What is a Semaphore in Java?
 * A1: A Semaphore is a thread synchronization mechanism that controls access to a shared resource through a set number of permits. Threads must acquire a permit before accessing the resource and release it afterward.
 * 
 * Q2: What is the primary use of a Semaphore?
 * A2: Semaphores are used to limit the number of threads that can access a shared resource simultaneously.
 * 
 * Q3: How do you create a Semaphore in Java?
 * A3: You can create a Semaphore using:
 *     Semaphore semaphore = new Semaphore(int permits);
 *     where `permits` is the number of threads allowed to access the resource at the same time.
 * 
 * Q4: What is the difference between a fair and non-fair Semaphore?
 * A4: A fair Semaphore ensures that permits are granted in the order of thread requests (FIFO), while a non-fair Semaphore may grant permits to threads in a random order, potentially causing starvation.
 * 
 * Q5: What happens if a thread tries to acquire a permit but none are available?
 * A5: The thread will block and wait until a permit becomes available.
 * 
 * Q6: Can a Semaphore have zero permits?
 * A6: Yes, a Semaphore can be initialized with zero permits. In this case, threads trying to acquire a permit will block until permits are released.
 * 
 * Q7: How do you release a permit in a Semaphore?
 * A7: Use the `release()` method to release a permit:
 *     semaphore.release();
 * 
 * Q8: What is the difference between Semaphore and synchronized?
 * A8: The key differences are:
 *     - Semaphore allows a specified number of threads to access a resource, while synchronized allows only one thread at a time.
 *     - Semaphore provides more flexibility and advanced control over thread synchronization.
 */
