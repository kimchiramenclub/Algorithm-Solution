import java.io.IOException;

class Main {
    /*  수학
        - (A-B)*n >= V-A 가 되는 순간, 다음 날 도달
        - / 연산 특징 상 나머지가 카운트가 안됨.  5 2 10의 경우 3일에 도달인데,
            (10-5)/(5-2)+1 = 2 ( 도달에 1.XX일이 걸리는데 XX가 카운트 안됨).
            그러므로, 나머지가 0이 아니면 +1
        */

    public static void main(String[] args) throws IOException {
        int A = readInt();
        int B = readInt();
        int V = readInt();

        int days = (V-A) / (A-B) + 1;
        if ((V - A) % (A - B) != 0) days++;
        System.out.println(days);
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}