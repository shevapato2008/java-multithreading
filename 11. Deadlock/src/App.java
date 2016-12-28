public class App {
	public static void main(String[] args) throws Exception {
		// final Runner_DeadLock runner = new Runner_DeadLock();
		// final Runner1 runner = new Runner1();
		final Runner2 runner = new Runner2();
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					runner.firstThread();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					runner.secondThread();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		runner.finished();
	}
}
