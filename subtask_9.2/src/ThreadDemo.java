import java.util.concurrent.Semaphore;

public class ThreadDemo implements Runnable{

    private Semaphore semaphore;

    public ThreadDemo() {
        this.semaphore = new Semaphore(1);
    }

    @Override
    public void run() {
        while (true) {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName());
                semaphore.release();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}