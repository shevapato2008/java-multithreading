import java.util.Scanner;

public class Processor {
	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("Producer thread running ...");
			wait();
			System.out.println("Resumed.");
		}
	}
	public void consume() throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);				// let produce run first
		synchronized (this) {
			System.out.println("Waiting for return key.");
			scanner.nextLine();
			System.out.println("Return key pressed.");
			notify();					// wake up the waiting thread(s)
			Thread.sleep(5000);			// wait 5 seconds to execute
		}
	}
}
