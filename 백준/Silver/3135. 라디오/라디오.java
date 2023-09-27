import java.io.IOException;

class Main {
    /*
     * */

    public static void main(String[] args) throws IOException {
        int A = readInt();
        int B = readInt();
        int N = readInt();

        int channelDiff = Math.abs(A-B);
        boolean changedChannel = false;

        for(int i=0;i<N;i++){
            int diff = Math.abs(readInt()-B);
            if(channelDiff > diff) {
                channelDiff = diff;
                changedChannel = true;
            }
        }
        if(changedChannel) channelDiff++;
        System.out.println(channelDiff);
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
