import java.io.*;

class Main {
    /*
     **/

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[][] array = new char[5][15];
        int[] widthLen = new int[5];

        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            widthLen[i] = line.length();
            array[i] = line.toCharArray();
        }

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                if (i >= widthLen[j]) continue;
                sb.append(array[j][i]);
            }
        }

        System.out.println(sb);
    }
}