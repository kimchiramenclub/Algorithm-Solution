import java.io.*;
import java.util.*;

class Main {
    /*  정렬
     * */

    static ArrayList<Integer> array = new ArrayList<>(10);

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        readDigits();
        array.sort(Collections.reverseOrder()); // 배열 정렬

        // array의 각 index에 저장된 숫자의 'ascii' 숫자대로, 해당 digit을 print
        for(int num : array) System.out.write(num);
        System.out.flush();
    }

    // 입력에서 한 자리 숫자를 읽고, 해당 ascii number로 저장하는 메서드
    static void readDigits() throws IOException {
        int c;
        while((c = System.in.read()) >= 32) array.add(c);
    }
}