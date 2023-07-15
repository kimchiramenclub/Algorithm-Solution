import java.io.*;
import java.util.*;

public class Main {

    /*  해쉬 맵
        - Y(2), F(3), O(4)
 */

    public static int solution(int N, char game, String[] people) {
        HashSet<String> play = new HashSet<>();
        int party = 1;
        int count = 0;
        int played = 0;

        switch(game){
            case 'F': party++; break;
            case 'O': party+=2; break;
        }

        for(String p : people){
            if(!play.contains(p)) {
                play.add(p);
                count++;
                if(count == party){
                    count %= party;
                    played++;
                }
            }
        }

        return played;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        char game = st.nextToken().charAt(0);
        String[] people = new String[N];
        for(int i=0;i<N;i++){
            people[i] = br.readLine();
        }

        System.out.println(solution(N, game, people));
    }
}

