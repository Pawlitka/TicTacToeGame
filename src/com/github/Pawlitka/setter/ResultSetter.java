package com.github.Pawlitka.setter;

import com.github.Pawlitka.TicTacToeGameState;
import com.github.Pawlitka.TileButton;

import javax.swing.*;

public abstract class ResultSetter {
    protected final TicTacToeGameState state;

    public ResultSetter(TicTacToeGameState state) {
        this.state = state;
    }

    public void set() {
        setWinningHeader();
    }

    protected void setWinningTile(TileButton tile) {
        tile.setAsWinning();
    }

    protected void setWinningHeader() {
        state.headerText().setText(state.currentPlayer() + " is the winner!!");
    }
}

