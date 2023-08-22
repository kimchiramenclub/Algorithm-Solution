import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println(readASCII());
    }

    static int readASCII() throws IOException {
        return System.in.read();
    }
}