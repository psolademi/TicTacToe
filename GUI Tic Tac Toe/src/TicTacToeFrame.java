import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private JButton[][] buttons;
    private TicTacToeGame game;
    private boolean xTurn;

    public TicTacToeFrame() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        game = new TicTacToeGame();
        xTurn = true;

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TicTacToeButton button = (TicTacToeButton) e.getSource();
                int row = button.getRow();
                int col = button.getCol();

                if (game.makeMove(row, col, xTurn ? 'X' : 'O')) {
                    button.setText(xTurn ? "X" : "O");
                    if (game.checkForWin(row, col, xTurn ? 'X' : 'O')) {
                        JOptionPane.showMessageDialog(null, (xTurn ? "X" : "O") + " wins!");
                        resetBoard();
                    } else if (game.checkForTie()) {
                        JOptionPane.showMessageDialog(null, "It's a tie!");
                        resetBoard();
                    } else {
                        xTurn = !xTurn;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid move. Try again.");
                }
            }
        };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new TicTacToeButton(i, j);
                buttons[i][j].addActionListener(buttonListener);
                add(buttons[i][j]);
            }
        }

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        add(quitButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void resetBoard() {
        game.resetBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        xTurn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToeFrame();
            }
        });
    }
}
