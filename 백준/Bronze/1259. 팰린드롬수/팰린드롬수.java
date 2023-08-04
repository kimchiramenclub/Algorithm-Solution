import java.io.*;

public class Main {
    /*      구현
     */

    static String solution(String tc) throws IOException{

        for(int i=0;i<tc.length()/2;i++){
            if(tc.charAt(i) != tc.charAt(tc.length()-1-i)) return "no";
        }
        return "yes";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String tc;
        while(true){
            tc = br.readLine();
            if(tc.charAt(0) == '0') break;

            bw.write(solution(tc));
            bw.newLine();
        }

        bw.flush();
    }
}
