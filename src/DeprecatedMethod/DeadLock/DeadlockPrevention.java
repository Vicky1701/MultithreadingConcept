package DeprecatedMethod.DeadLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

//Deadlock Prevention Utilities
class DeadlockPrevention {
// Using ReentrantLock with timeout
	static class TimeoutLocking {
		private static final ReentrantLock lock1 = new ReentrantLock();
		private static final ReentrantLock lock2 = new ReentrantLock();

		public static void performTask() {
			try {
				if (lock1.tryLock(1000, TimeUnit.MILLISECONDS)) {
					try {
						if (lock2.tryLock(1000, TimeUnit.MILLISECONDS)) {
							try {
								// Critical section
								System.out.println("Task completed successfully");
							} finally {
								lock2.unlock();
							}
						}
					} finally {
						lock1.unlock();
					}
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}