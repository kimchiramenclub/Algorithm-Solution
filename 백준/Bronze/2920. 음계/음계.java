import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        boolean finished = true, ascending;
        int scale = readInt();
        if(scale == 1) ascending = true;
        else ascending = false;

        for (int i = 2; i <= 8; i++) {
            scale = readInt();
            if(!(ascending && scale == i) && !(!ascending && scale == 9-i)){
                finished = false;
                break;
            }
        }

        if(!finished) System.out.println("mixed");
        else if(scale == 8) System.out.println("ascending");
        else System.out.println("descending");
    }

    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
