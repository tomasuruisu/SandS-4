/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.ict.se.sands;

import extra.BoyerMoore;

import java.util.HashMap;

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

		System.out.println("Amount of comparisons: " + boyermoore.getComparisonsForLastSearch());

		//////
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

		System.out.println("\n----- HUFFMAN COMPRESSION -----");

		String toCompress = "YES, we made it!";
		HuffmanCompression h = new HuffmanCompression(toCompress);
		HashMap<Character, Integer> map = h.charOccurrence();
		System.out.println("Occurrences of the characters in the text - blanks are spaces or tabs in the text");
		for (HashMap.Entry<Character, Integer> hm : map.entrySet()) {
			System.out.println(hm.getKey() + "\toccurence:\t" + hm.getValue());
		}
		HashMap<Character, String> map2 = h.codeTable;
		System.out.println("Codes of the characters in the text - blanks are spaces or tabs in the text");
		for (HashMap.Entry<Character, String> hm : map2.entrySet()) {
			System.out.println(hm.getKey() + "\tcode:\t" + hm.getValue());
		}
		System.out.println(toCompress + " compressed to " + h.compress());
	}

}
