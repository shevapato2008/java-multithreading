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
			/* Try different methods of reporting execution exceptions. */
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
 *
 *
 *
 * http://stackoverflow.com/questions/14609778/actual-implementation-of-callable-and-future
 * 
 * Actual implementation of Callable and Future
 * 
 * The main implementation of the Future interface is the FutureTask class. It is used by the
 * ExecutorService classes to represent a submitted job, etc.. Callable (like Runnable) is a
 * simple interface that you implement yourself. It wraps a task that you want the
 * ExecutorService thread-pools to execute. You should download the source jars for these
 * classes and take a look at the Java code yourself.
 * 
 * Neither of these classes contain any JVM black magic or anything. For example, if you
 * construct a Callable class, it won't run in another thread unless you submit it to a
 * thread-pool. You can use the Callable in many different places that have nothing to do with
 * threads.
 * 
 * The JVM "black magic" around Future and Callable is mostly contained in the Thread class.
 * It has underlying native support which does the actual job of running your task in another
 * thread. There is still a lot of Java code in it if you want to see what it does but there
 * are native calls that the real magic.
 * 
 * Here's a good tutorial about how to use the executor services that were added to Java in 1.5.
 */


