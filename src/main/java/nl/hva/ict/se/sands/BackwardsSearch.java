package nl.hva.ict.se.sands;

/*
* Original Boyer Moore algorithm taken from BoyerMoore.java in Algorithms 4th edition.
 */
public class BackwardsSearch {

	private final int R;     // the radix
	private int[] right;     // the bad-character skip array
	private int[] left;     // the bad-character skip array
	private String pat;      // the pattern string
	private boolean isOriginal; // true wanneer je het originele algoritme wil gebruiken.
	private int comparisonCount = 0; // amount of comparisons made during the algorithm

	/**
	 * Lege constructor want anders geeft de test unit een error (negeer het maar voor nu)
	 */
	BackwardsSearch() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	/**
	 * Preprocesses the pattern string.
	 *
	 * @param pat the pattern string
	 * @param isOriginal check if the original BoyerMoore code must be used
	 */
	public BackwardsSearch(String pat, boolean isOriginal) {
		this.R = 256;
		this.pat = pat;
		
		if (isOriginal) {
			// position of rightmost occurrence of c in the pattern
			right = new int[R];
			for (int c = 0; c < R; c++) {
				right[c] = -1;
			}
			for (int j = 0; j < pat.length(); j++) {
				right[pat.charAt(j)] = j;
			}	
		} else {
			// position of leftmost occurrence of c in the pattern
			left = new int[R];
			for (int c = 0; c < R; c++) {
				left[c] = pat.length();
			}
			for (int j = pat.length() - 1; j >= 0; j--) {
				left[pat.charAt(j)] = j;
			}
		}
	}

	/**
	 * Returns index of the right most location where <code>needle</code> occurs within <code>haystack</code>. Searching
	 * starts at the right end side of the text (<code>haystack</code>) and proceeds to the first character (left side).
	 * @param needle the text to search for.
	 * @param haystack the text which might contain the <code>needle</code>.
	 * @return -1 if the <code>needle</code> is not found and otherwise the left most index of the first
	 * character of the <code>needle</code>.
	 */
	/**
	 * Dit is het originele algoritme (behalve comparisonCount)
	 * Wat er nou precies gebeurt:
	 * checkt op bepaalde index of (deel van) patroon te vinden is.
	 * wanneer er uberhaupt niks van het patroon gevonden is, ga dan needle.length() indexen verder.
	 * wanneer er een deel van de match gevonden is, ga dan een kleiner stapje verder.
	 */
	int findLocationOriginal(String needle, String haystack) {
		// reset comparison count to 0 in case there was a previous comparison
		comparisonCount = 0;
		// get lengths of both needle and haystack
		int needleLength = needle.length();
		int haystackLength = haystack.length();

		// instantiate step skip 0
		int skip;

		// walk through haystack incremented by 'skip' amount
		for (int i = 0; i <= haystackLength - needleLength; i += skip) {
			// reset skip to 0 after every full loop
			skip = 0;

			// walk through needle starting from the end and compare to haystack
			for (int j = needleLength - 1; j >= 0; j--) {
				System.out.println("Comparing " + needle.charAt(j) + " to " + haystack.charAt(i + j));

				// the loop will now compare needle and haystack, so add 1 to comparisonCount
				comparisonCount++;

				// check if last char of needle is not at haystack positions
				if (needle.charAt(j) != haystack.charAt(i + j)) {
					// set skip and break out of loop to move to the next haystack position
					skip = Math.max(1, j - right[haystack.charAt(i + j)]);
					System.out.println("Set skip to " + skip);
					break;
				}
				// go back to the top and check if (last char - 1) is not at haystack position
			}

			// since skip gets reset to 0 will remain 0 if all chars are found in the correct order in the haystack,
			// return occurence index
			if (skip == 0) {
				return i;
			}
		}

		// return -1 if needle has not been found in the haystack
		return -1;
	}

	/**
	 * Deze doet het volgens hoe de opdracht het uitgelegd heeft.
	 * @param needle
	 * @param haystack
	 * @return
	 */
	int findLocation(String needle, String haystack) {
		// reset comparison count to 0 in case there was a previous comparison
		comparisonCount = 0;
		// get lengths of both needle and haystack
		int needleLength = needle.length();
		int haystackLength = haystack.length();

		// instantiate step skip 0
		int skip;

		// walk through haystack backwards incremented by 'skip' amount
		for (int i = haystackLength - needleLength; i >= 0; i -= skip) {
			// reset skip to 0 after every full loop
			skip = 0;
			// walk through needle starting from the end and compare to haystack
			for (int j = 0; j <= needleLength - 1; j++) {
				System.out.println("Comparing " + needle.charAt(j) + " to " + haystack.charAt(i + j));

				// the loop will now compare needle and haystack, so add 1 to comparisonCount
				comparisonCount++;

				// check if first char of needle is not at haystack position
				if (needle.charAt(j) != haystack.charAt(i + j)) {
					// set skip and break out of loop to move to the next haystack position
					skip = Math.min(needleLength, left[haystack.charAt(i + j)]);
					System.out.println("Set skip to " + skip);
					break;
				}
				// go back to the top and check if (first char + 1) is not at haystack position
			}

			// since skip gets reset to 0 will remain 0 if all chars are found in the correct order in the haystack,
			// return occurence index
			if (skip == 0) {
				return i;
			}
		}

		// return -1 if needle has not been found in the haystack
		return -1;
	}

	/**
	 * Returns the number of character compared during the last search.
	 *
	 * @return the number of character comparisons during the last search.
	 */
	int getComparisonsForLastSearch() {
		return comparisonCount;
	}

}
