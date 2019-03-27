import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p03_variations_without_repetitions {

    private static String[] elements;
    private static String[] variations;
    private static boolean[] used;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        elements = reader.readLine().split("\\s+");
        int k = Integer.parseInt(reader.readLine());
        used = new boolean[elements.length];
        variations = new String[k];

        generateVariations(k, 0);
    }

    private static void generateVariations(int k, int index) {
        if (index >= k) {
            System.out.println(String.join(" ", variations));
            return;
        }

        for (int i = 0; i < elements.length; i++) {
            if (!used[i]) {
                used[i] = true;
                variations[index] = elements[i];
                generateVariations(k, index + 1);
                used[i] = false;
            }
        }
    }
}
