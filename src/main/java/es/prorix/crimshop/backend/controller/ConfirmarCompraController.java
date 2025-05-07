package es.prorix.crimshop.backend.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import es.prorix.crimshop.config.UsuarioService;
import es.prorix.crimshop.database.ConexionBD;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Clase controladora de confirmar la compra del usuario
 * 
 * @author prorix
 * @version 1.0.0
 */
public class ConfirmarCompraController {

    @FXML
    private Label mensajeLabel;

    @FXML
    private TextField tarjetaTextField;

    @FXML
    private TextField cvvTextField;

    @FXML
    private TextField domicilioTextField;

    @FXML
    private TextField codigoPostalTextField;

    @FXML
    private ChoiceBox<String> ciudadChoiceBox;

    @FXML
    private TextField telefonoTextField;

    @FXML
    private Button confirmarButton;

    @FXML
    private Button volverButton;

    /**
     * Metodo de inicializacion
     */
    @FXML
    public void initialize() {
        ciudadChoiceBox.setItems(FXCollections.observableArrayList(
                "Madrid", "Barcelona", "Valencia", "Sevilla", "Zaragoza"));

        tarjetaTextField.setPrefWidth(300);
        cvvTextField.setPrefWidth(100);
        domicilioTextField.setPrefWidth(300);
        codigoPostalTextField.setPrefWidth(150);
        telefonoTextField.setPrefWidth(150);
    }

    /**
     * Metodo del boton de confirmar la compra
     * 
     * @param event evento
     * @throws IOException
     */
    @FXML
    private void confirmarButtonClick(ActionEvent event) throws IOException {
        if (validarCampos()) {
            realizarCompra(event);
        } else {
            mensajeLabel.setText("Por favor, rellena todos los campos.");
        }
    }

    /**
     * Metodo del boton de regresar una pantalla atras
     * 
     * @param event evento
     * @throws IOException
     */
    @FXML
    private void volverButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/prorix/crimshop/comprar.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 500, 590));
        stage.show();
    }

    /**
     * Metodo que valida que los campos esten rellenos
     * 
     * @return true / false
     */
    private boolean validarCampos() {
        // Validar los campos
        return !tarjetaTextField.getText().isEmpty() && !cvvTextField.getText().isEmpty() && !domicilioTextField.getText().isEmpty() && !codigoPostalTextField.getText().isEmpty() && !ciudadChoiceBox.getSelectionModel().isEmpty() && !telefonoTextField.getText().isEmpty();
    }

    /**
     * Metodo de realizar la compra tras revisar los campos
     * 
     * @param event evento
     * @throws IOException
     */
    private void realizarCompra(ActionEvent event) throws IOException {
        try {
            Connection conn = ConexionBD.getConexion();

            String sqlObtenerIdUsuario = "SELECT id FROM usuarios WHERE email = ?";
            PreparedStatement pStmtUsuario = conn.prepareStatement(sqlObtenerIdUsuario);
            pStmtUsuario.setString(1, UsuarioService.getUsuarioActual().getEmail());
            ResultSet rsUsuario = pStmtUsuario.executeQuery();
            int idUsuario = rsUsuario.getInt("id");

            String querry = "DELETE FROM carrito WHERE id_usuario = ?";
            PreparedStatement pStatement = conn.prepareStatement(querry);
            pStatement.setInt(1, idUsuario);
            pStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionBD.cerrarConexion();
        }

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Compra realizaca con éxito.");
        alerta.setHeaderText(null);
        alerta.setContentText(
                "Muchas gracias por su compra, se ha realizado con éxito, pronto recibirá un correo electrónico con información a su email asociado a su cuenta.");
        alerta.showAndWait();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/prorix/crimshop/productos.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 500, 590));
        stage.show();
    }
}
