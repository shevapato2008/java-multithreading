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

/**
 * http://stackoverflow.com/questions/34519/what-is-a-semaphore
 * What is a semaphore?
 * Think of semaphores as bouncers at a nightclub. There are a dedicated number of people that are allowed
 * in the club at once. If the club is full no one is allowed to enter, but as soon as one person leaves another
 * person might enter. It's simply a way to limit the number of consumers for a specific resource. For example,
 * to limit the number of simultaneous calls to a database in an application.
 * 
 * http://stackoverflow.com/questions/771347/what-is-mutex-and-semaphore-in-java-what-is-the-main-difference
 * What is mutex and semaphore in Java? What is the main difference?
 * Mutex (or a semaphore initialized to 1; meaning there's only one resource) is basically a mutual exclusion;
 * Only one thread can acquire the resource at once, and all other threads trying to acquire the resource are
 * blocked until the thread owning the resource releases.
 * Semaphore is used to control the number of threads executing. There will be fixed set of resources. The
 * resource count will get decremented every time when a thread owns the resource. When the semaphore count
 * reaches 0 then no other threads are allowed to acquire the resource. The threads get blocked till other
 * threads owning resource releases.
 * 
 * In short, the main difference is how many threads are allowed to acquire the resource at once.
 */
