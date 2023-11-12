import java.io.IOException;

public class Main {

    static boolean[] roll;

    public static void main(String[] args) throws IOException {
        int L = readInt();
        int N = readInt();
        roll = new boolean[L + 1];

        int first = 0;
        int second = 0;
        int count1 = 0;
        int count2 = 0;
        for (int n = 1; n <= N; n++) {
            int P = readInt();
            int K = readInt();
            if(count1 < (K-P)){
                count1 = K-P;
                first = n;
            }
            int tmp = fill(P, K);
            if(count2 < tmp){
                count2 = tmp;
                second = n;
            }
        }

        System.out.println(first+"\n"+second);
    }

    static int fill(int start, int end){
        int count = 0;

        for(int i = start; i<=end;i++){
            if(!roll[i]){
                roll[i] = true;
                count++;
            }
        }
        return count;
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
