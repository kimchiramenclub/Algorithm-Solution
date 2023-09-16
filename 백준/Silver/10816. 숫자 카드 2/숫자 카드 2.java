import java.io.*;
import java.util.*;

class Main {
    /*  해시맵
     * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> cards = new HashMap<>(N);

        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            int tmp = Integer.parseInt(st.nextToken());
            if(!cards.containsKey(tmp)) cards.put(tmp, 1);
            else cards.put(tmp, cards.get(tmp)+1);
        }

        br.readLine();
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            int tmp = Integer.parseInt(st.nextToken());
            if(cards.containsKey(tmp)) sb.append(cards.get(tmp)).append(" ");
            else sb.append("0 ");
        }

        System.out.println(sb);
    }
}
