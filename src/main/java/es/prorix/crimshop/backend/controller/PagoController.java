package es.prorix.crimshop.backend.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PagoController {
    @FXML private TextField txtTarjeta;
    @FXML private Label lblResultado;

    @FXML
    public void realizarPago() {
        String numero = txtTarjeta.getText();
        if (numero.length() == 16) {
            lblResultado.setText("Pago realizado correctamente (simulado)");
        } else {
            lblResultado.setText("Tarjeta inválida");
        }
    }
}
