import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class p02_non_crossing_bridges {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        String[] res = new String[arr.length];
        Map<Integer, Integer> byIndex = new HashMap<>();

        int bridgeCount = 0;
        int lastBridgeEnd = 0;

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (byIndex.containsKey(num) && byIndex.get(num) >= lastBridgeEnd) {
                String numAsStr = String.valueOf(num);
                res[byIndex.get(num)] = numAsStr;
                res[i] = numAsStr;
                lastBridgeEnd = i;
                bridgeCount++;
            } else {
                res[i] = "X";
            }

            byIndex.put(num, i);
        }

        if (bridgeCount == 0) System.out.println("No bridges found");
        if (bridgeCount == 1) System.out.println("1 bridge found");
        if (bridgeCount > 1) System.out.printf("%d bridges found%n", bridgeCount);
        System.out.println(String.join(" ", res));
    }
}
