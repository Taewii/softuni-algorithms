import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p03_word_differences {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        char[] first = reader.readLine().toCharArray();
        char[] second = reader.readLine().toCharArray();

        int[][] costMatrix = getCostMatrix(1, 1, first, second);
        System.out.printf("Deletions and Insertions: %d", costMatrix[first.length][second.length]);
    }

    private static int[][] getCostMatrix(int costInsert, int costDelete, char[] start, char[] goal) {
        int[][] dp = new int[start.length + 1][goal.length + 1];

        for (int col = 1; col <= goal.length; col++) {
            dp[0][col] = dp[0][col - 1] + costInsert;
        }

        for (int row = 1; row <= start.length; row++) {
            dp[row][0] = dp[row - 1][0] + costDelete;
        }

        for (int row = 1; row <= start.length; row++) {
            for (int col = 1; col <= goal.length; col++) {

                if (start[row - 1] == goal[col - 1]) {
                    dp[row][col] = dp[row - 1][col - 1];
                    continue;
                }

                int insert = dp[row][col - 1] + costInsert;
                int delete = dp[row - 1][col] + costDelete;

                dp[row][col] = Math.min(insert, delete);
            }
        }
        return dp;
    }
}
