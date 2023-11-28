import java.io.IOException;

public class Main {
    /*
     * */


    public static void main(String[] args) throws IOException {
        int x = readInt();
        int y = readInt();

        int[] dateSum = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
        String[] month = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};

        System.out.println(month[(dateSum[x - 1] + y) % 7]);
    }

    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}