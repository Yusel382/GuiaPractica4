import controlador.ControladorMantenimiento;
import modelo.Conexion;
import vista.VentanaMantenimiento;

public class Main {
    public static void main(String[] args) {
        VentanaMantenimiento vista = new VentanaMantenimiento();
        Conexion conexion = new Conexion();

        new ControladorMantenimiento(vista, conexion);

        vista.setVisible(true);
    }
}