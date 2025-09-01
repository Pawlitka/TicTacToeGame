package com.github.Pawlitka.validator;

import com.github.Pawlitka.TicTacToeGameState;
import com.github.Pawlitka.TileButton;
import com.github.Pawlitka.setter.ResultSetterName;

public class TiePatternValidator extends PatternValidator {
    public TiePatternValidator(TicTacToeGameState state) {
        super(state, ResultSetterName.TIE);
    }

    @Override
    public Boolean validate() {
        return state.turnsCounter() == 9;
    }
}
