import java.io.IOException;
import java.util.Arrays;

class Main {
    /*  정렬
        - System.in.read()로 숫자 읽는 방법 연습
        - CR : "\r"(커서 맨 앞으로 되돌리기)   LF : "\n"(줄 바꿈)
        - Windows OS 말고는 LF만으로 줄 바꿈이 됨. CR이 자동화
     */

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int N = readInt();
        int[] array = new int[N];

        for(int i=0;i<N;i++) array[i] = readInt();
        Arrays.sort(array);
        for(int i=0;i<N;i++) sb.append(array[i]).append("\n");
        System.out.println(sb.toString());
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        // c,n 변수를 int로 선언하면서, n에는 숫자의 첫 번째 자리인 '1'을 System.in.read()로 읽어옴
        // c는 선언만 될뿐 initialize가 되지 않은 상태임.
        // System.in.read() : 입력에서 문자 1개를 읽어옴
        // & 15 : 숫자 범위('0'~'9')에 있는 문자에 48을 빼서 숫자로 바꾸는 것과 같음
        int c, sign = 1, n = 0;
        if((c = System.in.read()) == '-') sign = -1;
        else n = c & 15;

        // c로 새로운 숫자문자를 받아오면서 white space 문자(ascii 코드 32 미만)인지 아닌 지 체크
        // << : 쉬프트 연산으로, (<< 1 : * 2^1), (>> 1 : / 2^1)
        // 즉, <<3 + <<1 은  n * 2^3 + n * 2^1  = n*10 (자릿수 바꾸기)
        // + (c & 15) : c를 한 자리 숫자로 변환해서 더하기
        // white space 문자가 나오면 일단 c = System.in.read()로 읽기는 하지만, 직후 탈출하므로 st.nextToken() 같은 역할을 함.
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n * sign;
    }
}