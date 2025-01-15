package synchronization.waitnotify;

//Main class to demonstrate the producer-consumer pattern
public class ProducerConsumerDemo {
	public static void main(String[] args) {
		SharedBuffer buffer = new SharedBuffer();

		// Create producer and consumer threads
		Thread producerThread = new Thread(new Producer(buffer), "Producer");
		Thread consumerThread = new Thread(new Consumer(buffer), "Consumer");

		// Start both threads
		producerThread.start();
		consumerThread.start();
	}
}


/*
Interview Questions on Wait and Notify:

Q: What are wait() and notify() in Java?
A: wait() and notify() are methods from the Object class used for inter-thread communication.
   - wait(): Causes the current thread to release the lock and wait until notified.
   - notify(): Wakes up one thread waiting on the object's monitor.
   - notifyAll(): Wakes up all threads waiting on the object's monitor.

Q: What is the purpose of wait() and notify()?
A: They are used to coordinate the execution of threads, allowing threads to signal each other 
   when a condition is met (e.g., producer-consumer problem).

Q: Why are wait(), notify(), and notifyAll() in the Object class?
A: Every object in Java has an intrinsic monitor lock. These methods must operate on the 
   object's monitor, so they are defined in the Object class rather than Thread.

Q: What are the key rules for using wait() and notify()?
1. Must be called within a synchronized block/method.
2. Thread must hold the object's lock to call these methods.
3. IllegalMonitorStateException is thrown if the thread does not own the lock.

Q: How does wait() work internally?
A: When wait() is called:
   1. Thread releases the lock on the object.
   2. Thread enters the object's wait set.
   3. Thread waits until notified or interrupted.

Q: What is the difference between wait() and sleep()?
A:
   - wait(): Releases the lock, used for inter-thread communication.
   - sleep(): Does not release the lock, used for pausing execution.

Q: What happens if notify() is called when no thread is waiting?
A: If no thread is waiting, notify() has no effect. It does not queue notifications.

Q: Can you explain the producer-consumer problem?
A: The producer-consumer problem involves two threads:
   - Producer: Produces data and signals the consumer.
   - Consumer: Consumes data and signals the producer.
   wait() and notify() are used to coordinate data production and consumption.

Q: What happens if wait() is not in a loop?
A: If wait() is not in a loop, spurious wakeups or incorrect conditions might lead to bugs. 
   Always use wait() inside a while loop to recheck the condition.

Example:
while (!condition) {
    wait();
}

Q: How do wait() and notify() affect thread scheduling?
A: notify() moves a waiting thread to the runnable state. However, thread scheduling 
   is handled by the OS and JVM, so the notified thread may not run immediately.
*/