import java.io.IOException;

public class Main {
    /*  스택
     * */
    
    static StringBuilder sb = new StringBuilder();
    static boolean[] popped;

    public static void main(String[] args) throws IOException {

        int n = readInt();
        popped = new boolean[n + 1];
        boolean notStack = false;

        int stackPos = 0;
        int idx = 1;

        for (int i = 1; i <= n; i++) {
            int cur = readInt();
            if (stackPos < cur) {
                push(stackPos, cur);
                pop(cur);
                stackPos = cur;
                idx = cur - 1;
            } else {
                if (pop(idx) != cur) {
                    notStack = true;
                    break;
                }
            }
        }

        if (!notStack) System.out.print(sb);
        else System.out.println("NO");

    }

    static void push(int idx, int cur) throws IOException {
        for (int i = idx + 1; i <= cur; i++) {
            sb.append("+\n");
        }
    }

    static int pop(int idx) throws IOException {
        while (true) {
            if (!popped[idx]) {
                popped[idx] = true;
                sb.append("-\n");
                return idx;
            }
            idx--;
        }
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