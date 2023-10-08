class Main {
    /*  두 포인터
        - 최대 합이 3억 이하이므로, int 범위로 해결가능
     */

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        int M = nextInt();
        int[] arr = new int[N + 1];
        for(int i=1;i<=N;i++) arr[i] = arr[i-1] + nextInt();    // 누적합 배열 입력

        int s = 0;  // arr[j]-arr[i-1] == A[i]+...+A[j] 
        int e = 1;
        int count = 0;
        while(e <= N){
            if(arr[e]-arr[s] == M) {count++; s++;}  // ==M 인경우, count++, 다음 경우의 수를 위해 포인터 s 이동
            else if(arr[e]-arr[s] > M) s++; // 누적합이 M보다 크므로, 포인터 s 이동
            else e++;   // 누적합이 M보다 작으므로, 포인터 e 이동
        }
        System.out.println(count);
    }

    static int idx, size, SIZE = 1 << 13;
    static byte[] buf = new byte[SIZE];

    static int nextInt() throws Exception {
        int n = 0;
        byte c;
        while ((c = read()) <= 32) ;
        do n = (n << 3) + (n << 1) + (c & 15);
        while (47 < (c = read()) && c < 58);
        return n;
    }

    static byte read() throws Exception {
        if (size == idx) {
            size = System.in.read(buf, idx = 0, SIZE);
            if (size < 0) buf[0] = -1;
        }
        return buf[idx++];
    }
}