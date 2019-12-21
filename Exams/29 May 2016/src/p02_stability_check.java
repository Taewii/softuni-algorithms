import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p02_stability_check {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int buildingSize = Integer.parseInt(reader.readLine());
        int fieldSize = Integer.parseInt(reader.readLine());
        int[][] matrix = new int[fieldSize][fieldSize];
        long[][] memo = new long[fieldSize - buildingSize + 1][fieldSize];

        for (int i = 0; i < fieldSize; i++) {
            int[] nums = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            System.arraycopy(nums, 0, matrix[i], 0, fieldSize);
        }

        System.out.println(printMaxSumSub(matrix, memo, buildingSize));
    }

    static long printMaxSumSub(int[][] matrix, long[][] memo, int buildingSize) {
        for (int row = 0; row < memo.length; row++) {
            for (int col = 0; col < memo[0].length; col++) {
                long sum = 0L;
                for (int r = row; r < row + buildingSize; r++) {
                    sum += matrix[r][col];
                }
                memo[row][col] = sum;
            }
        }

        long maxSum = Long.MIN_VALUE;
        for (long[] longs : memo) {
            for (int col = 0; col <= memo[0].length - buildingSize; col++) {
                long sum = 0L;
                for (int c = col; c < col + buildingSize; c++) {
                    sum += longs[c];
                }
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }

        return maxSum;
    }
}
