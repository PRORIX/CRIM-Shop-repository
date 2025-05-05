package es.prorix.crimshop.backend.model;

public class CarritoModel {
    private int id;
    private int idUsuario;
    private int idProducto;
    private int cantidad;

    public CarritoModel(int id, int idUsuario, int idProducto, int cantidad) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    // Getters
    public int getId() { return id; }
    public int getIdUsuario() { return idUsuario; }
    public int getIdProducto() { return idProducto; }
    public int getCantidad() { return cantidad; }
}
