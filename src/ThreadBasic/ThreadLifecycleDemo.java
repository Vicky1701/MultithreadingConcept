package ThreadBasic;

/**
* Demonstrates different states in a thread's lifecycle
*/
public class ThreadLifecycleDemo {

 public static void main(String[] args) throws InterruptedException {
     System.out.println("\n=== Thread Lifecycle Demo ===");

     // NEW state
     Thread thread = new Thread(() -> {
         try {
             // RUNNABLE state when executing
             System.out.println("Thread is running");

             // TIMED_WAITING state
             Thread.sleep(1000);

             synchronized (ThreadLifecycleDemo.class) {
                 // BLOCKED state when trying to enter synchronized block
                 System.out.println("Thread in synchronized block");
             }
             
         } catch (InterruptedException e) {
             Thread.currentThread().interrupt();
         }
     });

     // Print NEW state
     System.out.println("Thread State: " + thread.getState());

     // Start thread - transition to RUNNABLE
     thread.start();

     // TIMED_WAITING state demonstration
     Thread.sleep(500);
     System.out.println("Thread State: " + thread.getState());

     // Wait for thread to finish - TERMINATED state
     thread.join();
     System.out.println("Thread State: " + thread.getState());
 }
 
 
 /*
Explanation of synchronized:
Synchronized Block: The synchronized keyword can be applied to a block of code or a method to prevent concurrent access 
					by multiple threads.	
  					It locks the object or class, making other threads wait until the lock is released.
Monitor Lock: When a thread enters a synchronized block, it acquires the monitor lock on the specified object 
					(in this case, ThreadLifecycleDemo.class). 
					No other thread can enter any synchronized block or method that locks the same 
					object until the lock is released by the current thread. 




synchronized (ThreadLifecycleDemo.class): This line synchronizes on the Class object of ThreadLifecycleDemo. 
This means that any thread trying to execute this block must first acquire a lock on ThreadLifecycleDemo.class.

Blocked State: If another thread has already acquired the lock on ThreadLifecycleDemo.class,
 any thread that tries to enter this block will be in a BLOCKED state, waiting for the lock to be released.
 

Execution: Once the lock is acquired, the thread enters the block and executes the code 
(System.out.println("Thread in synchronized block")).
When the block is complete, the lock is released, allowing other waiting threads to acquire it.

When to Use synchronized?
Use synchronized when you need to protect shared resources (e.g., data structures, files, or shared variables) 
from concurrent access to avoid race conditions and data inconsistency.
Synchronized methods lock the entire method, whereas synchronized blocks lock only a part of the method, 
allowing more granular control and potentially improving performance.

Example Scenario:
If you have multiple threads trying to modify a shared counter or access a critical section of code, 
using synchronized ensures that only one thread at a time can modify or access the resource, preserving data consistency.

Visual Representation:
Imagine a single room with only one key:

When a thread acquires the lock (key), it enters the room and performs the task.
Other threads must wait outside (BLOCKED state) until the key is available (lock is released).
Once the thread inside finishes and exits the room, it releases the key, allowing the next waiting thread to enter.
       
       */
 
}