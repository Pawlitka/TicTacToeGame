package com.github.Pawlitka.setter;

import com.github.Pawlitka.TileButton;

import javax.swing.*;

public abstract class ResultSetter {
    protected final JLabel headerText;
    protected final String currentPlayer;
    protected final TileButton[][] board;

    public ResultSetter(JLabel headerText, String currentPlayer, TileButton[][] board) {
        this.headerText = headerText;
        this.currentPlayer = currentPlayer;
        this.board = board;
    }

    public void set() {
        setWinningHeader();
    }

    protected void setWinningTile(TileButton tile) {
        tile.setAsWinning();
    }

    protected void setWinningHeader() {
        headerText.setText(currentPlayer + " is the winner!!");
    }
}

