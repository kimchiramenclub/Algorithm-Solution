import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = 0, c;
        while ((c = System.in.read()) > 32) N = (N << 3) + (N << 1) + (c & 15);
        for(int i=1;i<=N;i++){
            bw.write(i+"\n");
        }
        bw.flush();
    }
}