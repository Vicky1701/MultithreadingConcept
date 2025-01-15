package PriorityThread;

public class ThreadPriorityExample {
    public static void main(String[] args) {
        // Creating threads with default priority
        PriorityThread thread1 = new PriorityThread("Thread-1 (Default Priority)");
        PriorityThread thread2 = new PriorityThread("Thread-2 (High Priority)");
        PriorityThread thread3 = new PriorityThread("Thread-3 (Low Priority)");

        // Setting thread priorities
        thread1.setPriority(Thread.NORM_PRIORITY); // Default: 5
        thread2.setPriority(Thread.MAX_PRIORITY);  // Maximum: 10
        thread3.setPriority(Thread.MIN_PRIORITY);  // Minimum: 1

        // Starting threads
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

/**
 * Java Thread Priority - Interview Questions with Answers
 * 
 * 1. What is the default thread priority in Java?
 * Answer:
 * The default thread priority is 5, which corresponds to Thread.NORM_PRIORITY.
 * 
 * 2. What are the valid ranges for thread priorities in Java?
 * Answer:
 * Thread priorities range from 1 to 10, where:
 * - Thread.MIN_PRIORITY = 1
 * - Thread.NORM_PRIORITY = 5
 * - Thread.MAX_PRIORITY = 10
 * 
 * 3. How can you set and retrieve a thread's priority?
 * Answer:
 * - To set a thread's priority, use the setPriority(int priority) method.
 * - To retrieve a thread's priority, use the getPriority() method.
 * 
 * Example:
 * Thread t = new Thread();
 * t.setPriority(Thread.MAX_PRIORITY); // Setting priority to 10
 * System.out.println(t.getPriority()); // Output: 10
 * 
 * 4. How does thread priority affect thread execution?
 * Answer:
 * Thread priority affects the likelihood of a thread being scheduled for execution. 
 * Higher-priority threads are more likely to be chosen for execution. However, it is not guaranteed, as scheduling is OS-dependent.
 * 
 * 5. Can thread priority alone determine thread scheduling? Why or why not?
 * Answer:
 * No, thread priority does not guarantee scheduling. The JVM relies on the underlying operating system's thread scheduling policy, 
 * which may use time-slicing or preemptive multitasking. Hence, thread execution order can vary.
 * 
 * 6. What is the impact of setting all threads to MAX_PRIORITY?
 * Answer:
 * If all threads are set to MAX_PRIORITY, the priority feature becomes ineffective since all threads have the same priority. 
 * The OS scheduler will decide the execution order, leading to behavior similar to when threads have default priority.
 * 
 * 7. Write a program to demonstrate thread priorities.
 * Answer:
 * 
 * class TaskThread extends Thread {
 *     public TaskThread(String name) {
 *         super(name);
 *     }
 * 
 *     @Override
 *     public void run() {
 *         for (int i = 0; i < 5; i++) {
 *             System.out.println(Thread.currentThread().getName() + 
 *                                " Priority: " + 
 *                                Thread.currentThread().getPriority());
 *         }
 *     }
 * }
 * 
 * public class ThreadPriorityDemo {
 *     public static void main(String[] args) {
 *         TaskThread t1 = new TaskThread("Low Priority Thread");
 *         TaskThread t2 = new TaskThread("Normal Priority Thread");
 *         TaskThread t3 = new TaskThread("High Priority Thread");
 * 
 *         t1.setPriority(Thread.MIN_PRIORITY);  // Priority 1
 *         t2.setPriority(Thread.NORM_PRIORITY); // Priority 5
 *         t3.setPriority(Thread.MAX_PRIORITY);  // Priority 10
 * 
 *         t1.start();
 *         t2.start();
 *         t3.start();
 *     }
 * }
 * 
 * 8. If two threads have the same priority, how does the JVM handle scheduling?
 * Answer:
 * If two threads have the same priority, the JVM uses the OS's thread scheduler to decide which thread runs. 
 * The scheduling can vary between systems, using either time-slicing or a random order.
 * 
 * 9. Explain thread priority in relation to the underlying operating system.
 * Answer:
 * The JVM delegates thread scheduling to the operating system. In preemptive systems, higher-priority threads might preempt lower-priority ones. 
 * In time-slicing systems, threads with equal priorities are allocated CPU time in a round-robin manner.
 * 
 * 10. What are the pitfalls of relying on thread priorities for critical applications?
 * Answer:
 * - Portability Issues: Thread scheduling behavior varies across operating systems.
 * - Unpredictability: Even with high priority, a thread may not always execute first due to OS-level decisions.
 * - Starvation Risk: Low-priority threads may not get CPU time if higher-priority threads dominate.
 * - Complex Debugging: Priority-related issues are often hard to debug due to their non-deterministic nature.
 */
