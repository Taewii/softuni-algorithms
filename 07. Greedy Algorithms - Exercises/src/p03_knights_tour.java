import java.util.Scanner;

public class p03_knights_tour {
    private static final int ROW = 0;
    private static final int COL = 1;
    private static final int[][] MOVES = {{1, 2}, {-1, 2}, {1, -2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};

    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);

        int boardSide = scanner.nextInt();
        int[][] board = new int[boardSide][boardSide];

        genMoves(board, boardSide);
        printBoard(board, boardSide);
    }

    private static void printBoard(final int[][] board, final int boardSide) {
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < boardSide; i++) {
            for (int j = 0; j < boardSide; j++) {
                sb.append(String.format("%4d", board[i][j]));
            }
            sb.append(System.lineSeparator());
        }

        System.out.print(sb);
    }

    private static void genMoves(int[][] board, int boardSide) {
        int row = 0;
        int col = 0;
        int step = 1;

        while (true) {
            board[row][col] = step++;
            int[] bestMove = null;
            int bestMoveOptions = Integer.MAX_VALUE;

            for (int[] move : MOVES) {
                int availableMoves = evaluateMoves(board, row + move[ROW], col + move[COL], boardSide);

                if (availableMoves >= bestMoveOptions || availableMoves == -1) {
                    continue;
                }

                bestMoveOptions = availableMoves;
                bestMove = move;
            }

            if (bestMove != null) {
                row = row + bestMove[ROW];
                col = col + bestMove[COL];
                continue;
            }

            break;
        }
    }

    private static int evaluateMoves(int[][] board, int row, int col, int boardSide) {
        if (!isValid(board, row, col, boardSide)) {
            return -1;
        }

        int validMoves = 0;
        for (int[] move : MOVES) {
            int nextRow = row + move[ROW];
            int nextCol = col + move[COL];
            if (isValid(board, nextRow, nextCol, boardSide)) {
                validMoves++;
            }
        }
        return validMoves;
    }

    private static boolean isValid(int[][] board, int row, int col, int boardSide) {
        return row >= 0 &&
                row < boardSide &&
                col >= 0 &&
                col < boardSide &&
                board[row][col] == 0;
    }
}
