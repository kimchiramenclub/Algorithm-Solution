import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        int c, n = 0;
        while((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        System.out.println((n * (n + 1) / 2));
    }
}