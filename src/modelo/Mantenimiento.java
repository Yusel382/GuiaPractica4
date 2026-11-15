package modelo;

public class Mantenimiento {
    private int id;
    private String motocicleta;
    private String servicio;
    private int kilometraje;
    private double costo;
    private String estado;

    // Constructor vacío
    public Mantenimiento() {
    }

    // Métodos Getter y Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotocicleta() {
        return motocicleta;
    }

    public void setMotocicleta(String motocicleta) {
        this.motocicleta = motocicleta;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}