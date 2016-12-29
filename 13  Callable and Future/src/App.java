import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<Integer> future = executor.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Random random = new Random();
				int duration = random.nextInt(4000);
				
				// try to throw exception from call
				// IOException is a type of ExecutionException
				if (duration > 2000) {
					throw new IOException("Sleeping for too long.");
				}
				
				System.out.println("Starting ...");
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Finished.");
				return duration;
			}
		});
		
		executor.shutdown();
		
		try {
			System.out.println("Result is: " + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			// Option 1
			System.out.println(e);
			// Option 2
			System.out.println(e.getMessage());
			// Option 3
			IOException ex = (IOException) e.getCause();
			System.out.println(ex.getMessage());
		}
	}
}

/* 
 * If you don't want to specify the return type, you can do the following.
 * Future<?> future = executor.submit(new Callable<Void>() {
 *     public Void call() throws Exception {
 *         ...
 *         return null;
 *     }
 * });
 */
