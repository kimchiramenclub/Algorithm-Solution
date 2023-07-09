import java.io.*;
import java.util.*;

public class Main {

    /*  해시 맵
     */


    public static void solution(Map<String, String> pass, String[] findPass) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (String key : findPass) {
            String tmp = pass.get(key);
            bw.write(tmp + "\n");
        }

        bw.flush();
        bw.close();
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, String> pass = new HashMap<>();
        String[] findPass = new String[M];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pass.put(st.nextToken(), st.nextToken());
        }
        for (int i = 0; i < M; i++) {
            findPass[i] = br.readLine();
        }

        solution(pass, findPass);
    }
}