import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class p07_all_paths_in_a_labyrinth {

    private static char[][] labyrinth;
    private static final Deque<Character> moves = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int rows = Integer.parseInt(reader.readLine());
        int cols = Integer.parseInt(reader.readLine());
        labyrinth = new char[rows][cols];

        for (int row = 0; row < rows; row++) {
            char[] tokens = reader.readLine().toCharArray();
            for (int col = 0; col < cols; col++) {
                labyrinth[row][col] = tokens[col];
            }
        }

        findPaths(0, 0, 'S');
    }

    private static void findPaths(int row, int col, char direction) {
        if (!isCellValid(row, col)) {
            return;
        }

        moves.offer(direction);
        if (labyrinth[row][col] == 'e') {
            printPath();
        } else {
            if (labyrinth[row][col] != '#') {
                labyrinth[row][col] = '#';
                findPaths(row, col + 1, 'R');
                findPaths(row + 1, col, 'D');
                findPaths(row, col - 1, 'L');
                findPaths(row - 1, col, 'U');
                labyrinth[row][col] = '-';
            }
        }

        moves.pollLast();
    }

    private static void printPath() {
        System.out.println(moves.toString().replaceAll("[\\[\\], ]", "").substring(1));
    }

    private static boolean isCellValid(int row, int col) {
        return row >= 0 && col >= 0 && row < labyrinth.length && col < labyrinth[0].length && labyrinth[row][col] != '*';
    }
}
