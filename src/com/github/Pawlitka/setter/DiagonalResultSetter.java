package com.github.Pawlitka.setter;

import com.github.Pawlitka.TicTacToeGameState;
import com.github.Pawlitka.TileButton;

import javax.swing.*;

public class DiagonalResultSetter extends ResultSetter {
    public DiagonalResultSetter(TicTacToeGameState state) {
        super(state);
    }

    @Override
    public void set() {
        super.set();
        for (int i = 0; i < 3; i++) {
            setWinningTile(state.board()[i][i]);
        }
    }
}
