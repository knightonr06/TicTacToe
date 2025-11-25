
// TicTacToe.java
import java.util.Scanner;

public class tictactoe {
    // constants and board (class-level)
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board[][] = new String[ROWS][COLS];

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Tic Tac Toe");
        boolean playAgain = true;
        while (playAgain) {
            clearBoard();
            String currentPlayer = "X";
            int moveCount = 0;
            boolean gameOver = false;

            display();

            while (!gameOver) {
                int r = SafeInput.getRangedInt(console, "Player " + currentPlayer + " - enter row (1-3):", 1, 3) - 1;
                int c = SafeInput.getRangedInt(console, "Player " + currentPlayer + " - enter column (1-3):", 1, 3) - 1;

                while (!isValidMove(r, c)) {
                    System.out.println("That square is not available. Try again.");
                    r = SafeInput.getRangedInt(console, "Player " + currentPlayer + " - enter row (1-3):", 1, 3) - 1;
                    c = SafeInput.getRangedInt(console, "Player " + currentPlayer + " - enter column (1-3):", 1, 3) - 1;
                }

                board[r][c] = currentPlayer;
                moveCount++;
                display();

                // only possible to win after 5 moves
                if (moveCount >= 5 && isWin(currentPlayer)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameOver = true;
                } else if (isTie()) {
                    System.out.println("The game is a tie.");
                    gameOver = true;
                } else {
                    // toggle player
                    currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                }
            }

            playAgain = SafeInput.getYNConfirm(console, "Play again?");
        }

        System.out.println("Thanks for playing.");
        console.close();
    }

    // helper methods (private static) below

    private static void clearBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                board[r][c] = " ";
            }
        }
    }

    private static void display() {
        System.out.println("   1   2   3");
        for (int r = 0; r < ROWS; r++) {
            System.out.print((r + 1) + " ");
            for (int c = 0; c < COLS; c++) {
                System.out.print(" " + board[r][c] + " ");
                if (c < COLS - 1) System.out.print("|");
            }
            System.out.println();
            if (r < ROWS - 1) System.out.println("  ---+---+---");
        }
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) return false;
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagnalWin(player);
    }

    private static boolean isColWin(String player) {
        for (int c = 0; c < COLS; c++) {
            boolean colWin = true;
            for (int r = 0; r < ROWS; r++) {
                if (!board[r][c].equals(player)) {
                    colWin = false;
                    break;
                }
            }
            if (colWin) return true;
        }
        return false;
    }

    private static boolean isRowWin(String player) {
        for (int r = 0; r < ROWS; r++) {
            boolean rowWin = true;
            for (int c = 0; c < COLS; c++) {
                if (!board[r][c].equals(player)) {
                    rowWin = false;
                    break;
                }
            }
            if (rowWin) return true;
        }
        return false;
    }

    // note: named as requested in the assignment (isDiagnalWin)
    private static boolean isDiagnalWin(String player) {
        // top-left to bottom-right
        boolean diag1 = true;
        for (int i = 0; i < ROWS; i++) {
            if (!board[i][i].equals(player)) {
                diag1 = false;
                break;
            }
        }
        if (diag1) return true;

        // top-right to bottom-left
        boolean diag2 = true;
        for (int i = 0; i < ROWS; i++) {
            if (!board[i][ROWS - 1 - i].equals(player)) {
                diag2 = false;
                break;
            }
        }
        return diag2;
    }

    private static boolean isTie() {
        // simple check: board full and no winner
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c].equals(" ")) return false;
            }
        }
        // board full; if no winner it's a tie
        return !isWin("X") && !isWin("O");
    }
}
