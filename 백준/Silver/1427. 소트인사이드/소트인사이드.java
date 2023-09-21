import java.io.IOException;

class Main {
    /*  정렬
     * */

    static int[] array = new int[10];

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int lastIdx = readDigits();
        int tmp = 0;

        for(int i=0;i<= lastIdx;i++){
            for(int j=i;j<=lastIdx;j++){
                if(array[i]<array[j]){
                    tmp = array[i];
                    array[i]= array[j];
                    array[j] = tmp;
                }
            }
        }

        // array의 각 index에 저장된 숫자의 'ascii' 숫자대로, 해당 digit을 print
        for(int i = 0; i<=lastIdx; i++) System.out.write(array[i]);
        System.out.flush();
    }

    // 입력에서 한 자리 숫자를 읽고, 해당 ascii number로 저장하는 메서드
    static int readDigits() throws IOException {
        int c, i = -1;
        while((c = System.in.read()) >= 32) array[++i] = c;
        return i;
    }
}