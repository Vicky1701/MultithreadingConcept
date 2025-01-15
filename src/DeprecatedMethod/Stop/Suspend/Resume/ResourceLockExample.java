package DeprecatedMethod.Stop.Suspend.Resume;

//Example 3: Problematic scenario with suspend()
class ResourceLockExample extends Thread {
 private static final Object lock = new Object();
 
 public void run() {
     synchronized(lock) {
         System.out.println("Thread acquired the lock");
         // WARNING: If thread is suspended here, it will hold the lock indefinitely!
         try {
             Thread.sleep(1000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         System.out.println("Thread releasing the lock");
     }
 }

 public static void main(String[] args) {
     ResourceLockExample thread = new ResourceLockExample();
     thread.start();
     
     try {
         Thread.sleep(500);
         // WARNING: This can cause deadlock!
         thread.suspend();  // Thread still holds the lock while suspended
         
         System.out.println("Trying to acquire lock...");
         synchronized(lock) {
             // This will never execute because suspended thread holds the lock
             System.out.println("Acquired lock (this won't print)");
         }
     } catch (InterruptedException e) {
         e.printStackTrace();
     }
 }
}
