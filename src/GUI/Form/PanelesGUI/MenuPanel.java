package GUI.Form.PanelesGUI;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.text.Style;

import GUI.Styles;
import GUI.CustomerControl.PrjButton;

/**
 * MenuPanel es una clase que extiende JPanel y representa un panel de menú
 * personalizado
 * con botones y una imagen de fondo.
 */
public class MenuPanel extends JPanel {

    private PrjButton btnVisitante = new PrjButton("Escanear Pintura");
    private PrjButton btnAdministrador = new PrjButton("Login");
    private JPanel panelDeAdorno = new JPanel();
    private Image fondo;



    /**
     * Constructor de la clase MenuPanel.
     * Inicializa el componente personalizado.
     */
    public MenuPanel() {
        customizeComponent();
        btnVisitante.addActionListener(e -> showEscaneoPanel());
        btnAdministrador.addActionListener(e -> showEscaneoLogin());
    }

    /**
     * Método privado que personaliza los componentes del panel.
     * Establece la imagen de fondo, el diseño del panel y añade los botones.
     */
    private void customizeComponent() {
        this.fondo = new ImageIcon(Styles.URL_FONDO).getImage();
        setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 30));
        btnVisitante.setForeground(Styles.COLOR_FONT);
        add(btnVisitante);
        add(btnAdministrador);
    }

    private void showEscaneoPanel() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            // frame.setContentPane(new EscaneoPanel(this)); // Pasar 'this' para el botón 'Regresar al menu'
            frame.setContentPane(new EscaneoPanelMejorado(this)); // Pasar 'this' para el botón 'Regresar al menu'
            frame.revalidate();
            frame.repaint();
        }
    }


    private void showEscaneoLogin() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.setContentPane(new EscaneoLogin(this));
            frame.revalidate();
            frame.repaint();
        }
    }

    /**
     * Sobrescribe el método paintComponent para dibujar la imagen de fondo en el
     * panel.
     * 
     * @param g el contexto gráfico en el que se dibuja el componente.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
