package GUI.Form.PanelesGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import BusinessLogic.AdministradorBL;
import DataAcces.DTO.AdministradorDTO;
import GUI.Styles;
import GUI.CustomerControl.PrjButton;
import GUI.CustomerControl.PrjLabel;

public class AddAdminPanel extends JPanel {
    private JLabel messageLabel;
    private AdminTipoPanel addAdminTipo;
    private AdministradorBL administradorbl = new AdministradorBL();
    private PrjButton btnBack;
    public PrjTextBox barcodeField;
    private PrjButton btnADD;
    private PrjButton btnDEL;
    private boolean mode = true;

    public AddAdminPanel(AdminTipoPanel addAdminTipo) {
        this.addAdminTipo = addAdminTipo;
        btnBack = new PrjButton("Volver");
        btnADD = new PrjButton("Agregar");
        btnADD.setForeground(Styles.COLOR_FOREGROUND);
        btnDEL = new PrjButton("Eliminar");
        btnDEL.setForeground(Styles.COLOR_FOREGROUND);
        messageLabel = new PrjLabel();
        barcodeField = new PrjTextBox();
        barcodeField.setPreferredSize(new Dimension(200, 30));

        setLayout(new BorderLayout());

        btnBack.addActionListener(e -> showAdminTipoPanel());

        btnADD.addActionListener(e -> {
            btnADD.setForeground(Styles.COLOR_FOREGROUND);
            btnDEL.setForeground(Styles.COLOR_FOREGROUND_PRESSED);
            this.mode = true;
        });

        btnDEL.addActionListener(e -> {
            btnDEL.setForeground(Styles.COLOR_FOREGROUND);
            btnADD.setForeground(Styles.COLOR_FOREGROUND_PRESSED);
            this.mode = false;
        });

        JPanel northPanel = new JPanel();
        northPanel = paintPanel(northPanel);
        northPanel.setLayout(new FlowLayout());
        JPanel centerPanel = new JPanel();
        centerPanel = paintPanel(centerPanel);
        centerPanel.setLayout(new FlowLayout());
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Styles.COLOR_FONT_LIGHT);
        southPanel.setLayout(new FlowLayout());

        northPanel.add(btnADD);
        northPanel.add(btnDEL);
        add(northPanel, BorderLayout.NORTH);

        centerPanel.add(barcodeField);
        add(centerPanel, BorderLayout.CENTER);

        southPanel.add(btnBack);
        southPanel.add(messageLabel);
        add(southPanel, BorderLayout.SOUTH);

        barcodeField.addActionListener(e -> {
            getBarcodeDeBarcodeField();
        });

        // Solicita el foco en el campo de texto cuando el panel se muestra
        SwingUtilities.invokeLater(() -> barcodeField.requestFocusInWindow());
    }

    private void getBarcodeDeBarcodeField() {
        String barcode = barcodeField.getText();
        if (!barcode.trim().isEmpty()) {
            try {
                processBarcode(barcode);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, "Error al procesar el código de barras: " + "   ",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            barcodeField.setText(""); // Limpiar el campo de texto después de procesar
        }
    }

    private void processBarcode(String barcode) throws Exception {
        if (this.mode) {
            addAdmin(barcode);
        } else {
            delAdmin(barcode);
        }
    }

    private JPanel paintPanel(JPanel panel) {
        panel.setBackground(Styles.COLOR_BACKGROUND);
        return panel;
    }

    private void delAdmin(String barcode) throws Exception {

        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar este Admin?",
        "Eliminar Admin", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // AdministradorDTO adminDelate = new AdministradorDTO(barcode);
                boolean exito = administradorbl.delete(barcode);
                if (exito) {
                    messageLabel.setText("Administrador Eliminado con éxito en la base de datos");
                } else {
                    messageLabel.setText("No se pudo agregar el administrador");
                }
            } catch (Exception e) {
                messageLabel.setText("Ups... No se pudo eliminar el administrador: " + e.getMessage());
                e.printStackTrace();
            }
            
        } else {
            messageLabel.setText("Escane De nuevo el codigo");
            
        }


        // if (tipoAdmin == false && this.mode == false) {
        //     messageLabel.setText("El codigo ingresado no pertenece a un administrador");
        // } else {
        //     try {
        //         boolean exito = administradorbl.delete(barcode);
        //         if (exito) {
        //             messageLabel.setText("Administrador eliminado con éxito de la base de datos");
        //         }
        //     } catch (Exception e) {
        //         messageLabel.setText("Ups... No se pudo eliminar el administrador");
        //         e.printStackTrace();
        //     }
        // }
    }

    private void addAdmin(String barcode) throws Exception {
        try {
            // Crear un nuevo AdministradorDTO
            AdministradorDTO admin = new AdministradorDTO(barcode, 1);
            // Intentar agregar el administrador
            boolean exito = administradorbl.add(admin);
            // Verificar el resultado de la operación
            if (exito) {
                messageLabel.setText("Administrador agregado con éxito en la base de datos");
            } else {
                messageLabel.setText("No se pudo agregar el administrador");
            }
        } catch (Exception e) {
            // Mostrar mensaje de error más detallado
            messageLabel.setText("Ups... No se pudo agregar el administrador: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAdminTipoPanel() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.setContentPane(addAdminTipo);
            frame.revalidate();
            frame.repaint();
        }
    }

}
