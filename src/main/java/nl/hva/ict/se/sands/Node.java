package nl.hva.ict.se.sands;

/**
 * NOTE: You are NOT allowed to implement the Serializable interface!!
 */
public class Node implements Comparable<Node> {
    private Node left;
    private Node right;
    private int weight;
    private Character character;

    public Node(int weight, Character c) {
        this.weight = weight;
        this.character = c;
    }

    public Node(Node left, Node right) {
        this.weight = left.weight + right.weight;
        this.left = left;
        this.right = right;
    }

    public Node() {
    }

    @Override
    public int compareTo(Node o) {

        // if both are leafs and have equal weight, compare on character
        if(isLeaf() && o.isLeaf() && this.weight == o.getWeight()){
            return this.character.compareTo(o.getCharacter());
        }

        // leaf has priority
        else if (isLeaf() && !o.isLeaf()) {
            return 1;
        } else if (!isLeaf() && o.isLeaf()) {
            return -1;
        }

        // neither is a leaf
        return 0;
    }

    public boolean isLeaf(){
        return left == null && right == null;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getWeight() {
        return weight;
    }

    public Character getCharacter() {
        return character;
    }

}
