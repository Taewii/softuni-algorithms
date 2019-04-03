import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p05_sum_with_limited_amount_of_coins {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] coins = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int targetSum = Integer.parseInt(reader.readLine());

        int[][] combinationsCount = new int[targetSum + 2][targetSum + 1];
        for (int i = 0; i <= coins.length; i++) {
            combinationsCount[i][0] = 1;
        }

        for (int i = 1; i <= coins.length; i++) {
            for (int j = targetSum; j >= 0; j--) {
                if (coins[i - 1] <= j && combinationsCount[i - 1][j - coins[i - 1]] != 0) {
                    combinationsCount[i][j]++;
                } else {
                    combinationsCount[i][j] = combinationsCount[i - 1][j];
                }
            }
        }

        int count = 0;
        for (int i = 0; i <= coins.length; i++) {
            if (combinationsCount[i][targetSum] != 0) {
                count++;
            }
        }

        System.out.println(count);
    }
}
