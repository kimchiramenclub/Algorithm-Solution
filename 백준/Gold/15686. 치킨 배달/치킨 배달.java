import java.io.IOException;
import java.util.ArrayList;

class Main {
    /*  백트래킹 - 치킨 배달

        - 치킨 거리합의 min을 구하는 문제
        - M 값에 따라, 백트래킹으로 치킨집 조합을 만들 수 있음. 재귀 함수
        - 집, 치킨집을 각각 arraylist로 저장 후, 이중 for문을 통해 집-치킨집 거리 배열 생성
        - 치킨집 조합에 따라, 각 집은 가장 가까운 치킨집을 선택해야 함   ex) 치킨집 1 2 3  -> 집 4는 치킨집 1이 젤 가깝
            ㄴ 조합을 완성했을 때, 각 집마다 어떤 치킨집과 가장 가까운 지 구하도록
     **/
    
    static ArrayList<Point> home, chic; // 집, 치킨집 좌표 배열
    static int[][] chicDist;    // 치킨거리 배열
    static int[] pickedChic;   // 선택된 조합의 치킨집 (M 크기)
    static int total = Integer.MAX_VALUE;    // 답

    // 치킨집을 1-2-3, 1-2-4 이런 식으로 조합해서 backtracking 하는 메서드
    public static void backTrack(int start, int picked, int M) {

        // 조합 선택이 완료되면, 각 집마다 어떤 치킨집이 가장 가까운 지 계산 후, 최소값으로 갱신
        if(picked >= M){
            int sum = 0;

            for(int h = 0; h < home.size(); h++){
                int dist = Integer.MAX_VALUE;
                for(int pc : pickedChic){
                    if(chicDist[h][pc] < dist) dist = chicDist[h][pc];
                }
                sum += dist;
            }

            if(sum < total) total = sum;
            return;
        }

        // 치킨집 조합 선택 (재귀)
        for (int i = start; i < chic.size(); i++){
            pickedChic[picked] = i;
            backTrack(i + 1, picked + 1, M);
        }
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();

        home = new ArrayList<>(2 * N);
        chic = new ArrayList<>(13);

        // 집, 치킨집 (동적)배열 생성
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int info = readInt();
                if (info == 1) home.add(new Point(i, j));
                else if (info == 2) chic.add(new Point(i, j));
            }
        }

        chicDist = new int[home.size()][chic.size()];   // 각 집과 각 치킨집 간의 치킨 거리를 저장한 배열

        // 집, 치킨집 배열을 순회하며 집 - 치킨집 거리 정보 저장
        for (int i = 0; i < home.size(); i++) {
            Point h = home.get(i);
            for (int j = 0; j < chic.size(); j++) {
                Point c = chic.get(j);
                chicDist[i][j] = Math.abs(h.x - c.x) + Math.abs(h.y - c.y);
            }
        }

        pickedChic = new int[M];    // 치킨집 조합. 선택한 치킨집의 idx를 저장하는 배열
        backTrack(0, 0, M);
        System.out.println(total);
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 입력 관련
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}