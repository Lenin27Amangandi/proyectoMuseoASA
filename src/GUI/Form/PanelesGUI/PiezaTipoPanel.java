package GUI.Form.PanelesGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import GUI.Styles;
import GUI.CustomerControl.PrjButton;
import GUI.CustomerControl.PrjLabel;

public class PiezaTipoPanel extends JPanel {
    private LogInPanel logInPanel;
    private PrjButton btnBack;
    private PrjLabel lblMessage;
    private PrjButton btnEditRegisters;

    public PiezaTipoPanel(LogInPanel logInPanel) {
        this.logInPanel = logInPanel;

        btnEditRegisters = new PrjButton("Editar Registros");
        btnEditRegisters.setForeground(Styles.COLOR_FONT);
        btnBack = new PrjButton("Volver");
        lblMessage = new PrjLabel();

        setLayout(new BorderLayout());

        btnBack.addActionListener(e -> showLoginPanel());
        btnEditRegisters.addActionListener(e -> showAddProductPanel());

        JPanel northPanel = new JPanel();
        northPanel = paintPanel(northPanel);
        northPanel.add(lblMessage);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel = paintPanel(centerPanel);
        centerPanel.add(btnEditRegisters);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.setBackground(Styles.COLOR_FONT_LIGHT);
        southPanel.add(btnBack);

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

    }

    private JPanel paintPanel(JPanel panel) {
        panel.setBackground(Styles.COLOR_BACKGROUND);
        return panel;
    }

    private void showAddProductPanel() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
        frame.setContentPane(new AddPiezaPanel(this));
        frame.revalidate();
        frame.repaint();
        }
        System.out.println("Vamos al otro registro");
    }

    private void showLoginPanel() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.setContentPane(logInPanel);
            frame.revalidate();
            frame.repaint();
        }
    }

}
