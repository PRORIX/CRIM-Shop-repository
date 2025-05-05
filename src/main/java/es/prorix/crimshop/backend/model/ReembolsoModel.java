package es.prorix.crimshop.backend.model;

public class ReembolsoModel {
    private int id;
    private int idPedido;
    private String motivo;
    private String estado;

    public ReembolsoModel(int id, int idPedido, String motivo, String estado) {
        this.id = id;
        this.idPedido = idPedido;
        this.motivo = motivo;
        this.estado = estado;
    }

    public int getId() { return id; }
    public int getIdPedido() { return idPedido; }
    public String getMotivo() { return motivo; }
    public String getEstado() { return estado; }
}
