import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class p02_cable_merchant {

    private static int[] prices;
    private static int[] bestPrices;
    private static int[] bestCombination;
    private static int connectorPrice;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] pricesStringArr = reader.readLine().split("\\s+");
        connectorPrice = Integer.parseInt(reader.readLine());

        prices = new int[pricesStringArr.length + 1];
        for (int i = 1; i < prices.length; i++) {
            prices[i] = Integer.parseInt(pricesStringArr[i - 1]);
        }

        for (int i = 1; i < prices.length; i++) {
            bestPrices = new int[prices.length];
            bestCombination = new int[prices.length];
            System.out.print(cutRod(i) + " ");
        }
    }

    private static int cutRod(int n) {
        int currentBest;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                currentBest = Math.max(bestPrices[i], prices[j] + bestPrices[i - j]);
                if (currentBest > bestPrices[i]) {
                    bestPrices[i] = currentBest;
                    bestCombination[i] = j;
                }
            }
        }

        return Math.max(prices[n], calculateFinalPrice(n));
    }

    private static int calculateFinalPrice(int n) {
        int finalPrice = bestPrices[n];
        int pieces = 0;

        while (n - bestCombination[n] != 0) {
            pieces++;
            n = n - bestCombination[n];
        }

        return finalPrice - 2 * pieces * connectorPrice;
    }
}
