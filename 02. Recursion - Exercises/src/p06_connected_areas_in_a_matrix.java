import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class p06_connected_areas_in_a_matrix {

    private static char[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Set<Area> areas = new TreeSet<>();

        int rows = Integer.parseInt(reader.readLine());
        int cols = Integer.parseInt(reader.readLine());
        matrix = new char[rows][cols];

        fillMatrix(reader);

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] != '*') {
                    Area area = new Area(row, col);

                    findArea(row, col, area);
                    areas.add(area);
                }
            }
        }

        printAreas(areas);
    }

    private static void fillMatrix(BufferedReader reader) throws IOException {
        for (char[] matrix1 : matrix) {
            char[] tokens = reader.readLine().toCharArray();
            System.arraycopy(tokens, 0, matrix1, 0, matrix[0].length);
        }
    }

    private static void findArea(int row, int col, Area area) {
        if (!isCellValid(row, col)) {
            return;
        }

        matrix[row][col] = '*';
        area.size++;

        findArea(row + 1, col, area);
        findArea(row - 1, col, area);
        findArea(row, col + 1, area);
        findArea(row, col - 1, area);
    }

    private static boolean isCellValid(int row, int col) {
        return row >= 0 && col >= 0 && row < matrix.length && col < matrix[row].length && matrix[row][col] != '*';
    }

    private static void printAreas(Set<Area> areas) {
        int count = 1;

        System.out.printf("Total areas found: %d%n", areas.size());
        for (Area area : areas) {
            System.out.printf("Area #%d %s%n", count++, area);
        }
    }
}

class Area implements Comparable<Area> {
    private int row;
    private int col;
    int size;

    @Override
    public int compareTo(Area other) {
        int result = other.size - this.size;
        if (result == 0) result = this.row - other.row;
        if (result == 0) result = this.col - other.col;
        return result;
    }

    Area(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return String.format("at (%d, %d), size: %d", this.row, this.col, this.size);
    }
}
