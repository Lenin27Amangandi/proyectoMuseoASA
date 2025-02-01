package GUI.Form.PanelesGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat.Style;

import GUI.Styles;
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
        panelNorte.setBackground(Styles.COLOR_FOREGROUND); // Color azul claro
        JLabel lblBienvenido = new JLabel("Bienvenido Administrador", SwingConstants.CENTER);
        lblBienvenido.setFont(new Font("Arial", Font.BOLD, 18));
        panelNorte.add(lblBienvenido);
        add(panelNorte, BorderLayout.NORTH);

        // Panel Central con imagen de fondo
        JPanel panelCentral = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(Styles.URL_FONDO_GENERAL); // Reemplaza con la ruta de la imagen
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

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
        panelSur.setBackground(Styles.COLOR_BACKGROUND2); // Color rosa claro
        btnIngresar = new PrjButton("Ingresar");
        btnIngresarPorCodigo = new PrjButton("Ingresar por Barcode");

        btnIngresarPorCodigo.addActionListener(e -> {
            String codigo = JOptionPane.showInputDialog(this, "Ingrese el código:", "Ingresar por Código", JOptionPane.QUESTION_MESSAGE);
            if (codigo != null && !codigo.trim().isEmpty()) {
                try {
                    processBarcode(codigo);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al procesar el código de barras: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No ingresó ningún código.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnIngresar.addActionListener(e -> processLogin());

        btnHome = new PrjButton("Home");
        btnHome.addActionListener(e -> showMenuPanel());
        panelSur.add(btnIngresar);
        panelSur.add(btnIngresarPorCodigo);
        panelSur.add(btnHome);
        add(panelSur, BorderLayout.SOUTH);

        txtContrasena.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    processLogin();
                }
            }
        });
    }

    private void processBarcode(String barcode) throws Exception {
        boolean adminTipo = administradorbl.tipoAdmin2(barcode);
        // if (adminTipo == true) {
        if (adminTipo == false) {
            showLoginPanel();
            System.out.println("Vamos a login panel de Admin");
        } else {
            showSupervisorTipoPanel(adminTipo);
            System.out.println("Vamos a login Panel tipo Supervisor");
        }
    }

    private void processLogin() {
        String usuario = txtUsuario.getText().trim();
        String contrasenia = new String(txtContrasena.getPassword()).trim(); // Convertir contraseña a String
        
        if (usuario.isEmpty() || contrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar usuario y contraseña.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        try {
            boolean adminTipo = administradorbl.tipoAdminByUsuarioYContrasenia(usuario, contrasenia);
            
            if (!adminTipo) {
                showLoginPanel();
                System.out.println("Vamos a login panel de Admin");
            } else {
                showSupervisorTipoPanel(adminTipo);
                System.out.println("Vamos a login Panel tipo Supervisor");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al validar credenciales: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); // Para depuración
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
