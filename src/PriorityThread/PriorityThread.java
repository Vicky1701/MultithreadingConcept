package PriorityThread;

class PriorityThread extends Thread {
    public PriorityThread(String name) {
        super(name); // Setting thread name
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + 
                           " is running with priority " + 
                           Thread.currentThread().getPriority());
    }
}

