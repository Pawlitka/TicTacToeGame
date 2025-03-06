package com.github.Pawlitka;

import javax.swing.*;
import java.util.Objects;

public class TicTacToeGameState {
    public static final String FIRST_PLAYER = "X";
    private static final String SECOND_PLAYER = "O";

    private final JPanel headerPanel;
    private final JLabel headerText;
    private final JPanel boardPanel;
    private final TileButton[][] board;
    private String currentPlayer;
    private Integer turnsCounter;
    private Boolean gameOver;

    public TicTacToeGameState(
            Integer boardSize
    ) {
        this.headerPanel = new JPanel();
        this.headerText = new JLabel();
        this.boardPanel = new JPanel();
        this.board = new TileButton[boardSize][boardSize];
        this.currentPlayer = FIRST_PLAYER;
        this.turnsCounter = 0;
        this.gameOver = false;
    }

    public void incrementTurnsCounter() {
        turnsCounter++;
    }

    public void updateForNextTurn() {
        updateCurrentPlayer();
        updateHeaderText();
    }

    public JPanel headerPanel() {
        return headerPanel;
    }

    public JLabel headerText() {
        return headerText;
    }

    public JPanel boardPanel() {
        return boardPanel;
    }

    public TileButton[][] board() {
        return board;
    }

    public String currentPlayer() {
        return currentPlayer;
    }

    public Integer turnsCounter() {
        return turnsCounter;
    }

    public Boolean gameOver() {
        return gameOver;
    }

    public void setGameOver(Boolean newVal) {
        gameOver = newVal;
    }

    private void updateCurrentPlayer() {
        currentPlayer = Objects.equals(currentPlayer, FIRST_PLAYER) ? SECOND_PLAYER : FIRST_PLAYER;
    }

    private void updateHeaderText() {
        headerText.setText(currentPlayer + "'s turn.");
    }
}
