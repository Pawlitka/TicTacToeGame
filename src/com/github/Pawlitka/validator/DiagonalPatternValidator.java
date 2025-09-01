package com.github.Pawlitka.validator;

import com.github.Pawlitka.TicTacToeGameState;
import com.github.Pawlitka.TileButton;
import com.github.Pawlitka.setter.ResultSetterName;

public class DiagonalPatternValidator extends PatternValidator {

    public DiagonalPatternValidator(TicTacToeGameState state) {
        super(state, ResultSetterName.DIAGONAL);
    }

    @Override
    public Boolean validate() {
        TileButton firstTile = state.board()[0][0];
        TileButton secondTile = state.board()[1][1];
        TileButton thirdTile = state.board()[2][2];
        return !firstTile.isBlank() && firstTile.equals(secondTile) && secondTile.equals(thirdTile);
    }
}
