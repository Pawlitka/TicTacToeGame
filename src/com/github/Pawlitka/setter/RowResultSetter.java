package com.github.Pawlitka.setter;

import com.github.Pawlitka.TileButton;

import javax.swing.*;

public class RowResultSetter extends ResultSetter {
    public int winningIndex;

    public RowResultSetter(JLabel headerText, String currentPlayer, TileButton[][] board, int winningIndex) {
        super(headerText, currentPlayer, board);
        this.winningIndex = winningIndex;
    }

    @Override
    public void set() {
        super.set();
        for (int column = 0; column < 3; column++) {
            setWinningTile(board[winningIndex][column]);
        }
    }
}
