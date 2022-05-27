package huffman_coding;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HuffmanC {
    public boolean checkHuff(Huffman huffman){
        return huffman == null;
    }
    //Checks if left tree is leaf or nahh
    public boolean LeafLeft(Huffman huffman){
        return huffman.left == null;
    }
    //Checks if right tree is leaf or nahh
    public boolean LeafRight(Huffman huffman){
        return huffman.right == null;
    }
    //Creates huffman tree - huffman_code_how_it_works.png
    public Huffman HuffmanEnc(ArrayList<Huffman> array){
        do{
            Huffman left = array.get(0);
            Huffman right = array.get(1);
            Huffman parent = new Huffman(null, left.freq+right.freq);
            parent.right = right;
            parent.left = left;
            array.remove(left);
            array.remove(right);
            array.add(parent);
        }while(array.size() > 1);
        return array.get(0);
    }
    //Get encoded text such as - 1011001...
    public void encodeText(String text,Huffman huffman, StringBuilder sb){
        StringBuilder sb2 = new StringBuilder();
        sb2.append(text);
        if(LeafLeft(huffman)&&LeafRight(huffman)){
            map.put(huffman.b,sb2.toString());
        }else{
            encodeText("0",huffman.left,sb2);
            encodeText("1",huffman.right,sb2);
        }
    }
    //Returns Map of codes that will be used in huffman encoding
    Map<Byte,String> map = new HashMap<>();
    StringBuilder sb = new StringBuilder();
    public Map<Byte,String> huffmanCode(Huffman huffman, StringBuilder sb, String s) throws Exception {
        if(checkHuff(huffman)){
            throw new Exception("Huffman is null");
        }else{
            huffmanCode(huffman.left,sb,"0");
            huffmanCode(huffman.right,sb,"1");
        }
        return map;
    }
    public void createZIP(String path) throws IOException {
        File f = new File(path);
        f.createNewFile();
    }
}
