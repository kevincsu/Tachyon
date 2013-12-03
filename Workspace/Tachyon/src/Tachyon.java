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
	private String t;

	// Recursive variables
	String[] pieces;
	int[][] indices;

	// For matching
	boolean startAnchored;
	boolean endAnchored;

	public Tachyon(String p) {
		this.p = p;

		String[] tempPieces = p.split("\\.\\*");
		ArrayList<String> newPieces = new ArrayList<String>();
		for (String tempPiece : tempPieces) {
			if (!tempPiece.equals("")) {
				newPieces.add(tempPiece);
			}
		}
		pieces = new String[newPieces.size()];
		for (int i = 0; i < newPieces.size(); i++) {
			pieces[i] = newPieces.get(i);
		}

		startAnchored = true;
		endAnchored = true;
		
		if (p.length() > 1) {
			startAnchored = !(p.charAt(0) == '.' && p.charAt(1) == '*');
			endAnchored = !(p.charAt(p.length() - 2) == '.' && p.charAt(p
					.length() - 1) == '*');
		}
	}

	/*
	 * Public methods.
	 */

	/**
	 * Searches for the pattern in a text.
	 * 
	 * @param t
	 *            Text to search.
	 * @return First index of occurrence.
	 */
	public int search(String t) {
		this.t = t;

		if (pieces.length < 1) {
			return 0;
		}

		indices = new int[pieces.length][0];

		// Populate all indices
		for (int i = 0; i < pieces.length; i++) {
			String piece = pieces[i];
			Tachyon pieceTachyon = new Tachyon(piece);
			ArrayList<Integer> pieceIndices = pieceTachyon.shiftAndWild(t);

			indices[i] = new int[pieceIndices.size()];
			for (int j = 0; j < pieceIndices.size(); j++) {
				indices[i][j] = pieceIndices.get(j);
			}
		}

		return recursiveSearch(0, 0);
	}

	public int recursiveSearch(int start, int pieceNumber) {
		if (pieceNumber > pieces.length - 1) {
			return 0;
		}

		int[] possibleStarts = indices[pieceNumber];

		for (int possibleStart : possibleStarts) {
			if (possibleStart < start) {
				continue;
			}

			// Try to match it from this index
			int newStart = possibleStart + pieces[pieceNumber].length();

			if (recursiveSearch(newStart, pieceNumber + 1) != -1) {
				return possibleStart;
			}
		}

		return -1;
	}

	/**
	 * Tries to match the pattern to the text.
	 * 
	 * @param t
	 *            Text to match against.
	 * @return Result of match.
	 */
	public boolean match(String t) {
		this.t = t;

		if (pieces.length < 1) {
			return true;
		}

		indices = new int[pieces.length][0];

		// Populate all indices
		for (int i = 0; i < pieces.length; i++) {
			String piece = pieces[i];
			Tachyon pieceTachyon = new Tachyon(piece);
			ArrayList<Integer> pieceIndices = pieceTachyon.shiftAndWild(t);

			indices[i] = new int[pieceIndices.size()];
			for (int j = 0; j < pieceIndices.size(); j++) {
				indices[i][j] = pieceIndices.get(j);
			}
		}

		return recursiveMatch(0, 0);
	}

	public boolean recursiveMatch(int start, int pieceNumber) {
		if (pieceNumber > pieces.length - 1) {
			return true;
		}

		int[] possibleStarts = indices[pieceNumber];

		for (int possibleStart : possibleStarts) {
			if (possibleStart < start) {
				continue;
			}

			// If start is anchored
			if (startAnchored && pieceNumber == 0) {
				if (possibleStart != 0) {
					continue;
				}
			}

			// Try to match it from this index
			int newStart = possibleStart + pieces[pieceNumber].length();

			if (endAnchored && pieceNumber == pieces.length - 1) {
				if (newStart != t.length()) {
					continue;
				}
			}

			if (recursiveMatch(newStart, pieceNumber + 1)) {
				return true;
			}
		}

		return false;
	}

	/*
	 * Private methods. These should be helper functions/routines. They're left
	 * as public for unit tests/visibility purposes.
	 */

	/**
	 * Shift-and with wildcard support "."
	 * 
	 * @param t
	 *            String to search.
	 * @return All occurrences of the pattern.
	 */
	public ArrayList<Integer> shiftAndWild(String t) {
		int plength = p.length();
		ArrayList<Integer> a = new ArrayList<Integer>();

		// If pattern is empty, don't bother checking
		if (plength == 0)
			return a;

		// Initialize array
		byte[] bitarray = new byte[plength + 1];
		bitarray[0] = 1;

		for (int i = 0; i < t.length(); i++) {
			for (int j = plength; j >= 1; j--) {
				byte b = 0;

				if (p.charAt(j - 1) == '.' || t.charAt(i) == p.charAt(j - 1)) {
					b = 1;
				}

				bitarray[j] = (byte) (bitarray[j - 1] & b);
			}

			if (bitarray[plength] != 0) {
				a.add(i - plength + 1);
			}
		}

		return a;
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