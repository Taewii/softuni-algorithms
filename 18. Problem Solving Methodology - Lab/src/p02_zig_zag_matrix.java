import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

                if (col % 2 == 0) {
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

        // TODO: 20.4.2019 г. recover path
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
