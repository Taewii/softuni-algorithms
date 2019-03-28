import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p06_combinations_with_repetitions {

    private static String[] elements;
    private static String[] combinations;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        elements = reader.readLine().split("\\s+");
        int k = Integer.parseInt(reader.readLine());
        combinations = new String[k];

        generateCombinations(0, 0);
    }

    private static void generateCombinations(int index, int border) {
        if (index >= combinations.length) {
            System.out.println(String.join(" ", combinations));
            return;
        }

        for (int i = border; i < elements.length; i++) {
            combinations[index] = elements[i];
            generateCombinations(index + 1, i);
        }
    }
}
