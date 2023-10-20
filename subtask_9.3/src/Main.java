public class Main {

    public static void main(String[] args) {

        DataBuffer dataBuffer = new DataBuffer(2);

        Thread producer = new Thread(() -> {
            {
                try {
                    dataBuffer.produce();
                }
                catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            {
                try {
                    dataBuffer.consume();
                }
                catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();
    }
}