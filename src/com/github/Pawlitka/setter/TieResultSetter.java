package com.github.Pawlitka.setter;

import com.github.Pawlitka.TileButton;

import javax.swing.*;
import java.awt.*;

public class TieResultSetter extends ResultSetter {

    public TieResultSetter(JLabel headerText, TileButton[][] board) {
        super(headerText, "", board);
    }

    @Override
    public void set() {
        super.set();
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                board[row][column].setForeground(Color.ORANGE);
                board[row][column].setBackground(Color.PINK);
            }
        }
    }

    @Override
    protected void setWinningHeader() {
        headerText.setText("Tie!!");
    }
}
