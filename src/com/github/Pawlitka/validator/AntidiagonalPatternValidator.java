package com.github.Pawlitka.validator;

import com.github.Pawlitka.TicTacToeGameState;
import com.github.Pawlitka.TileButton;
import com.github.Pawlitka.setter.ResultSetterName;

public class AntidiagonalPatternValidator extends PatternValidator{
    public AntidiagonalPatternValidator(TicTacToeGameState state) {
        super(state, ResultSetterName.ANTIDAGONAL);
    }

    @Override
    public Boolean validate() {
        TileButton firstTile = state.board()[0][2];
        TileButton secondTile = state.board()[1][1];
        TileButton thirdTile = state.board()[2][0];
        return !firstTile.isBlank() && firstTile.equals(secondTile) && secondTile.equals(thirdTile);
    }
}
