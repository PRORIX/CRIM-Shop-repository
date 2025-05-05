package es.prorix.crimshop.backend.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CarritoController {
    @FXML private ListView<String> listaCarrito;
    @FXML private Button btnPagar;
    @FXML private Label lblTotal;

    @FXML
    public void initialize() {
        listaCarrito.getItems().addAll("Camiseta CRIM - 20€", "Gorra CRIM - 15€");
        lblTotal.setText("Total: 35€");
    }

    @FXML
    public void pagar() {
        System.out.println("Pago simulado realizado.");
    }
}
