package nl.hva.ict.se.sands;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Original Huffman Compression algorithm taken from Huffman.java in Algorithms 4th edition.
 */
public class HuffmanCompression {
    private final String text;

    public HuffmanCompression(String text) {
        this.text = text;
    }

    public HuffmanCompression(InputStream input) {
        Scanner sc = new Scanner(input);
        sc.useDelimiter("\\Z"); // EOF marker
        text = sc.next();
    }

    /**
     * Returns the compression ratio assuming that every characters in the text uses 8 bits.
     * @return the compression ratio.
     */
    public double getCompressionRatio() {
        return 0.0;
    }

    /**
     * Compresses the text that was provided to the constructor.
     * @return
     */
    public String compress() {
        return "";
    }

    /**
     * Returns the root of the compression tree.
     * @return the root of the compression tree.
     */
    Node getCompressionTree() {
        return null;
    }

    /**
     * Returns a Map<Character, String> with the character and the code that is used to encode it.
     * For "aba" this would result in: ['b' -> "0", 'a' -> "1"]
     * And for "cacbcac" this would result in: ['b' -> "00", 'a' -> "01", 'c' -> "1"]
     * @return the Huffman codes
     */
    Map<Character, String> getCodes() {
        return null;
    }

    private HashMap charOccurrence() {

        // Creating a HashMap containing char
        // as a key and occurrences as  a value
        HashMap<Character, Integer> charCountMap
                = new HashMap<Character, Integer>();

        // Converting given string to char array

        char[] strArray = text.toCharArray();

        // checking each char of strArray
        for (char c : strArray) {
            if (charCountMap.containsKey(c)) {

                // If char is present in charCountMap,
                // incrementing it's count by 1
                charCountMap.put(c, charCountMap.get(c) + 1);
            }
            else {

                // If char is not present in charCountMap,
                // putting this char to charCountMap with 1 as it's value
                charCountMap.put(c, 1);
            }
        }

        // Printing the charCountMap
//        for (Map.Entry entry : charCountMap.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }

        return charCountMap;

    }

}
