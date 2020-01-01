package nl.hva.ict.se.sands;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Collectors;

import com.google.common.base.Stopwatch;

import extra.BoyerMoore;

public class BackwardsSearchTest {
    protected BackwardsSearch searchEngine;
	protected BoyerMoore searchEngineOriginal;

    @Before
    public void setup() {
        searchEngine = new BackwardsSearch("needle");
		searchEngineOriginal = new BoyerMoore("needle");
    }

    @Test
    public void findSingleOccurrence() {
        int index = searchEngine.findLocation("whereistheneedleinthishaystack");

        assertEquals("whereisthe".length(), index);
    }

    @Test
    public void cantFindOccurrence() {
        int index = searchEngine.findLocation("thereisnothinginthishaystack");

        assertEquals(-1, index);
    }

    @Test
    public void simpleCharacterCount() {
        searchEngine.findLocation("whereistheneedle");

        assertEquals(6, searchEngine.getComparisonsForLastSearch());
    }

	@Test
	public void compareBackwardsToOriginal() throws FileNotFoundException {
		// open text file containing large body of text
		BufferedReader rd
		 = new BufferedReader(new FileReader("src/main/resources/asyoulik.txt"));
		String message = rd.lines().collect(Collectors.joining());

		// split string in two halves and add "needle" in the middle, then join all together again.
		String firstHalf = message.substring(0, message.length() / 2);
		String secondHalf = message.substring(message.length() / 2);
		message = firstHalf + "needle" + secondHalf;

		// First do the reverse search
		Stopwatch stopwatch = Stopwatch.createStarted();
		int index1 = searchEngine.findLocation(message);
		stopwatch.stop();

		long reverseSearchTimeElapsed = stopwatch.elapsed(MILLISECONDS);

		// reset stopwatch to 0
		stopwatch.reset();

		// Use the original BoyerMoore algorithm
		stopwatch = Stopwatch.createStarted();
		int index2 = searchEngineOriginal.search(message);
		stopwatch.stop();

		long boyermooreSearchTimeElapsed = stopwatch.elapsed(MILLISECONDS);

		// both algorithms search for needle which isn't in the text file
		System.out.println("--- both algorithms search for 'needle' ---");
		System.out.print("Reverse search vs. Original search: ");
		System.out.print(reverseSearchTimeElapsed + "ms");
		System.out.println(" < " + boyermooreSearchTimeElapsed + "ms");

		// true if boyermoore took longer than reverse
		assertTrue(reverseSearchTimeElapsed < boyermooreSearchTimeElapsed);
	}


	@Test
	public void compareBackwardsToOriginalNoNeedle() throws FileNotFoundException {
		// open text file containing large body of text
		BufferedReader rd
		 = new BufferedReader(new FileReader("src/main/resources/asyoulik.txt"));
		String message = rd.lines().collect(Collectors.joining());

		// First do the reverse search
		Stopwatch stopwatch = Stopwatch.createStarted();
		int index1 = searchEngine.findLocation(message);
		stopwatch.stop();

		long reverseSearchTimeElapsed = stopwatch.elapsed(MILLISECONDS);

		// reset stopwatch to 0
		stopwatch.reset();

		// Use the original BoyerMoore algorithm
		stopwatch = Stopwatch.createStarted();
		int index2 = searchEngineOriginal.search(message);
		stopwatch.stop();

		long boyermooreSearchTimeElapsed = stopwatch.elapsed(MILLISECONDS);

		// both algorithms search for needle which isn't in the text file
		System.out.println("--- both algorithms search for needle which isn't in the text file ---");
		System.out.print("Reverse search vs. Original search (no needle): ");
		System.out.print(reverseSearchTimeElapsed + "ms");
		System.out.println(" < " + boyermooreSearchTimeElapsed + "ms");

		// true if boyermoore took longer than reverse
		assertTrue(reverseSearchTimeElapsed < boyermooreSearchTimeElapsed);
	}

	@Test
	public void compareBackwardsToOriginalComparisons() throws FileNotFoundException {
		// open text file containing large body of text
		BufferedReader rd
		 = new BufferedReader(new FileReader("src/main/resources/asyoulik.txt"));
		String message = rd.lines().collect(Collectors.joining());

		// First do the reverse search
		int index1 = searchEngine.findLocation(message);

		// Use the original BoyerMoore algorithm
		int index2 = searchEngineOriginal.search(message);

		// both algorithms search for a needle and compare the amount of comparisons
		System.out.print("Reverse search Comparisons vs. Original search Comparisons: ");
		System.out.print(searchEngine.getComparisonsForLastSearch());
		System.out.println(" < " + searchEngineOriginal.getComparisonsForLastSearch());

		// true if boyermoore comparisons are more than the reverse
		assertTrue(searchEngine.getComparisonsForLastSearch() < searchEngineOriginal.getComparisonsForLastSearch());
	}
}