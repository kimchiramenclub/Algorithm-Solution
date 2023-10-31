import java.io.*;

public class Main {
    /*  백트래킹
     * */

    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    static int[] seq;

    static void backtrack(int N, int depth) {
        if(depth >= N){
            for(int s : seq) sb.append(s).append(' ');
            sb.append('\n');
            return;
        }

        for (int i = 1; i <= N; i++) {
            if(!visited[i]){
                visited[i] = true;
                seq[depth] = i;
                backtrack(N, depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        seq = new int[N];

        backtrack(N, 0);
        System.out.println(sb);
    }
}