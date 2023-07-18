import java.io.*;
import java.util.StringTokenizer;

public class Main {

    /*
 */
    public static int blue = 0;
    public static int white = 0;

    public static int[] solution(boolean[][] colors){
        checkColor(colors, 0, colors.length, 0 , colors.length);
        return new int[]{white, blue};
    }

    public static void checkColor(boolean[][] colors, int lowX, int highX, int lowY, int highY){
        boolean color = colors[lowY][lowX]; // 초기 색깔 저장

        Loop : for(int i=lowY;i<highY;i++){
            for(int j=lowX; j<highX; j++){
                if(colors[i][j] != color){ // 다른 색이 나오면, 색종이를 분할하고 break
                    color = colors[i][j]; // 초기 색깔 변경
                    checkColor(colors, lowX, (lowX+highX)/2, lowY, (lowY+highY)/2);
                    checkColor(colors, (lowX+highX)/2, highX ,lowY, (lowY+highY)/2);
                    checkColor(colors, lowX, (lowX+highX)/2, (lowY+highY)/2, highY);
                    checkColor(colors, (lowX+highX)/2, highX, (lowY+highY)/2, highY);
                    break Loop;
                }
            }
        }
        if(color == colors[lowY][lowX]){ // 색 변경을 겪지 않고 도달했다면, 색종이의 영역이 모두 같은 색
            if(color) blue++;
            else white++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        boolean[][] colors = new boolean[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                if(Byte.parseByte(st.nextToken()) == (byte)1) colors[i][j] = true;
                else colors[i][j] = false;
            }
        }

        for(int ans : solution(colors)) System.out.println(ans);
    }
}