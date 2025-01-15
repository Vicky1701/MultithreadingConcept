package DaemonThread;

class DaemonThread extends Thread {
    public DaemonThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        if (Thread.currentThread().isDaemon()) {
            System.out.println(Thread.currentThread().getName() + " is a daemon thread.");
        } else {
            System.out.println(Thread.currentThread().getName() + " is a user thread.");
        }

        // Simulating task
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " executing task " + i);
            try {
                Thread.sleep(500); // 500ms delay
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
    }
}