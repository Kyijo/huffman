import ZIPComp.*;
import huffman_coding.HuffmanC;

public class Main {

    public static void main(String[] args) {
        ZIPMethods x = new ZIPMethods();
        HuffmanC ainowgnoikawsehjiéogawsenoighaesiohuiabwgbuisawehuíágwaháíuawgíháawgeáu = new HuffmanC();
        String in = "C:\\Users\\bleco\\OneDrive\\Plocha\\LMFAO.txt";
        String out = "C:\\Users\\bleco\\OneDrive\\Plocha\\ziposfile.zip";

        String in2 = "C:\\Users\\bleco\\OneDrive\\Plocha\\ziposfile.zip";
        String out2 = "C:\\Users\\bleco\\OneDrive\\Plocha\\unzipped.txt";
        try {
            ainowgnoikawsehjiéogawsenoighaesiohuiabwgbuisawehuíágwaháíuawgíháawgeáu.returnZIP(in,out);
            ainowgnoikawsehjiéogawsenoighaesiohuiabwgbuisawehuíágwaháíuawgíháawgeáu.ZIPLoad(in2,out2);
          //  x.returnZIP(in,out);
          //  x.ZIPLoad(in2,out2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
