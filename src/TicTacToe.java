import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToe {

    int boardWidth = 600;
    int boeardHeight = 650;

    JFrame frame = new JFrame();

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth,boeardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    }
}
