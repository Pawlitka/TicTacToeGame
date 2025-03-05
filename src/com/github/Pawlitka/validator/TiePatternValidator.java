package com.github.Pawlitka.validator;

import com.github.Pawlitka.TileButton;
import com.github.Pawlitka.setter.ResultSetterName;

public class TiePatternValidator extends PatternValidator {
    private final int turn;

    public TiePatternValidator(TileButton[][] board, ResultSetterName resultSetterName, int turn) {
        super(board, resultSetterName);
        this.turn = turn;
    }

    @Override
    public Boolean validate() {
        return turn == 9;
    }
}
