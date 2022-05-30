package huffman_coding;

public class Huffman {
    Byte b;
    int freq;
    Huffman left;
    Huffman right;

    public Huffman(Byte b, int freq){
        this.b = b;
        this.freq = freq;
    }
    public int comparable(Huffman huffman){
        return this.freq-huffman.freq;
    }
    public int sum(Huffman left, Huffman right){
        return left.freq + right.freq;
    }
}
