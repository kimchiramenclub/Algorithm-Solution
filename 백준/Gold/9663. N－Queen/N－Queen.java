import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int count = 0;
    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        boolean[] flag_a = new boolean[N];
        boolean[] flag_b = new boolean[2*N-1];
        boolean[] flag_c = new boolean[2*N-1];
        int[] pos = new int[N];
        
                
        
        setQueen(0,flag_a,flag_b,flag_c,pos);
        System.out.println(count);
    }
    
    public static void setQueen (int i, boolean[] flag_a, boolean[] flag_b, boolean[] flag_c, int[] pos) {
        for(int j = 0;j < N; j++) {  // 맨 첫번째 분기로 돌아왔을때, 0번째 행에 놓을 퀸의 열을 바꿔주는 역할을 함. 
            if(flag_a[j] == false &&
               flag_b[i+j] == false && // 알고리즘 교재 197p 참조
//               (0,2),(1,1),(2,0) ==> 다 i+j값이 같고, 위-오 대각선 이동임. 
//               따라서, i+j = 2인 / 대각선에 있나 없나 체크 가능
              flag_c[i-j+N-1] == false)
//              (0,0),(1,1),(2,2) or (0,3),(1,4),(2,5) ==> 다 i-j값이 같고, \ 대각선 방향 이동임.
//              0~15로 index를 맞추기 위해 N-1을 더해주는 것. ( (0,N-1)=> -(N-1)이 가장 작은 값이라)            
            {
                pos[i] = j;
                if (i == N-1) {count++;} // 열의 index가 N-1까지 도달하면 count를 1 올리고 쭉 빠져나오며 분기 마무리
                else {
                    flag_a[j] = flag_b[i+j] = flag_c[i-j+N-1] = true;
                    setQueen(i + 1,flag_a,flag_b,flag_c,pos); // 각 flag에 true를 대입하고서는 분기의 가지로 더 뻗어감
                    flag_a[j] = flag_b[i+j] = flag_c[i-j+N-1] = false; // 가지를 다 빠져나온 후 새로운 분기를 만들기 위해 다시 false처리
                }
            }
        }
        
    }
}
