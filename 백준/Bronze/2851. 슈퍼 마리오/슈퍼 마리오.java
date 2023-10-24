import java.io.IOException;

class Main {
    /*
     **/
    public static void main(String[] args) throws IOException {
        int[] scores = new int[11];
        int score = 0;

        for (int i = 1; i <= 10; i++) {
            scores[i] = scores[i - 1] + readInt();
            if (scores[i] < 100) score = scores[i];
            else {
                score = scores[i] - 100 <= 100 - scores[i - 1] ? scores[i] : scores[i - 1];
                break;
            }
        }
        System.out.println(score);
    }

    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}