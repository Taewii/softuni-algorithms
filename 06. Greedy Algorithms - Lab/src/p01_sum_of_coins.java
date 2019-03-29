import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class p01_sum_of_coins {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int[] coins = Arrays.stream(in.nextLine()
                .substring(7)
                .split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int targetSum = Integer.parseInt(in.nextLine().substring(5));

        Map<Integer, Integer> usedCoins = chooseCoins(coins, targetSum);
        int coinsToTake = usedCoins.values().stream().mapToInt(i -> i).sum();
        System.out.printf("Number of coins to take: %d%n", coinsToTake);
        usedCoins.forEach((k, v) -> System.out.printf("%d coin(s) with value %d%n", v, k));
    }

    static Map<Integer, Integer> chooseCoins(int[] coins, int targetSum) {
        Integer[] sorted = Arrays.stream(coins).boxed().sorted((a, b) -> b - a).toArray(Integer[]::new);
        Map<Integer, Integer> coinCount = new LinkedHashMap<>();

        for (Integer coin : sorted) {
            if (targetSum - coin < 0) {
                continue;
            }

            int coinsToFit = targetSum / coin;
            targetSum -= coinsToFit * coin;
            coinCount.put(coin, coinsToFit);
        }

        if (targetSum != 0) {
            throw new IllegalArgumentException();
        }

        return coinCount;
    }
}
