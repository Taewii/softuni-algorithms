import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p04_variations_with_repetitions {

    private static String[] elements;
    private static String[] variations;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        elements = reader.readLine().split("\\s+");
        int k = Integer.parseInt(reader.readLine());
        variations = new String[k];

        generateVariations(k, 0);
    }

    private static void generateVariations(int k, int index) {
        if (index >= k) {
            System.out.println(String.join(" ", variations));
            return;
        }

        for (String element : elements) {
            variations[index] = element;
            generateVariations(k, index + 1);
        }
    }
}
