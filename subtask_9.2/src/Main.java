import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(0);

        ThreadDemo threadDemo1 = new ThreadDemo(semaphore1, semaphore2);
        ThreadDemo threadDemo2 = new ThreadDemo(semaphore2, semaphore1);

        Thread thread1 = new Thread(threadDemo1);
        Thread thread2 = new Thread(threadDemo2);

        thread1.start();
        thread2.start();
    }
}
