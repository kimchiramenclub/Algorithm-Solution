import java.io.*;

class Main {
    /*  그리디
        - (-)부호가 나온 순간 부터, 다 마이너스 그룹에 더해주기
        - (-)부호가 한번이라도 나오기 전까지는 플러스 그룹으로

        n+n-(n+n)-(n+n)-(n)-(n+n+n)
    */

    static int solution(String opr) {
        int result = 0;
        int lastIdx = 0;
        boolean isMinus = false;

        for(int i=0;i<opr.length();i++){
            char sign = opr.charAt(i);

            // 연산 부호를 만난다면
            if(sign =='+' || sign == '-'){
                int num = Integer.parseInt(opr.substring(lastIdx, i));
                lastIdx = i + 1;

                // 첫 - 부호 전에는 무조건 +
                if(!isMinus) {
                    result += num;
                    if(sign =='-') isMinus = true;
                }
                // 첫 - 부호 이후, 무조건 -
                else result -= num;
            }
        }
        // 마지막 숫자 연산
        // 마지막까지 + 부호만 있었으면 덧셈, - 부호가 한번이라도 나왔다면 뺄셈
        int lastNum = Integer.parseInt(opr.substring(lastIdx, opr.length()));
        result = isMinus ? result - lastNum : result + lastNum;

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(solution(br.readLine()));
        br.close();
    }
}