import java.io.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = br.readLine().split(" ");

        BigInteger n = new BigInteger(nums[0]);
        BigInteger m = new BigInteger(nums[1]);

        System.out.println(n.divide(m));
        System.out.println(n.mod(m));
    }
}