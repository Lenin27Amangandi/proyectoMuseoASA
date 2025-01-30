package GUI.Form.PanelesGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import BusinessLogic.PiezaDeArteBL;
import GUI.Styles;
import GUI.CustomerControl.PrjButton;

public class EscaneoPanel extends JPanel {

    private JLabel nameLabel;
    private JLabel priceLabel;
    private JLabel autorLabel;
    private JLabel descripcionLabel;

    public PrjTextBox barcodeField;

    private PiezaDeArteBL piezabl = new PiezaDeArteBL();
    private PrjButton btnHome;
    private MenuPanel menuPanel;
    private Image fondo;


    public EscaneoPanel(MenuPanel menuPanel) {
        priceLabel = new JLabel();
        this.menuPanel = menuPanel;
        btnHome = new PrjButton("Regresar al menu");
        setLayout(new BorderLayout());
        setBackground(Styles.COLOR_BACKGROUND);

        nameLabel = new JLabel("Esperando a que escanee el código del producto...", SwingConstants.CENTER);
        nameLabel.setFont(Styles.FONT_BOLD);
        nameLabel.setForeground(Styles.COLOR_FOREGROUND);

        priceLabel = new JLabel("...", SwingConstants.CENTER);
        priceLabel.setFont(Styles.FONT_BOLD_20);
        priceLabel.setForeground(Styles.COLOR_FOREGROUND);

        autorLabel = new JLabel("...", SwingConstants.CENTER);
        autorLabel.setFont(Styles.FONT_BOLD_20);
        autorLabel.setForeground(Styles.COLOR_FOREGROUND);

        descripcionLabel = new JLabel("...", SwingConstants.CENTER);
        descripcionLabel.setFont(Styles.FONT_BOLD_20);
        descripcionLabel.setForeground(Styles.COLOR_FOREGROUND);


        JPanel northPanel = new JPanel();

        northPanel.setLayout(new BorderLayout());
        northPanel.setBackground(Styles.COLOR_BACKGROUND);
        northPanel.setPreferredSize(new Dimension(200, 100));
        northPanel.add(nameLabel, BorderLayout.CENTER);

        
        add(northPanel, BorderLayout.NORTH);
        add(priceLabel, BorderLayout.WEST);
        northPanel.add(autorLabel,BorderLayout.WEST);
        add(descripcionLabel, BorderLayout.EAST);

        barcodeField = new PrjTextBox();
        barcodeField.setPreferredSize(new Dimension(200, 30));

        JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.setBackground(Styles.COLOR_FONT_LIGHT);
        southPanel.add(barcodeField);
        southPanel.add(btnHome);

        add(southPanel, BorderLayout.SOUTH);

        btnHome.addActionListener(e -> showMenuPanel());
        barcodeField.addActionListener(e -> {
            String barcode = barcodeField.getText();
            if (!barcode.trim().isEmpty()) {
                try {
                    processBarcode(barcode);
                } catch (Exception e1) {

                    nameLabel.setText(" ... ");
                    priceLabel.setText("Vuelva a escanear nuevamente!");
                    autorLabel.setText("Ningun Autor");
                    descripcionLabel.setText(" ... ");
                }
                barcodeField.setText("");
            }
        });
        SwingUtilities.invokeLater(()->barcodeField.requestFocusInWindow());
        SwingUtilities.invokeLater(() -> barcodeField.requestFocusInWindow());

    }

    private void processBarcode(String barcode) throws Exception {
        String nombre = piezabl.getNombreBy(barcode);
        String precio = piezabl.getPrecioBy(barcode);
        String autor = piezabl.getAutorBy(barcode);
        String descripcion = piezabl.getDescripcionBy(barcode);

        nameLabel.setText("Nombre de la Pieza: " + nombre + "\n");
        priceLabel.setText("Precio Replica: " + precio + "\n");
        autorLabel.setText("Autor: "+ autor +"\n");
        descripcionLabel.setText("Descripcion: "+ descripcion+ "\n");
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
