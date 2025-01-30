package GUI.Form.PanelesGUI;

import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import GUI.Styles;

/**
 * PrjTextBox es un JTextField personalizado con estilos y bordes específicos.
 * Personaliza la apariencia del cuadro de texto para que coincida con el diseño de la aplicación.
 */
public class PrjTextBox extends JTextField {

    /**
     * Personaliza el componente estableciendo varios estilos como borde, fuente, colores y márgenes.
     * Este método se llama para aplicar los estilos personalizados al cuadro de texto.
     */
    private void customizeComponent() {
        setBorderRect(); // Establece un borde rectangular con estilos específicos.
        setFont(Styles.FONT); // Establece el estilo de fuente para el cuadro de texto.
        setForeground(Styles.COLOR_FONT); // Establece el color de la fuente.
        setBackground(Styles.COLOR_FONT_BG); // Establece el color de fondo.
        setCaretColor(Styles.COLOR_CURSOR); // Establece el color del cursor de texto.
        setMargin(new Insets(5, 5, 5, 5)); // Establece el margen dentro del cuadro de texto.
        setOpaque(true); // Asegura que el cuadro de texto sea opaco.
    }

    /**
     * Establece un borde rectangular con un borde de línea y un borde vacío dentro de él.
     * Este método se utiliza para aplicar un estilo de borde específico al cuadro de texto.
     */
    public void setBorderRect() {
        Border lineBorder = BorderFactory.createLineBorder(Styles.COLOR_BORDER_BUTTON); // Crea un borde de línea con un color específico.
        Border emptyBorder = new EmptyBorder(5, 5, 5, 5); // Crea un borde vacío con los márgenes especificados.
        setBorder(new CompoundBorder(lineBorder, emptyBorder)); // Combina el borde de línea y el borde vacío.
    }

    /**
     * Establece un borde de línea con un grosor especificado en la parte inferior del cuadro de texto.
     * Este método se utiliza para aplicar un estilo de borde diferente al cuadro de texto.
     */
    public void setBorderLine() {
        int thickness = 1; // Especifica el grosor del borde.
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 0, thickness, 0), // Crea un borde vacío con los márgenes especificados.
                BorderFactory.createMatteBorder(0, 0, thickness, 0, Styles.COLOR_BORDER_BUTTON))); // Crea un borde mate con el grosor y color especificados.
    }

}
