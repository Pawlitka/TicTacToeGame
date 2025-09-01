package com.github.Pawlitka.setter;

import com.github.Pawlitka.TicTacToeGameState;
import com.github.Pawlitka.TileButton;

import javax.swing.*;
import java.awt.*;

public class TieResultSetter extends ResultSetter {

    public TieResultSetter(TicTacToeGameState state) {
        super(state);
    }

    @Override
    public void set() {
        super.set();
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                state.board()[row][column].setForeground(Color.ORANGE);
                state.board()[row][column].setBackground(Color.PINK);
            }
        }
    }

    @Override
    protected void setWinningHeader() {
        state.headerText().setText("Tie!!");
    }
}
