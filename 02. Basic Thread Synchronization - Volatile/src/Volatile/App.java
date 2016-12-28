package Volatile;

import java.util.Scanner;

class Processor extends Thread {
	
	private volatile boolean running = true;		// Keyword volatile
	
	@Override
	public void run() {
		System.out.println("Hello");
		while (running) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown() {
		running = false;
	}

}

public class App {
	private static Scanner sc;

	public static void main(String[] args) {
		Processor proc1 = new Processor();
		proc1.start();
		
		System.out.println("Press return to stop ...");
		sc = new Scanner(System.in);
		sc.nextLine();								// This pauses the execution of
													// the main program.
		proc1.shutdown();
	}
}

/**
 * The volatile modifier is used to let the JVM know that
 * a thread accessing the variable must always merge its own
 * private copy of the variable with the master copy in the memory.
 * 
 * Accessing a volatile variable synchronizes all the cached copies
 * of the variables in the main memory. Volatile can only be
 * applied to instance variables, which are of type object or private.
 * A volatile object reference can be null.
 */


