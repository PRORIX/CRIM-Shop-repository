package es.prorix.crimshop.backend.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ProductosController {
    @FXML
    private GridPane gridProductos;

    @FXML
    public void initialize() {
        // Configurar las columnas para que tengan el mismo tamaño
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(33);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(33);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(33);

        // Agregar las columnas al GridPane
        gridProductos.getColumnConstraints().addAll(col1, col2, col3);

        // Configurar las filas para que se expandan automáticamente
        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.ALWAYS);
        RowConstraints row2 = new RowConstraints();
        row2.setVgrow(Priority.ALWAYS);

        // Agregar las filas al GridPane
        gridProductos.getRowConstraints().addAll(row1, row2);
    }
}
