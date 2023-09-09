import java.io.*;

class Main {
    /*  스택
        - 굳이 Stack 안 쓰고 배열을 스택처럼 사용해도 괜찮음.
        - size를 통해 LIFO 구성
     */

    static boolean[] VPS = new boolean[50];
    static int size;

    static boolean solution(String PS) {
        for(int i=0;i<size;i++) VPS[i] = true;  // array를 재사용하기 위해 clean

        size = 0;   // size 초기화

        try {
            for (int i = 0; i < PS.length(); i++) {
                if (PS.charAt(i) == '(') VPS[size++] = true;   //
                else VPS[(size--) - 1] = false;
            }
        } catch(Exception e){return false;} // )가 (보다 많아서 index를 벗어나면 VPS가 아니므로 NO return

        return size == 0;   // size = 0이여서 VPS일때만 YES return
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = readInt();
        for(int i=0;i<N;i++){
            String PS = br.readLine();
            if(solution(PS)) sb.append("YES");
            else sb.append("NO");
            sb.append("\n");
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