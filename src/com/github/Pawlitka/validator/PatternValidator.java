package com.github.Pawlitka.validator;

import com.github.Pawlitka.TicTacToeGameState;
import com.github.Pawlitka.TileButton;
import com.github.Pawlitka.setter.ResultSetterName;

public abstract class PatternValidator {
    protected final TicTacToeGameState state;
    protected final ResultSetterName resultSetterName;

    public PatternValidator(TicTacToeGameState state, ResultSetterName resultSetterName) {
        this.state = state;
        this.resultSetterName = resultSetterName;
    }

    public abstract Boolean validate();

    public ResultSetterName getResultSetterName() {
        return this.resultSetterName;
    }
}
