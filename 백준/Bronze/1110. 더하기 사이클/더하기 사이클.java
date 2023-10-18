import java.io.IOException;

class Main {
    /*
     **/

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int newN = N;
        int count = 0;

        do{
            int left = newN < 10 ? 0 : newN / 10;
            int right = newN % 10;
            newN = right * 10 + (left + right) % 10;
            count++;
        }
        while(newN != N);

        System.out.println(count);
    }

    static int readInt() throws IOException {
        int c, n = 0;
        while((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}