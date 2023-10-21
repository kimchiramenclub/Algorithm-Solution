import java.io.IOException;

class Main {
    /*
     **/
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        for(int i=1;;i++){
            int L = readInt();
            int P = readInt();
            int V = readInt();
            if(L == 0 && P == 0 && V == 0) break;

            int maxDay = 0;
            maxDay += (V / P * L);
            maxDay += Math.min((V % P), L);

            sb.append("Case ").append(i).append(": ").append(maxDay);
            sb.append('\n');
        }

        System.out.print(sb);
    }

    static int readInt() throws IOException{
        int c, n = 0;
        while((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}