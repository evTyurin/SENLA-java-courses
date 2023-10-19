public class ThreadDemo implements Runnable{

    @Override
    public void run() {

        while (true) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}