package com.github.Pawlitka;

import com.github.Pawlitka.setter.*;
import com.github.Pawlitka.validator.*;

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

    // TODO START
    private void checkIfGameIsOver() {
        ArrayList<PatternValidator> validators = new ArrayList<>(
                List.of(
                        new RowPatternValidator(gameBoardButtons, ResultSetterName.ROW),
                        new ColumnPatternValidator(gameBoardButtons, ResultSetterName.COLUMN),
                        new DiagonalPatternValidator(gameBoardButtons, ResultSetterName.DIAGONAL),
                        new AntidiagonalPatternValidator(gameBoardButtons, ResultSetterName.ANTIDAGONAL),
                        new TiePatternValidator(gameBoardButtons, ResultSetterName.TIE, gameTurnsCounter)
                )
        );

        for(int i = 0; i < validators.size(); i++) {
            PatternValidator validator = validators.get(i);
            if(validator.validate()) {
                if(validator instanceof RowPatternValidator) {
                    winningIndex = ((RowPatternValidator) validator).winningIndex;
                } else if(validator instanceof ColumnPatternValidator) {
                    winningIndex = ((ColumnPatternValidator) validator).winningIndex;
                }
                gameOver = true;
                setPattern(validator.getResultSetterName());
                break;
            }
        }
    }

    private void setPattern(ResultSetterName setterName) {
        ResultSetter setter = switch (setterName) {
            case ROW -> new RowResultSetter(headerText, currentPlayer, gameBoardButtons, winningIndex);
            case COLUMN -> new ColumnResultSetter(headerText, currentPlayer, gameBoardButtons, winningIndex);
            case DIAGONAL -> new DiagonalResultSetter(headerText, currentPlayer, gameBoardButtons);
            case ANTIDAGONAL -> new AntidiagonalResultSetter(headerText, currentPlayer, gameBoardButtons);
            case TIE -> new TieResultSetter(headerText, gameBoardButtons);
//            default -> throw new NoResultSetterException("Too much validators!");
        };
        setter.set();
        boardGamePanel.updateUI();
    }

    // TODO END
}
