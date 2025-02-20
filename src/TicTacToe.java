import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;
import java.util.Objects;
import javax.swing.*;
public class TicTacToe {

    int boardWidth = 600;
    int boeardHeight = 650;

    JFrame frame = new JFrame();
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    boolean gameOver = false;
    int turns = 0;


    TicTacToe() {
        frame.setVisible(true);
        frame.setTitle("Tic-Tac-Toe");
        frame.setSize(boardWidth,boeardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.PINK);
        textLabel.setForeground(Color.BLACK);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.PINK);
        frame.add(boardPanel);

        for(int row = 0; row < 3; row++) {
            for(int column = 0; column < 3; column++) {
                JButton tile = new JButton();
                board[row][column] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.BLACK);
                tile.setForeground(Color.PINK);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                tile.setOpaque(false);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(gameOver) return;
                        JButton tile =  (JButton) e.getSource();
                        if(tile.getText() == "") {
                        tile.setText(currentPlayer);
                        turns++;
                        checkWinner();
                            if(!gameOver){
                                currentPlayer = Objects.equals(currentPlayer, playerX) ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                        }
                    }
                });
            }
        }

    }
    void checkWinner() {
        for (int row = 0; row < 3; row++) {
            if(board[row][0].getText() == "") continue;

            if(board[row][0].getText() == board[row][1].getText() &&
                board[row][1].getText() == board[row][2].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[row][i]);
                }
                gameOver = true;
                return;
            }
        }

        for(int column = 0; column < 3; column++) {
            if(board[0][column].getText() == "") continue;

            if(board[0][column].getText() == board[1][column].getText() &&
                    board[1][column].getText() == board[2][column].getText()) {
                for(int i = 0; i < 3; i++) {
                    setWinner(board[i][column]);
                }
                gameOver = true;
                return;
            }
        }

        if(board[0][0].getText() == board[1][1].getText() &&
                board[1][1].getText() == board[2][2].getText() &&
                board[0][0].getText() != "") {
            for(int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }

        if(board[0][2].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][0].getText() &&
            board[0][2].getText() != "") {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }

        if(turns == 9) {
            for(int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {
                    setTie(board[row][column]);
                }
            }
            gameOver = true;
            return;
        }
    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.GREEN);
        tile.setBackground(Color.PINK);
        textLabel.setText(currentPlayer + " is the winner!!");
    }

    void setTie(JButton tile) {
        tile.setForeground(Color.ORANGE);
        tile.setBackground(Color.PINK);
        textLabel.setText("Tie!!");
    }
}
