import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class p03_terran {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        char[] elements = reader.readLine().toCharArray();
        Map<Character, Integer> count = new HashMap<>();

        for (char element : elements) {
            count.putIfAbsent(element, 0);
            count.put(element, count.get(element) + 1);
        }

        int[] a = count.values().stream().mapToInt(i -> i).toArray();
        System.out.println(multinominalCoefficient(a));
    }

    private static long multinominalCoefficient(int[] lst) {
        long res = 1;
        long i = 1;

        for (int a : lst) {
            for (int j = 1; j < a + 1; j++) {
                res *= i;
                res /= j;
                i++;
            }
        }

        return res;
    }
}
