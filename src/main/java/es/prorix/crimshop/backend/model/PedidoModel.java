package es.prorix.crimshop.backend.model;

import java.time.LocalDate;

public class PedidoModel {
    private int id;
    private int idUsuario;
    private LocalDate fecha;
    private double total;
    private String estado;

    public PedidoModel(int id, int idUsuario, LocalDate fecha, double total, String estado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }

    public int getId() { return id; }
    public int getIdUsuario() { return idUsuario; }
    public LocalDate getFecha() { return fecha; }
    public double getTotal() { return total; }
    public String getEstado() { return estado; }
}
