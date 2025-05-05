package es.prorix.crimshop.backend.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;

public class GestorPedidosController {
    @FXML private ListView<String> listaPedidos;
    @FXML private Label lblEstado;

    @FXML
    public void initialize() {
        listaPedidos.getItems().addAll("Pedido #101 - En preparación", "Pedido #102 - Pendiente");
    }

    @FXML
    public void marcarComoEnviado() {
        String pedido = listaPedidos.getSelectionModel().getSelectedItem();
        if (pedido != null) {
            lblEstado.setText(pedido + " -> Enviado");
        }
    }
}
