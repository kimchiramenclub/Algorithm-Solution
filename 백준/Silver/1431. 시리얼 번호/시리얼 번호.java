import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        ArrayList<Serial> serials = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            String serial = br.readLine();
            serials.add(new Serial(serial, intSum(serial)));
        }
        Collections.sort(serials);

        for (Serial s : serials) sb.append(s.s).append('\n');
        System.out.println(sb);
    }

    static int intSum(String s) {
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '1' && s.charAt(i) <= '9') {
                n += (s.charAt(i) & 15);
            }
        }
        return n;
    }

    static int compareString(String o1, String o2) {
        int result = 0;

        for (int i = 0; i < o1.length(); i++) {
            if (o1.charAt(i) != o2.charAt(i)) {
                result = o1.charAt(i) - o2.charAt(i);
                break;
            }
        }
        return result;
    }

    public static class Serial implements Comparable<Serial> {
        String s;
        int sum;

        Serial(String s, int sum) {
            this.s = s;
            this.sum = sum;
        }

        @Override
        public int compareTo(Serial o) {
            if (this.s.length() - o.s.length() != 0) {
                return this.s.length() - o.s.length();
            } else if (this.sum - o.sum != 0) {
                return this.sum - o.sum;
            } else {
                return compareString(this.s, o.s);
            }
        }
    }
}