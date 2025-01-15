package DeprecatedMethod.DeadLock;

//Previous examples of deprecated methods remain...

//Comprehensive Deadlock Examples
class DeadlockScenarios {

	// Example 1: Classical Deadlock with Bank Accounts
	static class BankAccount {
		private int balance;
		private final int id;

		public BankAccount(int id, int balance) {
			this.id = id;
			this.balance = balance;
		}

		public void transfer(BankAccount to, int amount) {
			synchronized (this) {
				System.out.println("Lock acquired on account: " + this.id);
				try {
					Thread.sleep(100); // Increase chance of deadlock
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				synchronized (to) {
					this.balance -= amount;
					to.balance += amount;
				}
			}
		}
	}

	// Example 2: Dining Philosophers Problem (Classic Deadlock Scenario)
	static class DiningPhilosophers {
		private Object[] forks;
		private Thread[] philosophers;

		public DiningPhilosophers(int numPhilosophers) {
			forks = new Object[numPhilosophers];
			philosophers = new Thread[numPhilosophers];

			for (int i = 0; i < numPhilosophers; i++) {
				forks[i] = new Object();
			}

			for (int i = 0; i < numPhilosophers; i++) {
				final int philosopherId = i;
				philosophers[i] = new Thread(() -> dine(philosopherId));
			}
		}

		private void dine(int philosopherId) {
			Object leftFork = forks[philosopherId];
			Object rightFork = forks[(philosopherId + 1) % forks.length];

			while (true) {
				synchronized (leftFork) {
					System.out.println("Philosopher " + philosopherId + " picked up left fork");
					synchronized (rightFork) {
						System.out.println("Philosopher " + philosopherId + " picked up right fork and is eating");
					}
				}
			}
		}

		public void startDining() {
			for (Thread philosopher : philosophers) {
				philosopher.start();
			}
		}
	}

	// Example 3: Deadlock Detection and Prevention
	static class DeadlockDetection {
		private static final Object resource1 = new Object();
		private static final Object resource2 = new Object();
		private static final Object resource3 = new Object();

		// Method to detect potential deadlock using timeout
		public static void tryLockWithTimeout() {
			Thread thread1 = new Thread(() -> {
				try {
					synchronized (resource1) {
						Thread.sleep(100);
						synchronized (resource2) {
							System.out.println("Thread 1: Got both resources");
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});

			Thread thread2 = new Thread(() -> {
				try {
					synchronized (resource2) {
						Thread.sleep(100);
						synchronized (resource1) {
							System.out.println("Thread 2: Got both resources");
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});

			thread1.start();
			thread2.start();
		}

		// Deadlock Prevention using Resource Ordering
		public static void preventDeadlockWithOrdering() {
			Thread thread1 = new Thread(() -> {
				synchronized (resource1) {
					synchronized (resource2) {
						synchronized (resource3) {
							System.out.println("Thread 1: Got all resources in order");
						}
					}
				}
			});

			Thread thread2 = new Thread(() -> {
				synchronized (resource1) {
					synchronized (resource2) {
						synchronized (resource3) {
							System.out.println("Thread 2: Got all resources in order");
						}
					}
				}
			});

			thread1.start();
			thread2.start();
		}
	}

	public static void main(String[] args) {
		// Demonstrate Bank Account Deadlock
		BankAccount account1 = new BankAccount(1, 1000);
		BankAccount account2 = new BankAccount(2, 1000);

		new Thread(() -> account1.transfer(account2, 500)).start();
		new Thread(() -> account2.transfer(account1, 300)).start();

		// Demonstrate Dining Philosophers Deadlock
		DiningPhilosophers philosophers = new DiningPhilosophers(5);
		philosophers.startDining();

		// Demonstrate Deadlock Detection and Prevention
		DeadlockDetection.tryLockWithTimeout();
		DeadlockDetection.preventDeadlockWithOrdering();
	}
}
