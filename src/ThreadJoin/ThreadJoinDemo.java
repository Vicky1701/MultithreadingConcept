package ThreadJoin;

/**
 * Demonstrates different scenarios of using Thread.join()
 */
public class ThreadJoinDemo {
    
    // Example 1: Basic join operation
    static class FileProcessor {
        public void processFile() {
            System.out.println("Starting file processing...");
            try {
                // Simulate file processing
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("File processing completed");
        }
    }

    // Example 2: Processing with dependent tasks
    static class DataProcessor {
        private boolean isDataLoaded = false;
        private boolean isDataProcessed = false;

        public void loadData() {
            try {
                System.out.println("Loading data...");
                Thread.sleep(2000);
                isDataLoaded = true;
                System.out.println("Data loaded successfully");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public void processData() {
            if (!isDataLoaded) {
                System.out.println("Cannot process: Data not loaded!");
                return;
            }
            try {
                System.out.println("Processing data...");
                Thread.sleep(1500);
                isDataProcessed = true;
                System.out.println("Data processing completed");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public void generateReport() {
            if (!isDataProcessed) {
                System.out.println("Cannot generate report: Data not processed!");
                return;
            }
            System.out.println("Generating report...");
            System.out.println("Report generated successfully");
        }
    }

    public static void main(String[] args) {
        System.out.println("\n=== Example 1: Basic Join ===");
        demonstrateBasicJoin();

        System.out.println("\n=== Example 2: Multiple Thread Join ===");
        demonstrateMultipleJoin();

        System.out.println("\n=== Example 3: Join with Timeout ===");
        demonstrateJoinWithTimeout();
    }

    private static void demonstrateBasicJoin() {
        FileProcessor processor = new FileProcessor();
        
        Thread processingThread = new Thread(() -> {
            processor.processFile();
        }, "ProcessingThread");

        System.out.println("Starting the processing thread");
        processingThread.start();

        try {
            System.out.println("Main thread waiting for processing to complete");
            processingThread.join();  // Main thread waits for processing to complete
            System.out.println("Main thread resumed after processing");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void demonstrateMultipleJoin() {
        DataProcessor processor = new DataProcessor();

        // Create threads for each task
        Thread loadThread = new Thread(() -> {
            processor.loadData();
        }, "LoadThread");

        Thread processThread = new Thread(() -> {
            processor.processData();
        }, "ProcessThread");

        Thread reportThread = new Thread(() -> {
            processor.generateReport();
        }, "ReportThread");

        // Start all threads
        loadThread.start();
        processThread.start();
        reportThread.start();

        try {
            // Wait for each thread in sequence
            System.out.println("Waiting for load thread to complete");
            loadThread.join();
            
            System.out.println("Waiting for process thread to complete");
            processThread.join();
            
            System.out.println("Waiting for report thread to complete");
            reportThread.join();
            
            System.out.println("All operations completed");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void demonstrateJoinWithTimeout() {
        Thread longRunningThread = new Thread(() -> {
            try {
                System.out.println("Long running task started");
                Thread.sleep(5000); // Simulate long running task
                System.out.println("Long running task completed");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Long running task interrupted");
            }
        }, "LongRunningThread");

        longRunningThread.start();

        try {
            System.out.println("Waiting for long running task with timeout");
            longRunningThread.join(2000); // Wait for only 2 seconds
            
            if (longRunningThread.isAlive()) {
                System.out.println("Timeout reached, long running task still executing");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


/**
 * Interview Questions on join():
 * 
 * 1. **What is the purpose of the join() method in Java?**
 *    - Answer: The `join()` method ensures that the current thread waits until the specified thread completes its execution.
 * 
 * 2. **Can join() cause a thread to block indefinitely?**
 *    - Answer: Yes, if the target thread never completes, the current thread will be blocked indefinitely unless interrupted.
 * 
 * 3. **What are the overloaded versions of join()?**
 *    - Answer: 
 *      - `void join()` - Waits indefinitely for the thread to complete.
 *      - `void join(long millis)` - Waits for the specified number of milliseconds.
 *      - `void join(long millis, int nanos)` - Waits for the specified time in milliseconds and nanoseconds.
 * 
 * 4. **How can you avoid indefinite blocking when using join()?**
 *    - Answer: Use the overloaded version with a timeout (e.g., `join(long millis)`).
 * 
 * 5. **What happens if join() is called on a thread that is already completed?**
 *    - Answer: The `join()` method will return immediately without blocking since the thread has already finished execution.
 * 
 * 6. **Can join() be interrupted? If yes, how?**
 *    - Answer: Yes, if the current thread is interrupted while waiting for another thread to complete, it throws `InterruptedException`.
 * 
 * 7. **What is the difference between join() and sleep()?**
 *    - Answer: 
 *      - `join()`: Waits for another thread to finish.
 *      - `sleep()`: Pauses the current thread for a specified time but does not depend on any other thread.
 * 
 * 8. **Can join() be called multiple times on the same thread?**
 *    - Answer: Yes, calling `join()` multiple times will not cause issues. If the thread has already completed, it will return immediately.
 * 
 * 9. **Why is join() important in multi-threaded programming?**
 *    - Answer: It helps synchronize threads by ensuring one thread completes before another continues, enabling predictable execution flow.
 * 
 * 10. **What happens if join() is called on the current thread itself?**
 *     - Answer: If a thread calls `join()` on itself, it will result in a `java.lang.IllegalThreadStateException`.
 */