import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p01_connecting_cables {

    private static int[] unordered;
    private static int[] ordered;
    private static Integer[][] memorization;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        unordered = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        ordered = Arrays.stream(unordered).sorted().toArray();
        memorization = new Integer[unordered.length][ordered.length];

        int result = getMaxConnections(unordered.length - 1, ordered.length -1);
        System.out.println("Maximum pairs connected: " + result);
    }

    private static int getMaxConnections(int orderedIndex, int unorderedIndex) {
        if (orderedIndex < 0 || unorderedIndex < 0) {
            return 0;
        }

        if (memorization[orderedIndex][unorderedIndex] != null) {
            return memorization[orderedIndex][unorderedIndex];
        }

        if (unordered[unorderedIndex] == orderedIndex + 1) {
            memorization[orderedIndex][unorderedIndex] = 1 + getMaxConnections(orderedIndex - 1, unorderedIndex - 1);
        } else {
            int reducedOrdered = getMaxConnections(orderedIndex - 1, unorderedIndex);
            int reducedUnordered = getMaxConnections(orderedIndex, unorderedIndex - 1);

            memorization[orderedIndex][unorderedIndex] = Math.max(reducedOrdered, reducedUnordered);
        }

        return memorization[orderedIndex][unorderedIndex];
    }
}
