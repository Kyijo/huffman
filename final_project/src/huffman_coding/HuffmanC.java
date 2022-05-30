package huffman_coding;

import java.util.*;

public class HuffmanC {
    //Checks if huffman is null
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
    public void huffmanCode(String text,Huffman huffman, StringBuilder sb){
        StringBuilder sb2 = new StringBuilder();
        sb2.append(text);
        if(LeafLeft(huffman)&&LeafRight(huffman)){
            map.put(huffman.b,sb2.toString());
        }else{
            huffmanCode("0",huffman.left,sb2);
            huffmanCode("1",huffman.right,sb2);
        }
    }
    Map<Byte,String> map = new HashMap<>();
    StringBuilder sb = new StringBuilder();
    //Returns Map of codes that will be used in huffman encoding

    public Map<Byte,String> huffmanCode(Huffman huffman) throws Exception {
        if(checkHuff(huffman)){
            throw new Exception("Huffman is null");
        }else{
            huffmanCode("0", huffman.left, sb);
            huffmanCode("1", huffman.right, sb);
        }
        return map;
    }
    //Reads byte from file/png and puts em into arraylist
    public ArrayList<Huffman> readBytes(byte[] b){
      ArrayList<Huffman> array = new ArrayList<>();
      Map<Byte,Integer> mapXDD = new HashMap<>();
      for(Byte pls : b){
          Integer tmp = mapXDD.get(pls);
          if(tmp == null){
              mapXDD.put(pls,1);
          }else{
              mapXDD.put(pls,tmp+1);
          }
          }
      Set<Byte> hashset = mapXDD.keySet();
      for(byte lmaokmspls : hashset){
          int mentalissues = mapXDD.get(lmaokmspls);
          Huffman notuwu = new Huffman(lmaokmspls,mentalissues);
          array.add(notuwu);
      }
      return array;
      }
}
