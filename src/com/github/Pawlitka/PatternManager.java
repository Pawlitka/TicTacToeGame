package com.github.Pawlitka;

import com.github.Pawlitka.setter.*;
import com.github.Pawlitka.validator.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PatternManager {
    private ArrayList<PatternValidator> validators;
    private int winningIndex;

    public PatternManager() {
    }

    public Boolean isGameOver(JPanel boardGamePanel, JLabel headerText, String currentPlayer, TileButton[][] board, int turns) {
        createValidators(board, turns);

        for (PatternValidator validator : validators) {
            if (validator.validate()) {
                setPossibleWinningIndex(validator);
                setPattern(boardGamePanel, headerText, currentPlayer, board, validator.getResultSetterName());
                return true;
            }
        }
        return false;
    }

    private void createValidators(TileButton[][] board, int turns) {
        validators = new ArrayList<>(List.of(new RowPatternValidator(board, ResultSetterName.ROW), new ColumnPatternValidator(board, ResultSetterName.COLUMN), new DiagonalPatternValidator(board, ResultSetterName.DIAGONAL), new AntidiagonalPatternValidator(board, ResultSetterName.ANTIDAGONAL), new TiePatternValidator(board, ResultSetterName.TIE, turns)));
    }

    private void setPossibleWinningIndex(PatternValidator validator) { // todo think about interface for getter WinningIndexGettable chatgpt ask
        if (validator instanceof RowPatternValidator) {
            winningIndex = ((RowPatternValidator) validator).winningIndex;
        } else if (validator instanceof ColumnPatternValidator) {
            winningIndex = ((ColumnPatternValidator) validator).winningIndex;
        }
//        WinningIndexGettable g = (WinningIndexGettable) validator;
//        g.getWinningIndex();
    }

    private void setPattern(JPanel boardGamePanel, JLabel headerText, String currentPlayer,
                            TileButton[][] gameBoardButtons, ResultSetterName setterName) {

        ResultSetter setter = switch (setterName) {
            case ROW -> new RowResultSetter(headerText, currentPlayer, gameBoardButtons, winningIndex);
            case COLUMN -> new ColumnResultSetter(headerText, currentPlayer, gameBoardButtons, winningIndex);
            case DIAGONAL -> new DiagonalResultSetter(headerText, currentPlayer, gameBoardButtons);
            case ANTIDAGONAL -> new AntidiagonalResultSetter(headerText, currentPlayer, gameBoardButtons);
            case TIE -> new TieResultSetter(headerText, gameBoardButtons);
            default -> throw new NoResultSetterException("Too much validators!");
        };
        setter.set();
        boardGamePanel.updateUI();
    }
}
