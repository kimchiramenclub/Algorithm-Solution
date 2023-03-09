package Programmers.Stack_Queue;
// https://velog.io/@kimbad1992/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EA%B8%B0%EB%8A%A5%EA%B0%9C%EB%B0%9C

import java.util.Arrays;

public class pro_featureDev {
    public int[] solution(int[] progresses, int[] speeds) {

        int[] releaseDateCount = new int[100];
        int day = -1;

        for(int i=0; i<progresses.length; i++) {
            while(progresses[i] + speeds[i] * day < 100){
                day++;
            }
            releaseDateCount[day]++;
        }

        // 바꾸는 과정이 메모리를 많이 잡아먹는다함. for문으로 고치는 게 메모리상 효율적
        // int[] answer = Arrays.stream(releaseDateCount).filter(i -> i!=0).toArray();
        return Arrays.stream(releaseDateCount).filter(i -> i!=0).toArray();

    }







   /* public static void main(String[] args) {
        // 테스트 코드
        int[] progresses = new int[5];
        int[] speeds = new int[5];

        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            progresses[i] = in.nextInt();
        }
        for (int i = 0; i < 5; i++) {
            speeds[i] = in.nextInt();
        }

        int[] releaseDateCount = new int[100];
        int day = -1;

        for(int i=0; i<progresses.length; i++) {
            while(progresses[i] + speeds[i] * day < 100){
                day++;
            }
            releaseDateCount[day]++;
        }
*/





    }

