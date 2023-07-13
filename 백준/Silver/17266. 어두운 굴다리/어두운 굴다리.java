import java.io.*;
import java.util.*;

public class Main {

    /*  이분탐색
        - 이진탐색 구현

        - 겹치는 일이 있어도 굴다리 전체가 커버되야함
        - 가로등 위치는 규칙적이지 않음.
        - 첫 가로등부터 시작, 끝을 체크해서 다음 가로등의 시작 끝과 이어지는 지 체크?
        - 다음 가로등과 겹치기까지 부족한 거리를 재서, 그만큼 추가하기.
        - loc[i]의 low와 loc[i-1]의 high가 겹치는 지 체크

        문제 :
        - 높이를 확 높여버려서 최소 높이가 아닌 경우가 있음
        - 이진 탐색이 아니라 greedy 방식으로 풀고 있었음

        수정 :
        - 높이를 1~N 사이에서 이진 탐색으로 찾고, check 메서드에 내가 사용한 방식을 이용하게 변경
        - 1차 풀이는 백준에 틀린 기록으로 남기겠음
 */

    public static int solution(int N, int M, int[] loc) {
        return binarySearch(N, loc, 1, N);
    }

    public static int binarySearch(int N, int[] loc, int low, int high) {
        if(low >= high) return low; // 탈출 조건

        int mid = (low + high) / 2;
        if (checkLight(N, loc, mid)) return binarySearch(N, loc, low, mid);
        else return binarySearch(N, loc, mid+1, high);
    }

    public static boolean checkLight(int N, int[] loc, int height) {
        int moved = 0; // 이동한 거리

        for (int index : loc) {
            if (index - height > moved) return false; // 이동한 거리까지 다음 가로등이 비춰주지 못한다면 false
            moved = index + height; // 가로등이 비추는 범위까지 이동
        }
        return moved >= N; // 굴다리를 다 이동했다면 true, 아니라면 false
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] loc = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            loc[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, M, loc));
    }
}
