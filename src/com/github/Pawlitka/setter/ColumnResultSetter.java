package com.github.Pawlitka.setter;

import com.github.Pawlitka.TicTacToeGameState;
import com.github.Pawlitka.TileButton;

import javax.swing.*;

public class ColumnResultSetter extends ResultSetter {
    public int winningIndex;

    public ColumnResultSetter(TicTacToeGameState state, int winningIndex) {
        super(state);
        this.winningIndex = winningIndex;
    }

    @Override
    public void set() {
        super.set();
        for (int row = 0; row < 3; row++) {
            setWinningTile(state.board()[row][winningIndex]);
        }
    }
}
