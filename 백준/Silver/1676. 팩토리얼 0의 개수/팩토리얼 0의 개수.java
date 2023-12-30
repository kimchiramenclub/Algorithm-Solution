import java.io.*;
    
    public class Main {
        public static void main(String[] args) throws IOException {
            int c, N = 0, tmp;
            while ((c = System.in.read()) > 32) N = (N << 3) + (N << 1) + (c & 15);
    
            int[] twos = new int[N + 1];
            int[] fives = new int[N + 1];
    
            for (int i = 1; i <= N; i++) {
                tmp = i;
                twos[i] = twos[i-1];
                fives[i] = fives[i-1];
    
                while(tmp % 2 == 0){
                    tmp /= 2;
                    twos[i]++;
                }
                while(tmp % 5 == 0){
                    tmp /= 5;
                    fives[i]++;
                }
            }
            System.out.println(Math.min(twos[N], fives[N]));
        }
    }