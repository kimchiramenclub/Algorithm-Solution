import java.io.*;
import java.util.*;

public class Main {

    /* 해시맵
     */

    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());

      Map<String, Integer> check = new HashMap<>();
      List<String> same = new ArrayList<>();

      for(int i=0;i<N+M;i++){
          String tmp = br.readLine();
          if(check.containsKey(tmp)) {
              same.add(tmp);
          }
          else check.put(tmp, i);
      }

      Collections.sort(same);
      System.out.println(same.size());
      for(String ans : same){
          System.out.println(ans);
      }

    }
}
