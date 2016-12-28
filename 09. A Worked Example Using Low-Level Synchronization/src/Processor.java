import java.util.LinkedList;
import java.util.Random;

public class Processor {
	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 10;				// this sets the size of the list
	private Object lock = new Object();
	
	public void produce() throws InterruptedException {
		int value = 0;
		while (true) {
			synchronized (lock) {
				while(list.size() == LIMIT) {	// keep checking if list is full
					lock.wait();				// produce() waits for consume() when the list is full
				}
				list.add(value++);
				lock.notify();					// after adding an item, notify consume()
			}

		}
	}
	
	public void consume() throws InterruptedException {
		Random random = new Random();
		while (true) {
			synchronized (lock) {
				while (list.size() == 0) {		// keep checking if list is empty
					lock.wait();				// consume() waits for produce() when the list is empty 
				}
				System.out.print("List size is: " + list.size());
				int value = list.removeFirst();
				System.out.println("; value is: " + value);
				lock.notify();					// after removing an item, notify produce()
			}
			Thread.sleep(random.nextInt(1000));	// let it sleep for a random time less than 1 second
												// this makes adding items faster than removing items
		}
	}
}
