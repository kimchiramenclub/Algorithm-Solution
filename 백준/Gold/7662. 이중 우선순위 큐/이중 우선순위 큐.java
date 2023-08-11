import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int k = Integer.parseInt(br.readLine());
            String[] operations = new String[k];
            for (int j = 0; j < k; j++) {
                operations[j] = br.readLine();
            }
            System.out.println(solution(operations));
        }
    }

    public static String solution(String[] operations) {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (String op : operations) {
            StringTokenizer st = new StringTokenizer(op, " ");
            String command = st.nextToken();
            int value = Integer.parseInt(st.nextToken());

            if ("I".equals(command)) {
                map.put(value, map.getOrDefault(value, 0) + 1);
            } else {
                if (!map.isEmpty()) {
                    int keyToRemove;
                    if (value == 1) {
                        keyToRemove = map.lastKey();
                    } else {
                        keyToRemove = map.firstKey();
                    }

                    map.put(keyToRemove, map.get(keyToRemove) - 1);
                    if (map.get(keyToRemove) <= 0) {
                        map.remove(keyToRemove);
                    }
                }
            }
        }

        if (map.isEmpty()) {
            return "EMPTY";
        } else {
            return map.lastKey() + " " + map.firstKey();
        }
    }
}