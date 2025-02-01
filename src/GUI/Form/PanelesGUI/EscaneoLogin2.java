package GUI.Form.PanelesGUI;

import javax.swing.*;
import java.awt.*;
import GUI.CustomerControl.PrjButton;
import BusinessLogic.Administrador2BL;

public class EscaneoLogin2 extends JPanel {
    private Administrador2BL administradorbl = new Administrador2BL();
    private MenuPanel menuPanel;
    private PrjButton btnHome;
    private PrjButton btnIngresar;
    private PrjButton btnIngresarPorCodigo;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;

    public EscaneoLogin2(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;
        setLayout(new BorderLayout());

        // Panel Norte
        JPanel panelNorte = new JPanel();
        JLabel lblBienvenido = new JLabel("Bienvenido Administrador", SwingConstants.CENTER);
        lblBienvenido.setFont(new Font("Arial", Font.BOLD, 18));
        panelNorte.add(lblBienvenido);
        add(panelNorte, BorderLayout.NORTH);

        // Panel Central
        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblUsuario = new JLabel("Usuario:");
        txtUsuario = new JTextField(15);
        JLabel lblContrasena = new JLabel("Contraseña:");
        txtContrasena = new JPasswordField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCentral.add(lblUsuario, gbc);
        gbc.gridx = 1;
        panelCentral.add(txtUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCentral.add(lblContrasena, gbc);
        gbc.gridx = 1;
        panelCentral.add(txtContrasena, gbc);

        add(panelCentral, BorderLayout.CENTER);

        // Panel Sur
        JPanel panelSur = new JPanel(new FlowLayout());
        btnIngresar = new PrjButton("Ingresar");
        btnIngresarPorCodigo = new PrjButton("Ingresar por Barcode");
        btnHome = new PrjButton("Home");

        panelSur.add(btnIngresar);
        panelSur.add(btnIngresarPorCodigo);
        panelSur.add(btnHome);

        add(panelSur, BorderLayout.SOUTH);
    }
}
