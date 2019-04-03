import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p04_sum_with_unlimited_amount_of_coins {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] coins = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int targetSum = Integer.parseInt(reader.readLine());

        int[] combinationsCount = new int[targetSum + 1];
        combinationsCount[0] = 1;

        for (int coin : coins) {
            for (int sum = 1; sum <= targetSum; sum++) {
                if (coin <= sum) {
                    combinationsCount[sum] += combinationsCount[sum - coin];
                }
            }
        }

        System.out.println(combinationsCount[targetSum]);
    }
}
