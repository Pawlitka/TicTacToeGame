package com.github.Pawlitka.setter;

import com.github.Pawlitka.TicTacToeGameState;
import com.github.Pawlitka.TileButton;

import javax.swing.*;

public class RowResultSetter extends ResultSetter {
    public int winningIndex;

    public RowResultSetter(TicTacToeGameState state, int winningIndex) {
        super(state);
        this.winningIndex = winningIndex;
    }

    @Override
    public void set() {
        super.set();
        for (int column = 0; column < 3; column++) {
            setWinningTile(state.board()[winningIndex][column]);
        }
    }
}
