package com.github.Pawlitka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToeGame {
    private final JFrame gameFrame = new JFrame();
    private final TicTacToeGameState state = new TicTacToeGameState(3);
    private final PatternManager patternManager = new PatternManager(state);

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
        gameFrame.add(state.headerPanel(), BorderLayout.NORTH);
    }

    private void addBoardToFrame() {
        setBoardPanel();
        gameFrame.add(state.boardPanel());
    }

    private void addTileButtonsToBoard() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                TileButton tile = new TileButton();
                tile.assignClickListener(this::handleTileButtonClick);
                state.board()[row][column] = tile;
                state.boardPanel().add(tile);
            }
        }
        state.boardPanel().updateUI();
    }

    private void setHeaderText() {
        JLabel headerText = state.headerText();
        headerText.setBackground(Color.PINK);
        headerText.setForeground(Color.BLACK);
        headerText.setFont(new Font("Arial", Font.BOLD, 50));
        headerText.setHorizontalAlignment(JLabel.CENTER);
        headerText.setText("Tic-Tac-Toe");
        headerText.setOpaque(true);
    }

    private void setHeaderPanel() {
        JPanel headerPanel = state.headerPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(state.headerText());
    }

    private void setBoardPanel() {
        JPanel boardPanel = state.boardPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.PINK);
    }

    private void handleTileButtonClick(ActionEvent e) {
        if (state.gameOver()) return;
        TileButton tile = (TileButton) e.getSource();
        if (tile.isBlank()) {
            tile.setText(state.currentPlayer());
            state.incrementTurnsCounter();
            patternManager.checkIsGameOver();
            if (!state.gameOver()) {
                state.updateForNextTurn();
            }
        }
    }
}
