import java.io.IOException;

class Main {
    /*  정렬

         - Heap을 쓰면 얼마나 빨라지는 지 체크 위해 https://www.acmicpc.net/source/66032163 이분 코드 참고
     * */

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int N = nextInt();
        Heap heap = new Heap(N);
        // 학생 배열에 학생 정보 저장
        for (int i = 0; i < N; i++) heap.offer(new Student(nextString(), nextInt(), nextInt(), nextInt()));

        // 학생 정렬 및 출력
        while (!heap.isEmpty()) sb.append(heap.poll()).append('\n');
        System.out.print(sb);
    }

    static class Heap {
        Student[] stu;
        int size;

        Heap(int capacity) {
            stu = new Student[capacity + 1];
        }

        private void swap(int i1, int i2) {
            Student tmp = stu[i1];
            stu[i1] = stu[i2];
            stu[i2] = tmp;
        }

        public boolean offer(Student s) {
            stu[++size] = s;

            int idx = size;

            while (idx >> 1 > 0 && stu[idx >> 1].compareTo(stu[idx]) > 0) {
                swap(idx >> 1, idx);
                idx >>= 1;
            }

            return true;
        }

        public String poll() {
            int idx = 1;
            Student res = stu[idx];
            stu[idx] = stu[size];
            stu[size--] = null;

            while ((idx <<= 1) <= size) {
                if (stu[idx + 1] != null)
                    idx = stu[idx].compareTo(stu[idx + 1]) < 0 ? idx : idx + 1;

                if (stu[idx >> 1].compareTo(stu[idx]) <= 0)
                    break;

                swap(idx >> 1, idx);
            }

            return res.name;
        }

        public boolean isEmpty() {
            if (size == 0)
                return true;
            return false;
        }
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
        while (64 < (c = read()) && c < 123);
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