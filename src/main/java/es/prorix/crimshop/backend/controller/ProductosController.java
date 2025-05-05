package es.prorix.crimshop.backend.controller;

import java.io.IOException;

import es.prorix.crimshop.PrincipalApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ProductosController {


    @FXML
    private Button verOficial;

    @FXML
    private Button verGorra;

    @FXML
    private Button verDragon;

    @FXML
    private Button verSolo;

    @FXML
    private Button verRobot;

    @FXML
    private Button verBasic;

    @FXML
    private BorderPane pantallaPrincipal;

    @FXML
    protected void verOficialClick(){
        verProducto("sudadera", verOficial);
    }

    @FXML
    protected void verGorraClick(){}

    @FXML
    protected void verDragonClick(){}

    @FXML
    protected void verSoloClick(){}

    @FXML
    protected void verRobotClick(){}

    @FXML
    private void verBasicClick(){}

    

    private void verProducto(String producto, javafx.scene.Node nodo){
                try {
            FXMLLoader loader = new FXMLLoader(PrincipalApplication.class.getResource("producto.fxml"));
            Scene scene = new Scene(loader.load(), 1280, 800);

            ProductoController productoView = loader.getController();
            productoView.setProducto(producto);

            Stage stage = (Stage) nodo.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {



    }

    }


