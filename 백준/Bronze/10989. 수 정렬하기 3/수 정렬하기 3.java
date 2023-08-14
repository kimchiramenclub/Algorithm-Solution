import java.io.IOException;
import java.util.Arrays;

class Main {
    /*  정렬
        */

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int N = readInt();
        int[] array = new int[N];

        for(int i=0;i<N;i++) array[i] = readInt();
        Arrays.sort(array);
        for(int i=0;i<N;i++) sb.append(array[i]).append("\n");
        System.out.println(sb);
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}