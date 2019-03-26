import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p04_needles {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        reader.readLine(); // unused

        int[] numbers = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] needles = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] indexes = new int[needles.length];

        for (int index = 0; index < needles.length; index++) {
            int needle = needles[index];
            boolean match = false;

            for (int i = 0; i < numbers.length; i++) {
                if (numbers[i] >= needle) {
                    match = true;
                    indexes[index] = getInsertionIndex(numbers, i - 1);
                    break;
                }
            }

            if (!match) {
                indexes[index] = getInsertionIndex(numbers, numbers.length - 1);
            }
        }

        System.out.println(Arrays.toString(indexes).replaceAll("[\\[\\],]", ""));
    }

    private static int getInsertionIndex(int[] numbers, int index) {
        while (index >= 0) {
            if (numbers[index] != 0) {
                return index + 1;
            }
            index--;
        }

        return 0;
    }
}