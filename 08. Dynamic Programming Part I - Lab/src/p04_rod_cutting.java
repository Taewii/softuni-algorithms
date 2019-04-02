import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p04_rod_cutting {

    private static int[] prices;
    private static int[] bestPrice;
    private static int[] index;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        prices = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        bestPrice = new int[prices.length];
        index = new int[prices.length];
        int length = Integer.parseInt(reader.readLine());

        int bestPrice = cutRodIterative(length);
//        int bestPrice = cutRodRecursive(length);

        StringBuilder sb = new StringBuilder();
        while (length != 0) {
            sb.append(index[length]).append(" ");
            length -= index[length];
        }

        System.out.println(bestPrice);
        System.out.println(sb.toString());
    }

    private static int cutRodIterative(int length) {
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= i; j++) {
                int currentBest = Math.max(bestPrice[i], prices[j] + bestPrice[i - j]);
                if (currentBest > bestPrice[i]) {
                    bestPrice[i] = currentBest;
                    index[i] = j;
                }
            }
        }

        return bestPrice[length];
    }

    private static int cutRodRecursive(int length) {
        if (bestPrice[length] != 0) {
            return bestPrice[length];
        }

        if (length == 0) {
            return 0;
        }

        int currentBestPrice = prices[length];
        int currentLength = length;

        for (int cut = 1; cut < length; cut++) {
            int currentPrice = prices[cut] + cutRodRecursive(length - cut);
            if (currentPrice > currentBestPrice) {
                currentBestPrice = currentPrice;
                currentLength = cut;
            }
        }

        index[length] = currentLength;
        bestPrice[length] = currentBestPrice;

        return currentBestPrice;
    }
}
