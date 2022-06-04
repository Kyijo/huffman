package huffman_coding;
public class Huffman implements Comparable<Huffman> {
    Byte b;
    int freq;
    Huffman left;
    Huffman right;
    public Huffman(Byte b, int freq) {
        this.b = b;
        this.freq = freq;
    }

    @Override
    public int compareTo(Huffman o) {
        return this.freq - o.freq;
    }
    @Override
    public String toString() {
        return "Byte b: "+b+" frequence: "+freq;
    }
    public void TraversalOrder() {
        if(this.left != null) {
            this.left.TraversalOrder();
        }
        if(this.right != null) {
            this.right.TraversalOrder();
        }
    }
}

