package huffman_coding;

import java.io.*;
import java.util.*;

/**
 * The type Huffman c.
 */
public class HuffmanC {
    /**
     * Check huff boolean.
     *
     * @param huffman the huffman
     * @return the boolean
     */
//Checks if huffman is null
    public boolean checkHuff(Huffman huffman){
        return huffman == null;
    }

    /**
     * Leaf left boolean.
     *
     * @param huffman the huffman
     * @return the boolean
     */
//Checks if left tree is leaf or nahh
    public boolean LeafLeft(Huffman huffman){
        return huffman.left == null;
    }

    /**
     * Leaf right boolean.
     *
     * @param huffman the huffman
     * @return the boolean
     */
//Checks if right tree is leaf or nahh
    public boolean LeafRight(Huffman huffman){
        return huffman.right == null;
    }

    /**
     * Huffman enc huffman.
     *
     * @param array the array
     * @return the huffman
     */
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

    /**
     * Huffman code.
     *
     * @param text    the text
     * @param huffman the huffman
     * @param sb      the sb
     */
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

    /**
     * The Map.
     */
    Map<Byte,String> map = new HashMap<>();
    /**
     * The Sb.
     */
    StringBuilder sb = new StringBuilder();
    //Returns Map of codes that will be used in huffman encoding

    /**
     * Huffman code map.
     *
     * @param huffman the huffman
     * @return the map
     * @throws Exception the exception
     */
    public Map<Byte,String> huffmanCode(Huffman huffman) throws Exception {
        if (!checkHuff(huffman)) {
            huffmanCode("0", huffman.left, sb);
            huffmanCode("1", huffman.right, sb);
        } else {
            throw new Exception("Huffman is null");
        }
        return map;
    }

    /**
     * Read bytes array list.
     *
     * @param b the b
     * @return the array list
     */
//Reads byte from file/png and puts em into arraylist
    public ArrayList<Huffman> readBytes(byte[] b){
        Map<Byte,Integer> mapXDD = new HashMap<>();
      for(Byte pls : b){
          Integer tmp = mapXDD.get(pls);
          if (tmp == null) {
              mapXDD.put(pls,1);
          } else {
              mapXDD.put(pls,tmp+1);
          }
      }
        Set<Byte> hashset = mapXDD.keySet();
        ArrayList<Huffman> array = new ArrayList<>();
        for(byte lmaokmspls : hashset){
          int mentalissues = mapXDD.get(lmaokmspls);
          Huffman notuwu = new Huffman(lmaokmspls,mentalissues);
            array.add(notuwu);
      }
      return array;
      }
    Map<Byte,String> map4 = new HashMap<>();

    /**
     * The Hf.
     */


    /**
     * Create zip byte [ ].
     *
     * @param map   the map
     * @param entry the entry
     * @return the byte [ ]
     */
    public byte[] createZIP(Map<Byte,String> map, byte[] entry) {
        StringBuilder sb = new StringBuilder();
        String str;
        int tmp;
        int tmp2 = 0;
        for(byte x : entry) {
            String getValue = map.get(x);
            sb.append(getValue);
        }
        if(sb.toString().length()%8 == 0){
            tmp = sb.toString().length()/8;
        }else{
            tmp = sb.toString().length()/8+1;
        }
        byte[] bytes = new byte[tmp];
        for(int i = 0; i < sb.length(); i = i + 8) {
            if ((i + 8) > sb.length()) {
                str = sb.substring(i);
            } else {
                str = sb.substring(i, i + 8);
            }
            bytes[tmp2++] = (byte) Integer.parseInt(str, 2);
        }
        return bytes;
    }

    /**
     * Zip input byte [ ].
     *
     * @param input the input
     * @return the byte [ ]
     * @throws Exception the exception
     */
    public byte[] ZipInput(byte[] input) throws Exception {
        ArrayList<Huffman> huffman = this.readBytes(input);
        Huffman encode = this.HuffmanEnc(huffman);
        Map<Byte,String> map2 = this.huffmanCode(encode);
        return createZIP(map2, input);
    }

    /**
     * Return zip.
     * @param input  the input
     * @param output the output
     */
    public void returnZIP(String input,String output){
        FileOutputStream out = null;
        FileInputStream in = null;
        ObjectOutputStream obj = null;
        try {
            in = new FileInputStream(input);
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            byte[] zip = ZipInput(bytes);
            out = new FileOutputStream(output);
            obj = new ObjectOutputStream(out);
            obj.writeObject(zip);
            obj.writeObject(map4);
        } catch (Exception e) {
            e.printStackTrace();
        }if(in==null) {
            try {
                in.close();
                out.close();
                obj.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void ZIPLoad(String In,String Out){
        FileOutputStream out;
        FileInputStream in;
        ObjectInputStream obj;
        try {
            in = new FileInputStream(In);
            obj = new ObjectInputStream(in);
            byte[] b = (byte[]) obj.readObject();
            Map<Byte,String> map = (Map<Byte, String>) obj.readObject();
            byte[] decode = convertZIP(map,b);
            out = new FileOutputStream(Out);
            out.write(decode);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }if(out == null){
            try {
                obj.close();
                in.close();
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String convertByteToBinaryString(int a,byte b){
        int c = b;
        if(a == 0){
            c|= 128;
        }
        String BString = Integer.toBinaryString(c);
        if(a == 0){
            BString = BString.substring(BString.length()-8);
        }
        return BString;
    }
    private byte[] convertZIP(Map<Byte,String> map, byte[] b){
        StringBuilder sb = new StringBuilder();
        for (byte value : b) {
            int a = (b.length - 1);
            sb.append(convertByteToBinaryString(a, value));
        }
        Map<String,Byte> map2 = new HashMap<>();
        Set<Byte> set = map.keySet();
        for(Byte c :set){
            String str = map.get(c);
            map2.put(str, c);
        }
        List<Byte> bytes = new ArrayList<>();
        int i = 0;
        while (i < sb.length()) {
            int sum = 1;
            Byte b1 = 0;
            boolean cycle = true;
            while(cycle){
                String substring = sb.substring(i,i+sum);
                b1 = map2.get(substring);
                if(b1 == null){
                    sum++;
                }else cycle = false;
            }
            bytes.add(b1);
            i+=sum;
        }
        byte[] bytes2 = new byte[bytes.size()];{
            int w = 0;
            while (w < bytes2.length) {
                bytes2[w] = bytes.get(w);
                w++;
            }
        }
        return bytes2;
    }
}

// XDXDKMSPLS
