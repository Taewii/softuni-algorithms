import java.util.HashSet;
import java.util.Set;

public class p06_8_queens_puzzle {

    private static final boolean[][] board = new boolean[8][8];

    private static final Set<Integer> attackedRows = new HashSet<>();
    private static final Set<Integer> attackedCols = new HashSet<>();
    private static final Set<Integer> attackedLeftDiagonals = new HashSet<>();
    private static final Set<Integer> attackedRightDiagonals = new HashSet<>();

    public static void main(String[] args) {
        placeQueen(0);
    }

    private static void placeQueen(int row) {
        if (row == board.length) {
            printSolution();
            return;
        }

        for (int col = 0; col < board.length; col++) {
            if (!canPlaceQueen(row, col)) {
                continue;
            }

            mark(row, col);
            placeQueen(row + 1);
            clearMark(row, col);
        }

    }

    private static void clearMark(int row, int col) {
        board[row][col] = false;
        attackedRows.remove(row);
        attackedCols.remove(col);
        attackedLeftDiagonals.remove(row + col);
        attackedRightDiagonals.remove(row - col);
    }

    private static void mark(int row, int col) {
        board[row][col] = true;
        attackedRows.add(row);
        attackedCols.add(col);
        attackedLeftDiagonals.add(row + col);
        attackedRightDiagonals.add(row - col);
    }

    private static boolean canPlaceQueen(int row, int col) {
        return !attackedRows.contains(row)
                && !attackedCols.contains(col)
                && !attackedLeftDiagonals.contains(row + col)
                && !attackedRightDiagonals.contains(row - col);
    }

    private static void printSolution() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                System.out.print(board[row][col] ? "* " : "- ");
            }

            System.out.println();
        }

        System.out.println();
    }
}
