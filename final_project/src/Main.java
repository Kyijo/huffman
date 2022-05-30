import ZIPComp.*;
public class Main {

    public static void main(String[] args) {
        ZIPComp x = new ZIPComp();
        String in = "C:\\Users\\bleco\\OneDrive\\Plocha\\LMFAO.txt";
        String out = "C:\\Users\\bleco\\OneDrive\\Plocha\\ziposfile.zip";
        try {
            x.returnZIP(in,out);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
