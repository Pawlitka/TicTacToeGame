package com.github.Pawlitka.validator;

import com.github.Pawlitka.TicTacToeGameState;
import com.github.Pawlitka.TileButton;
import com.github.Pawlitka.setter.ResultSetterName;

public class RowPatternValidator extends PatternValidator {
    public int winningIndex;

    public RowPatternValidator(TicTacToeGameState state) {
        super(state, ResultSetterName.ROW);
    }

    @Override
    public Boolean validate() {
        for (int row = 0; row < 3; row++) {
            TileButton firstTile = state.board()[row][0];
            TileButton secondTile = state.board()[row][1];
            TileButton thirdTile = state.board()[row][2];

            boolean hasWinningPattern = !firstTile.isBlank() && firstTile.equals(secondTile) && secondTile.equals(thirdTile);
            if (hasWinningPattern) {
                winningIndex = row;
                return true;
            }
        }
        return false;
    }
}
