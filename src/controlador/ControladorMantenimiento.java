package controlador;

import modelo.Conexion;
import modelo.Mantenimiento;
import vista.VentanaMantenimiento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ControladorMantenimiento implements ActionListener {

    private VentanaMantenimiento vista;
    private Conexion conexion;
    private int idSeleccionado = -1;

    public ControladorMantenimiento(VentanaMantenimiento vista, Conexion conexion) {
        this.vista = vista;
        this.conexion = conexion;

        // Detecta los clics de los botones de la vista
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);

        // Evento al hacer clic en una fila de la tabla
        this.vista.tablaMantenimientos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tablaMantenimientos.getSelectedRow();
                if (fila >= 0) {
                    idSeleccionado = Integer.parseInt(vista.modeloTabla.getValueAt(fila, 0).toString());
                    vista.cbMoto.setSelectedItem(vista.modeloTabla.getValueAt(fila, 1).toString());
                    vista.txtServicio.setText(vista.modeloTabla.getValueAt(fila, 2).toString());
                    vista.spKilometraje.setValue(Integer.parseInt(vista.modeloTabla.getValueAt(fila, 3).toString()));
                    vista.txtCosto.setText(vista.modeloTabla.getValueAt(fila, 4).toString());
                    vista.cbEstado.setSelectedItem(vista.modeloTabla.getValueAt(fila, 5).toString());
                }
            }
        });

        // Mostrar los datos al iniciar
        mostrarDatos();
    }

    // Detectar que boton fue presionado
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            agregar();
        } else if (e.getSource() == vista.btnActualizar) {
            actualizar();
        } else if (e.getSource() == vista.btnEliminar) {
            eliminar();
        } else if (e.getSource() == vista.btnLimpiar) {
            limpiarCampos();
        }
    }

    private void agregar() {
        String moto = vista.cbMoto.getSelectedItem().toString();
        String servicio = vista.txtServicio.getText();
        String costoTexto = vista.txtCosto.getText();

        if (moto.isEmpty() || servicio.isEmpty() || costoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos.");
            return;
        }

        try {
            Mantenimiento m = new Mantenimiento();
            m.setMotocicleta(moto);
            m.setServicio(servicio);
            m.setKilometraje(Integer.parseInt(vista.spKilometraje.getValue().toString()));
            m.setCosto(Double.parseDouble(costoTexto));
            m.setEstado(vista.cbEstado.getSelectedItem().toString());

            if (conexion.agregar(m)) {
                JOptionPane.showMessageDialog(vista, "Registro guardado correctamente.");
                limpiarCampos();
                mostrarDatos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al guardar el registro.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El costo debe ser un numero valido.");
        }
    }

    private void actualizar() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro de la tabla para actualizar.");
            return;
        }

        String moto = vista.cbMoto.getSelectedItem().toString();
        String servicio = vista.txtServicio.getText();
        String costoTexto = vista.txtCosto.getText();

        if (moto.isEmpty() || servicio.isEmpty() || costoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos.");
            return;
        }

        try {
            Mantenimiento m = new Mantenimiento();
            m.setId(idSeleccionado);
            m.setMotocicleta(moto);
            m.setServicio(servicio);
            m.setKilometraje(Integer.parseInt(vista.spKilometraje.getValue().toString()));
            m.setCosto(Double.parseDouble(costoTexto));
            m.setEstado(vista.cbEstado.getSelectedItem().toString());

            if (conexion.actualizar(m)) {
                JOptionPane.showMessageDialog(vista, "Registro actualizado correctamente.");
                limpiarCampos();
                mostrarDatos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al actualizar el registro.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El costo debe ser un numero valido.");
        }
    }

    private void eliminar() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro de la tabla para eliminar.");
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(vista, "¿Desea eliminar este registro?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            if (conexion.eliminar(idSeleccionado)) {
                JOptionPane.showMessageDialog(vista, "Registro eliminado.");
                limpiarCampos();
                mostrarDatos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al eliminar el registro.");
            }
        }
    }

    private void mostrarDatos() {
        vista.modeloTabla.setRowCount(0);
        List<Mantenimiento> lista = conexion.listar();

        for (int i = 0; i < lista.size(); i++) {
            Mantenimiento m = lista.get(i);
            Object[] fila = new Object[6];
            fila[0] = m.getId();
            fila[1] = m.getMotocicleta();
            fila[2] = m.getServicio();
            fila[3] = m.getKilometraje();
            fila[4] = m.getCosto();
            fila[5] = m.getEstado();

            vista.modeloTabla.addRow(fila);
        }
    }

    private void limpiarCampos() {
        idSeleccionado = -1;
        vista.cbMoto.setSelectedIndex(0);
        vista.txtServicio.setText("");
        vista.spKilometraje.setValue(1000);
        vista.txtCosto.setText("");
        vista.cbEstado.setSelectedIndex(0);
        vista.tablaMantenimientos.clearSelection();
    }
}