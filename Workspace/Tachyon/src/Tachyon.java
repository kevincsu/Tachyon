import java.util.ArrayList;

/**
 * Optimized String class for efficient searching.
 * 
 * @author ksu, akilbo, schothani
 * 
 */
public class Tachyon {
	// Pattern that this Tachyon represents
	private String p;

	public Tachyon(String p) {
		this.p = p;
	}

	/*
	 * Public methods.
	 */

	/**
	 * Searches for the pattern in a text.
	 * 
	 * @param t
	 *            Text to search.
	 * @return Indices of all occurrences.
	 */
	public ArrayList<Integer> search(String t) {
		return null;
	}

	/**
	 * Tries to match the pattern to the text.
	 * 
	 * @param t
	 *            Text to match against.
	 * @return Result of match.
	 */
	public boolean match(String t) {
		return false;
	}

	/*
	 * Private methods. These should be helper functions/routines. They're left
	 * as public for unit tests/visibility purposes.
	 */

	/**
	 * Implementation of shift-and.
	 * 
	 * @param t
	 *            String to search.
	 * @return Indices of all occurrences.
	 */
	public ArrayList<Integer> shiftAnd(String t) {
		return null;
	}

	/**
	 * Shift-and with wildcard support "."
	 * 
	 * @param t
	 *            String to search.
	 * @return All occurrences of the pattern.
	 */
	public ArrayList<Integer> shiftAndWild(String t) {
		return null;
	}

	/**
	 * Gives the string representation of the Tachyon.
	 * 
	 * @return The string representation.
	 */
	public String toString() {
		return p;
	}
}