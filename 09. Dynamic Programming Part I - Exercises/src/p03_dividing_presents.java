import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p03_dividing_presents {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] presents = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int total = Arrays.stream(presents).sum();
        Integer[] values = new Integer[total + 1];
        values[0] = 0;

        for (int i = 0; i < presents.length; i++) {
            for (int j = total; j >= 0; j--) {
                if (values[j] != null && values[j + presents[i]] == null) {
                    values[j + presents[i]] = i;
                }
            }
        }

        int half = total / 2;
        for (int i = half; i >= 0; i--) {
            if (values[i] != null) {
                System.out.printf("Difference: %d%n", total - i * 2);
                System.out.printf("Alan:%d Bob:%d%n", i, total - i);
                System.out.print("Alan takes: ");

                while (i > 0 && values[i] != null) {
                    System.out.print(presents[values[i]] + " ");
                    i -= presents[values[i]];
                }

                break;
            }
        }

        System.out.printf("%nBob takes the rest.");
    }
}
