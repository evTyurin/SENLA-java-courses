import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class DataBuffer {

    private Queue<Integer> buffer;
    private int capacity;

    public DataBuffer(int capacity) {
        this.capacity = capacity;
        buffer = new LinkedList<>();
    }

    public void produce() throws InterruptedException
    {
        while (true) {
            synchronized (this)
            {
                while (buffer.size() == capacity) {
                    wait();
                }
                int randomValue = ThreadLocalRandom.current().nextInt();
                System.out.println("Producer produced - " + randomValue);
                buffer.add(randomValue);
                notify();
                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException
    {
        while (true) {
            synchronized (this)
            {
                while (buffer.size() == 0) {
                    wait();
                }
                System.out.println("Consumer consumed - " + buffer.poll());
                notify();
                Thread.sleep(500);
            }
        }
    }
}