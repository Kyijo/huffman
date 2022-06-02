package huffman_coding;

/**
 * The type Huffman.
 */
public class Huffman {
    /**
     * The B.
     */
    Byte b;
    /**
     * The Freq.
     */
    int freq;
    /**
     * The Left.
     */
    Huffman left;
    /**
     * The Right.
     */
    Huffman right;

    /**
     * Instantiates a new Huffman.
     *
     * @param b    the b
     * @param freq the freq
     */
    public Huffman(Byte b, int freq){
        this.b = b;
        this.freq = freq;
    }

    /**
     * Comparable int.
     *
     * @param huffman the huffman
     * @return the int
     */
    public int comparable(Huffman huffman){
        return this.freq-huffman.freq;
    }

    /**
     * Sum int.
     *
     * @param left  the left
     * @param right the right
     * @return the int
     */
    public int sum(Huffman left, Huffman right){
        return left.freq + right.freq;
    }
}

