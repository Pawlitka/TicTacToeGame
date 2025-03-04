import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.*;

public class TicTacToeGame {
    private final JFrame gameFrame = new JFrame();
    private final JLabel headerText = new JLabel();
    private final JPanel headerPanel = new JPanel();
    private final JPanel boardGamePanel = new JPanel();
    private final TileButton[][] gameBoardButtons = new TileButton[3][3];
    private final String firstPlayer = "X";
    private final String secondPlayer = "O";
    private String currentPlayer = firstPlayer;
    private boolean gameOver = false;
    private int gameTurnsCounter = 0;

    private int winningIndex;

    public TicTacToeGame() {
    }

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
        boardGamePanel.updateUI();
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
        TileButton tile = (TileButton) e.getSource();
        if (tile.isBlank()) {
            tile.setText(currentPlayer);
            gameTurnsCounter++;
            checkIfGameIsOver();
            if (!gameOver) {
                currentPlayer = Objects.equals(currentPlayer, firstPlayer) ? secondPlayer : firstPlayer;
                headerText.setText(currentPlayer + "'s turn.");
            }
        }
    }

    private Boolean checkIfRowPatternExists() {
        for (int row = 0; row < 3; row++) {
            TileButton firstTile = gameBoardButtons[row][0];
            TileButton secondTile = gameBoardButtons[row][1];
            TileButton thirdTile = gameBoardButtons[row][2];

            boolean hasWinningPattern = !firstTile.isBlank() && firstTile.equals(secondTile) && secondTile.equals(thirdTile);
            if (hasWinningPattern) {
                winningIndex = row;
                return true;
            }
        }
        return false;
    }

    private Boolean checkIfColumnPatternExists() {
        for (int column = 0; column < 3; column++) {
            TileButton firstTile = gameBoardButtons[0][column];
            TileButton secondTile = gameBoardButtons[1][column];
            TileButton thirdTile = gameBoardButtons[2][column];

            boolean hasWinningPattern = !firstTile.isBlank() && firstTile.equals(secondTile) && secondTile.equals(thirdTile);
            if (hasWinningPattern) {
                winningIndex = column;
                return true;
            }
        }
        return false;
    }

    private Boolean checkIfDiagonalPattern() {
        TileButton firstTile = gameBoardButtons[0][0];
        TileButton secondTile = gameBoardButtons[1][1];
        TileButton thirdTile = gameBoardButtons[2][2];
        return !firstTile.isBlank() && firstTile.equals(secondTile) && secondTile.equals(thirdTile);
    }

    private Boolean checkIfAntidiagonalPattern() {
        TileButton firstTile = gameBoardButtons[0][2];
        TileButton secondTile = gameBoardButtons[1][1];
        TileButton thirdTile = gameBoardButtons[2][0];
        return !firstTile.isBlank() && firstTile.equals(secondTile) && secondTile.equals(thirdTile);
    }

    private Boolean checkIfTiePatternExists() {
        return gameTurnsCounter == 9;
    }


    private void checkIfGameIsOver() {
        ArrayList<Boolean> checkers = new ArrayList<>(
                List.of(
                        checkIfRowPatternExists(),
                        checkIfColumnPatternExists(),
                        checkIfDiagonalPattern(),
                        checkIfAntidiagonalPattern(),
                        checkIfTiePatternExists()
                )
        );


        for(int i = 0; i < checkers.size(); i++) {
            Boolean patternExists = checkers.get(i);
            if(patternExists) {
                gameOver = true;
                setPattern(i);
                break;
            }
        }
    }

    private void setPattern(Integer setterId) {
        switch (setterId) {
            case 0:
                setWinningRowPattern();
                break;
            case 1:
                setWinningColumnPattern();
                break;
            case 2:
                setWinningDiagonalPattern();
                break;
            case 3:
                setWinningAnitdiagonalPattern();
                break;
            case 4: // tie pattern id, itd...
                setTiePattern();
                break;
            default:
                // todo show error or smth lol
        }
        boardGamePanel.updateUI();
    }

//    private void checkWinner() {
//        checkIfTieOccurs();
//        checkIfRowPatternExists();
//        checkIfColumnPatternExists();
//        checkIfDiagonalPattern();
//        checkIfAntidiagonalPattern();
//
//        checkIfGameIsOver();
//    }

    private void setTiePattern() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                gameBoardButtons[row][column].setForeground(Color.ORANGE);
                gameBoardButtons[row][column].setBackground(Color.PINK);
            }
        }
        headerText.setText("Tie!!");
    }

    private void setWinningRowPattern() {
        for (int column = 0; column < 3; column++) {
            setWinningTile(gameBoardButtons[winningIndex][column]);
        }
        setWinningHeader();
    }

    private void setWinningColumnPattern() {
        for (int row = 0; row < 3; row++) {
            setWinningTile(gameBoardButtons[row][winningIndex]);
        }
        setWinningHeader();
    }

    private void setWinningDiagonalPattern() {
        for (int i = 0; i < 3; i++) {
            setWinningTile(gameBoardButtons[i][i]);
        }
        setWinningHeader();
    }

    private void setWinningAnitdiagonalPattern() {
        for (int i = 0; i < 3; i++) {
            int col = gameBoardButtons.length - 1 - i;
            setWinningTile(gameBoardButtons[i][col]);
        }
        setWinningHeader();
    }

    private void setWinningTile(TileButton tile) {
        tile.setAsWinning();
    }

    private void setWinningHeader() {
        headerText.setText(currentPlayer + " is the winner!!");
    }
}
