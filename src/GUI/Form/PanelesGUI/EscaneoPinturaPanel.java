package GUI.Form.PanelesGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import BusinessLogic.PiezaDeArteBL;
import GUI.Styles;
import GUI.CustomerControl.PrjButton;

public class EscaneoPinturaPanel extends JPanel {

    private JPanel menuPanelPintura;
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JLabel autroLabel;
    private JLabel descripcionLabel;

    private MenuPanel menuPanel;

    private PrjButton btnHome;

    private PiezaDeArteBL piezabl = new PiezaDeArteBL();

    public PrjTextBox barcodeField;

    public EscaneoPinturaPanel(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;
        // setLayout(new BorderLayout()); Con esto dividimos el panel en 5 partes,
        // norete,suer,este y oeste
        // setBackground(Styles.COLOR_BACKGROUND);

        nameLabel = new JLabel("Esperando a que escanee el código ...", SwingConstants.CENTER);
        nameLabel.setFont(Styles.FONT_BOLD_20);
        nameLabel.setForeground(Color.BLACK); // Ponemos el color de la letra
        nameLabel.setBounds(40, 60, 500, 30);
        // nameLabel.setBorder(new LineBorder(Color.black, 2));
        nameLabel.setOpaque(false); // Pintar el fondo de la etiqueta

        priceLabel = new JLabel("Price ...", SwingConstants.LEFT);
        priceLabel.setFont(Styles.FONT_BOLD);
        priceLabel.setForeground(Color.BLACK); // Ponemos el color de la letra
        priceLabel.setBounds(40, 120, 300, 20);
        // priceLabel.setBorder(new LineBorder(Color.black, 2));
        priceLabel.setOpaque(false); // Pintar el fondo de la etiqueta

        autroLabel = new JLabel("Autor ...", SwingConstants.LEFT);
        autroLabel.setFont(Styles.FONT_BOLD);
        autroLabel.setForeground(Color.BLACK); // Ponemos el color de la letra
        autroLabel.setBounds(40, 160, 300, 20);
        // autroLabel.setBorder(new LineBorder(Color.BLACK, 2));
        autroLabel.setOpaque(false);

        descripcionLabel = new JLabel("Descripcion ...", SwingConstants.LEFT);
        descripcionLabel.setFont(Styles.FONT_BOLD);
        descripcionLabel.setForeground(Color.BLACK); // Ponemos el color de la letra
        descripcionLabel.setBounds(40, 190, 300, 100);
        // descripcionLabel.setBorder(new LineBorder(Color.BLACK, 2));
        descripcionLabel.setOpaque(false);

        add(nameLabel);
        add(autroLabel);
        add(priceLabel);
        add(descripcionLabel);
        setLayout(null); // Desactivar el diseño del panel

        // Inicializa los componentes adicionales
        barcodeField = new PrjTextBox();
        // barcodeField.setPreferredSize(new Dimension(200, 30));
        barcodeField.setBounds(100, 400, 200, 30); // Posicionar barcodeField manualmente

        btnHome = new PrjButton("Volver");
        btnHome.setBounds(500, 400, 100, 30);

        // Agregar el campo de texto y el botón al panel
        add(barcodeField);
        add(btnHome);

        setVisible(true);

        btnHome.addActionListener(e -> showMenuPanel());

        barcodeField.addActionListener(e -> {
            getBarcode();
        });

        SwingUtilities.invokeLater(() -> barcodeField.requestFocusInWindow());

        // colocarImagenPieza();

    }

    private void colocarImagenPieza() {
        ImageIcon icon = new ImageIcon(Styles.URL_LOGO);
        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(240, 240, java.awt.Image.SCALE_SMOOTH));
        JLabel logo = new JLabel(scaledIcon);

