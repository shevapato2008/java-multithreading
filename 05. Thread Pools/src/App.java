import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {
	
	private int id;
	
	public Processor(int id) {
		this.id = id;
	}
	
	public void run() {
		System.out.println("Starting: " + id);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Completed: " + id);
	}
}

public class App {
	
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(2);		// 2 threads to be employed
																		// recycling the thread pool
		for (int i = 0; i < 5; i++) {									// 5 tasks to be finished
			executor.submit(new Processor(i));
		}
		
		executor.shutdown();
		
		System.out.println("All tasks submitted.");
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All tasks completed.");
	}
	
}

// http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/
