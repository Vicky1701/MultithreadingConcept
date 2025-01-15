package synchronization.waitnotify;

//Shared resource class that will be accessed by both producer and consumer
class SharedBuffer {
	private int data;
	private boolean hasData = false;

	// Method used by producer to add data
	public synchronized void produce(int value) {
		// While there is unprocessed data, wait
		while (hasData) {
			try {
				System.out.println("Producer waiting as buffer is full...");
				wait(); // Releases the lock and waits for notification
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		data = value;
		hasData = true;
		System.out.println("Produced: " + value);

		// Notify waiting consumer that data is available
		notify(); // Wakes up one waiting thread
	}

	// Method used by consumer to retrieve data
	public synchronized int consume() {
		// While there is no data to process, wait
		while (!hasData) {
			try {
				System.out.println("Consumer waiting as buffer is empty...");
				wait(); // Releases the lock and waits for notification
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		hasData = false;
		System.out.println("Consumed: " + data);

		// Notify waiting producer that buffer is empty
		notify(); // Wakes up one waiting thread
		return data;
	}
}
