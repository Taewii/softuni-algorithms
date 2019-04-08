import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class p02_areas_in_a_matrix {

    private static char[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        matrix = new char[n][];
        Map<Character, Integer> areas = new HashMap<>();

        for (int i = 0; i < n; i++) {
            matrix[i] = reader.readLine().toCharArray();
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != '#') {
                    char ch = matrix[i][j];
                    areas.putIfAbsent(ch, 0);
                    areas.put(ch, areas.get(ch) + 1);
                    dfs(i, j, ch);
                }
            }
        }

        System.out.printf("Areas: %d%n", areas.values().stream().mapToInt(i -> i).sum());
        areas
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(entry -> System.out.printf("Letter '%s' -> %d%n", entry.getKey(), entry.getValue()));
    }

    private static void dfs(int row, int col, char ch) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length || matrix[row][col] != ch) {
            return;
        }

        matrix[row][col] = '#';

        dfs(row + 1, col, ch);
        dfs(row - 1, col, ch);
        dfs(row, col + 1, ch);
        dfs(row, col - 1, ch);
    }
}
