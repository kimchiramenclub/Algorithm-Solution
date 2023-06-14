import java.io.*;
import java.util.*;

public class Main {
    // 시행횟수가 가장 적은 방법?

    public static String solution(int[] numbers){
        Arrays.sort(numbers);
        if(numbers[0]+numbers[1]<=numbers[2]) {return "Invalid";}
        else if(numbers[0]==numbers[2]) {return "Equilateral";}
        else if(numbers[0]==numbers[1] || numbers[1]==numbers[2]) {return "Isosceles";}
        else {return "Scalene";}
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[3];

        int temp = 0;
        while(st.hasMoreTokens()){
            temp %= 3;
            numbers[temp] = Integer.parseInt(st.nextToken());


            if(++temp == 3) {
                if(numbers[temp-1] == 0) {System.exit(0);}

                System.out.println(solution(numbers));
                st = new StringTokenizer(br.readLine());
            }
        }
    }
}