package ZIPComp;
import huffman_coding.*;

import java.io.*;
import java.util.*;

/**
 * The type Zip comp.
 */
public class ZIPMethods {
    /**
     * The Map.
     */
    Map<Byte,String> map4 = new HashMap<>();

    /**
     * The Hf.
     */
    HuffmanC hf = new HuffmanC();

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
        ArrayList<Huffman> huffman = hf.readBytes(input);
        Huffman encode = hf.HuffmanEnc(huffman);
        Map<Byte,String> map2 = hf.huffmanCode(encode);
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
// IAHVE MENTAL ISSUES FORM THIS PRPJECT -D O NEXCT RECOMMED NAHAH HA HAHAH A
