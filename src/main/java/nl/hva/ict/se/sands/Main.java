/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.ict.se.sands;

/**
 *
 * @author Thomas & Donovan
 */
public class Main {

	public static void main(String[] args) {
		String pattern = "einah";
		String text = "needleinahaystackneedleinahaystackneedleinahaystackneedleinahaystackneedleinahaystack";
		boolean isOriginal = true;
		int offset;

		System.out.println("----- ORIGINAL ALGORITHM -----");

		BackwardsSearch boyermoore = new BackwardsSearch(pattern, isOriginal);
		offset = boyermoore.findLocationOriginal(pattern, text);

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

		isOriginal = false;
		BackwardsSearch boyermooreReverse = new BackwardsSearch(pattern, isOriginal);
		offset = boyermooreReverse.findLocation(pattern, text);

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
		HuffmanCompression h = new HuffmanCompression(" !\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~	");
		h.compress();
	}

}
