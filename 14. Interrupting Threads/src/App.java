import java.util.Random;

public class App {
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Starting.");
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				Random rand = new Random();
				for (int i = 0; i < 1E8; i++) {
					/* 1st method to check if current thread is being interrupted */
					/*
					if (Thread.currentThread().isInterrupted()) {	// .
						System.out.println("Interrupted!");
						break;
					}
					*/
					/* 2nd method uses Thread.sleep() to throw interrupted exception. */
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						System.out.println("Interrupted!");
						break;
					}
					
					Math.sin(rand.nextDouble());
				}
			}
		});
		
		t1.start();
		Thread.sleep(500);
		t1.interrupt();			// Interrupt, NOT stop.
		t1.join();
		
		System.out.println("Finished.");
	}
	
}

/* 
 * More Readings:
 * 
 * http://stackoverflow.com/questions/3590000/what-does-java-lang-thread-interrupt-do
 * http://docs.oracle.com/javase/tutorial/essential/concurrency/interrupt.html
 */
