import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class p04_xelnaga {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] params = reader.readLine().split("\\s+");

        Map<Integer, Integer> byAge = new HashMap<>();

        for (int i = 0; i < params.length - 1; i++) {
            int species = Integer.parseInt(params[i]) + 1;
            byAge.putIfAbsent(species, 0);
            byAge.put(species, byAge.get(species) + 1);
        }

        int result = 0;
        for (Map.Entry<Integer, Integer> entry : byAge.entrySet()) {
            double key = entry.getKey();
            double value = entry.getValue();
            result += key * Math.ceil(value / key);
        }

        System.out.println(result);
    }
}
