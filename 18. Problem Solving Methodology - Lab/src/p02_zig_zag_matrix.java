import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class p02_zig_zag_matrix {

    private static int[][] matrix;
    private static int[][] path;
    private static int[][] prev;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int rows = Integer.parseInt(reader.readLine());
        int cols = Integer.parseInt(reader.readLine());
        matrix = new int[rows][cols];
        path = new int[rows][cols];
        prev = new int[rows][cols];
        fillMatrix(reader);

        for (int i = 1; i < rows; i++) {
            path[i][0] = matrix[i][0];
        }

        for (int col = 1; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                int prevMax = 0;

                if (col % 2 != 0) {
                    for (int i = row + 1; i < rows; i++) {
                        if (path[i][col - 1] > prevMax) {
                            prevMax = path[i][col - 1];
                            prev[row][col] = i;
                        }
                    }
                } else {
                    for (int i = 0; i < row; i++) {
                        if (path[i][col - 1] > prevMax) {
                            prevMax = path[i][col - 1];
                            prev[row][col] = i;
                        }
                    }
                }

                path[row][col] = prevMax + matrix[row][col];
            }
        }

        int rowIndex = getLastRowIndexOfPath(cols);
        List<Integer> path = recoverPath(cols, rowIndex);

        int sum = path.stream().mapToInt(i -> i).sum();
        StringBuilder sb = new StringBuilder();
        sb.append(sum).append(" = ");
        path.forEach(p -> sb.append(p).append(" + "));

        System.out.println(sb.toString().substring(0, sb.length() - 3));
    }

    private static List<Integer> recoverPath(int cols, int rowIndex) {
        List<Integer> path = new ArrayList<>();
        int colIndex = cols - 1;

        while (colIndex >= 0) {
            path.add(matrix[rowIndex][colIndex]);
            rowIndex = prev[rowIndex][colIndex];

            colIndex--;
        }

        Collections.reverse(path);
        return path;
    }

    private static int getLastRowIndexOfPath(int cols) {
        int currentIndex = -1;
        int globalMax = 0;

        for (int row = 0; row < path.length; row++) {
            if (path[row][cols - 1] > globalMax) {
                globalMax = path[row][cols - 1];
                currentIndex = row;
            }
        }

        return currentIndex;
    }

    private static void fillMatrix(BufferedReader reader) throws IOException {
        for (int i = 0; i < matrix.length; i++) {
            String[] tokens = reader.readLine().split(",");
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Integer.parseInt(tokens[j]);
            }
        }
    }
}
