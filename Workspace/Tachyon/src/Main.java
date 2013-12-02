/**
 * Test cases.
 * 
 * @author ksu
 * 
 */
public class Main {
	public static void main(String args[]) {
		// Correctness Tests
		System.out.println("Running correctness tests...");
		runSearchTests();
		runMatchTests();
		
		System.out.println();

		// Performance Tests
		System.out.println("Running performance tests...");
		runPerfTests();
	}

	public static void runSearchTests() {
		Tachyon t1 = new Tachyon("abc");
		if (t1.search("this is abc here.") != 8) {
			fail(1);
		}
		
		
	}

	public static void runMatchTests() {

	}

	public static void runPerfTests() {

	}
	
	public static void fail(int i) {
		System.out.println("Failed test " + i + ".");
	}
}
