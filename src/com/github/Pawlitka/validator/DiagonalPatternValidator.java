package com.github.Pawlitka.validator;

import com.github.Pawlitka.TileButton;

public class DiagonalPatternValidator extends PatternValidator {

    public DiagonalPatternValidator(TileButton[][] board) {
        super(board);
    }

    @Override
    public Boolean validate() {
        TileButton firstTile = board[0][0];
        TileButton secondTile = board[1][1];
        TileButton thirdTile = board[2][2];
        return !firstTile.isBlank() && firstTile.equals(secondTile) && secondTile.equals(thirdTile);
    }
}
