import java.io.IOException;

class Main {
    /* 
     * */


    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[] numSet = new int[10];

        while(N > 0){
            numSet[N%10]++;
            N /= 10;
        }
        int SixOrNine = (numSet[6]+numSet[9]) % 2 == 0 ? (numSet[6]+numSet[9]) / 2 : (numSet[6]+numSet[9]+1)/2;
        numSet[6] = numSet[9] = SixOrNine;

        int max = 0;
        for(int i=0;i<10;i++) max = Math.max(max, numSet[i]);

        System.out.println(max);
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
