import java.io.IOException;

class Main {
    /*  dp
     */
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int T = readInt();
        int[][] apt = new int[15][14];
        // 아파트 0층 1~14호 정보 저장
        for (int n = 1; n <= 14; n++) apt[0][n - 1] = n;

        for (int k = 1; k <= 14; k++) {
            apt[k][0] = 1;  // 아파트 k층 1호는 항상 1
            for (int n = 1; n < 14; n++) {
                // k층 n호 인원 = k층 n-1호 인원(k-1층 1~n-1호 인원 합) + k-1층 n호 인원
                apt[k][n] = apt[k][n-1] + apt[k-1][n]; 
            }
        }

        for (int i = 0; i < T; i++) {
            int k = readInt();
            int n = readInt();
            sb.append(apt[k][n-1]).append('\n');    // k층 n호 인원 값 출력
        }

        System.out.println(sb);
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}