package ThreadBasic;

public class ThreadCreationDemo {
	
	// Method 1: Extending Thread class
    static class MyThread extends Thread {
        @Override
        public void run() {
            // Thread execution code
            System.out.println("Thread running using Thread class: " +
                    Thread.currentThread().getName());
        }
    }
    
    
    
 // Method 2: Implementing Runnable interface
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            // Thread execution code
            System.out.println("Thread running using Runnable: " +
                    Thread.currentThread().getName());
        }
    }

	public static void main(String[] args) {
		System.out.println("=== Thread Creation Demo ===");

        // Create and start thread using Thread class
		
		 System.out.println("Thread running using Thread class: " +
                 Thread.currentThread().getName());

        Thread thread1 = new MyThread(); //While Creating thread using Extend method we don't need pass parameter in object
        thread1.start();

        // Create and start thread using Runnable interface
        Thread thread2 = new Thread(new MyRunnable());  // we need to pass Runnable Object
        thread2.start();

        // Create and start thread using lambda expression
        Thread thread3 = new Thread(() -> {
            System.out.println("Thread running using Lambda: " +
                    Thread.currentThread().getName());
        });
        thread3.start();
    }
			

}

