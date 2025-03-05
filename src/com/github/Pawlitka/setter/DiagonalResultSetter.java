package com.github.Pawlitka.setter;

import com.github.Pawlitka.TileButton;

import javax.swing.*;

public class DiagonalResultSetter extends ResultSetter {
    public DiagonalResultSetter(JLabel headerText, String currentPlayer, TileButton[][] board) {
        super(headerText, currentPlayer, board);
    }

    @Override
    public void set() {
        super.set();
        for (int i = 0; i < 3; i++) {
            setWinningTile(board[i][i]);
        }
    }
}
