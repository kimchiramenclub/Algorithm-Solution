package Sorting;

import java.util.Arrays;

public class pro_BiggestNum {

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {

            int mid = (left + right) / 2;
            // 재귀로 분할
            mergeSort(arr, 0, mid);
            mergeSort(arr, mid + 1, right);
            // 병합
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        // 병합할 두 배열의 크기만큼의 임시 배열 생성
        int[] temp = new int[right - left + 1];

        int i = left; // 왼쪽 배열의 시작 인덱스
        int j = mid + 1; // 오른쪽 배열의 시작 인덱스
        int k = 0; // 임시 배열의 인덱스

        // 왼쪽 배열과 오른쪽 배열을 비교하며 순서대로 임시 배열에 삽입하고 병합
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // 한 쪽 배열이 끝났을 경우, 다른 쪽 배열의 나머지 값들 삽입
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // 임시 배열의 값들을 원래 배열에 복사
        for(int l = 0; l < k; l++) {
            arr[left + l] = temp[l];
        }
    }

    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        //명령의 갯수에 맞춰 반복문 작성 , 각 반복문마다 i,j,k 값 설정
        for (int row = 0; row < commands.length; row++) {
            int i = commands[row][0];
            int j = commands[row][1];
            int k = commands[row][2];

            // 문제의 요구사항 대로 배열 분할
            int[] sorted = Arrays.copyOfRange(array, i - 1, j - 1);
            mergeSort(sorted, 0, sorted.length - 1);

            // k번째 값을 답 array에 복사
            answer[row] = sorted[k - 1];


        }

        return answer;
    }
}
