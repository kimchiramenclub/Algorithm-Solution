import java.io.*;

class Main {
    /*  그리디
     * */

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int q5 = N / 5;
        int q3 = -1;

        do{
            if((N - q5 * 5) % 3 == 0) {
                q3 = (N - q5 *5) / 3;
                break;
            }
        } while(--q5 >= 0);

        if(q3 != -1) System.out.println(q3+q5);
        else System.out.println(q3);

    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
