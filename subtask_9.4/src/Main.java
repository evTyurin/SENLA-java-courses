public class Main {

    public static void main(String[] args) throws InterruptedException {

        ThreadDemo threadDemo = new ThreadDemo(1);

        Thread thread = new Thread(threadDemo);
        thread.setDaemon(true);
        thread.start();
        thread.join();
    }
}
