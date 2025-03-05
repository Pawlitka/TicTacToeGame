package com.github.Pawlitka.validator;

import com.github.Pawlitka.TileButton;

public class TiePatternValidator extends PatternValidator {
    private final int turn;

    public TiePatternValidator(TileButton[][] board, int turn) {
        super(board);
        this.turn = turn;
    }

    @Override
    public Boolean validate() {
        return turn == 9;
    }
}
