import java.time.LocalTime;

public class ThreadDemo implements Runnable{

    private int secondsAmount;

    public ThreadDemo(int secondsAmount) {
        this.secondsAmount = secondsAmount;
    }

    @Override
    public void run() {

        while (true) {
            try {
                System.out.println("System time - " + LocalTime.now());
                Thread.sleep(1000 * secondsAmount);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}