package com.github.Pawlitka.validator;

import com.github.Pawlitka.TileButton;

public class AntidiagonalPatternValidator extends PatternValidator{
    public AntidiagonalPatternValidator(TileButton[][] board) {
        super(board);
    }

    @Override
    public Boolean validate() {
        TileButton firstTile = board[0][2];
        TileButton secondTile = board[1][1];
        TileButton thirdTile = board[2][0];
        return !firstTile.isBlank() && firstTile.equals(secondTile) && secondTile.equals(thirdTile);
    }
}
