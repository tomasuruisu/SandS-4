package nl.hva.ict.se.sands;

/**
* Original Boyer Moore algorithm taken from BoyerMoore.java in Algorithms 4th edition.
* Modified by Thomas and Donovan
*/
public class BackwardsSearch {

	private final int R;     // the radix
	private int[] left;     // the bad-character skip array
	private String pat;      // the pattern string
	private int comparisonCount = 0; // amount of comparisons made during the algorithm

	/**
	 * Preprocesses the pattern string.
	 * @param pat the pattern string
	 */
	public BackwardsSearch(String pat) {
		this.R = 256;
		this.pat = pat;

		// initialize the 'left' array
		left = new int[R];
		for (int c = 0; c < R; c++) {
			left[c] = pat.length();
		}
		for (int j = pat.length() - 1; j >= 0; j--) {
			left[pat.charAt(j)] = j;
		}
	}

	/**
	 * find the location of the needle in the given haystack
	 * @param haystack text to search in
	 * @return index of the first character of the needle in the haystack
	 */
	int findLocation(String haystack) {
		// reset comparison count to 0 in case there was a previous comparison
		comparisonCount = 0;
		// get lengths of both needle and haystack
		int needleLength = this.pat.length();
		int haystackLength = haystack.length();

		// instantiate step skip
		int skip;

		// walk through haystack backwards incremented by 'skip' amount
		for (int i = haystackLength - needleLength; i >= 0; i -= skip) {
			// reset skip to 0 after every full loop
			skip = 0;
			// walk through needle starting from the end and compare to haystack
			for (int j = 0; j <= needleLength - 1; j++) {
				
				// the loop will now compare needle and haystack, so add 1 to comparisonCount
				comparisonCount++;

				// check if first char of needle is not at haystack position
				if (this.pat.charAt(j) != haystack.charAt(i + j)) {
					// set skip and break out of loop to move to the next haystack position
					skip = Math.min(needleLength, left[haystack.charAt(i + j)]);
					break;
				}
				// go back to the top and check if (first char + 1) is not at haystack position
			}

			// since skip gets reset to 0, it will remain 0 if all chars are found in the correct order in the haystack,
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
