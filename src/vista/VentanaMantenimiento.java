package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaMantenimiento extends JFrame {

    // Componentes del formulario (publicos para acceso desde el controlador)
    public JTextField txtServicio;
    public JTextField txtCosto;
    public JComboBox<String> cbMoto;
    public JComboBox<String> cbEstado;
    public JSpinner spKilometraje;

    // Botones de accion
    public JButton btnAgregar;
    public JButton btnActualizar;
    public JButton btnEliminar;
    public JButton btnLimpiar;

    // Componentes para la tabla de datos
    public JTable tablaMantenimientos;
    public DefaultTableModel modeloTabla;

    public VentanaMantenimiento() {
        // Configuracion basica de la ventana
        setTitle("José Manuel Moreira Henríquez - 25-4717-2022");
        setSize(740, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Creacion del panel para los campos del formulario
        JPanel panelCampos = new JPanel(new GridLayout(5, 2, 8, 8));
        panelCampos.setBorder(BorderFactory.createTitledBorder("Registro de Servicio"));

        panelCampos.add(new JLabel("Motocicleta:"));
        String[] listaMotos = {"AKT TT 250 Adventour", "Hero Eco 150", "Yamaha FZ 25", "Honda Shadow 150"};
        cbMoto = new JComboBox<>(listaMotos);
        cbMoto.setEditable(true);
        panelCampos.add(cbMoto);

        panelCampos.add(new JLabel("Servicio / Repuesto:"));
        txtServicio = new JTextField();
        panelCampos.add(txtServicio);

        panelCampos.add(new JLabel("Kilometraje (km):"));
        spKilometraje = new JSpinner(new SpinnerNumberModel(1000, 0, 500000, 500));
        panelCampos.add(spKilometraje);

        panelCampos.add(new JLabel("Costo ($):"));
        txtCosto = new JTextField();
        panelCampos.add(txtCosto);

        panelCampos.add(new JLabel("Estado:"));
        String[] listaEstados = {"Completado", "Pendiente", "En Proceso"};
        cbEstado = new JComboBox<>(listaEstados);
        panelCampos.add(cbEstado);

        // Creacion del panel para los botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar Campos");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        // Panel superior que une formulario y botones
        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(panelCampos, BorderLayout.CENTER);
        panelNorte.add(panelBotones, BorderLayout.SOUTH);
        add(panelNorte, BorderLayout.NORTH);

        // Configuracion de la tabla
        String[] columnas = {"ID", "Motocicleta", "Servicio", "Kilometraje", "Costo ($)", "Estado"};
        modeloTabla = new DefaultTableModel(null, columnas);
        tablaMantenimientos = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaMantenimientos);
        add(scrollTabla, BorderLayout.CENTER);
    }
}