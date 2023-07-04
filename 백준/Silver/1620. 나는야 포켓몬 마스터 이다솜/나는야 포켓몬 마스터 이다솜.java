import java.io.*;
import java.util.*;

public class Main {

    /* 해시맵
     */
    public static Map<Integer, String> pokemon;
    public static Map<String, Integer> pokemon_value;


    public static String[] solution(int N, int M, String[] input) {
        String[] answer = new String[M];

        for(int i=0;i<M;i++){
            // 문제가 숫자일 때
            if(input[i].charAt(0) <= 57){
                answer[i] = pokemon.get(Integer.parseInt(input[i]));
            } else{ // 문제가 포켓몬 이름일 때
                answer[i] = pokemon_value.get(input[i]).toString();
            }
        }
        return answer;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // 해쉬맵은 value를 통해 key 전달하는 메서드는 없으므로, 포켓몬 이름을 key, value 동시에 담음.
        pokemon = new HashMap<>();
        pokemon_value = new HashMap<>();

        String[] input = new String[M];

        for(int i=1;i<=N;i++){
            String tmp = br.readLine();
            pokemon.put(i, tmp);
            pokemon_value.put(tmp, i);
        }

        for(int i=0;i<M;i++){
            input[i] = br.readLine();
        }

        String[] answer = solution(N, M, input);
        for(String ans : answer){
            System.out.println(ans);
        }
    }
}