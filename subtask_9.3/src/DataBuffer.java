import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class DataBuffer {

    private BlockingQueue<Integer> buffer;

    public DataBuffer(int capacity) {
        buffer = new ArrayBlockingQueue<>(capacity);
    }

    public void produce() throws InterruptedException
    {
        while (true) {
            int randomValue = ThreadLocalRandom.current().nextInt();
            System.out.println("Producer produced - " + randomValue);
            buffer.put(randomValue);
        }
    }

    public void consume() throws InterruptedException
    {
        while (true) {
            System.out.println("Consumer consumed - " + buffer.take());
        }
    }
}