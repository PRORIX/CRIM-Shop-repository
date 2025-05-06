package es.prorix.crimshop.backend.controller;

import es.prorix.crimshop.PrincipalApplication;
import es.prorix.crimshop.config.UsuarioService;
import es.prorix.crimshop.database.ConexionBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Clase controladora de la pantalla del carrito
 * @author prorix
 * @version 1.0.0
 */
public class CarritoController {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox contenedorProductos;

    @FXML
    private Button volverButton;

    @FXML
    private Button perfilButton;

    @FXML
    private Button comprarButton;


    /**
     * Metodo del boton de volver una pantalla atras
     * @param event evento
     * @throws IOException
     */
    @FXML
    private void volverButtonClick(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/prorix/crimshop/productos.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 500, 590));
        stage.show();
    }

    /**
     * Metodo para acceder al perfil del usuario
     * @param event evento
     */
    @FXML
    private void perfilButtonClick(ActionEvent event){
        try {
            Stage stage = (Stage) perfilButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("perfil.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 300);
            stage.setTitle("registro");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para acceder a la pantalla de compra
     * @param event evento
     * @throws IOException
     */
    @FXML
    private void comprarButtonClick(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/prorix/crimshop/comprar.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 500, 590));
        stage.show();
    }

    /**
     * Metodo inicializador
     */
    @FXML
    public void initialize() {
        cargarProductosDelCarrito();
    }

    /**
     * Metodo que carga los productos que tiene el usuario actual en el carrito
     * Crea un hbox con informacion y boton de eliminar para cada producto encontrado en la BBDD
     */
    private void cargarProductosDelCarrito() {
        contenedorProductos.getChildren().clear();

        try {
            Connection conn = ConexionBD.getConexion();

            String sql = """
                SELECT c.id, p.nombreCodigo, p.nombre, p.precio, c.cantidad
                FROM carrito c
                JOIN productos p ON c.id_producto = p.id
                WHERE c.id_usuario = (SELECT id FROM usuarios WHERE email = ?)
                """;

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, UsuarioService.getUsuarioActual().getEmail());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
                int id = rs.getInt("id");
                String codigo = rs.getString("nombreCodigo");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");

                HBox item = new HBox(10);
                item.setStyle("-fx-padding: 10; -fx-border-color: lightgray; -fx-border-radius: 5;");
                item.setPrefWidth(440);

                ImageView imagen = new ImageView(new Image(getClass().getResource("/es/prorix/crimshop/imagenes/productos/" + codigo + ".png").toExternalForm()));
                imagen.setFitHeight(50);
                imagen.setFitWidth(50);

                VBox info = new VBox(5);
                Label nombreLabel = new Label(nombre);
                nombreLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                Label precioLabel = new Label("Precio: " + precio + "€");
                Label cantidadLabel = new Label("Cantidad: " + cantidad);

                info.getChildren().addAll(nombreLabel, precioLabel, cantidadLabel);

                Button quitarButton = new Button("Quitar");
                quitarButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                quitarButton.setOnAction(e -> quitarProductoDelCarrito(id, item));

                item.getChildren().addAll(imagen, info, quitarButton);
                contenedorProductos.getChildren().add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionBD.cerrarConexion();
        }
    }

    /**
     * Metodo que elimina un producto del carrito del usuario
     * @param idProducto id del producto a eliminar
     * @param item hbox a eliminar
     */
    private void quitarProductoDelCarrito(int idProducto, HBox item) {
        try {
            Connection conn = ConexionBD.getConexion();
            String sql = "DELETE FROM carrito WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProducto);
            stmt.executeUpdate();
            contenedorProductos.getChildren().remove(item);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionBD.cerrarConexion();
        }
    }
}
