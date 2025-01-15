package DeprecatedMethod.Stop.Suspend.Resume;

class DeprecatedMethodsExample extends Thread {
    private int counter = 0;
    
    public void run() {
        while (true) {
            System.out.println("Counter: " + counter++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
    }

    public static void main(String[] args) {
        // Example 1: stop() method
        DeprecatedMethodsExample thread1 = new DeprecatedMethodsExample();
        thread1.start();
        
        try {
            Thread.sleep(3000);
            // WARNING: stop() is dangerous and deprecated!
            thread1.stop();  // This will forcefully kill the thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Example 2: suspend() and resume() methods
        DeprecatedMethodsExample thread2 = new DeprecatedMethodsExample();
        thread2.start();
        
        try {
            Thread.sleep(2000);
            // WARNING: suspend() is dangerous and deprecated!
            thread2.suspend();  // This will freeze the thread
            
            System.out.println("Thread is suspended for 3 seconds...");
            Thread.sleep(3000);
            
            // WARNING: resume() is dangerous and deprecated!
            thread2.resume();  // This will unfreeze the thread
            
            Thread.sleep(2000);
            thread2.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

