/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.ict.se.sands;

import com.google.common.base.Stopwatch;

import extra.BoyerMoore;

/**
 *
 * @author Thomas & Donovan
 */
public class Main {

	public static void main(String[] args) {

		String pattern = "einah";
		String text = "needleinahaystackneedleinahaystackneedleinahaystackneedleinahaystackneedleinahaystack";
		int offset;

		System.out.println("----- ORIGINAL ALGORITHM -----");

		Stopwatch stopwatch = Stopwatch.createStarted();

		BoyerMoore boyermoore = new BoyerMoore(pattern);
		offset = boyermoore.search(text);

		// print results
		System.out.println("text:    " + text);

		System.out.print("pattern: ");
		for (int i = 0; i < offset; i++) {
			System.out.print(" ");
		}
		System.out.println(pattern);

		if (offset != -1) {
			System.out.println("first occurence at index " + offset);
		} else {
			System.out.println("pattern not found: " + offset);
		}
		
		System.out.println("----- REVERSE ALGORITHM -----");

		BackwardsSearch boyermooreReverse = new BackwardsSearch(pattern);
		offset = boyermooreReverse.findLocation(text);

		// print results
		System.out.println("text:    " + text);

		System.out.print("pattern: ");
		for (int i = 0; i < offset; i++) {
			System.out.print(" ");
		}
		System.out.println(pattern);

		if (offset != -1) {
			System.out.println("first occurence at index " + offset);
		} else {
			System.out.println("pattern not found: " + offset);
		}

		System.out.println("Amount of comparisons: " + boyermooreReverse.getComparisonsForLastSearch());
	}

}
