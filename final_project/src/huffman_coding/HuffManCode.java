package huffman_coding;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HuffManCode
{

    public static void main(String[] args)
    {
        String srcFile="C:\\Users\\bleco\\OneDrive\\Plocha\\XD.txt";// File to be compressed
        String dstFile="C:\\Users\\bleco\\OneDrive\\Plocha\\XD.zip";// A compressed file
        zipFile(srcFile, dstFile);// Zip file
        System.out.println(" Compression succeeded! ");
    }


    public static void zipFile(String srcFile,String dstFile)
    {
        FileInputStream inputStream=null;
        OutputStream outputStream=null;
        ObjectOutputStream objectOutputStream=null;

        try
        {
            inputStream=new FileInputStream(srcFile);
            byte [] b=new byte[inputStream.available()];
            inputStream.read(b);
            byte[] huffmanZip = huffmanZip(b);
            outputStream=new FileOutputStream(dstFile);
            objectOutputStream=new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(huffmanZip);
            objectOutputStream.writeObject(map);
        } catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            if(inputStream!=null)
            {
                try
                {
                    objectOutputStream.close();
                    outputStream.close();
                    inputStream.close();// Release resources

                } catch (Exception e2)
                {
                    System.out.println(e2);
                }

            }
        }
    }
    private static byte[] huffmanZip(byte [] array)
    {
        List<Node> nodes = getNodes(array);
        Node createHuffManTree = createHuffManTree(nodes);
        Map<Byte, String> m=getCodes(createHuffManTree);
        byte[] zip = zip(array, m);
        return zip;
    }

    //
    private static byte[] zip(byte [] array,Map<Byte,String> map)
    {
        StringBuilder sBuilder=new StringBuilder();
        for(byte item:array)
        {
            String value=map.get(item);
            sBuilder.append(value);
        }
        //System.out.println(sBuilder);
        int len;
        if(sBuilder.toString().length()%8==0)// If you can divisive exactly
        {
            len=sBuilder.toString().length()/8;
        }
        else // If you can't divisive exactly
        {
            len=sBuilder.toString().length()/8+1;
        }

        byte [] by=new byte[len];
        int index=0;
        for(int i=0;i<sBuilder.length();i+=8)
        {
            String string;
            if((i+8)>sBuilder.length())
            {
                string=sBuilder.substring(i);
            }
            else
            {
                string=sBuilder.substring(i, i+8);
            }

            by[index]=(byte)Integer.parseInt(string,2);
            index++;
        }

        return by;

    }
    public static List<Node> getNodes(byte [] array)
    {
        List<Node> list=new ArrayList<Node>();
        Map<Byte, Integer> map=new HashMap<Byte, Integer>();
        for(Byte data:array)
        {
            Integer count=map.get(data);// Get a value by key
            if(count==null)// Explain that at this time map Characters have not been changed in the collection
            {
                map.put(data, 1);
            }
            else
            {
                map.put(data,count+1);
            }
        }
        // Traversal map Set
        Set<Byte> set=map.keySet();
        for(Byte key:set)
        {
            int value=map.get(key);
            Node node=new Node(key, value);
            list.add(node);
        }
        return list;
    }

}