        logo.setBounds(410, 120, 200, 200);
        add(logo);
    }

    private URL URL_Pieza;

    // private void colocarImagenPieza(String nombrePieza) {
    //     try {
    //         String img = nombrePieza;
    //         URL_Pieza = Styles.class.getResource(img);
    //         ImageIcon icon = new ImageIcon(URL_Pieza);
    //         ImageIcon scaledIcon = new ImageIcon(
    //                 icon.getImage().getScaledInstance(240, 240, java.awt.Image.SCALE_SMOOTH));
    //         JLabel logo = new JLabel(scaledIcon);
    //         logo.setBounds(410, 120, 200, 200);
    //         add(logo);
    //     } catch (Exception e) {
    //         System.out.println("Nose pudo agregar la imagen");
    //     }
    // }

    private JLabel logo = null; // Guardamos una referencia a la imagen

    private void colocarImagenPieza(String nombrePieza) {
        try {
            // Si ya hay una imagen, la eliminamos
            if (logo != null) {
                remove(logo);
            }

            String img = nombrePieza;
            URL_Pieza = Styles.class.getResource(img);
            ImageIcon icon = new ImageIcon(URL_Pieza);
            ImageIcon scaledIcon = new ImageIcon(
                    icon.getImage().getScaledInstance(240, 240, java.awt.Image.SCALE_SMOOTH));

            // Creamos una nueva JLabel con la imagen
            logo = new JLabel(scaledIcon);
            logo.setBounds(410, 120, 200, 200);
            add(logo);

            // Volver a pintar el componente (hacer visible la actualización)
            revalidate();
            repaint();
        } catch (Exception e) {
            System.out.println("No se pudo agregar la imagen");
        }
    }

    private void getBarcode() {
        String barcode = barcodeField.getText();
        if (!barcode.trim().isEmpty()) {
            try {
                processBarcode(barcode);
            } catch (Exception e1) {
                nameLabel.setText(" ... ");
                priceLabel.setText("Vuelva a escanear nuevamente!");
                autroLabel.setText("Ningún Autor");
                descripcionLabel.setText(" ... ");
                colocarImagenPieza();
            }
            barcodeField.setText("");
        }
    }

    private void processBarcode(String barcode) throws Exception {
        String nombre = piezabl.getNombreBy(barcode);
        String precio = piezabl.getPrecioBy(barcode);
        String autor = piezabl.getAutorBy(barcode);
        String descripcion = piezabl.getDescripcionBy(barcode);

        String imgPintura = piezabl.getNombreBy(barcode);
        String ubicacion = "/GUI/Resource/Imagenes/" + imgPintura + ".jpg";

        nameLabel.setText("Nombre de la Pieza: " + nombre + "\n");
        priceLabel.setText("Precio Replica: " + precio + "\n");
        autroLabel.setText("Autor: " + autor + "\n");
        descripcionLabel.setText("Descripción: " + descripcion + "\n");
        System.out.println(ubicacion);
        colocarImagenPieza(ubicacion);

    }

    // Método para dibujar el degradado como fondo
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Definir los puntos de inicio y final del degradado
        Point start = new Point(0, 0);
        Point end = new Point(0, getHeight()); // Degradado vertical

        // Colores en formato hexadecimal
        // Color color1 = Color.decode("#BBB49D");
        Color color2 = Color.decode("#9A7787");
        Color color3 = Color.decode("#E4AFB0");

        // Posiciones donde cada color debe cambiar (en valores entre 0.0f y 1.0f)
        // float[] fractions = { 0.0f, 0.5f, 1.0f };
        float[] fractions = { 0.3f, 1.0f };

        // Crear el degradado con los colores y posiciones definidas
        LinearGradientPaint gradient = new LinearGradientPaint(start, end, fractions,
                new Color[] { color2, color3 });

        // Aplicar el degradado como fondo
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight()); // Rellenar el área con el degradado
    }

    private void showMenuPanel() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.setContentPane(menuPanel);
            frame.revalidate();
            frame.repaint();
        }
    }

}
