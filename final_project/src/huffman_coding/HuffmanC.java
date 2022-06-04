package huffman_coding;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class HuffmanC {
    public static void returnZIPFile(String input,String output){
        try {
            FileInputStream in = new FileInputStream(input);
            byte[] bytes1 = new byte[in.available()];
            in.read(bytes1);
            byte[] zipfile = createZIP(bytes1);
            FileOutputStream out = new FileOutputStream(output);
            ObjectOutputStream obj = new ObjectOutputStream(out);
            obj.writeObject(zipfile);
            obj.writeObject(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static byte[] decodeZIPToFile(Map<Byte, String> map,byte [] bytes){
        StringBuilder sb = new StringBuilder();
        for (byte entry : bytes) {
            boolean ll=(entry==bytes.length-1);
            sb.append(convertByteToBinaryString(!ll, entry));
        }
        Map<String, Byte> mapmap=new HashMap<>();
        Set<Byte> setset = map.keySet();
        for(Byte entry: setset){
            String string=map.get(entry);
            mapmap.put(string, entry);
        }
        ArrayList<Byte> arraylist=new ArrayList<>();
        int i = 0;
        while (i < sb.length()) {
            int sum = 1;
            Byte b1 = 0;
            boolean cycle = true;
            while(cycle){
                String string2 = sb.substring(i,i+sum);
                b1 = mapmap.get(string2);
                if (b1 != null) {
                    cycle = false;
                } else {
                    sum++;
                }
            }
            arraylist.add(b1);
            i+=sum;
        }
        byte[] bytes3 = new byte[arraylist.size()];
        for(int a = 0; a < bytes3.length; a++) {
            bytes3[a] = arraylist.get(a);
        }
        return bytes3;
    }
    private static String convertByteToBinaryString(boolean a, byte b){
        int c = b;
        if(a){
            c|= 256;
        }
        String BString = Integer.toBinaryString(c);
        if(a){
            BString = BString.substring(BString.length()-8);
        }
        return BString;
    }
    public static void returnFileFromZIP(String input,String output){
        try {
            FileInputStream in = new FileInputStream(input);
            ObjectInputStream obj = new ObjectInputStream(in);
            byte[] b = (byte[]) obj.readObject();
            Map<Byte,String> map = (Map<Byte, String>) obj.readObject();
            byte[] decodedFile = decodeZIPToFile(map,b);
            FileOutputStream out = new FileOutputStream(output);
            out.write(decodedFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static byte[] createZIP(byte[] array){
        ArrayList<Huffman> arraylist = getHuffmanCodes(array);
        Huffman huffman = makeATree(arraylist);
        Map<Byte, String> mapmap=getHuffman(huffman);
        return encodeZIP(array, mapmap);
    }
    private static byte[] encodeZIP(byte [] input,Map<Byte,String> map){
        StringBuilder sb = new StringBuilder();
        for(byte entry : input) {
            String string = map.get(entry);
            sb.append(string);
        }
        int temp = 0;
        if(sb.toString().length()%8 == 0){
            temp = sb.toString().length()/8;
        }else{
            temp = sb.toString().length()/8+1;
        }
        byte[] bytes = new byte[temp];
        int temp2 = 0;
        for(int i = 0; i < sb.length(); i = i + 8) {
            String str;
            if ((i + 8) > sb.length()) {
                str = sb.substring(i);
            } else {
                str = sb.substring(i, i + 8);
            }
            bytes[temp2++] = (byte) Integer.parseInt(str, 2);
        }
        return bytes;
    }
    public static boolean checkHuff(Huffman huffman) {
        return huffman == null;
    }
    private static Map<Byte, String> getHuffman(Huffman huffman){
        if(checkHuff(huffman)){
            return null;
        }
        returnHuffmanCodes(huffman.left,"0",sb);
        returnHuffmanCodes(huffman.right,"1",sb);
        return map;
    }
    static Map<Byte, String> map=new HashMap<>();
    static StringBuilder sb=new StringBuilder();
    public static void returnHuffmanCodes(Huffman huffman,String string,StringBuilder sb){
        StringBuilder sb2=new StringBuilder(sb);
        sb2.append(string);
        if(huffman==null){
            System.out.println("Huffman is null");
        }else {
            if(huffman.b==null){
                returnHuffmanCodes(huffman.left,"0",sb2);
                returnHuffmanCodes(huffman.right,"1",sb2);
            }
            else {
                map.put(huffman.b,sb2.toString());
            }
        }
    }
    public static ArrayList<Huffman> getHuffmanCodes(byte [] array){
        Map<Byte, Integer> map=new HashMap<>();
        for(Byte input:array){
            map.merge(input, 1, Integer::sum);
        }
        Set<Byte> set=map.keySet();
        ArrayList<Huffman> arraylist=new ArrayList<>();
        for(Byte entry:set){
            int sum=map.get(entry);
            Huffman huffman=new Huffman(entry, sum);
            arraylist.add(huffman);
        }
        return arraylist;
    }
    private static Huffman makeATree(ArrayList<Huffman> arraylist){
        do{
            Collections.sort(arraylist);
            Huffman left = arraylist.get(0);
            Huffman right = arraylist.get(1);
            Huffman parent=new Huffman(null, left.freq+ right.freq);
            parent.left = left;
            parent.right = right;
            arraylist.remove(left);
            arraylist.remove(right);
            arraylist.add(parent);
        }while(arraylist.size()>1);
        return arraylist.get(0);
    }
}
// XDXDKMSPLS -  I AHTE THIS PROJECT DO NOT RECOMMENH AHHA HAHAH HA H