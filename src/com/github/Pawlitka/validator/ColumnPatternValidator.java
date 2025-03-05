package com.github.Pawlitka.validator;

import com.github.Pawlitka.TileButton;

public class ColumnPatternValidator extends PatternValidator {
    public int winningIndex;

    public ColumnPatternValidator(TileButton[][] board) {
        super(board);
    }

    @Override
    public Boolean validate() {
        for (int column = 0; column < 3; column++) {
            TileButton firstTile = board[0][column];
            TileButton secondTile = board[1][column];
            TileButton thirdTile = board[2][column];

            boolean hasWinningPattern = !firstTile.isBlank() && firstTile.equals(secondTile) && secondTile.equals(thirdTile);
            if (hasWinningPattern) {
                winningIndex = column;
                return true;
            }
        }
        return false;
    }
}
