import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable{

    private BlockingQueue<Integer> buffer;

    public Producer(BlockingQueue<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            if (buffer.size() == 0) {
                for (int i = buffer.remainingCapacity(); i > 0; i--) {
                    int randomValue = ThreadLocalRandom.current().nextInt();
                    System.out.println("Producer produced - " + randomValue);
                    try {
                        buffer.put(randomValue);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }
    }
}
