import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Deque;
import java.util.LinkedList;

public class p02_minimum_edit_distance {
    public static void main(final String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        int costReplace = Integer.parseInt(reader.readLine().split("=")[1].trim());
        int costInsert = Integer.parseInt(reader.readLine().split("=")[1].trim());
        int costDelete = Integer.parseInt(reader.readLine().split("=")[1].trim());
        String start = reader.readLine().split("=")[1].trim();
        String goal = reader.readLine().split("=")[1].trim();

        int[][] dp = getCostMatrix(costReplace, costInsert, costDelete, start, goal);
        System.out.printf("Minimum edit distance: %d%n", dp[start.length()][goal.length()]);

        Deque<String> path = getPath(costReplace, costInsert, costDelete, start, goal, dp);
        path.forEach(System.out::println);
    }

    private static int[][] getCostMatrix(int costReplace, int costInsert, int costDelete, String start, String goal) {
        int[][] dp = new int[start.length() + 1][goal.length() + 1];

        for (int col = 1; col <= goal.length(); col++) {
            dp[0][col] = dp[0][col - 1] + costInsert;
        }

        for (int row = 1; row <= start.length(); row++) {
            dp[row][0] = dp[row - 1][0] + costDelete;
        }

        for (int row = 1; row <= start.length(); row++) {
            for (int col = 1; col <= goal.length(); col++) {

                if (start.charAt(row - 1) == goal.charAt(col - 1)) {
                    dp[row][col] = dp[row - 1][col - 1];
                    continue;
                }

                int insert = dp[row][col - 1] + costInsert;
                int delete = dp[row - 1][col] + costDelete;
                int replace = dp[row - 1][col - 1] + costReplace;

                dp[row][col] = Math.min(insert, Math.min(delete, replace));
            }
        }
        return dp;
    }

    private static Deque<String> getPath(int costReplace, int costInsert, int costDelete, String start, String goal, int[][] dp) {
        Deque<String> path = new LinkedList<>();

        int row = start.length();
        int col = goal.length();

        while (dp[row][col] != 0) {
            int minCol = Math.max(0, col - 1);
            int minRow = Math.max(0, row - 1);

            int currentCost = dp[row][col];
            int insert = dp[row][minCol] + costInsert;
            int delete = dp[minRow][col] + costDelete;
            int replace = dp[minRow][minCol] + costReplace;

            if (start.charAt(minRow) == goal.charAt(minCol)) {
                row = minRow;
                col = minCol;
            } else if (insert == currentCost) {
                path.addFirst(String.format("INSERT(%d, %c)", minCol, goal.charAt(minCol)));
                col = minCol;
            } else if (replace == currentCost) {
                path.addFirst(String.format("REPLACE(%d, %c)", minRow, goal.charAt(minCol)));
                row = minRow;
                col = minCol;
            } else if (delete == currentCost) {
                path.addFirst(String.format("DELETE(%d)", minRow));
                row = minRow;
            }
        }

        return path;
    }
}
