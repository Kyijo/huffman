import huffman_coding.HuffmanC;

public class Main {

    public static void main(String[] args) {
        String in = "C:\\Users\\bleco\\OneDrive\\Plocha\\LMFAO.txt";
        String out = "C:\\Users\\bleco\\OneDrive\\Plocha\\ziposfile.zip";

        String in2 = "C:\\Users\\bleco\\OneDrive\\Plocha\\ziposfile.zip";
        String out2 = "C:\\Users\\bleco\\OneDrive\\Plocha\\unzipped.txt";
        try {
            HuffmanC.returnZIPFile(in,out);
            HuffmanC.returnFileFromZIP(in2,out2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
