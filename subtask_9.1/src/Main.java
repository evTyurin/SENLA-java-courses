public class Main {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            final Thread secondThread = Thread.currentThread();
            try {
                Thread.sleep(3000);
                synchronized (secondThread) {
                    secondThread.wait();
                }
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });

        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        try {
            int counter = 0;
            do {
                Thread.sleep(2000);
                System.out.println(thread.getState());
                counter++;
                if (counter == 2) {
                    synchronized (thread) {
                        Thread.sleep(1000);
                        thread.notify();
                        System.out.println(thread.getState());
                    }
                }
            } while (Thread.State.TERMINATED != thread.getState());
        } catch (InterruptedException exception) {
            System.out.println(thread.getState());
        }
    }
}