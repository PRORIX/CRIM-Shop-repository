package es.prorix.crimshop.backend.controller;

import java.io.IOException;

import es.prorix.crimshop.PrincipalApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * Clase controladora de la pantalla de la lista de productos
 * @author prorix
 * @version 1.0.0
 */
public class ProductosController {


    @FXML
    private Button verOficial;

    @FXML
    private Button verGorra;

    @FXML
    private Button verDragon;

    @FXML
    private Button perfilButton;

    @FXML
    private Button verSolo;

    @FXML
    private Button verRobot;

    @FXML
    private Button verBasic;

    @FXML
    private BorderPane pantallaPrincipal;

    @FXML
    private Button verCarrito;

    @FXML
    private Button cerrarButton;


    @FXML
    private void cerrarButtonClick(){
        try {
            Stage stage = (Stage) perfilButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo del boton de acceder a la pantalla del carrito
     * @param event evento
     * @throws IOException
     */
    @FXML
    private void verCarritoClick(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/prorix/crimshop/carrito.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 500, 590));
        stage.show();
    }

    /**
     * Metodo del boton de acceder a la pantalla del perfil del usuario
     */
    @FXML
    protected void perfilButtonClick(){
        try {
            Stage stage = (Stage) perfilButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("perfil.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 300);
            stage.setTitle("perfil");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo del boton de ver la sudadera
     */
    @FXML
    protected void verOficialClick(){
        verProducto("sudadera", verOficial);
    }

    /**
     * Metodo del boton de ver la gorra
     */
    @FXML
    protected void verGorraClick(){
        verProducto("gorra", verGorra);
    }

    /**
     * Metodo del boton de ver el sueter
     */
    @FXML
    protected void verDragonClick(){
        verProducto("sueter", verDragon);
    }

    /**
     * Metodo del boton de ver la camiseta_msqma
     */
    @FXML
    protected void verSoloClick(){
        verProducto("camiseta_msqma", verSolo);
    }

    /**
     * Metodo del boton de ver la camiseta_robot
     */
    @FXML
    protected void verRobotClick(){
        verProducto("camiseta_robot", verRobot);
    }

    /**
     * Metodo del boton de ver la sudadera_basic
     */
    @FXML
    private void verBasicClick(){
        verProducto("sudadera_basic", verBasic);
    }

    

    /**
     * Metodo de cargar la informacion del producto
     * @param producto nombre en codigo del producto
     * @param nodo nodo boton
     */
    private void verProducto(String producto, javafx.scene.Node nodo){
                try {
            FXMLLoader loader = new FXMLLoader(PrincipalApplication.class.getResource("producto.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);

            ProductoController productoView = loader.getController();
            productoView.setProducto(producto);

            Stage stage = (Stage) nodo.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo inicializador
     */
    @FXML
    public void initialize() {

    }

    }


