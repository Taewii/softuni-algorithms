import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class p03_move_down_right {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());
        int[][] matrix = new int[n][m];
        fillMatrix(matrix, reader);

        // fill the first row
        for (int i = 1; i < matrix[0].length; i++) {
            matrix[0][i] = matrix[0][i - 1] + matrix[0][i];
        }

        // fill the first col
        for (int i = 1; i < matrix.length; i++) {
            matrix[i][0] = matrix[i - 1][0] + matrix[i][0];
        }

        // fill the remaining of the matrix with either the sum of the element, with the
        // one above, or the one on the left, depending on which one has the better sum.
        for (int row = 1; row < matrix.length; row++) {
            for (int col = 1; col < matrix[row].length; col++) {
                int current = matrix[row][col];
                int up = matrix[row - 1][col];
                int left = matrix[row][col - 1];
                matrix[row][col] = Math.max(current + up, current + left);
            }
        }

        String path = findPath(matrix);
        System.out.println(path);
    }

    private static String findPath(int[][] matrix) {
        Deque<String> path = new ArrayDeque<>();
        int currentRow = matrix.length - 1;
        int currentCol = matrix[currentRow].length - 1;

        while (true) {
            path.push(String.format("[%d, %d]", currentRow, currentCol));

            if (currentRow == 0 && currentCol == 0) {
                break;
            }

            if (currentRow == 0) {
                currentCol--; // left is the only option
            } else if (currentCol == 0) {
                currentRow--; // up is the only option
            } else {
                int up = matrix[currentRow - 1][currentCol];
                int left = matrix[currentRow][currentCol - 1];
                if (up > left) {
                    currentRow--; // up - higher value
                } else {
                    currentCol--; // left - higher or equal value
                }
            }
        }
        return String.join(" ", path);
    }

    private static void fillMatrix(int[][] matrix, BufferedReader reader) throws IOException {
        for (int i = 0; i < matrix.length; i++) {
            String[] params = reader.readLine().split("\\s+");
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Integer.parseInt(params[j]);
            }
        }
    }
}
