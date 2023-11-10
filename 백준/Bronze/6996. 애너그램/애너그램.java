import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char[] A = st.nextToken().toCharArray();
            char[] B = st.nextToken().toCharArray();

            sb.append(A).append(" & ").append(B).append(" are ");
            Arrays.sort(A);
            Arrays.sort(B);
            if (!isAnagram(A, B)) sb.append("NOT ");
            sb.append("anagrams.\n");
        }

        System.out.println(sb);

    }

    public static boolean isAnagram(char[] A, char[] B) {
        if(A.length != B.length) return false;

        for (int i = 0; i < A.length; i++) {
            if(A[i] != B[i]) return false;
        }
        return true;
    }
}