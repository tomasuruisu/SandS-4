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
    public HashMap<Character, String> codeTable;
    public String bitString = "";

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
        // get the occurences for the characters
        HashMap<Character, Integer> map = charOccurrence();
        double huffmanBits = 0;
        double normalRatio = 0;


        for (HashMap.Entry<Character, String> h : codeTable.entrySet()) {
            huffmanBits += h.getValue().length() * map.get(h.getKey());
            normalRatio += map.get(h.getKey()) * 8;

            }
        huffmanBits = huffmanBits / normalRatio;

        return huffmanBits;
    }

    /**
     * Compresses the text that was provided to the constructor.
     * @return
     */
    public String compress() {
        buildTrie();
        buildCodes(getCompressionTree());
        bitString = "";
        writeTrie(strArray);
        return bitString;
    }

    /**
     * Returns the root of the compression tree.
     * @return the root of the compression tree.
     */
    Node getCompressionTree() {
        return root;
    }

    /**
     * Returns a Map<Character, String> with the character and the code that is used to encode it.
     * For "aba" this would result in: ['b' -> "0", 'a' -> "1"]
     * And for "cacbcac" this would result in: ['b' -> "00", 'a' -> "01", 'c' -> "1"]
     * @return the Huffman codes
     */
    Map<Character, String> getCodes() {
        return codeTable;
    }

    /**
     * builds the compression codes, assigns the code map and prints the characters with their individual codes.
     * @param root
     */
    private void buildCodes(Node root) {
        HashMap<Character, String> map = new HashMap<>();
        buildRecursive(map, root, "");

        //*

        //*

        codeTable = map;
    }

    /**
     * recursively builds the compression codes.
     * @param map
     * @param x
     * @param s
     */
    private void buildRecursive(HashMap<Character, String> map, Node x, String s) {

        if (x.isLeaf()) {
            map.put(x.getCharacter(), s);
            return;
        }
            buildRecursive(map, x.getLeft(), s + '0');
            buildRecursive(map, x.getRight(), s + '1');

    }

    /**
     * builds the compression tree.
     * @return the root of the compression tree
     */
    private Node buildTrie(){

        // get the occurences for the characters
        HashMap<Character, Integer> map = charOccurrence();

        // priority queue to keep the weights organized
        PriorityQueue<Node> pq = new PriorityQueue<>();

        //*

        // for each char in the text
        for (HashMap.Entry<Character, Integer> h : map.entrySet()) {
            //*

            // create a node from the char
            pq.add(new Node(map.get(h.getKey()), h.getKey()));
        }

        // loop while there is multiple nodes in the queue
        // until the root is left
        while (pq.size() > 1) {
            // left node
            Node l = pq.peek();
            pq.poll();

            // right node
            Node r = pq.peek();
            pq.poll();

            // merge the nodes into parent
            Node parent = new Node(l, r);

            // define trie root
            root = parent;

            // parent node goes back in the queue
            pq.add(parent);
        }

        //*

        return pq.poll();
    }

    /**
     * counts the character occurence from text.
     * @return hashmap with character occurences
     */
    public HashMap charOccurrence() {

        // Creating a HashMap containing a char
        // as a key and the occurrences as value
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

    /**
     * Writes a bitstring from the text in order using the code table.
     */
    private void writeTrie(char[] strArray) {
        for (int i = 0; i < strArray.length; i++) {
            bitString += codeTable.get(strArray[i]);
        }
    }

}
