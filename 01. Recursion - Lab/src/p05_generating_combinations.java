import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p05_generating_combinations {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] set = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int n = Integer.parseInt(reader.readLine());

        int[] vector = new int[n];
        generateCombinations(set, vector, 0, 0);
    }

    private static void generateCombinations(int[] set, int[] vector, int index, int border) {
        if (index >= vector.length) {
            System.out.println(Arrays.toString(vector).replaceAll("[\\[\\],]", ""));
            return;
        }

        for (int i = border; i < set.length; i++) {
            vector[index] = set[i];
            generateCombinations(set, vector, index + 1, i + 1);
        }
    }
}
