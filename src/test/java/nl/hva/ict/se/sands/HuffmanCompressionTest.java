package nl.hva.ict.se.sands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HuffmanCompressionTest {
    HuffmanCompression compressor;

    @BeforeEach
    public void setup() {
        compressor = new HuffmanCompression(getClass().getResourceAsStream("/edu/princeton/cs/algs4/Huffman.java"));
    }

    @Test
    public void checkWeightSimple() {
        compressor = new HuffmanCompression("aba");

        Node compressionTree = compressor.getCompressionTree();

        assertEquals(3, compressionTree.getWeight());

        Node left = compressionTree.getLeft();
        Node right = compressionTree.getRight();
        assertEquals(1, left.getWeight());
        assertEquals(2, right.getWeight());
    }

    @Test
    public void checkUniqueCharacter() {
        // Handle Linux/Mac and Windows end-of-line characters, 86 and 87 are both ok.
        int numberOfChars = compressor.getCodes().size();
        assertTrue(numberOfChars == 86 || numberOfChars == 87, "You appear to have some very strange end-of-line configuration on your machine!");
    }

    @Test
    public void checkSimpleCompressionRatio() {
        compressor = new HuffmanCompression("aba");

        assertEquals(0.125,compressor.getCompressionRatio(), 0.0001);
    }


    /**
     * Added test methods
     */
    @Test
    public void sortLowerWeightFirst() {
        compressor = new HuffmanCompression("aba");
        String b = compressor.getCodes().get('b');

        // b only occurs once, thus should come first.
        assertEquals("0",b);
    }

    @Test
    public void checkAlphabeticalSort() {
        // Both are leafs and weight is equal, alphabetical comes first.
        compressor = new HuffmanCompression("cb");
        String b = compressor.getCodes().get('b');

        assertEquals("0",b);
    }

    @Test
    public void sortLeafFirst() {
        // Leaf comes first
        compressor = new HuffmanCompression("cbcbaa");
        String c = compressor.getCodes().get('c');

        // PriorityQueue will first check a & b.
        assertEquals("0",c);
    }

    @Test
    public void checkBitStringOrder() {
        compressor = new HuffmanCompression("aba");

        assertEquals("101",compressor.bitString);
    }

    @Test
    public void checkRootWeight() {
        // Added 27 characters which should result in a root weight of 27
        compressor = new HuffmanCompression("abcdefghijklmnopqrstuvwxyzz");

        assertEquals(27,compressor.getCompressionTree().getWeight());
    }

}