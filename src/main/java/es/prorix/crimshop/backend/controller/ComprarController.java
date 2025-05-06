package es.prorix.crimshop.backend.controller;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Clase controladora de la pantalla de comprar
 * @author prorix
 * @veresultSetion 1.0.0
 */
public class ComprarController {

    @FXML
    private VBox contenedorProductos;

    @FXML
    private Label totalLabel;

    @FXML
    private Button comprarButton;

    @FXML
    private Button volverButton;

    private double total = 0;

    /**
     * Metodo para volver la pantalla anterior
     * @param event evento
     * @throws IOException
     */
    @FXML
    private void volverButtonClick(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/prorix/crimshop/carrito.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 500, 590));
        stage.show();
    }

    /**
     * Metodo para acceder a la pantalla de confirmar compra
     * @param event evento
     * @throws IOException
     */
    @FXML
    private void comprarButtonClick(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/prorix/crimshop/confirmarcompra.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 500, 650));
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
     * Metodo que carga los productos desde el carrito para mostrarlos en esta pantalla
     */
    private void cargarProductosDelCarrito() {
        contenedorProductos.getChildren().clear();
        total = 0;

        try {
            Connection conn = ConexionBD.getConexion();

            String sql = """
                SELECT p.nombreCodigo, p.nombre, p.precio, c.cantidad
                FROM carrito c
                JOIN productos p ON c.id_producto = p.id
                WHERE c.id_usuario = (SELECT id FROM usuarios WHERE email = ?)
                """;

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, UsuarioService.getUsuarioActual().getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String codigo = resultSet.getString("nombreCodigo");
                String nombre = resultSet.getString("nombre");
                double precio = resultSet.getDouble("precio");
                int cantidad = resultSet.getInt("cantidad");

                HBox item = new HBox(10);
                item.setStyle("-fx-padding: 10; -fx-border-color: lightgray; -fx-border-radius: 5;");
                item.setPrefWidth(440);

                Label nombreLabel = new Label(nombre);
                nombreLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                Label precioLabel = new Label("Precio: " + precio + "€");
                Label cantidadLabel = new Label("Cantidad: " + cantidad);

                item.getChildren().addAll(nombreLabel, precioLabel, cantidadLabel);

                contenedorProductos.getChildren().add(item);

                total += precio * cantidad;
            }

            totalLabel.setText("Total: " + total + "€");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionBD.cerrarConexion();
        }
    }
}
