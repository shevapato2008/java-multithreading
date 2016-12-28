import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
	private static int count;
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					count = 0;
					consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		System.out.println("Start..");
		
		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}
	
	private static void producer() throws InterruptedException {
		Random random = new Random();
		while (count <= 100) {
			queue.put(random.nextInt(100));		// Think about an example of sending text messages.
		}
	}
	
	private static void consumer() throws InterruptedException {
		Random random = new Random();
		while (count <= 10) {
			Thread.sleep(10);
			if (random.nextInt(10) == 0) {		// only 1 / 10 chance to execute take()
				Integer value = queue.take();	// take() will wait to take a value if no value exists in the queue.
				System.out.println("Taken value: " + value + 
						"; Queue size is: " + queue.size() +
						"; Count = " + count);
				count++;
			}
		}
	}
}

// https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/BlockingQueue.html
