import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;

public class TicTacToeGame {
    private final JFrame gameFrame = new JFrame();
    private final JLabel headerText = new JLabel();
    private final JPanel headerPanel = new JPanel();
    private final JPanel boardGamePanel = new JPanel();
    private final TileButton[][] gameBoardButtons = new TileButton[3][3];
    private final String firstPlayer = "X";
    private  final String secondPlayer = "O";
    private  String currentPlayer = firstPlayer;
    private boolean gameOver = false;
    private int gameTurnsCounter = 0;

    public TicTacToeGame() { }

    public void run() {
        setGameFrame();
        addHeaderToFrame();
        addBoardToFrame();
        addTileButtonsToBoard();
    }

    private void setGameFrame() {
        final int boardWidth = 600;
        final int boardHeight = 650;

        gameFrame.setVisible(true);
        gameFrame.setTitle("Tic-Tac-Toe");
        gameFrame.setSize(boardWidth, boardHeight);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLayout(new BorderLayout());
    }

    private void addHeaderToFrame() {
        setHeaderText();
        setHeaderPanel();
        gameFrame.add(headerPanel, BorderLayout.NORTH);
    }

    private void addBoardToFrame() {
        setBoardGamePanel();
        gameFrame.add(boardGamePanel);
    }

    private void addTileButtonsToBoard() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                TileButton tile = new TileButton();
                tile.assignClickListener(this::handleTileButtonClick);
                gameBoardButtons[row][column] = tile;
                boardGamePanel.add(tile);
            }
        }
    }

    private void setHeaderText() {
        this.headerText.setBackground(Color.PINK);
        this.headerText.setForeground(Color.BLACK);
        this.headerText.setFont(new Font("Arial", Font.BOLD, 50));
        this.headerText.setHorizontalAlignment(JLabel.CENTER);
        this.headerText.setText("Tic-Tac-Toe");
        this.headerText.setOpaque(true);
    }

    private void setHeaderPanel() {
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(headerText);
    }

    private void setBoardGamePanel() {
        boardGamePanel.setLayout(new GridLayout(3, 3));
        boardGamePanel.setBackground(Color.PINK);
    }

    private void handleTileButtonClick(ActionEvent e) {
        if (gameOver) return;
        JButton tile = (JButton) e.getSource();
        if (tile.getText().isBlank()) {
            tile.setText(currentPlayer);
            gameTurnsCounter++;
            checkWinner();
            if (!gameOver) {
                currentPlayer = Objects.equals(currentPlayer, firstPlayer) ? secondPlayer : firstPlayer;
                headerText.setText(currentPlayer + "'s turn.");
            }
        }
    }

    void checkWinner() {
        for (int row = 0; row < 3; row++) {
            if (gameBoardButtons[row][0].getText().isBlank()) continue;

            if (gameBoardButtons[row][0].getText().equals(gameBoardButtons[row][1].getText()) && gameBoardButtons[row][1].getText().equals(gameBoardButtons[row][2].getText())) {
                for (int i = 0; i < 3; i++) {
                    setWinner(gameBoardButtons[row][i]);
                }
                gameOver = true;
                return;
            }
        }

        for (int column = 0; column < 3; column++) {
            if (gameBoardButtons[0][column].getText().isBlank()) continue;

            if (gameBoardButtons[0][column].getText().equals(gameBoardButtons[1][column].getText()) && gameBoardButtons[1][column].getText().equals(gameBoardButtons[2][column].getText())) {
                for (int i = 0; i < 3; i++) {
                    setWinner(gameBoardButtons[i][column]);
                }
                gameOver = true;
                return;
            }
        }

        if (gameBoardButtons[0][0].getText().equals(gameBoardButtons[1][1].getText()) &&
                gameBoardButtons[1][1].getText().equals(gameBoardButtons[2][2].getText()) &&
                !gameBoardButtons[0][0].getText().isBlank()) {
            for (int i = 0; i < 3; i++) {
                setWinner(gameBoardButtons[i][i]);
            }
            gameOver = true;
            return;
        }

        if (gameBoardButtons[0][2].getText().equals(gameBoardButtons[1][1].getText()) &&
                gameBoardButtons[1][1].getText().equals(gameBoardButtons[2][0].getText()) &&
                !gameBoardButtons[0][2].getText().isBlank()) {
            setWinner(gameBoardButtons[0][2]);
            setWinner(gameBoardButtons[1][1]);
            setWinner(gameBoardButtons[2][0]);
            gameOver = true;
            return;
        }

        if (gameTurnsCounter == 9) {
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {
                    setTie(gameBoardButtons[row][column]);
                }
            }
            gameOver = true;
        }
    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.GREEN);
        tile.setBackground(Color.PINK);
        headerText.setText(currentPlayer + " is the winner!!");
    }

    void setTie(JButton tile) {
        tile.setForeground(Color.ORANGE);
        tile.setBackground(Color.PINK);
        headerText.setText("Tie!!");
    }
}
