package com.github.Pawlitka;

import com.github.Pawlitka.setter.*;
import com.github.Pawlitka.validator.*;

import java.util.ArrayList;
import java.util.List;

public class PatternManager {
    private final TicTacToeGameState state;
    private final ArrayList<PatternValidator> validators;
    private int winningIndex;

    public PatternManager(TicTacToeGameState state) {
        this.state = state;
        this.validators = createValidators(state);
    }

    public void checkIsGameOver() {
        Boolean isGameOver = isGameOver();
        state.setGameOver(isGameOver);
    }

    private Boolean isGameOver() {
        for (PatternValidator validator : validators) {
            if (validator.validate()) {
                setPossibleWinningIndex(validator);
                setPattern(validator.getResultSetterName());
                return true;
            }
        }
        return false;
    }

    private void setPossibleWinningIndex(PatternValidator validator) {
        if (validator instanceof RowPatternValidator) {
            winningIndex = ((RowPatternValidator) validator).winningIndex;
        } else if (validator instanceof ColumnPatternValidator) {
            winningIndex = ((ColumnPatternValidator) validator).winningIndex;
        }
    }

    private void setPattern(ResultSetterName setterName) {

        ResultSetter setter = switch (setterName) {
            case ROW -> new RowResultSetter(state, winningIndex);
            case COLUMN -> new ColumnResultSetter(state, winningIndex);
            case DIAGONAL -> new DiagonalResultSetter(state);
            case ANTIDAGONAL -> new AntidiagonalResultSetter(state);
            case TIE -> new TieResultSetter(state);
            default -> throw new NoResultSetterException("Too much validators!");
        };
        setter.set();
        state.boardPanel().updateUI();
    }

    private ArrayList<PatternValidator> createValidators(TicTacToeGameState state) {
        return new ArrayList<>(
                List.of(
                        new RowPatternValidator(state),
                        new ColumnPatternValidator(state),
                        new DiagonalPatternValidator(state),
                        new AntidiagonalPatternValidator(state),
                        new TiePatternValidator(state)
                )
        );
    }
}
