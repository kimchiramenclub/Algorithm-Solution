package BackJoon.BackTracking;


import java.io.*;
import java.util.StringTokenizer;

public class back_15652 {
    static int N;
    static int M;
    static int[] arr;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 숫자의 범위 1~8
        M = Integer.parseInt(st.nextToken()); // N의 범위에서 고를 숫자의 수.
        arr = new int[M];

        putNum(1, 0);
        bw.flush();
        bw.close();


    }

    public static void putNum(int index, int depth) throws IOException {
        if (depth >= M) {
            print();
            return;
        }
        for (int i = index; i <= N; i++) {

            arr[depth] = i;
            putNum(i, depth + 1);

        }
    }

    public static void print() throws IOException {
        for (int i : arr) {
            bw.write(i + " ");
        }
        bw.write("\n");
    }

}
