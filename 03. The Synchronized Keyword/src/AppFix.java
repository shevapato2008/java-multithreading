public class AppFix {
	
	private int count = 0;
	
	/* Modification is here. */
	public synchronized void increment() {			// This guarantees only one of thread t1 or
		count++;									// thread t2 can hold the lock for increment().
	}
	
	public static void main(String[] args) {
		AppFix app = new AppFix();
		app.doWork();
	}
	
	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment();					// Use Synchronized method increment().
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment();					// Use Synchronized method increment().
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
