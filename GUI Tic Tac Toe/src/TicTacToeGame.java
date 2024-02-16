public class TicTacToeGame {
    private char[][] board;
    private int moves;

    public TicTacToeGame() {
        board = new char[3][3];
        resetBoard();
    }

    public boolean makeMove(int row, int col, char player) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != '\0') {
            return false;
        }
        board[row][col] = player;
        moves++;
        return true;
    }

    public boolean checkForWin(int row, int col, char player) {
        // Check row
        if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
            return true;
        }
        // Check column
        if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
            return true;
        }
        // Check diagonals
        if ((row == col || row + col == 2) &&
                ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                        (board[0][2] == player && board[1][1] == player && board[2][0] == player))) {
            return true;
        }
        return false;
    }

    public boolean checkForTie() {
        return moves == 9;
    }

    public void resetBoard() {
        board = new char[3][3];
        moves = 0;
    }
}
