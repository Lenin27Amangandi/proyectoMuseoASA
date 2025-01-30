package GUI.CustomerControl;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import GUI.Styles;

public class PrjButton extends JButton implements MouseListener {

    public PrjButton(String text) {
        customizeComponent(text);
    }

    public PrjButton(String text, int x, int y) {
        customizeComponent(text, x, y);
    }

    /**
     * Personaliza el componente con el texto especificado.
     *
     * @param text el texto que se mostrará en el componente.
     *             Este método ajusta el texto del componente, lo hace opaco,
     *             desactiva la pintura del foco y del borde, llena el área de
     *             contenido,
     *             establece el color del texto, la alineación horizontal, la fuente
     *             y el cursor.
     */
    public void customizeComponent(String text) {
        setText(text); // Establece el texto del componente.
        setOpaque(true); // Hace que el componente sea opaco.
        setFocusPainted(false); // Desactiva la pintura del foco.
        setBorderPainted(false); // Desactiva la pintura del borde.
        setContentAreaFilled(true); // Llena el área de contenido del componente.
        setForeground(Styles.COLOR_FONT); // Establece el color del texto del componente.
        // setHorizontalAlignment(Styles.ALIGNMENT_LEFT); // Establece la alineación horizontal del texto a la izquierda.
        setFont(Styles.FONT); // Establece la fuente del texto del componente.
        setCursor(new Cursor(Cursor.HAND_CURSOR)); // Establece el cursor a una mano cuando se pasa sobre el componente.
    }

    public void customizeComponent(String text, int x, int y) {
        setText(text); // Establece el texto del componente.
        setOpaque(true); // Hace que el componente sea opaco.
        setFocusPainted(false); // Desactiva la pintura del foco.
        setBorderPainted(false); // Desactiva la pintura del borde.
        setContentAreaFilled(true); // Llena el área de contenido del componente.
        setForeground(Styles.COLOR_FONT); // Establece el color del texto del componente.
        // setHorizontalAlignment(Styles.ALIGNMENT_LEFT); // Establece la alineación horizontal del texto a la izquierda.
        setFont(Styles.FONT); // Establece la fuente del texto del componente.
        setCursor(new Cursor(Cursor.HAND_CURSOR)); // Establece el cursor a una mano cuando se pasa sobre el componente.
        setBounds(x, y, getPreferredSize().width, getPreferredSize().height); // Establece la posición del botón.
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setForeground(Color.BLACK);
        setCursor(Styles.CURSOR_HAND);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setForeground(Color.GRAY);
        setCursor(Styles.CURSOR_DEFAULT);

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

}
