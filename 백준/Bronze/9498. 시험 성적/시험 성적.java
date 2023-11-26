import java.io.IOException;

public class Main {
    /*
     * */

    public static void main(String[] args) throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);

        switch (n / 10) {
            case 10:
            case 9:
                System.out.println('A');
                break;
            case 8:
                System.out.println('B');
                break;
            case 7:
                System.out.println('C');
                break;
            case 6:
                System.out.println('D');
                break;
            default:
                System.out.println('F');
                break;
        }
    }
}