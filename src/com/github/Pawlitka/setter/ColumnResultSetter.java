package com.github.Pawlitka.setter;

import com.github.Pawlitka.TileButton;

import javax.swing.*;

public class ColumnResultSetter extends ResultSetter {
    public int winningIndex;

    public ColumnResultSetter(JLabel headerText, String currentPlayer, TileButton[][] board, int winningIndex) {
        super(headerText, currentPlayer, board);
        this.winningIndex = winningIndex;
    }

    @Override
    public void set() {
        super.set();
        for (int row = 0; row < 3; row++) {
            setWinningTile(board[row][winningIndex]);
        }
    }
}
