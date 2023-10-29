import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    private BlockingQueue<Integer> buffer;

    public Consumer(BlockingQueue<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            if (buffer.remainingCapacity() == 0) {
                for (int i = buffer.size(); i > 0; i--) {
                    try {
                        System.out.println("Consumer consumed - " + buffer.take());
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }
    }
}
