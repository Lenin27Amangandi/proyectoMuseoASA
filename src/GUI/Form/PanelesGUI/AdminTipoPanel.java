package GUI.Form.PanelesGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import GUI.Styles;
import GUI.CustomerControl.PrjButton;
import GUI.CustomerControl.PrjLabel;

public class AdminTipoPanel extends JPanel {
    private LogInPanel logInPanel;
    private PrjButton btnBack;
    private PrjButton btnSuperAdmin;
    private PrjButton btnAdmin;
    private PrjLabel lblMessage;

    public AdminTipoPanel(LogInPanel logInPanel) {
        this.logInPanel = logInPanel;

        btnSuperAdmin = new PrjButton("Supervisor");
        btnSuperAdmin.setForeground(Styles.COLOR_FOREGROUND);
        btnAdmin = new PrjButton("Administrador");
        btnAdmin.setForeground(Styles.COLOR_FOREGROUND);
        btnBack = new PrjButton("Volver");
        lblMessage = new PrjLabel();
        lblMessage = new PrjLabel("¿Que tipo de administrador desea configurar?");

        setLayout(new BorderLayout());

        btnBack.addActionListener(e -> showLoginPanel());
        btnSuperAdmin.addActionListener(e -> showAddSupervisorPanel());
        btnAdmin.addActionListener(e -> showAddAdminPanel());


        JPanel northPanel = new JPanel();
        northPanel = paintPanel(northPanel);
        northPanel.add(lblMessage);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel = paintPanel(centerPanel);
        centerPanel.add(btnSuperAdmin);
        centerPanel.add(btnAdmin);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel = paintPanel(southPanel);
        southPanel.setBackground(Styles.COLOR_FONT_LIGHT);
        southPanel.add(btnBack);

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

    }


    private void showAddSupervisorPanel() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.setContentPane(new AddSupervisorPanel(this));
            frame.revalidate();
            frame.repaint();
        }
    }

    private void showAddAdminPanel() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.setContentPane(new AddAdminPanel(this));
            frame.revalidate();
            frame.repaint();
        }
    }



    private JPanel paintPanel(JPanel panel) {
        panel.setBackground(Styles.COLOR_BACKGROUND);
        return panel;
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
