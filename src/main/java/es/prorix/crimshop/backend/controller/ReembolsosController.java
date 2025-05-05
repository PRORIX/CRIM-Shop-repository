package es.prorix.crimshop.backend.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;

public class ReembolsosController {
    @FXML private ListView<String> listaReembolsos;
    @FXML private Label lblRespuesta;

    @FXML
    public void initialize() {
        listaReembolsos.getItems().addAll("Pedido #101 - Motivo: Producto dañado");
    }

    @FXML
    public void aprobarReembolso() {
        String item = listaReembolsos.getSelectionModel().getSelectedItem();
        if (item != null) {
            lblRespuesta.setText("Reembolso aprobado para: " + item);
        }
    }
}
