import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class p02_longest_zigzag_subsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] numbers = Arrays
                .stream(reader.readLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] dp = new int[numbers.length][4];
        dp[0][0] = 1;
        dp[0][1] = 1;
        dp[0][2] = -1;
        dp[0][3] = -1;

        int maxLength = 1;
        int maxIndexRow = 2;
        int maxIndexCol = 2;

        for (int currentIndex = 1; currentIndex < numbers.length; currentIndex++) {
            for (int previousIndex = 0; previousIndex < currentIndex; previousIndex++) {
                int current = numbers[currentIndex];
                int previous = numbers[previousIndex];

                if (current > previous && dp[currentIndex][0] < dp[previousIndex][1] + 1) {
                    dp[currentIndex][0] = dp[previousIndex][1] + 1;
                    dp[currentIndex][2] = previousIndex;
                }

                if (current < previous && dp[currentIndex][1] < dp[previousIndex][0] + 1) {
                    dp[currentIndex][1] = dp[previousIndex][0] + 1;
                    dp[currentIndex][3] = previousIndex;
                }
            }

            if (dp[currentIndex][0] > maxLength) {
                maxLength = dp[currentIndex][0];
                maxIndexRow = currentIndex;
                maxIndexCol = 2;
            }
            if (dp[currentIndex][1] > maxLength) {
                maxLength = dp[currentIndex][1];
                maxIndexRow = currentIndex;
                maxIndexCol = 3;
            }
        }

        Deque<Integer> sequence = new LinkedList<>();
        while (maxIndexRow >= 0) {
            sequence.addFirst(numbers[maxIndexRow]);
            maxIndexRow = dp[maxIndexRow][maxIndexCol];

            if (maxIndexCol == 2) {
                maxIndexCol = 3;
            } else {
                maxIndexCol = 2;
            }
        }

        System.out.println(sequence.toString().replaceAll("[,\\[\\]]", "").trim());
    }
}
