import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		regexPerf("book.txt", "Voltaire");
		tachyonPerf("book.txt", "Voltaire");
	}

	public static void regexPerf(String file, String p) {
		BufferedReader br = null;

		Pattern pat = Pattern.compile(p);
		Matcher mat;

		try {
			String sCurrentLine;

			br = new BufferedReader(new FileReader(file));

			while ((sCurrentLine = br.readLine()) != null) {
				String[] words = sCurrentLine.split(" ");

				for (String word : words) {
					mat = pat.matcher(word);
					if (mat.matches()) {
						System.out.println(word);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void tachyonPerf(String file, String p) {
		BufferedReader br = null;
		Tachyon t = new Tachyon(p);
		
		try {
			String sCurrentLine;

			br = new BufferedReader(new FileReader(file));

			while ((sCurrentLine = br.readLine()) != null) {
				String[] words = sCurrentLine.split(" ");

				for (String word : words) {
					if (t.match(word)) {
						System.out.println(word);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void fail(int i) {
		System.out.println("Failed test " + i + ".");
	}
}
