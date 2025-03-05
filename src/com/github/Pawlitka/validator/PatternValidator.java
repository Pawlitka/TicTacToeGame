package com.github.Pawlitka.validator;

import com.github.Pawlitka.TileButton;
import com.github.Pawlitka.setter.ResultSetterName;

public abstract class PatternValidator {
    protected final TileButton[][] board;
    protected final ResultSetterName resultSetterName;

    public PatternValidator(TileButton[][] board, ResultSetterName resultSetterName) {
        this.board = board;
        this.resultSetterName = resultSetterName;
    }

    public abstract Boolean validate();

    public ResultSetterName getResultSetterName() {
        return this.resultSetterName;
    }
}
