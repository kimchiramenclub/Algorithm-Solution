import java.io.*;

class Main {
    /*  그리디
        - 이전의 활잡이가 잡은 활잡이는, 절대 이전의 활잡이의 기록을 깰 수 없음. 자기 봉우리가 더 낮기 때문임
        - 따라서, for문에서 넘길 때 마지막에 잡은 활잡이 다음으로 넘겨주면 됨.
        - 마지막 활잡이는 kill 수를 갱신 전에 for문이 종료되므로, 마지막 갱신 체크 필요
        - 주의) 같은 높이 봉우리는 다음 활잡이로 넘기지는 않지만, 잡지도 못함.
     **/

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int maxKill = 0, kill = 0, height = readInt();

        for (int i = 1; i < N; i++) {
            int next = readInt();
            if(next < height) kill++;
            else{
                maxKill = Math.max(maxKill, kill);
                kill = 0;
                height = next;
            }
        }
        maxKill = Math.max(maxKill, kill); // 마지막 비교를 위해 추가

        System.out.println(maxKill);
    }

    // 입력 관련
    static int idx, size, SIZE = 1 << 13;
    static byte[] buf = new byte[SIZE];

    static int readInt() throws IOException {
        int n = 0;
        byte c;
        while ((c = read()) <= 32) ;
        do n = (n << 3) + (n << 1) + (c & 15);
        while (47 < (c = read()) && c < 58);
        return n;
    }

    static byte read() throws IOException {
        if (size == idx) {
            size = System.in.read(buf, idx = 0, SIZE);
            if (size < 0) buf[0] = -1;
        }
        return buf[idx++];
    }
}
