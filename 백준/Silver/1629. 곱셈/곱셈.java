import java.io.IOException;

public class Main {
        /*
            https://st-lab.tistory.com/237 참고

            단순해 보이는 문제지만, 바로 계산을 때릴 경우 2,147,483,647번 연산할 수도 있음.
            (A * A * A) % C = ((A % C) * (A % C)) % C 이런 식으로 분배법칙이 성립함
            지수 법칙 + 모듈러 성질

            a^8 = a^4 * a^4 이고, 값이 같으므로 연산횟수가 줄어듬.
            이런 식으로 계속 지수를 2개로 나눠나가면서 (지수가 홀수일 경우, a가 남는데 그 값은 이미 아는 값), 연산 횟수를 log N으로 줄임.
        
            시간복잡도 O(log N)
        * */

    static long C;

    // 분할 정복
    static long pow(long a, long exponent){
        if(exponent == 1) return a % C;

        // 분할 시작
        // a의 지수를 반으로 나눔.
        long tmp = pow(a, exponent/2);

        // 만약 지수가 홀수였다면,
        /* (tmp * tmp * a) % C  이 계산은 long의 범위를 넘을 수 있음. 따라서, 모듈러 성질을 응용해서,
            (tmp * tmp * a) % C = ((tmp * tmp) % C * (a % C)) mod C
            = (((tmp * tmp % C) % C) * (a % C)) % C                       // tmp * tmp % C % C = tmp * tmp % C
            = ((tmp * tmp % C) * a) % C                 */
        if(exponent % 2 == 1) return (tmp * tmp % C) * a % C ;

        // 지수가 짝수라면
        return tmp * tmp % C;
    }

    public static void main(String[] args) throws Exception {
        long A = readLong();
        long B = readLong();
        C = readLong();

        System.out.println(pow(A, B));
    }

    static long readLong() throws IOException{
        long c, n = 0;
        while((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}