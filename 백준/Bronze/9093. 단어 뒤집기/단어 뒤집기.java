import java.io.*;
import java.util.StringTokenizer;

class Main {
    /*
     **/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        String tmp;

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            
            while (st.hasMoreTokens()) {
                tmp = st.nextToken();
               
                for (int i = tmp.length() - 1; i >= 0; i--) {
                    sb.append(tmp.charAt(i));
                }
                sb.append(' ');
            }
            
            sb.append('\n');
        }
        System.out.println(sb);
    }
}