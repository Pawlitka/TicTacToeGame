import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TileButton extends JButton {
    private ActionListener clickListener = null;

    public TileButton() {
        this.setBackground(Color.BLACK);
        this.setForeground(Color.PINK);
        this.setFont(new Font("Arial", Font.BOLD, 120));
        this.setFocusable(false);
        this.setOpaque(false);
    }

    public void assignClickListener(ActionListener clickListener) {
        boolean isNotClickListenerAssigned = this.clickListener == null;
        if(isNotClickListenerAssigned) {
            this.addActionListener(clickListener);
            this.clickListener = clickListener;
        }
    }
}
