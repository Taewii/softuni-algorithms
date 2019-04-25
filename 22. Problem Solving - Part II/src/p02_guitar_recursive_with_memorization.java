import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class p02_guitar_recursive_with_memorization {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] elements = Arrays.stream(reader.readLine().split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        int capacity = Integer.parseInt(reader.readLine());
        int maxCapacity = Integer.parseInt(reader.readLine());
        Map<String, Integer> lookup = new HashMap<>();

        System.out.println(findMaxVolume(elements, maxCapacity, -1, capacity, lookup));
    }

    private static int findMaxVolume(int[] elements, int maxCapacity, int index, int capacity, Map<String, Integer> lookup) {
        if (capacity < 0 || capacity > maxCapacity) {
            return -1;
        }

        if (index >= elements.length - 1) {
            return capacity;
        }

        String key = index + "|" + capacity;

        if (!lookup.containsKey(key)) {
            int add = findMaxVolume(elements, maxCapacity, index + 1, capacity + elements[index + 1], lookup);
            int subtract = findMaxVolume(elements, maxCapacity, index + 1, capacity - elements[index + 1], lookup);

            lookup.put(key, Integer.max(add, subtract));
        }

        return lookup.get(key);
    }
}
