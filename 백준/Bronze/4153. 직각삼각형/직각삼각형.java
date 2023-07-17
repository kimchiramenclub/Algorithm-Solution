import java.io.*;
import java.util.StringTokenizer;

public class Main {

    /*
 */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;



        while(true){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            if(A ==0) break;
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int max = Math.max(A, Math.max(B,C));
            if(-2 * (max*max) + A*A + B*B + C*C == 0) bw.write("right\n");
            else bw.write("wrong\n");
        }

        bw.flush();
        bw.close();
    }
}