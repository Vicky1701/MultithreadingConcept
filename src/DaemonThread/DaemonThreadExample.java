package DaemonThread;

public class DaemonThreadExample {
    public static void main(String[] args) {
        DaemonThread userThread = new DaemonThread("User Thread");
        DaemonThread daemonThread = new DaemonThread("Daemon Thread");

        // Setting daemon thread
        daemonThread.setDaemon(true);

        // Starting threads
        userThread.start();
        daemonThread.start();

        System.out.println("Main thread execution completed.");
    }
}

/**
 * Java Daemon Threads - Interview Questions with Answers
 *
 * 1. What is a daemon thread in Java?
 * Answer:
 * A daemon thread is a low-priority thread that runs in the background to perform tasks such as garbage collection. 
 * It does not prevent the JVM from exiting once all user threads have finished execution.
 *
 * 2. How can you identify whether a thread is a daemon or a user thread?
 * Answer:
 * Use the `isDaemon()` method to check if a thread is a daemon. It returns `true` for daemon threads and `false` for user threads.
 * 
 * Example:
 * Thread t = new Thread();
 * System.out.println(t.isDaemon()); // Output: false (default is user thread)
 *
 * 3. How can you set a thread as a daemon thread?
 * Answer:
 * Use the `setDaemon(true)` method to mark a thread as a daemon before starting it. 
 * Once a thread has started, its daemon status cannot be changed.
 *
 * Example:
 * Thread t = new Thread();
 * t.setDaemon(true); // Setting as daemon
 *
 * 4. Can the `main` thread be a daemon thread?
 * Answer:
 * No, the `main` thread cannot be set as a daemon thread. It is always a user thread.
 *
 * 5. What happens if all user threads finish execution but daemon threads are still running?
 * Answer:
 * The JVM terminates automatically once all user threads complete their execution, regardless of whether daemon threads are still running.
 *
 * 6. What are some common use cases for daemon threads?
 * Answer:
 * - Background tasks like garbage collection
 * - Monitoring and housekeeping tasks
 * - Implementing services that do not need to block the JVM shutdown
 *
 * 7. Write a program to demonstrate the behavior of daemon threads.
 * Answer:
 *
 * class DaemonBehavior extends Thread {
 *     public void run() {
 *         while (true) {
 *             System.out.println(Thread.currentThread().getName() + " is running.");
 *             try {
 *                 Thread.sleep(1000);
 *             } catch (InterruptedException e) {
 *                 System.out.println("Thread interrupted.");
 *             }
 *         }
 *     }
 * }
 *
 * public class DaemonExample {
 *     public static void main(String[] args) {
 *         DaemonBehavior thread = new DaemonBehavior();
 *         thread.setDaemon(true); // Setting as daemon
 *         thread.start();
 *
 *         System.out.println("Main thread execution completed.");
 *     }
 * }
 *
 * Output:
 * The daemon thread may terminate abruptly as soon as the main thread finishes execution.
 *
 * 8. What is the default nature of a thread - daemon or user?
 * Answer:
 * By default, every thread in Java is a user thread unless explicitly marked as a daemon using `setDaemon(true)`.
 *
 * 9. Can you change a thread to a daemon thread after it has started?
 * Answer:
 * No, you cannot change the status of a thread (to daemon or user) once the thread has started execution. 
 * Attempting to do so will throw `IllegalThreadStateException`.
 *
 * 10. What are the differences between a daemon thread and a user thread?
 * Answer:
 * - **Daemon Thread**: Runs in the background, does not block JVM termination, primarily used for housekeeping tasks.
 * - **User Thread**: Runs in the foreground, blocks JVM termination, used for executing the main application logic.
 *
 * 11. Why is it important to use daemon threads carefully in Java?
 * Answer:
 * - Daemon threads may terminate abruptly if all user threads finish execution, potentially leaving tasks incomplete.
 * - They should not be used for tasks requiring guaranteed execution or data integrity.
 */
