package synchronization;

// Example demonstrating different types of synchronization in Java
public class SynchronizationDemo {

    private int counter = 0;
    private static int staticCounter = 0;

    // 1. Synchronized Instance Method
    // Only one thread can access this method for a given instance
    public synchronized void incrementCounter() {
        System.out.println(Thread.currentThread().getName() + " entering synchronized method");
        counter++;
        try {
            Thread.sleep(1000); // Simulate some work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + " counter value: " + counter);
        System.out.println(Thread.currentThread().getName() + " exiting synchronized method");
    }

    // 2. Synchronized Static Method
    // Only one thread can access this method across all instances
    public static synchronized void incrementStaticCounter() {
        System.out.println(Thread.currentThread().getName() + " entering static synchronized method");
        staticCounter++;
        try {
            Thread.sleep(1000); // Simulate some work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + " static counter value: " + staticCounter);
        System.out.println(Thread.currentThread().getName() + " exiting static synchronized method");
    }

    // 3. Synchronized Block on Object
    private final Object lockObject = new Object();

    public void incrementWithSyncBlock() {
        System.out.println(Thread.currentThread().getName() + " before synchronized block");
        synchronized (lockObject) {
            System.out.println(Thread.currentThread().getName() + " entering synchronized block");
            counter++;
            try {
                Thread.sleep(1000); // Simulate some work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName() + " counter value: " + counter);
            System.out.println(Thread.currentThread().getName() + " exiting synchronized block");
        }
    }

    // 4. Synchronized Block on this
    public void incrementWithSyncThis() {
        System.out.println(Thread.currentThread().getName() + " before synchronized(this) block");
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " entering synchronized(this) block");
            counter++;
            try {
                Thread.sleep(1000); // Simulate some work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName() + " counter value: " + counter);
            System.out.println(Thread.currentThread().getName() + " exiting synchronized(this) block");
        }
    }

    public static void main(String[] args) {
        SynchronizationDemo demo = new SynchronizationDemo();
        SynchronizationDemo demo2 = new SynchronizationDemo();

        // Test 1: Synchronized Instance Method
        System.out.println("\nTesting Synchronized Instance Method:");
        Thread t1 = new Thread(() -> demo.incrementCounter(), "Thread-1");
        Thread t2 = new Thread(() -> demo.incrementCounter(), "Thread-2");

        /*
         * If we use Thread t2 = new Thread(() -> demo2.incrementCounter(), "Thread-2"); (demo2)
         * YES, Multiple Threads Can Enter Simultaneously IF:
         * - They are using different instances
         * - Because each instance has its own monitor lock
         */

        // Test 2: Synchronized Static Method
        System.out.println("\nTesting Synchronized Static Method:");
        Thread t3 = new Thread(() -> SynchronizationDemo.incrementStaticCounter(), "Thread-3");
        Thread t4 = new Thread(() -> SynchronizationDemo.incrementStaticCounter(), "Thread-4");

        // Test 3: Synchronized Block
        System.out.println("\nTesting Synchronized Block:");
        Thread t5 = new Thread(() -> demo.incrementWithSyncBlock(), "Thread-5");
        Thread t6 = new Thread(() -> demo.incrementWithSyncBlock(), "Thread-6");

        // Test 4: Different instances with synchronized methods
        System.out.println("\nTesting Different Instances:");
        Thread t7 = new Thread(() -> demo.incrementWithSyncThis(), "Thread-7");
        Thread t8 = new Thread(() -> demo2.incrementWithSyncThis(), "Thread-8");

        // Start all threads
        t1.start();
        t2.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        t3.start();
        t4.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        t5.start();
        t6.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        t7.start();
        t8.start();
    }
}

/*
Basic Synchronization Questions:

Q: What is synchronization in Java?
A: Synchronization is a mechanism that controls access to shared resources by multiple threads. 
   It ensures that only one thread can access the resource at a time, preventing data corruption 
   and race conditions.

Q: What is the difference between synchronized method and synchronized block?
   // Synchronized method - locks entire method
   public synchronized void method() { }

   // Synchronized block - locks specific section
   synchronized(lockObject) {
       // critical section
   }
A: Synchronized block provides better performance and more fine-grained control as it only 
   locks the critical section, while synchronized method locks the entire method.

Monitor Lock Questions:

Q: What is monitor lock in Java?
A: Every object in Java has an associated monitor lock (intrinsic lock). When a thread enters 
   a synchronized block/method, it acquires the monitor lock of the object and releases it when 
   exiting the block/method.

Q: How many threads can acquire a monitor lock at the same time?
A: Only one thread can hold a monitor lock at a time. Other threads must wait until the lock 
   is released.

Static vs Instance Synchronization:

Q: What's the difference between static synchronized and instance synchronized methods?
   // Instance synchronized - locks on instance
   public synchronized void instanceMethod() { }

   // Static synchronized - locks on class
   public static synchronized void staticMethod() { }

A: Static synchronized methods lock on the class object (SynchronizationDemo.class) and affect 
   all instances, while instance synchronized methods lock on the instance (this) and only affect 
   that specific instance.

Object Lock Questions:

Q: What can be used as a lock object in synchronized block?
A: Any object can be used as a lock, but commonly used locks are:
   1. this - current instance
   2. ClassName.class - class level lock
   3. private final Object lock = new Object(); - dedicated lock object

Thread Safety Questions:

Q: What makes a code thread-safe?
A: Code is thread-safe when:
   1. It uses proper synchronization
   2. Manages shared resources correctly
   3. Handles concurrent access
   4. Uses atomic operations or volatile variables when appropriate
   5. Follows proper locking strategies

Performance Questions:

Q: How does synchronization affect performance?
A: Synchronization can impact performance by:
   1. Causing thread contention
   2. Adding overhead for lock acquisition/release
   3. Potentially causing threads to wait

   Best practices:
   1. Use synchronized blocks instead of methods
   2. Keep critical sections as small as possible
   3. Consider using alternative concurrent utilities

Real-World Scenarios:

Q: When would you use different types of synchronization?
A: Usage scenarios:
   1. synchronized method: When entire method needs thread safety
   2. synchronized block: When only specific code section needs protection
   3. static synchronized: When sharing across all instances
   4. Object lock: When need fine-grained control over locking

Advanced Concepts:

Q: What is the difference between volatile and synchronized?
A: 
   volatile: 
   - Ensures visibility of changes across threads
   - No atomicity guarantee
   - No locking involved

   synchronized:
   - Provides both visibility and atomicity
   - Involves locking
   - Can synchronize multiple operations

Code Analysis Questions:

Q: What will happen if you remove synchronized from a method?
A: Without synchronized:
   1. Multiple threads can access method simultaneously
   2. Can lead to race conditions
   3. No visibility guarantee between threads
   4. Potential data corruption
*/
