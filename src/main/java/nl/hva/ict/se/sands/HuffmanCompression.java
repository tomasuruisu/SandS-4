package nl.hva.ict.se.sands;


import java.io.InputStream;
import java.util.*;

/*
 * Original Huffman Compression algorithm taken from Huffman.java in Algorithms 4th edition.
 */
public class HuffmanCompression {
    private final String text;                  // Text input
    private final Integer firstCharCount = 1;  // First occurrence of a char
    private final int R = 256;                  // The radix
    char[] strArray;       // Converting given string to char array
    private Node root;

    public HuffmanCompression(String text) {
        this.text = text;
        strArray = this.text.toCharArray();
    }

    public HuffmanCompression(InputStream input) {
        Scanner sc = new Scanner(input);
        sc.useDelimiter("\\Z"); // EOF marker
        text = sc.next();
        strArray = this.text.toCharArray();
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
        buildTrie();
        return "";
    }

    /**
     * Returns the root of the compression tree.
     * @return the root of the compression tree.
     */
    Node getCompressionTree() {
        return buildTrie();
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
    
    private Node buildTrie(){

        // get the occurences for the characters
        HashMap<Character, Integer> map = charOccurrence();

        // priority queue to keep the weights organized
        PriorityQueue<Node> pq = new PriorityQueue<>();

        System.out.println("If a blank character appears it is a TAB or a SPACE");

        // for each char in the text
        for (HashMap.Entry<Character, Integer> h : map.entrySet()) {
            System.out.println("Character: " + h.getKey() + "\toccurrences:\t" + map.get(h.getKey()));

            // create a node from the char
            pq.add(new Node(map.get(h.getKey()), h.getKey()));
        }

        // loop while there is multiple nodes in the queue
        // until the root is left
        while (pq.size() > 1) {
            // left node
            Node x = pq.peek();
            pq.poll();

            // right node
            Node y = pq.peek();
            pq.poll();

            // parent node to merge the small trees
            Node parent = new Node(x, y);

            // define trie root
            root = parent;

            // parent node goes back in the queue
            pq.add(parent);
        }

        System.out.println("The weight of the root is:" + pq.peek().getWeight());

        return pq.poll();
    }

    private HashMap charOccurrence() {

        // Creating a HashMap containing char
        // as a key and occurrences as  a value
        HashMap<Character, Integer> charCountMap
                = new HashMap<>();

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
                charCountMap.put(c, firstCharCount);
            }
        }

        return charCountMap;

    }

}
