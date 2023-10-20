import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SystemTimeScheduler implements Runnable{

    private ScheduledExecutorService executorService;

    public SystemTimeScheduler(int secondsAmount) {
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this,0, secondsAmount, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        System.out.println("System time - " + LocalTime.now());
    }
}