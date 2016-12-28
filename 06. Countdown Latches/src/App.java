import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {
	
	private CountDownLatch latch;
	
	public Processor(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("Started.");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		latch.countDown();
	}
	
}

public class App {
	
	public static void main(String[] args) {
		
		CountDownLatch latch = new CountDownLatch(3);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for (int i = 0; i < 3; i++) {
			executor.submit(new Processor(latch));
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Completed.");
	}
	
}

// http://stackoverflow.com/questions/17827022/how-is-countdownlatch-used-in-java-multithreading
// http://stackoverflow.com/questions/12254704/what-is-the-difference-between-join-and-countdownlatch