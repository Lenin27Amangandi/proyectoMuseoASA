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

import BusinessLogic.PiezaDeArteBL;
import DataAcces.DTO.PiezaDeArteDTO;
import GUI.CustomerControl.PrjButton;
import GUI.CustomerControl.PrjLabel;

public class AddPiezaPanel extends JPanel {

    private JLabel messageLabel;
    private PiezaTipoPanel addPiezaTipo;
    private EscaneoLogin2 addEscaneoLogin2;
    private PiezaDeArteBL piezaDeArteBL = new PiezaDeArteBL();
    private PrjButton btnBack;

    public PrjTextBox barcodeField;
    public PrjTextBox nombreField;
    public PrjTextBox precioField;
    public PrjTextBox seccionField;
    public PrjTextBox categoriaField;

    public PrjTextBox autorField;
    public PrjTextBox descripcionField;

    private JTable productTable;
    private DefaultTableModel tableModel;

    private PrjButton btnADD;
    private PrjButton btnDEL;
    private PrjButton btnMOD;

    public AddPiezaPanel(PiezaTipoPanel addPiezaTipo) {

        this.addPiezaTipo = addPiezaTipo;
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

        nombreField = new PrjTextBox();
        nombreField.setPreferredSize(new Dimension(200, 30));
        nombreField.setBackground(Styles.COLOR_FONT_LIGHT);

        autorField = new PrjTextBox();
        autorField.setPreferredSize(new Dimension(200, 30));
        autorField.setBackground(Styles.COLOR_FONT_LIGHT);

        descripcionField = new PrjTextBox();
        descripcionField.setPreferredSize(new Dimension(300, 400));
        descripcionField.setBackground(Styles.COLOR_FONT_LIGHT);

        precioField = new PrjTextBox();
        precioField.setPreferredSize(new Dimension(200, 30));
        precioField.setBackground(Styles.COLOR_FONT_LIGHT);

        seccionField = new PrjTextBox();
        seccionField.setPreferredSize(new Dimension(200, 30));
        seccionField.setBackground(Styles.COLOR_FONT_LIGHT);

        categoriaField = new PrjTextBox();
        categoriaField.setPreferredSize(new Dimension(200, 30));
        categoriaField.setBackground(Styles.COLOR_FONT_LIGHT);

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
                editProduct();
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
        tableModel.addColumn("Nombre de la Pintura");
        tableModel.addColumn("Autor");
        tableModel.addColumn("Descripcion");
        tableModel.addColumn("PrecioReplica");
        tableModel.addColumn("Sección del producto");
        tableModel.addColumn("Categoría del producto");

        productTable = new JTable(tableModel);
        productTable.setPreferredScrollableViewportSize(new Dimension(500, 300));
        productTable.setSelectionForeground(Styles.COLOR_FOREGROUND);
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int selectedRow = productTable.getSelectedRow();
                    if (selectedRow != -1) {
                        String barcode = (String) tableModel.getValueAt(selectedRow, 0);
                        String nombre = (String) tableModel.getValueAt(selectedRow, 1);
                        String autor = (String) tableModel.getValueAt(selectedRow, 2);
                        String descripcion = (String) tableModel.getValueAt(selectedRow, 3);
                        double precio = (double) tableModel.getValueAt(selectedRow, 4);
                        int seccion = (int) tableModel.getValueAt(selectedRow, 5);
                        int categoria = (int) tableModel.getValueAt(selectedRow, 6);

                        barcodeField.setText(barcode);
                        nombreField.setText(nombre);
                        autorField.setText(autor);
                        descripcionField.setText(descripcion);
                        precioField.setText(String.valueOf(precio));
                        seccionField.setText(String.valueOf(seccion));
                        categoriaField.setText(String.valueOf(categoria));
                    }
                }
            }
        });
        productTable.setFillsViewportHeight(true);
        // Agregar la tabla a un JScrollPane
        JScrollPane tableScrollPane = new JScrollPane(productTable);

        // Agregar el JScrollPane al panel
        add(tableScrollPane, BorderLayout.WEST); // Ponerlo al este

        // Cargar los datos de la base de datos en la tabla
        loadProductsFromDatabase();

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
        centerPanel.add(new JLabel("Nombre del Producto:"), gbc);
        gbc.gridy = 3;
        centerPanel.add(nombreField, gbc);

        // Precio
        gbc.gridy = 4;
        centerPanel.add(new JLabel("Precio del Producto:"), gbc);
        gbc.gridy = 5;
        centerPanel.add(precioField, gbc);

        // Sección
        gbc.gridy = 6;
        centerPanel.add(new JLabel("Sección:"), gbc);
        gbc.gridy = 7;
        centerPanel.add(seccionField, gbc);

        // Categoría
        gbc.gridy = 8;
        centerPanel.add(new JLabel("Categoría del Producto:"), gbc);
        gbc.gridy = 9;
        centerPanel.add(categoriaField, gbc);

        // Autor
        gbc.gridy = 10;
        centerPanel.add(new JLabel("Autor:"), gbc);
        gbc.gridy = 11;
        centerPanel.add(autorField, gbc);

        // Descripción
        gbc.gridy = 12;
        centerPanel.add(new JLabel("Descripción:"), gbc);
        gbc.gridy = 13;
        centerPanel.add(descripcionField, gbc);

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

        // Agregar el panel sur al layout
        add(southPanel, BorderLayout.SOUTH);

        // northPanel.add(btnADD);
        // northPanel.add(btnMOD);
        // northPanel.add(btnDEL);
        // add(northPanel, BorderLayout.NORTH);

        add(centerPanel, BorderLayout.CENTER);

        southPanel.add(btnBack);
        southPanel.add(messageLabel);
        add(southPanel, BorderLayout.SOUTH);

        SwingUtilities.invokeLater(() -> barcodeField.requestFocusInWindow());
    }

    private EscaneoLogin2 addPieza;
    public AddPiezaPanel(EscaneoLogin2 addPieza) {

        this.addPieza = addPieza;
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

        nombreField = new PrjTextBox();
        nombreField.setPreferredSize(new Dimension(200, 30));
        nombreField.setBackground(Styles.COLOR_FONT_LIGHT);

        autorField = new PrjTextBox();
        autorField.setPreferredSize(new Dimension(200, 30));
        autorField.setBackground(Styles.COLOR_FONT_LIGHT);

        descripcionField = new PrjTextBox();
        descripcionField.setPreferredSize(new Dimension(300, 400));
        descripcionField.setBackground(Styles.COLOR_FONT_LIGHT);

        precioField = new PrjTextBox();
        precioField.setPreferredSize(new Dimension(200, 30));
        precioField.setBackground(Styles.COLOR_FONT_LIGHT);

        seccionField = new PrjTextBox();
        seccionField.setPreferredSize(new Dimension(200, 30));
        seccionField.setBackground(Styles.COLOR_FONT_LIGHT);

        categoriaField = new PrjTextBox();
        categoriaField.setPreferredSize(new Dimension(200, 30));
        categoriaField.setBackground(Styles.COLOR_FONT_LIGHT);

        setLayout(new BorderLayout());

        btnBack.addActionListener(e -> showEscaneoLogin2());
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
                editProduct();
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
        tableModel.addColumn("Nombre de la Pintura");
        tableModel.addColumn("Autor");
        tableModel.addColumn("Descripcion");
        tableModel.addColumn("PrecioReplica");
        tableModel.addColumn("Sección del producto");
        tableModel.addColumn("Categoría del producto");

        productTable = new JTable(tableModel);
        productTable.setPreferredScrollableViewportSize(new Dimension(500, 300));
        productTable.setSelectionForeground(Styles.COLOR_FOREGROUND);
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int selectedRow = productTable.getSelectedRow();
                    if (selectedRow != -1) {
                        String barcode = (String) tableModel.getValueAt(selectedRow, 0);
                        String nombre = (String) tableModel.getValueAt(selectedRow, 1);
                        String autor = (String) tableModel.getValueAt(selectedRow, 2);
                        String descripcion = (String) tableModel.getValueAt(selectedRow, 3);
                        double precio = (double) tableModel.getValueAt(selectedRow, 4);
                        int seccion = (int) tableModel.getValueAt(selectedRow, 5);
                        int categoria = (int) tableModel.getValueAt(selectedRow, 6);

                        barcodeField.setText(barcode);
                        nombreField.setText(nombre);
                        autorField.setText(autor);
                        descripcionField.setText(descripcion);
                        precioField.setText(String.valueOf(precio));
                        seccionField.setText(String.valueOf(seccion));
                        categoriaField.setText(String.valueOf(categoria));
                    }
                }
            }
        });
        productTable.setFillsViewportHeight(true);
        // Agregar la tabla a un JScrollPane
        JScrollPane tableScrollPane = new JScrollPane(productTable);

        // Agregar el JScrollPane al panel
        add(tableScrollPane, BorderLayout.WEST); // Ponerlo al este

        // Cargar los datos de la base de datos en la tabla
        loadProductsFromDatabase();

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
        centerPanel.add(new JLabel("Nombre del Producto:"), gbc);
        gbc.gridy = 3;
        centerPanel.add(nombreField, gbc);

        // Precio
        gbc.gridy = 4;
        centerPanel.add(new JLabel("Precio del Producto:"), gbc);
        gbc.gridy = 5;
        centerPanel.add(precioField, gbc);

        // Sección
        gbc.gridy = 6;
        centerPanel.add(new JLabel("Sección:"), gbc);
        gbc.gridy = 7;
        centerPanel.add(seccionField, gbc);

        // Categoría
        gbc.gridy = 8;
        centerPanel.add(new JLabel("Categoría del Producto:"), gbc);
        gbc.gridy = 9;
        centerPanel.add(categoriaField, gbc);

        // Autor
        gbc.gridy = 10;
        centerPanel.add(new JLabel("Autor:"), gbc);
        gbc.gridy = 11;
        centerPanel.add(autorField, gbc);

        // Descripción
        gbc.gridy = 12;
        centerPanel.add(new JLabel("Descripción:"), gbc);
        gbc.gridy = 13;
        centerPanel.add(descripcionField, gbc);

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

        // Agregar el panel sur al layout
        add(southPanel, BorderLayout.SOUTH);

        // northPanel.add(btnADD);
        // northPanel.add(btnMOD);
        // northPanel.add(btnDEL);
        // add(northPanel, BorderLayout.NORTH);

        add(centerPanel, BorderLayout.CENTER);

        southPanel.add(btnBack);
        southPanel.add(messageLabel);
        add(southPanel, BorderLayout.SOUTH);

        SwingUtilities.invokeLater(() -> barcodeField.requestFocusInWindow());
    }

    private void deleteProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            String barcode = (String) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar este producto?",
                    "Eliminar producto", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    boolean exito = piezaDeArteBL.delete(barcode);
                    if (exito) {
                        messageLabel.setText("Producto eliminado con éxito");
                        tableModel.removeRow(selectedRow);
                    } else {
                        messageLabel.setText("No se pudo eliminar el producto");
                    }
                } catch (Exception e) {
                    messageLabel.setText("Ups... No se pudo eliminar el producto");
                    e.printStackTrace();
                }
            }
        } else {
            messageLabel.setText("Por favor, selecciona un producto para eliminar");
        }
    }

    private void loadProductsFromDatabase() {
        try {
            // Obtener los productos de la base de datos
            List<PiezaDeArteDTO> productos = piezaDeArteBL.getAll();
            // Agregar los productos a la tabla
            for (PiezaDeArteDTO producto : productos) {
                tableModel.addRow(new Object[] {
                        producto.getBarCode(),
                        producto.getNombre(),
                        producto.getAutor(),
                        producto.getDescripcion(),
                        producto.getPrecioReplica(),
                        producto.getIdSeccion(),
                        producto.getIdCategoria()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateProductTable() {
        tableModel.setRowCount(0); // Limpiar la tabla
        loadProductsFromDatabase(); // Recargar los datos de la base de datos
    }

    private void editProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            String barcode = (String) tableModel.getValueAt(selectedRow, 0);
            String nombre = nombreField.getText();
            String autor = autorField.getText();
            String descripcion = descripcionField.getText();
            double precio = Double.parseDouble(precioField.getText());
            int seccion = Integer.parseInt(seccionField.getText());
            int categoria = Integer.parseInt(categoriaField.getText());

            try {
                boolean exito = piezaDeArteBL.update(new PiezaDeArteDTO(barcode, nombre, autor, descripcion, precio, seccion, categoria));
                if (exito) {
                    tableModel.setValueAt(nombre, selectedRow, 1);
                    tableModel.setValueAt(autor, selectedRow, 2);
                    tableModel.setValueAt(descripcion, selectedRow, 3);
                    tableModel.setValueAt(precio, selectedRow, 4);
                    tableModel.setValueAt(seccion, selectedRow, 5);
                    tableModel.setValueAt(categoria, selectedRow, 6);
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
        String nombre = nombreField.getText();
        String barcode = barcodeField.getText();
        String autor = autorField.getText();
        String descripcion = descripcionField.getText();
        double precio = Double.parseDouble(precioField.getText());
        int seccion = Integer.parseInt(seccionField.getText());
        int categoria = Integer.parseInt(categoriaField.getText());

        try {
            boolean exito = piezaDeArteBL
                    .add(new PiezaDeArteDTO(barcode, nombre, autor, descripcion, precio, categoria, seccion));
            if (exito) {
                messageLabel.setText("Producto agregado con éxito en la base de datos");
            }
        } catch (Exception e) {
            messageLabel.setText("Ups... No se pudo agregar el producto");
            e.printStackTrace();
        }
        tableModel.addRow(new Object[] {
                barcode,
                nombre,
                autor,
                descripcion,
                precio,
                seccion,
                categoria
        });
    }

    private JPanel paintPanel(JPanel panel) {
        panel.setBackground(Styles.COLOR_BACKGROUND);
        return panel;
    }

    private void showProductTipoPanel() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.setContentPane(addPiezaTipo);
            frame.revalidate();
            frame.repaint();
        }
    }


    private void showEscaneoLogin2() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.setContentPane(addPieza);
            frame.revalidate();
            frame.repaint();
        }
    }
}
