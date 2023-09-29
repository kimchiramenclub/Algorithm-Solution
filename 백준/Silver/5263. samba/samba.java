import java.io.IOException;
import java.util.*;

class Main {
    /*  해시맵
     * */

    public static void main(String[] args) throws IOException {
        int n = readInt();
        int k = readInt();
        HashMap<Integer, Integer> danceGroup = new HashMap<>();

        for(int i=0;i<n;i++){
            int dancerId = readInt();
            if(danceGroup.containsKey(dancerId)) danceGroup.put(dancerId , danceGroup.get(dancerId)+1);
            else danceGroup.put(dancerId, 1);
        }

        int answer = 0;
        for(Map.Entry<Integer, Integer> group : danceGroup.entrySet()){
            if(group.getValue() % k != 0){
                answer = group.getKey();
                break;
            }
        }

        System.out.println(answer);
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
