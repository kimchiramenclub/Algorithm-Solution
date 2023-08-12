import java.io.*;
import java.util.*;

class Main {
    /*  정렬
    */

    static void solution(int N, int[][] order, String[] name) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        Arrays.sort(order, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                int cmp = Integer.compare(o1[0], o2[0]);
                if(cmp == 0) cmp = Integer.compare(o1[1], o2[1]);
                return cmp;
            }
        });

        for(int i=0;i<N;i++){
            sb.append(order[i][0]).append(" ");
            sb.append(name[order[i][1]]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] order = new int[N][2];
        String[] name = new String[N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            order[i][1] = i;
            order[i][0] = Integer.parseInt(st.nextToken());
            name[i] = st.nextToken();
        }

        solution(N, order, name);
    }
}
