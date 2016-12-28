public class App {
	
	private int count = 0;
	
	public static void main(String[] args) {
		App app = new App();
		app.doWork();
	}
	
	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					count++;
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					count++;
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Count is: " + count);
	}
	
}

/* 
 * Expected Output:
 * 20000
 * Actual Outputs:
 * 11507
 * 10533
 * 10442
 */

/**
 * The join method allows one thread to wait for the completion of another.
 * If t is a Thread object whose thread is currently executing,
 * 		t.join();
 * causes the current thread to pause execution until t's thread terminates.
 */

