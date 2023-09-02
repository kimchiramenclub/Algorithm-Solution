import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int N = readInt();
        int[][] array = new int[N][2];

        for (int i = 0; i < N; i++){
            array[i][0] = readInt();
            array[i][1] = readInt();
        }

        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
                return Integer.compare(o1[0], o2[0]);
            }
        });

        for(int[] arr : array){
            sb.append(arr[0]).append(" ").append(arr[1]).append("\n");
        }

        System.out.println(sb);
    }

    static int readInt() throws IOException {
        int c, sign = 1, n = 0;
        if ((c = System.in.read()) == '-') sign = -1;
        else n = c & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return sign * n;
    }
}
