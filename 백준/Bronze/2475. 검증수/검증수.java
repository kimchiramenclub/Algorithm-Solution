import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int sum = 0;
        for(int i=0;i<5;i++) sum += readDoubled();
        System.out.println((sum%10));
    }

    static int readDoubled() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32);
        return (c & 15) * (c & 15);
    }
}