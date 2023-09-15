import java.io.*;
import java.util.HashMap;

class Main {


    static String[] scaleArray;
    static HashMap<String, Integer> scale;
    static StringBuilder sb = new StringBuilder();

    static void solution(String testCase, int move){
        String[] tc = testCase.split(" ");
        int[] tcIdx = new int[tc.length];

        for(int i=0;i<tc.length;i++){
            if(scale.containsKey(tc[i])) {
                tcIdx[i] = scale.get(tc[i]) % 12;
            }
            else{
                String T = tc[i];

                if(T.charAt(1) == 'b'){
                    T = String.valueOf(T.charAt(0));
                    tcIdx[i] = (12 + scale.get(T) - 1) % 12;
                } else{
                    T = String.valueOf(T.charAt(0));
                    tcIdx[i] = (12 + scale.get(T) + 1) % 12;
                }

            }
        }

        for(int i=0;i<tcIdx.length;i++){
            tcIdx[i] = (tcIdx[i]+move+12) % 12;
            sb.append(scaleArray[tcIdx[i]]).append(" ");
        }
        sb.append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String tmp = "A A# B C C# D D# E F F# G G#";
        scaleArray = tmp.split(" ");
        scale = new HashMap<>(12);
        for(int i=0;i<scaleArray.length;i++) scale.put(scaleArray[i], i);

        while(true){
            String testCase = br.readLine();
            if(testCase.charAt(0) == '*') break;

            int move = Integer.parseInt(br.readLine());
            solution(testCase, move);
        }

        System.out.println(sb);
    }
}
