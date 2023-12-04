import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String me = br.readLine(), doc = br.readLine();
        System.out.println(me.length() < doc.length() ? "no" : "go");
    }
}