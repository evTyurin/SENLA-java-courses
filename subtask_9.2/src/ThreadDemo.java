import java.util.concurrent.Semaphore;

public class ThreadDemo implements Runnable{

    private Semaphore semaphore1;
    private Semaphore semaphore2;

    public ThreadDemo(Semaphore semaphore1, Semaphore semaphore2) {
        this.semaphore1 = semaphore1;
        this.semaphore2 = semaphore2;
    }

    @Override
    public void run() {
        while (true) {
            try {
                semaphore1.acquire();
                System.out.println(Thread.currentThread().getName());
                semaphore2.release();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}