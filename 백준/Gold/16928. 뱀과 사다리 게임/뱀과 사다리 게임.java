import java.io.*;
import java.util.*;

class Main {
    /*  그래프 탐색, BFS + 동적계획법?


       BFS 안 쓰는 방법 :
        - 사다리가 6 이내에 있는 지 체크하고, 사다리 이동 숫자가 6보다 크면 사다리로 이동
        - 6이 뱀인지 체크.
        - 사다리 타자마자 뱀은 아닌지 체크
        - 이 모든 게 아닐 경우, 항상 6 이동.

        * 극단적인 케이스 :
        - 만약 1에서 2~7이 모두 사다리라면? -> 이 경우는 해결. moveUp 으로 최고 이동을 계산
        - 만약 1에서 2~7이 모두 뱀이라면?
          ㄴ 만약 5만 뱀이 아니지만, 7칸의 뱀은 6으로 이동시킨다면?

    */

    // BFS
    static int solution(int[] board, boolean[] visited){
        Queue<int[]> move = new ArrayDeque<>();
        move.offer(new int[]{1,0});
        visited[1] = true;

        while(!move.isEmpty()){
            int[] D = move.poll();
            // 탈출 조건
            if(D[0] >= 100) return D[1];

            for(int i=6;i>=1;i--){
                // 사다리, 뱀이라면
                if(board[D[0]+i] > 0){
                    // 사다리, 뱀으로 이동한 장소가 방문한 곳이 아니라면
                    if(!visited[board[D[0]+i]]) {
                        visited[board[D[0] + i]] = true;
                        move.offer(new int[]{board[D[0] + i], D[1] + 1});
                    }
                }
                // 사다리, 뱀이 아니고 방문하지 않았다면
                else if(!visited[D[0]+i]){
                    visited[D[0]+i] = true;
                    move.offer(new int[]{D[0]+i, D[1]+1});
                }
            }
        }

        return -1;
    }

//    // Non BFS 시도
//    static int solution(int[] board) {
//        int idx = 1;
//        int moveUp = 0;
//        int dice;
//        int count = 0;
//
//        while (idx < 100) {
//            moveUp = idx+6;
//            dice = 6;
//
//            // 사다리, 뱀 찾기
//            for (int i = 6; i >= 1; i--) {
//                // 사다리 또는 뱀을 마주한다면
//                if (board[idx + i] > 0) {
//                    // 6 이상의 사다리라면 움직일 칸 변경
//                    if (board[idx + i] > idx + 6 && board[idx + i] > moveUp) {
//                        moveUp = board[idx + i];
//                        dice = i;
//                    }
//                    // 수정 필요
//                    // 최상의 이동이 뱀이라면, 주사위 이동을 1 낮추기
//                    // ex) 6 이동하려는 데 뱀이면, 5 이동. 5 이동도 뱀이면, 4 이동
//                    else if(board[idx + i] < idx + i && dice == i){
//                        dice--;
//                    }
//                }
//            }
//            // 주사위 혹은 사다리 타고 이동
//            idx = Math.max(idx+dice, board[idx + dice]);
//            count++;
//        }
//        return count;
//    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] board = new int[106];  //99+6 가정
        boolean[] visited = new boolean[106];

        for (int i = 1; i <= N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            board[index] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(board, visited));
    }
}