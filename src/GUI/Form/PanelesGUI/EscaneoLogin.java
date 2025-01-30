package GUI.Form.PanelesGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Menu;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import BusinessLogic.AdministradorBL;
import GUI.Styles;
import GUI.CustomerControl.PrjButton;

public class EscaneoLogin extends JPanel {

    private JLabel messageLabel;
    public PrjTextBox barcodeField;
    private AdministradorBL administradorbl = new AdministradorBL();
    private MenuPanel menuPanel;
    private PrjButton btnHome;

    public EscaneoLogin(MenuPanel menuPanel) {

        this.menuPanel = menuPanel;
        btnHome = new PrjButton("Volver");
        barcodeField = new PrjTextBox();
        barcodeField.setBackground(Styles.COLOR_FONT_LIGHT);
        barcodeField.setForeground(Styles.COLOR_FONT);
        barcodeField.setCaretColor(Color.BLACK);
        barcodeField.setPreferredSize(new Dimension(200, 30));
        messageLabel = new JLabel("Escanee identificacion...", SwingConstants.CENTER);
        messageLabel.setForeground(Styles.COLOR_FOREGROUND);
        setLayout(new BorderLayout());
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        northPanel.add(messageLabel);
        northPanel.setBackground(Styles.COLOR_BACKGROUND);
        add(northPanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(barcodeField);
        centerPanel.setBackground(Styles.COLOR_FOREGROUND);
        add(centerPanel, BorderLayout.CENTER);
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.add(btnHome);
        southPanel.setBackground(Styles.COLOR_FONT_LIGHT);
        add(southPanel, BorderLayout.SOUTH);
        btnHome.addActionListener(e -> showMenuPanel());
        barcodeField.addActionListener(e -> {
            String barcode = barcodeField.getText();
            if (!barcode.trim().isEmpty()) {
                try {
                    processBarcode(barcode);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "Error al procesar el código de barras: " + e1.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                barcodeField.setText("");
            }
        });
        SwingUtilities.invokeLater(() -> barcodeField.requestFocusInWindow());
    }

    private void processBarcode(String barcode) throws Exception {
        boolean adminTipo = administradorbl.tipoAdmin(barcode);
        // if (adminTipo == true) {
        if (adminTipo == false) {
            showLoginPanel();
            System.out.println("Vamos a login panel de Admin");
        } else {
            showSupervisorTipoPanel(adminTipo);
            System.out.println("Vamos a login Panel tipo Superviso");
        }
    }

    private void showSupervisorTipoPanel(boolean adminTipo) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.setContentPane(new LogInPanel(menuPanel, adminTipo));
            frame.revalidate();
            frame.repaint();
        }
    }

    private void showLoginPanel() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.setContentPane(new LogInPanel(menuPanel));
            frame.revalidate();
            frame.repaint();
        }
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
