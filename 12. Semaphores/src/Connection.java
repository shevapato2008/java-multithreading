import java.util.concurrent.Semaphore;

public class Connection {
	
	private static Connection instance = new Connection();
	private Semaphore sem = new Semaphore(10, true);
	/*
	 * Limit connections to 10. "true" means whichever thread gets first in the waiting pool (queue),
	 * waiting to acquire a resource, is the first to obtain the permit.
	 * 
	 * Note that I called it a pool! The reason is when you say "Queue", you're saying that things
	 * are scheduled to be FIFO.. which is not always the case here! When you initialize the semaphore
	 * with fairness, by setting its second argument to true, it will treat the waiting threads like
	 * FIFO. But it doesn't have to be that way if you don't set on the fairness. The JVM may schedule
	 * the waiting threads in some other manner that it sees best (See the Java specifications for that).
	 */
	private int connections = 0;
	
	private Connection() { }
	
	public static Connection getInstance() {
		return instance;
	}
	
	public void connect() {
		try {
			sem.acquire();			// acquire() decreases the number of semaphore permits.
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		try {
			doConnect();
		} finally {
			sem.release();			// release() increases the number of semaphore permits.
		}
	}
	
	public void doConnect() {
		synchronized (this) {
			connections++;
			System.out.println("Current connections (max 10 allowed): " + connections);
		}
		try {
			System.out.println("Working on connections " + Thread.currentThread().getName());
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (this) {
			connections--;
			System.out.println("I'm done " + Thread.currentThread().getName() +
					" Connection is released , connection count: " + connections);
		}
	}
}
