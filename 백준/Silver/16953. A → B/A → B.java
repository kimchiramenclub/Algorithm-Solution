import java.io.IOException;

class Main {
    /*    그리디
          - B -> A로 역산  
     **/
    public static void main(String[] args) throws IOException {
        int A = readInt();
        int B = readInt();
        int count = 1;

        while (B != A) {
            if (B < A || (B % 2 != 0 && B % 10 != 1)) {
                count = -1;
                break;
            }
            else if (B % 10 == 1) B /= 10;
            else B /= 2;
            count++;
        }

        System.out.print(count);
    }

    static int readInt() throws IOException {
        int c, n = 0;
        while((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}