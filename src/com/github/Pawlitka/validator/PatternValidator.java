package com.github.Pawlitka.validator;

import com.github.Pawlitka.TileButton;

public abstract class PatternValidator {
    protected final TileButton[][] board;

    public PatternValidator(TileButton[][] board) {
        this.board = board;
    }

    public abstract Boolean validate();
}
