import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p02_largest_common_subsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String firstStr = reader.readLine();
        String secondStr = reader.readLine();
        int[][] lcs = new int[firstStr.length() + 1][secondStr.length() + 1];

        for (int row = 1; row <= firstStr.length(); row++) {
            for (int col = 1; col <= secondStr.length(); col++) {
                int up = lcs[row - 1][col];
                int left = lcs[row][col - 1];

                int max = Math.max(up, left);

                if (firstStr.charAt(row - 1) == secondStr.charAt(col - 1)) {
                    max = Math.max(1 + lcs[row - 1][col - 1], max);
                }

                lcs[row][col] = max;
            }
        }

        System.out.println(lcs[firstStr.length()][secondStr.length()]);
    }
}
