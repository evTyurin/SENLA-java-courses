public class Main {

    public static void main(String[] args) {

        ThreadDemo thread = new ThreadDemo();

        Thread thread1 = new Thread(thread);
        Thread thread2 = new Thread(thread);

        thread1.start();
        thread2.start();
    }
}
