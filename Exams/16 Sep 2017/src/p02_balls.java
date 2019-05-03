import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p02_balls {

    private static int ballCount = 0;
    private static StringBuilder result = new StringBuilder();
    private static int[] vector;
    private static int pockets = 0;
    private static int capacity = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        pockets = Integer.parseInt(reader.readLine());
        ballCount = Integer.parseInt(reader.readLine());
        capacity = Integer.parseInt(reader.readLine());
        vector = new int[pockets];

        generateCombinationsWithRep(0, 0);
        System.out.println(result.toString().trim());
    }

    private static void generateCombinationsWithRep(int index, int currentSum) {
        if (index == pockets && currentSum == ballCount) {
            StringBuilder current = new StringBuilder();

            for (Integer integer : vector) {
                current.append(integer).append(", ");
            }

            current.setLength(current.length() - 2);
            result.append(current.append(System.lineSeparator()).toString());
            return;
        } else if (currentSum >= ballCount || index >= pockets) {
            return;
        }

        for (int i = 1; i <= capacity; i++) {
            currentSum += i;
            vector[index] = i;
            generateCombinationsWithRep(index + 1, currentSum);
            currentSum -= i;
        }
    }
}