public class App {
	public static void main(String[] args) {
		new Worker1().main();
		new Worker2().main();
	}
}

/*
 * Output:
 * Starting Worker1 ...
 * Time take: 5230
 * List1: 2000; List2: 2000
 * Starting Worker2 ...
 * Time take: 2651
 * List1: 2000; List2: 2000 
 */
