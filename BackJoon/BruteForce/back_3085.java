package BackJoon.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class back_3085 {

    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        int MaxLength = 0;
        int checkMax; // MaxLength와 체크해서 그 길이를 넘을 경우 갱신
        boolean jump_flag; // jump를 했는 지 안 했는지 판별하는 변수

        // 1. 각 줄마다 입력을 받고 쪼개서 배열에 넣음
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        // 2. 배열을 가로, 세로로 돌면서 가장 긴 사탕 줄 찾기? (한번 점프까지만 가능. 그 다음이 같은 사탕이라면!)
        //   ㄴ 각 색깔마다 점프를 다 저장해야하므로 기각
        //   ㄴ + 점프 개념으로는  초 초 노        초 초 초            이런 방식이 안됨.
//                                          초              노

        // 2-1 MaxLength를 갱신하면서 나아가야함.
        // check row
        for (int i = 0; i < board.length; i++) {
            checkMax = 1;
            jump_flag = false;
            int tmp = 0; // 점프했다가 끊기면 다시 돌아갈 행의 위치   ex) 초 노 초 노 노  일 경우 j = 1 위치 저장해둬야 함.
            for (int j = 0; j < board.length - 1; j++) {
                if (board[i][j] == board[i][j + 1]) {
                    checkMax++;  // 옆의 사탕이 같은 사탕이라면 length 갱신
                } else if (chkRow(i,j,jump_flag)) { //점프해도 같은 사탕이고, 주변에 같은 사탕이 있고, 점프한 적이 없다면
                    jump_flag = true;
                    checkMax += 2; // 점프 주변의 사탕도 같은색, 점프 후도 같은 색이므로 2 up
                    tmp = j + 1; // 점프 사이의 사탕 위치 임시 저장
                    j++;
                } else if (jump_flag){ // 점프한 적이 있다면, 점프 전 저장한 위치로 return
                    jump_flag = false;
                    j = tmp;
                }
            }
            if (checkMax > MaxLength) { MaxLength = checkMax;}
        }

        for (int j = 0; j < board.length; j++) {
            checkMax = 1;
            jump_flag = false;
            int tmp = 0; // 점프했다가 끊기면 다시 돌아갈 열의 위치   ex) 초 노 초 노 노  일 경우 j = 1 위치 저장해둬야 함.
            for (int i = 0; i < board.length - 1; i++) {
                if (board[i][j] == board[i + 1][j]) {
                    checkMax++;  // 옆의 사탕이 같은 사탕이라면 length 갱신
                } else if (chkCol(i,j,jump_flag)) { //점프해도 같은 사탕이고, 주변에 같은 사탕이 있고, 점프한 적이 없담녀
                    jump_flag = true;
                    checkMax += 2; // 점프 주변의 사탕도 같은색, 점프 후도 같은 색이므로 2 up
                    tmp = i + 1; // 점프 사이의 사탕 위치 임시 저장
                    i++;
                } else if (jump_flag){
                    jump_flag = false;
                    i = tmp;
                }
            }
            if (checkMax > MaxLength) { MaxLength = checkMax;}
        }

        System.out.println(MaxLength);
    }

    // 점프할 때
    static boolean chkRow(int i, int j, boolean flag) { // 점프 위치 주변을 체크하는 메소드 (같은 색 사탕이 있는지)
        if (j < board.length - 2 && board[j] == board[j + 2] && !flag) {
            if (i == 0 && board[i][j] == board[i + 1][j + 1]) {
                return true;
            } else if (i == board.length - 1 && board[i][j] == board[i + 1][j - 1]) {
                return true;
            } else if (i != 0 && i != board.length - 1 && (board[i][j] == board[i + 1][j - 1] || board[i][j] == board[i + 1][j + 1])) {
                return true;
            }
        }
        return false;
    }

    static boolean chkCol(int i, int j, boolean flag) {
        if (j == 0 && board[i][j] == board[i + 1][j + 1]) {
            return true;
        } else if (j == board.length - 1 && board[i][j] == board[i + 1][j - 1]) {
            return true;
        } else if (j != 0 && j != board.length - 1 && (board[i][j] == board[i + 1][j + 1] || board[i][j] == board[i + 1][j - 1])) {
            return true;
        }
        return false;
    }

}

