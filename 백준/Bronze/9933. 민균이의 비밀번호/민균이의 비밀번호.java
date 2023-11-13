import java.io.*;
import java.util.*;

public class Main {

    static boolean[] roll;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        HashMap<String,Integer> pwd = new HashMap<>(N);

        for(int i=0;i<N;i++){
            String word = br.readLine();
            int len = word.length();

            if(!pwd.containsKey(word)) pwd.put(word, len);
            if(pwd.containsKey(reverse(word))){
                System.out.println(len+" "+word.charAt(len/2));
                break;
            }
        }
    }

    static String reverse(String word){
        StringBuilder rev = new StringBuilder(word).reverse();
        return rev.toString();
    }
}