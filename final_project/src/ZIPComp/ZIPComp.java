package ZIPComp;
import huffman_coding.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ZIPComp {
    Map<Byte,String> map = new HashMap<>();

    HuffmanC hf = new HuffmanC();

    //compress bytes - return array of bytes
    public byte[] createZIP(Map<Byte,String> map, byte[] entry) {
        StringBuilder sb = new StringBuilder();
        for(byte x : entry) {
            String getValue = map.get(x);
            sb.append(getValue);
        }
        int tmp;
        if(sb.toString().length()%8 == 0){
            tmp = sb.toString().length()/8;
        }else{
           tmp = sb.toString().length()/8+1;
        }
        byte[] bytes = new byte[tmp];
        int tmp2 = 0;
        String str;
        for(int i = 0; i < sb.length(); i++){
            if(( i+8)>sb.length()){
                str = sb.substring(i);
            }else{
                str = sb.substring(i,i+8);
            }
            bytes[tmp2] = (byte)Integer.parseInt(str, 2);
            tmp2++;
        }
        return bytes;
    }

    public byte[] ZipInput(byte[] input) throws Exception {
        ArrayList<Huffman> huffman = hf.readBytes(input);
        Huffman encode = hf.HuffmanEnc(huffman);
        Map<Byte,String> map2 = hf.huffmanCode(encode);
        return createZIP(map2, input);
    }
    public void returnZIP(String input,String output){
        FileInputStream in = null;
        FileOutputStream out = null;
        ObjectOutputStream obj = null;
        try {
        in = new FileInputStream(input);
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        byte[] zip = ZipInput(bytes);
        out = new FileOutputStream(output);
        obj = new ObjectOutputStream(out);
        obj.writeObject(zip);
        obj.writeObject(map);
        } catch (Exception e) {
            e.printStackTrace();
        }if(in==null){
            try {
                in.close();
                out.close();
                obj.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
