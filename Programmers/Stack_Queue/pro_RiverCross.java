package Programmers.Stack_Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

public class pro_RiverCross {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int bridge_length = Integer.parseInt(br.readLine());
        int weight = Integer.parseInt(br.readLine());
        int[] truck_weights = new int[1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<truck_weights.length;i++) {
            truck_weights[i] = Integer.parseInt(st.nextToken());
        }
        // 입력 예시
        // 2
        // 10
        // 7 4 5 6
        // 맨 처음부터 0 다 집어넣어서 과정을 줄일 수 있을까?


        // 건너는 데 다리 길이 만큼 걸림. 큐의 최대 크기를 정해놓고, 이걸 다리의 길이처럼.
        ArrayBlockingQueue<Integer> bridge = new ArrayBlockingQueue<Integer>(bridge_length);
        int timeCount = 0;
        int emptySpace = 0;
        int weightCapacity = weight;


        // 트럭 다리에 넣기
        for(int i=0;i<truck_weights.length;) {
            // 다리에 트럭을 넣기 전에 먼저 다리 큐가 꽉 찼는지 체크 후, 꽉 찼다면 poll하면서 weight에 더해줌.
            if(bridge.size() == bridge_length) {
                weightCapacity += bridge.poll();
            }
            // n의 무게 트럭이 다리 큐에 들어갈 때마다 대기 배열의 index를 넘기고, 다리 capacity(weight)에서 트럭 무게를 뺌.
            if(weightCapacity - truck_weights[i] >= 0) {
                weightCapacity -= truck_weights[i];
                bridge.add(truck_weights[i]);
                i++;
            }
            // 만약 빼서 -가 되는지 체크하고, -면 안 뺌. 이 때는 index 넘기지 않음.
            // 트럭이 올라가지 못하면 그 대신 0을 다리 큐에 넣어서 트럭의 위치를 옮겨줌. ex) 3(트럭) - -   -> 0 3(트럭) -
            else {
                bridge.add(emptySpace);
            }
            timeCount++;
        }
        // 대기 트럭이 모두 다리에 올라가면, 먼저 꽉 찰때까지(트럭이 끝까지 갈때까지) emptySpace add
        while(bridge.size() < bridge_length) {
            bridge.add(emptySpace);
            timeCount++;
        }


        // 트럭 다리에서 빼기
        while(true) {
            weightCapacity += bridge.poll();
            bridge.add(emptySpace);
            timeCount++;
            // 마지막 트럭이 나가서 다리 무게 capacity가 원래 weight랑 같아지는 순간 break
            if(weightCapacity == weight && bridge.size() == bridge_length) {break;}
        }

        System.out.println(timeCount);





    }
}
