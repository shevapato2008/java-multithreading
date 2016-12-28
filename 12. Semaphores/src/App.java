import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
	public static void main(String[] args) throws Exception {
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for (int i = 0; i < 20; i++) {
			executor.submit(new Runnable() {
				public void run() {
					Connection.getInstance().connect();
				}
			});
		}
		executor.shutdown();							// Shut down the thread managerial service
														// after all threads finish their jobs.
		executor.awaitTermination(1, TimeUnit.DAYS);	// Wait until they finish.
	}
}