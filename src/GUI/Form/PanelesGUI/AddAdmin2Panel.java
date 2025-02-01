package GUI.Form.PanelesGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import GUI.Styles;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import BusinessLogic.Administrador2BL;
import BusinessLogic.PiezaDeArteBL;
import DataAcces.DTO.Administrador2DTO;
import DataAcces.DTO.PiezaDeArteDTO;
import GUI.Styles;
import GUI.CustomerControl.PrjButton;
import GUI.CustomerControl.PrjLabel;

public class AddAdmin2Panel extends JPanel {

    private JLabel messageLabel;
    private AdminTipoPanel addAdminTipoPnl;
    private Administrador2BL administrador2bl = new Administrador2BL();

    private PrjButton btnBack;
    public PrjTextBox barcodeField;
    public PrjTextBox usuarioField;
    public PrjTextBox contraseniaField;
    public PrjTextBox tipoAdminField;

    private PrjButton btnADD;
    private PrjButton btnDEL;
    private PrjButton btnMOD;

    private JTable administradorTable;
    private DefaultTableModel tableModel;

    public AddAdmin2Panel(AdminTipoPanel addAdminTipoPnl) {

        this.addAdminTipoPnl = addAdminTipoPnl;
        btnBack = new PrjButton("Volver");
        btnADD = new PrjButton("Nuevo");
        btnMOD = new PrjButton("Editar");
        btnDEL = new PrjButton("Eliminar");
        btnDEL.setForeground(Styles.COLOR_FONT);
        btnADD.setForeground(Styles.COLOR_FONT);
        btnMOD.setForeground(Styles.COLOR_FONT);
        messageLabel = new PrjLabel();

        barcodeField = new PrjTextBox();
        barcodeField.setPreferredSize(new Dimension(200, 30));
        barcodeField.setBackground(Styles.COLOR_FONT_LIGHT);

        usuarioField = new PrjTextBox();
        usuarioField.setPreferredSize(new Dimension(200, 30));
        usuarioField.setBackground(Styles.COLOR_FONT_LIGHT);

        contraseniaField = new PrjTextBox();
        contraseniaField.setPreferredSize(new Dimension(200, 30));
        contraseniaField.setBackground(Styles.COLOR_FONT_LIGHT);

        tipoAdminField = new PrjTextBox();
        tipoAdminField.setPreferredSize(new Dimension(300, 400));
        tipoAdminField.setBackground(Styles.COLOR_FONT_LIGHT);

        setLayout(new BorderLayout());

        btnBack.addActionListener(e -> showProductTipoPanel());
        btnADD.addActionListener(e -> {
            btnADD.setForeground(Styles.COLOR_FONT);
            btnMOD.setForeground(Styles.COLOR_FONT_BG);
            btnDEL.setForeground(Styles.COLOR_FONT_BG);
            try {
                addProduct();
            } catch (Exception ex) {
                messageLabel.setText("Ups... No se pudo agregar el producto");
                ex.printStackTrace();
            }
        });

        btnDEL.addActionListener(e -> {
            btnDEL.setForeground(Styles.COLOR_FONT);
            btnMOD.setForeground(Styles.COLOR_FONT_BG);
            btnADD.setForeground(Styles.COLOR_FONT_BG);
            try {
                deleteProduct();
            } catch (Exception ex) {
                messageLabel.setText("Ups... No se pudo eliminar el producto");
                ex.printStackTrace();
            }
        });

        btnMOD.addActionListener(e -> {
            btnMOD.setForeground(Styles.COLOR_FONT);
            btnDEL.setForeground(Styles.COLOR_FONT_BG);
            btnADD.setForeground(Styles.COLOR_FONT_BG);
            try {
                editAdmin();
            } catch (Exception ex) {
                messageLabel.setText("Ups... No se pudo editar el producto");
                ex.printStackTrace();
            }
        });

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // La celda no es editable
            }
        };

        tableModel.addColumn("Barcode");
        tableModel.addColumn("Usuario");
        tableModel.addColumn("Contrasenia");
        tableModel.addColumn("TipoAdmin");

        administradorTable = new JTable(tableModel);
        administradorTable.setPreferredScrollableViewportSize(new Dimension(500, 300));
        administradorTable.setSelectionForeground(Styles.COLOR_FOREGROUND);
        administradorTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int selectedRow = administradorTable.getSelectedRow();
                    if (selectedRow != -1) {
                        String barcode = (String) tableModel.getValueAt(selectedRow, 0);
                        String usuario = (String) tableModel.getValueAt(selectedRow, 1);
                        String contrasaenia = (String) tableModel.getValueAt(selectedRow, 2);
                        int tipoAdmin = (int) tableModel.getValueAt(selectedRow, 3);

                        barcodeField.setText(barcode);
                        usuarioField.setText(usuario);
                        contraseniaField.setText(contrasaenia);
                        tipoAdminField.setText(String.valueOf(tipoAdmin));
                    }
                }
            }
        });

        administradorTable.setFillsViewportHeight(true);
        // Agregar la tabla a un JScrollPane
        JScrollPane tableScrollPane = new JScrollPane(administradorTable);

        // Agregar el JScrollPane al panel
        add(tableScrollPane, BorderLayout.WEST); // Ponerlo al este

        // Cargar los datos de la base de datos en la tabla
        loadAdministradoresFromDatabase();

        JPanel northPanel = new JPanel();
        northPanel = paintPanel(northPanel);
        northPanel.setLayout(new FlowLayout());
        // ...

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel = paintPanel(centerPanel);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Código de barras
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("Código de Barras:"), gbc);
        gbc.gridy = 1;
        centerPanel.add(barcodeField, gbc);

        // Nombre
        gbc.gridy = 2;
        centerPanel.add(new JLabel("Usuario:"), gbc);
        gbc.gridy = 3;
        centerPanel.add(usuarioField, gbc);

        // Contrasenia
        gbc.gridy = 4;
        centerPanel.add(new JLabel("Contrasenia:"), gbc);
        gbc.gridy = 5;
        centerPanel.add(contraseniaField, gbc);

        // tipoAdmin
        gbc.gridy = 6;
        centerPanel.add(new JLabel("TipoAdmin:"), gbc);
        gbc.gridy = 7;
        centerPanel.add(tipoAdminField, gbc);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());

        // JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Agregar los botones al panel sur
        southPanel.add(btnADD);
        southPanel.add(btnMOD);
        southPanel.add(btnDEL);
        southPanel.add(btnBack);
        southPanel.add(messageLabel);

        add(southPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        southPanel.add(btnBack);
        southPanel.add(messageLabel);
        add(southPanel, BorderLayout.SOUTH);

        SwingUtilities.invokeLater(() -> barcodeField.requestFocusInWindow());
    

    }

    LogInPanel addLoginPanel;

    public AddAdmin2Panel(LogInPanel addLoginPanel) {

        this.addLoginPanel = addLoginPanel;
        btnBack = new PrjButton("Volver");
        btnADD = new PrjButton("Nuevo");
        btnMOD = new PrjButton("Editar");
        btnDEL = new PrjButton("Eliminar");
        btnDEL.setForeground(Styles.COLOR_FONT);
        btnADD.setForeground(Styles.COLOR_FONT);
        btnMOD.setForeground(Styles.COLOR_FONT);
        messageLabel = new PrjLabel();

        barcodeField = new PrjTextBox();
        barcodeField.setPreferredSize(new Dimension(200, 30));
        barcodeField.setBackground(Styles.COLOR_FONT_LIGHT);

        usuarioField = new PrjTextBox();
        usuarioField.setPreferredSize(new Dimension(200, 30));
        usuarioField.setBackground(Styles.COLOR_FONT_LIGHT);

        contraseniaField = new PrjTextBox();
        contraseniaField.setPreferredSize(new Dimension(200, 30));
        contraseniaField.setBackground(Styles.COLOR_FONT_LIGHT);

        tipoAdminField = new PrjTextBox();
        tipoAdminField.setPreferredSize(new Dimension(300, 400));
        tipoAdminField.setBackground(Styles.COLOR_FONT_LIGHT);

        setLayout(new BorderLayout());

        btnBack.addActionListener(e -> showLoginPanel());
        btnADD.addActionListener(e -> {
            btnADD.setForeground(Styles.COLOR_FONT);
            btnMOD.setForeground(Styles.COLOR_FONT_BG);
            btnDEL.setForeground(Styles.COLOR_FONT_BG);
            try {
                addProduct();
            } catch (Exception ex) {
                messageLabel.setText("Ups... No se pudo agregar el producto");
                ex.printStackTrace();
            }
        });

        btnDEL.addActionListener(e -> {
            btnDEL.setForeground(Styles.COLOR_FONT);
            btnMOD.setForeground(Styles.COLOR_FONT_BG);
            btnADD.setForeground(Styles.COLOR_FONT_BG);
            try {
                deleteProduct();
            } catch (Exception ex) {
                messageLabel.setText("Ups... No se pudo eliminar el producto");
                ex.printStackTrace();
            }
        });

        btnMOD.addActionListener(e -> {
            btnMOD.setForeground(Styles.COLOR_FONT);
            btnDEL.setForeground(Styles.COLOR_FONT_BG);
            btnADD.setForeground(Styles.COLOR_FONT_BG);
            try {
                editAdmin();
            } catch (Exception ex) {
                messageLabel.setText("Ups... No se pudo editar el producto");
                ex.printStackTrace();
            }
        });

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // La celda no es editable
            }
        };

        tableModel.addColumn("Barcode");
        tableModel.addColumn("Usuario");
        tableModel.addColumn("Contrasenia");
        tableModel.addColumn("TipoAdmin");

        administradorTable = new JTable(tableModel);
        administradorTable.setPreferredScrollableViewportSize(new Dimension(500, 300));
        administradorTable.setSelectionForeground(Styles.COLOR_FOREGROUND);
        administradorTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int selectedRow = administradorTable.getSelectedRow();
                    if (selectedRow != -1) {
                        String barcode = (String) tableModel.getValueAt(selectedRow, 0);
                        String usuario = (String) tableModel.getValueAt(selectedRow, 1);
                        String contrasaenia = (String) tableModel.getValueAt(selectedRow, 2);
                        int tipoAdmin = (int) tableModel.getValueAt(selectedRow, 3);

                        barcodeField.setText(barcode);
                        usuarioField.setText(usuario);
                        contraseniaField.setText(contrasaenia);
                        tipoAdminField.setText(String.valueOf(tipoAdmin));
                    }
                }
            }
        });

        administradorTable.setFillsViewportHeight(true);
        // Agregar la tabla a un JScrollPane
        JScrollPane tableScrollPane = new JScrollPane(administradorTable);

        // Agregar el JScrollPane al panel
        add(tableScrollPane, BorderLayout.WEST); // Ponerlo al este

        // Cargar los datos de la base de datos en la tabla
        loadAdministradoresFromDatabase();

        JPanel northPanel = new JPanel();
        northPanel = paintPanel(northPanel);
        northPanel.setLayout(new FlowLayout());
        // ...

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel = paintPanel(centerPanel);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Código de barras
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("Código de Barras:"), gbc);
        gbc.gridy = 1;
        centerPanel.add(barcodeField, gbc);

        // Nombre
        gbc.gridy = 2;
        centerPanel.add(new JLabel("Usuario:"), gbc);
        gbc.gridy = 3;
        centerPanel.add(usuarioField, gbc);

        // Contrasenia
        gbc.gridy = 4;
        centerPanel.add(new JLabel("Contrasenia:"), gbc);
        gbc.gridy = 5;
        centerPanel.add(contraseniaField, gbc);

        // tipoAdmin
        gbc.gridy = 6;
        centerPanel.add(new JLabel("TipoAdmin:"), gbc);
        gbc.gridy = 7;
        centerPanel.add(tipoAdminField, gbc);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());

        // JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Agregar los botones al panel sur
        southPanel.add(btnADD);
        southPanel.add(btnMOD);
        southPanel.add(btnDEL);
        southPanel.add(btnBack);
        southPanel.add(messageLabel);

        add(southPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        southPanel.add(btnBack);
        southPanel.add(messageLabel);
        add(southPanel, BorderLayout.SOUTH);

        SwingUtilities.invokeLater(() -> barcodeField.requestFocusInWindow());
    

    }

    private void deleteProduct() {
        int selectedRow = administradorTable.getSelectedRow();
        if (selectedRow != -1) {
            String barcode = (String) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar este Administrador?",
                    "Eliminar producto", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    boolean exito= administrador2bl.delete(barcode);
                    if (exito) {
                        messageLabel.setText("Administrador eliminado con éxito");
                        tableModel.removeRow(selectedRow);
                    } else {
                        messageLabel.setText("No se pudo eliminar el Administrador");
                    }
                } catch (Exception e) {
                    messageLabel.setText("Ups... No se pudo eliminar el Administrador");
                    e.printStackTrace();
                }
            }
        } else {
            messageLabel.setText("Por favor, selecciona un producto para eliminar");
        }
    }


    private void loadAdministradoresFromDatabase() {
        try {
            // Obtener los productos de la base de datos
            List<Administrador2DTO> administrador = administrador2bl.getAll();
            // Agregar los productos a la tabla
            for (Administrador2DTO adminDTO : administrador) {
                tableModel.addRow(new Object[] {
                        adminDTO.getCodigo(),
                        adminDTO.getUsuario(),
                        adminDTO.getContrasenia(),
                        adminDTO.getTipo(),
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateAdminTable() {
        tableModel.setRowCount(0); // Limpiar la tabla
        loadAdministradoresFromDatabase(); // Recargar los datos de la base de datos
    }

    private void editAdmin() {
        int selectedRow = administradorTable.getSelectedRow();
        if (selectedRow != -1) {
            String barcode = (String) tableModel.getValueAt(selectedRow, 0);
            String usuario = usuarioField.getText();
            String contrasenia = contraseniaField.getText();
            int tipoAdmin = Integer.parseInt(tipoAdminField.getText());

            try {
                boolean exito = administrador2bl.update(new Administrador2DTO(barcode, usuario, contrasenia, tipoAdmin));
                if (exito) {
                    tableModel.setValueAt(usuario, selectedRow, 1);
                    tableModel.setValueAt(contrasenia, selectedRow, 2);
                    tableModel.setValueAt(tipoAdmin, selectedRow, 3);
                    messageLabel.setText("Producto editado con éxito en la base de datos");
                }
            } catch (Exception e) {
                messageLabel.setText("Ups... No se pudo editar el producto");
                e.printStackTrace();
            }
        } else {
            messageLabel.setText("Por favor, selecciona un producto para editar");
        }
    }

    private void addProduct() throws Exception {
        String barcode = barcodeField.getText();
        String usuario= usuarioField.getText();
        String contrasenia = contraseniaField.getText();
        int tipoAdmin= Integer.parseInt(tipoAdminField.getText());

        try {
            boolean exito = administrador2bl.add(new Administrador2DTO(barcode, usuario, contrasenia, tipoAdmin));
            // piezaDeArteBL.add(new PiezaDeArteDTO(barcode, nombre, autor, descripcion, precio, categoria, seccion));
            if (exito) {
                messageLabel.setText("Producto agregado con éxito en la base de datos");
            }
        } catch (Exception e) {
            messageLabel.setText("Ups... No se pudo agregar el producto");
            e.printStackTrace();
        }
        tableModel.addRow(new Object[] {
                barcode,
                usuario,
                contrasenia,
                tipoAdmin
        });
    }




    private JPanel paintPanel(JPanel panel) {
        panel.setBackground(Styles.COLOR_BACKGROUND);
        return panel;
    }

    private void showLoginPanel() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.setContentPane(addLoginPanel);
            frame.revalidate();
            frame.repaint();
        }
    }




    private void showProductTipoPanel() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.setContentPane(addAdminTipoPnl);
            frame.revalidate();
            frame.repaint();
        }
    }

}
