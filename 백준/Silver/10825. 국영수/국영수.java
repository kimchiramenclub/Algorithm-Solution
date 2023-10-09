import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    /*  정렬
     * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

//        int N = nextInt();
        int N = Integer.parseInt(br.readLine());
        Student[] students = new Student[N];
        // 학생 배열에 학생 정보 저장
//        for (int i = 0; i < N; i++) students[i] = new Student(nextString(), nextInt(), nextInt(), nextInt());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            students[i] = new Student(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 학생 정렬 및 출력
        Arrays.sort(students);
        for (int i = 0; i < N; i++) sb.append(students[i].name).append('\n');
        System.out.print(sb);
    }

    static class Student implements Comparable<Student> {
        String name;
        int kor, eng, math;

        Student(String name, int kor, int eng, int math) {
            this.name = name;
            this.kor = kor;
            this.eng = eng;
            this.math = math;
        }

        // Student array를 조건대로 정렬하기 위한 implementation
        @Override
        public int compareTo(Student o) {
            if (this.kor != o.kor) return o.kor - this.kor; // 국어 감소 정렬
            else if (this.eng != o.eng) return this.eng - o.eng;    // 영어 증가 정렬
            else if (this.math != o.math) return o.math - this.math;    // 수학 감소 정렬
            else return this.name.compareTo(o.name);    // 이름 사전순 정렬
        }
    }

    static int idx, size, SIZE = 1 << 13;
    static byte[] buf = new byte[SIZE];

    static String nextString() throws IOException {
        StringBuilder sb = new StringBuilder();
        byte c;
        while ((c = read()) <= 32) ;
        do sb.append((char) c);
        while (65 < (c = read()) && c < 122);
        return sb.toString();
    }

    static int nextInt() throws IOException {
        int n = 0;
        byte c;
        while ((c = read()) <= 32) ;
        do n = (n << 3) + (n << 1) + (c & 15);
        while (47 < (c = read()) && c < 58);
        return n;
    }

    static byte read() throws IOException {
        if (size == idx) {
            size = System.in.read(buf, idx = 0, SIZE);
            if (size < 0) buf[0] = -1;
        }
        return buf[idx++];
    }
}