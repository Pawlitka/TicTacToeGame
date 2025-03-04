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

    public void setAsWinning() {
        this.setForeground(Color.GREEN);
        this.setBackground(Color.PINK);
    }

    public Boolean isBlank() {
        return this.getText().isBlank();
    }

    @Override
    public boolean equals(Object obj) {
        boolean isObjectComparedWithItself = obj == this;
        if (isObjectComparedWithItself) {
            return true;
        }

        boolean isNotObjectInstanceOfTileButton = !(obj instanceof TileButton);
        if (isNotObjectInstanceOfTileButton) {
            return false;
        }

        TileButton tileButton = (TileButton) obj;
        return this.getText().equals(tileButton.getText());
    }

    @Override
    public String toString() {
        return "Background color: "  + this.getBackground() + ", Foreground color:" + this.getForeground();
    }
}
