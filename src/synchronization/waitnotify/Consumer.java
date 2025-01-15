package synchronization.waitnotify;

//Consumer thread that processes data
class Consumer implements Runnable {
	private SharedBuffer buffer;

	public Consumer(SharedBuffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 5; i++) {
			buffer.consume();
			try {
				Thread.sleep(1500); // Simulate some processing time
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
