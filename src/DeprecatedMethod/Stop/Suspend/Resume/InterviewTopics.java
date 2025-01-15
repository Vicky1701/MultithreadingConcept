package DeprecatedMethod.Stop.Suspend.Resume;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class InterviewTopics {

}

/*
STOP(), SUSPEND(), RESUME() INTERVIEW QUESTIONS & CONCEPTS
=======================================================

1. Why stop() is Deprecated?
   - Releases all locks immediately
   - Leaves objects in inconsistent states
   - No chance for cleanup operations
   - Can corrupt data structures
   - Can leave synchronized blocks in inconsistent states

2. Why suspend() and resume() are Deprecated?
   - Can cause deadlocks as suspended thread holds locks
   - No way to check if thread is suspended
   - Risk of indefinite blocking
   - Thread state inconsistency issues
*/

// Example 1: Problems with stop()
class StopMethodProblems {
    private int balance = 1000;
    private boolean shouldRun = true;
    
    // PROBLEM: Using stop() can corrupt data
    public void transferMoney() {
        Thread transferThread = new Thread(() -> {
            while (shouldRun) {
                synchronized(this) {
                    if (balance >= 500) {
                        // If thread is stopped here, balance will be inconsistent
                        balance -= 500;
                        // If stopped between these operations, receiver won't get money
                        // but sender's balance is reduced
                        makeTransfer(500);
                    }
                }
            }
        });
        transferThread.start();
        
        // BAD: Using stop()
        try {
            Thread.sleep(1000);
            transferThread.stop(); // Can leave balance in corrupt state
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private void makeTransfer(int amount) {
        // Transfer logic
    }
}

// Example 2: Problems with suspend() and resume()
class SuspendResumeProblems {
    private static final Object LOCK = new Object();
    
    public void demonstrateSuspendProblem() {
        Thread thread = new Thread(() -> {
            synchronized(LOCK) {
                System.out.println("Thread acquired lock");
                // BAD: If suspended here, lock is never released
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread releasing lock");
            }
        });
        
        thread.start();
        
        // BAD: Using suspend()
        try {
            Thread.sleep(500);
            thread.suspend(); // Thread holds lock indefinitely
            // Other threads waiting for LOCK will be blocked forever
            
            Thread.sleep(1000);
            thread.resume(); // May never get here if deadlock occurs
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
3. Proper Alternatives:
   a) Instead of stop(): Use volatile flag or interruption
   b) Instead of suspend()/resume(): Use wait()/notify() or modern concurrency utilities
*/

// Example 3: Safe Alternative to stop()
class SafeStopAlternative {
    private volatile boolean running = true;
    private int balance = 1000;
    
    public void safeTransferMoney() {
        Thread transferThread = new Thread(() -> {
            while (running && !Thread.currentThread().isInterrupted()) {
                synchronized(this) {
                    if (balance >= 500) {
                        try {
                            balance -= 500;
                            makeTransfer(500);
                        } catch (Exception e) {
                            // Rollback if necessary
                            balance += 500;
                            break;
                        }
                    }
                }
            }
            // Cleanup code here
            cleanup();
        });
        
        transferThread.start();
        
        // GOOD: Safe shutdown
        try {
            Thread.sleep(1000);
            running = false;
            transferThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private void makeTransfer(int amount) {
        // Transfer logic
    }
    
    private void cleanup() {
        // Cleanup resources
    }
}

// Example 4: Safe Alternative to suspend()/resume()
class SafeSuspendResumeAlternative {
    private volatile boolean suspended = false;
    private final Object lock = new Object();
    
    class SafeThread extends Thread {
        @Override
        public void run() {
            while (!isInterrupted()) {
                synchronized(lock) {
                    while (suspended) {
                        try {
                            // GOOD: Using wait() instead of suspend()
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }
                // Do work here
                doWork();
            }
        }
        
        private void doWork() {
            // Work logic
        }
    }
    
    // GOOD: Safe pause method
    public void pauseThread() {
        suspended = true;
    }
    
    // GOOD: Safe resume method
    public void resumeThread() {
        synchronized(lock) {
            suspended = false;
            lock.notifyAll(); // Wake up waiting thread
        }
    }
}

/*
4. Interview Questions about Deprecated Thread Methods:

Q1: What are the risks of using Thread.stop()?
A1: - Releases all locks immediately
    - Can leave shared data in inconsistent state
    - No cleanup chance
    - Can corrupt object state
    - Can break invariants in synchronized blocks

Q2: Why is suspend() dangerous?
A2: - Holds locks while suspended
    - Can cause deadlocks
    - No way to query suspension state
    - Thread state becomes unreliable

Q3: What are the proper alternatives?
A3: - Use volatile boolean flags
    - Use interruption mechanism
    - Use wait()/notify()
    - Use modern concurrency utilities
    - Use ExecutorService shutdown

Q4: How to safely stop a thread?
A4: - Use volatile boolean flag
    - Use interruption
    - Implement cleanup handlers
    - Release resources properly
*/

// Example 5: Modern Concurrency Alternative
class ModernConcurrencyExample {
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    
    public void modernThreadManagement() {
        Future<?> task = executor.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                // Do work
            }
        });
        
        // Modern way to cancel task
        task.cancel(true);
        
        // Modern way to shutdown
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}