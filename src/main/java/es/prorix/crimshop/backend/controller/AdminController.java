package es.prorix.crimshop.backend.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AdminController {
    @FXML private ListView<String> listaProductos;
    @FXML private TextField txtNuevoProducto;

    @FXML
    public void initialize() {
        listaProductos.getItems().addAll("Camiseta CRIM", "Sudadera CRIM");
    }

    @FXML
    public void agregarProducto() {
        String nuevo = txtNuevoProducto.getText();
        if (!nuevo.isEmpty()) {
            listaProductos.getItems().add(nuevo);
            txtNuevoProducto.clear();
        }
    }
}
