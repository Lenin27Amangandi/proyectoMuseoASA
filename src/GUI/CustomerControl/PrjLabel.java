package GUI.CustomerControl;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.text.Style;

import GUI.Styles;

public class PrjLabel extends JLabel {

    public PrjLabel() {
        customizeComponent();
    }

    public PrjLabel(String text) {
        setText(text);
        customizeComponent();
    }

    private void customizeComponent() {
        setCustomizeComponent(getText(), Styles.FONT, Styles.COLOR_FONT, Styles.ALIGNMENT_LEFT);
    }

    public void setCustomizeComponent(String text, Font font, Color color, int alignment) {
        setText(text);
        setFont(font);
        setOpaque(false);
        setBackground(null);
        setForeground(Styles.COLOR_FOREGROUND);
        setHorizontalAlignment(alignment);
        // setIcon(new ImageIcon(iconPath));
    }
}
