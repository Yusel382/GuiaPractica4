package modelo;

import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Conexion {

    // Datos de conexión a MySQL en XAMPP
    private static final String URL = "jdbc:mysql://localhost:3306/taller_motos?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Método para conectar a la base de datos
    public Connection conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexion: " + e.getMessage());
        }
        return con;
    }

    // Método para insertar un registro
    public boolean agregar(Mantenimiento m) {
        String sql = "INSERT INTO mantenimientos (motocicleta, servicio, kilometraje, costo, estado) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection con = conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, m.getMotocicleta());
            ps.setString(2, m.getServicio());
            ps.setInt(3, m.getKilometraje());
            ps.setDouble(4, m.getCosto());
            ps.setString(5, m.getEstado());

            ps.executeUpdate();
            ps.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al agregar: " + ex.getMessage());
            return false;
        }
    }

    // Método para actualizar un registro
    public boolean actualizar(Mantenimiento m) {
        String sql = "UPDATE mantenimientos SET motocicleta = ?, servicio = ?, kilometraje = ?, costo = ?, estado = ? WHERE id = ?";
        try {
            Connection con = conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, m.getMotocicleta());
            ps.setString(2, m.getServicio());
            ps.setInt(3, m.getKilometraje());
            ps.setDouble(4, m.getCosto());
            ps.setString(5, m.getEstado());
            ps.setInt(6, m.getId());

            ps.executeUpdate();
            ps.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
            return false;
        }
    }

    // Método para eliminar un registro
    public boolean eliminar(int id) {
        String sql = "DELETE FROM mantenimientos WHERE id = ?";
        try {
            Connection con = conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar: " + ex.getMessage());
            return false;
        }
    }

    // Método para obtener todos los registros
    public List<Mantenimiento> listar() {
        List<Mantenimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM mantenimientos";
        try {
            Connection con = conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Mantenimiento m = new Mantenimiento();
                m.setId(rs.getInt("id"));
                m.setMotocicleta(rs.getString("motocicleta"));
                m.setServicio(rs.getString("servicio"));
                m.setKilometraje(rs.getInt("kilometraje"));
                m.setCosto(rs.getDouble("costo"));
                m.setEstado(rs.getString("estado"));
                lista.add(m);
            }

            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error al listar: " + ex.getMessage());
        }
        return lista;
    }
}