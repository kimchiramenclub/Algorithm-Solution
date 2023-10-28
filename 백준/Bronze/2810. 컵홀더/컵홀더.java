import java.io.*;

class Main {
    /*  그리디
        - S : 2개의 홀더
        - LL : 2개의 홀더
        LL 을 사실상 S 처럼 한 개의 좌석으로 취급 가능. 단, LL의 경우 + 1을 해줘야함.
        - 단 한번이라도 LL이 끼면, 무조건 +1
     **/

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String seats = br.readLine();
        int coupleNum = 0;

        for (int i = 0; i < seats.length(); i++){
            if(seats.charAt(i) == 'L'){
                coupleNum++;
                i++;
            }
        }

        System.out.println(coupleNum == 0 ? N : N - coupleNum + 1);
    }
}