import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class p01_zerg {

    private static BigInteger[][] matrix;
    private static final BigInteger ENEMY = BigInteger.valueOf(-1);

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] matrixParams = reader.readLine().split("\\s+");
        matrix = new BigInteger[Integer.parseInt(matrixParams[0])][Integer.parseInt(matrixParams[1])];
        for (BigInteger[] ints : matrix) {
            Arrays.fill(ints, BigInteger.ZERO);
        }

        String[] destinationCoordinates = reader.readLine().split("\\s+");
        int destRow = Integer.parseInt(destinationCoordinates[0]);
        int destCol = Integer.parseInt(destinationCoordinates[1]);

        int enemyCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < enemyCount; i++) {
            String[] enemyCoords = reader.readLine().split("\\s+");
            matrix[Integer.parseInt(enemyCoords[0])][Integer.parseInt(enemyCoords[1])] = ENEMY;
        }

        BigInteger res = numberOfPaths(destRow, destCol);
        System.out.println(res);
    }

    private static BigInteger numberOfPaths(int destRow, int destCol) {
        BigInteger[][] count = new BigInteger[matrix.length][matrix[0].length];

        for (BigInteger[] bigIntegers : count) {
            Arrays.fill(bigIntegers, BigInteger.ZERO);
        }

        for (int i = 0; i <= destRow; i++) {
            if (matrix[i][0].equals(ENEMY)) {
                break;
            }
            count[i][0] = BigInteger.ONE;
        }

        for (int j = 0; j <= destCol; j++) {
            if (matrix[0][j].equals(ENEMY)) {
                break;
            }
            count[0][j] = BigInteger.ONE;
        }

        for (int i = 1; i <= destRow; i++) {
            for (int j = 1; j <= destCol; j++) {
                if (!matrix[i][j].equals(ENEMY)) {
                    count[i][j] = count[i - 1][j].add(count[i][j - 1]);
                }
            }

        }

        return count[destRow][destCol];
    }
}
