package com.github.Pawlitka.validator;

import com.github.Pawlitka.TicTacToeGameState;
import com.github.Pawlitka.TileButton;
import com.github.Pawlitka.setter.ResultSetterName;

public class ColumnPatternValidator extends PatternValidator {
    public int winningIndex;

    public ColumnPatternValidator(TicTacToeGameState state) {
        super(state, ResultSetterName.COLUMN);
    }

    @Override
    public Boolean validate() {
        for (int column = 0; column < 3; column++) {
            TileButton firstTile = state.board()[0][column];
            TileButton secondTile = state.board()[1][column];
            TileButton thirdTile = state.board()[2][column];

            boolean hasWinningPattern = !firstTile.isBlank() && firstTile.equals(secondTile) && secondTile.equals(thirdTile);
            if (hasWinningPattern) {
                winningIndex = column;
                return true;
            }
        }
        return false;
    }
}
