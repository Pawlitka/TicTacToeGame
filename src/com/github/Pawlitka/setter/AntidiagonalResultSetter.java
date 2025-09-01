package com.github.Pawlitka.setter;

import com.github.Pawlitka.TicTacToeGameState;
import com.github.Pawlitka.TileButton;

import javax.swing.*;

public class AntidiagonalResultSetter extends ResultSetter {
    public AntidiagonalResultSetter(TicTacToeGameState state) {
        super(state);
    }

    @Override
    public void set() {
        super.set();
        for (int i = 0; i < 3; i++) {
            int column = state.board().length - 1 - i;
            setWinningTile(state.board()[i][column]);
        }
    }
}
