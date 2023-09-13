import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = readInt();
        String pwd = br.readLine();
        int row = pwd.length() / K;
        char[][] array = new char[row][K];

        int pwdIdx = 0;
        for (int i = 0; i < row; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < K; j++) {
                    array[i][j] = pwd.charAt(pwdIdx++);
                }
            } else {
                for (int j = K - 1; j >= 0; j--) {
                    array[i][j] = pwd.charAt(pwdIdx++);
                }
            }
        }

        //출력부
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < K; j++) {
            for (int i = 0; i < row; i++) sb.append(array[i][j]);
        }
        System.out.println(sb);
    }

    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}