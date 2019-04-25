import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p02_guitar_DP {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] elements = Arrays.stream(reader.readLine().split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        int capacity = Integer.parseInt(reader.readLine());
        int maxCapacity = Integer.parseInt(reader.readLine());

        int[][] dp = new int[elements.length + 1][maxCapacity + 1];
        dp[0][capacity] = 1;

        for (int i = 1; i <= elements.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                int prev = elements[i - 1];

                if (dp[i - 1][j] == 1) {
                    if (j + prev <= maxCapacity) {
                        dp[i][j + prev] = 1;
                    }

                    if (j - prev >= 0) {
                        dp[i][j - prev] = 1;
                    }
                }
            }
        }

        for (int i = dp[0].length - 1; i >= 0; i--) {
            if (dp[elements.length][i] == 1) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
    }
}
