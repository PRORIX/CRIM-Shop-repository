package es.prorix.crimshop.backend.model;

public class ProductoModel {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String imagen;
    private int stock;

    public ProductoModel(int id, String nombre, String descripcion, double precio, String imagen, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.stock = stock;
    }

    // Getters y setters...

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public String getImagen() { return imagen; }
    public int getStock() { return stock; }

    public void setStock(int stock) { this.stock = stock; }
}
