package com.github.Pawlitka.validator;

import com.github.Pawlitka.TileButton;

public class RowPatternValidator extends PatternValidator {
    public int winningIndex;

    public RowPatternValidator(TileButton[][] board) {
        super(board);
    }

    @Override
    public Boolean validate() {
        for (int row = 0; row < 3; row++) {
            TileButton firstTile = board[row][0];
            TileButton secondTile = board[row][1];
            TileButton thirdTile = board[row][2];

            boolean hasWinningPattern = !firstTile.isBlank() && firstTile.equals(secondTile) && secondTile.equals(thirdTile);
            if (hasWinningPattern) {
                winningIndex = row;
                return true;
            }
        }
        return false;
    }
}
