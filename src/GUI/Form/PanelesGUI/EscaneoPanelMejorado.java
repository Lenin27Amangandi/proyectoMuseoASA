package GUI.Form.PanelesGUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import BusinessLogic.PiezaDeArteBL;
import GUI.Styles;
import GUI.CustomerControl.PrjButton;

public class EscaneoPanelMejorado extends JPanel {

    private JLabel nameLabel;
    private JLabel priceLabel;
    private JLabel autorLabel;
    private JLabel descripcionLabel;

    public PrjTextBox barcodeField;

    private PiezaDeArteBL piezabl = new PiezaDeArteBL();
    private PrjButton btnHome;
    private MenuPanel menuPanel;
    private JLabel imageLabel; // JLabel para la imagen


    public EscaneoPanelMejorado(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;
        setLayout(new BorderLayout());
        setBackground(Styles.COLOR_BACKGROUND);

        // Creación de los componentes
        nameLabel = new JLabel("Esperando a que escanee el código de la Pieza ...", SwingConstants.CENTER);
        nameLabel.setFont(Styles.FONT);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setBorder(new LineBorder(Color.BLACK, 2));


        priceLabel = new JLabel("...", SwingConstants.CENTER);
        priceLabel.setFont(Styles.FONT);
        priceLabel.setForeground(Color.BLACK);
        priceLabel.setBorder(new LineBorder(Color.BLACK, 2));


        autorLabel = new JLabel("...", SwingConstants.CENTER);
        autorLabel.setFont(Styles.FONT);
        autorLabel.setForeground(Color.BLACK);
        autorLabel.setBorder(new LineBorder(Color.BLACK, 2));


        descripcionLabel = new JLabel("...", SwingConstants.CENTER);
        descripcionLabel.setFont(Styles.FONT);
        descripcionLabel.setForeground(Color.BLACK);
        descripcionLabel.setBorder(new LineBorder(Color.BLACK, 2));



        // Panel para el norte con el nombre de la pieza
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setBackground(new Color(0xFEFDEF));
        northPanel.setPreferredSize(new Dimension(200, 25));
        northPanel.add(nameLabel, BorderLayout.CENTER);

        // Panel central para los detalles
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Styles.COLOR_BACKGROUND2);

        // Definir el GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Espaciado entre los componentes

        // Nombre de la pieza
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(new JLabel("Nombre de la pieza:"), gbc);
        gbc.gridy++;
        centerPanel.add(nameLabel, gbc);

        // Precio
        gbc.gridy++;
        gbc.gridwidth = 2;
        centerPanel.add(new JLabel("Precio:"), gbc);
        gbc.gridy++;
        centerPanel.add(priceLabel, gbc);

        // Autor
        gbc.gridy++;
        gbc.gridwidth = 2;
        centerPanel.add(new JLabel("Autor:"), gbc);
        gbc.gridy++;
        centerPanel.add(autorLabel, gbc);

        // Descripción
        gbc.gridy++;
        gbc.gridwidth = 2;
        centerPanel.add(new JLabel("Descripción:"), gbc);
        gbc.gridy++;
        centerPanel.add(descripcionLabel, gbc);

        // Panel para el barcode (campo de texto para escanear)
        barcodeField = new PrjTextBox();
        barcodeField.setPreferredSize(new Dimension(200, 30));

        // Botón Home
        btnHome = new PrjButton("Regresar al menú");
        
        // Panel para la parte sur con el campo de texto y el botón
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.setBackground(Styles.COLOR_FONT_LIGHT);
        southPanel.add(barcodeField);
        southPanel.add(btnHome);

        // Agregar paneles al contenedor principal
        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        // Acción del botón Home
        btnHome.addActionListener(e -> showMenuPanel());

        // Acción para el barcode (escaneo)
        barcodeField.addActionListener(e -> {
            String barcode = barcodeField.getText();
            if (!barcode.trim().isEmpty()) {
                try {
                    processBarcode(barcode);
                } catch (Exception e1) {
                    nameLabel.setText(" ... ");
                    priceLabel.setText("Vuelva a escanear nuevamente!");
                    autorLabel.setText("Ningún Autor");
                    descripcionLabel.setText(" ... ");
                }
                barcodeField.setText("");
            }
        });
        // Focus inicial en el campo de texto
        SwingUtilities.invokeLater(() -> barcodeField.requestFocusInWindow());
    }

    private void processBarcode(String barcode) throws Exception {
        String nombre = piezabl.getNombreBy(barcode);
        String precio = piezabl.getPrecioBy(barcode);
        String autor = piezabl.getAutorBy(barcode);
        String descripcion = piezabl.getDescripcionBy(barcode);

        nameLabel.setText("Nombre de la Pieza: " + nombre + "\n");
        priceLabel.setText("Precio Replica: " + precio + "\n");
        autorLabel.setText("Autor: " + autor + "\n");
        descripcionLabel.setText("Descripción: " + descripcion + "\n");
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