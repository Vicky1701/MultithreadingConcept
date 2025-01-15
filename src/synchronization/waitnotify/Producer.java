package synchronization.waitnotify;

//Producer thread that generates data
class Producer implements Runnable {
	private SharedBuffer buffer;

	public Producer(SharedBuffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 5; i++) {
			buffer.produce(i);
			try {
				Thread.sleep(1000); // Simulate some processing time
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
