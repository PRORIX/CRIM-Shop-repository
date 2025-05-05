package es.prorix.crimshop.backend.controller;

import es.prorix.crimshop.PrincipalApplication;
import es.prorix.crimshop.database.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Clase controladora de la pantalla de recuperacion de contrasenia
 * @author prorix
 * @version 1.0.2
 */
public class RecuperarContraseniaController {

    @FXML
    private TextField emailTextField;

    @FXML
    private Button siguienteButton;

    @FXML
    private Button cancelarButton;

    /**
     * Metodo del boton de confirmar la recuperacion de contrasenia
     */
    @FXML
    public void siguienteButtonClick() {
        String email = emailTextField.getText().trim();

        if (email.isEmpty()) {
            mostrarAlerta("Error", "Por favor, introduce tu correo electrónico.", Alert.AlertType.ERROR);
            return;
        }

        try (Connection conn = ConexionBD.getConexion()) {
            if (conn == null || conn.isClosed()) {
                mostrarAlerta("Error de conexión", "No se pudo conectar con la base de datos.", Alert.AlertType.ERROR);
                return;
            }

            String sql = "SELECT * FROM Usuarios WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    mostrarAlerta("Éxito", "Se ha enviado un correo electrónico para restablecer tu contraseña.", Alert.AlertType.INFORMATION);

                    Stage stage = (Stage) siguienteButton.getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("login.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 330, 500);
                    stage.setTitle("registro");
                    stage.setScene(scene);
                    stage.show();
                } else {
                    mostrarAlerta("Error", "No existe un usuario con ese correo electrónico.", Alert.AlertType.ERROR);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "Ha ocurrido un error al comprobar el correo.", Alert.AlertType.ERROR);
        }finally{
            ConexionBD.cerrarConexion();
        }
    }

    /**
     * Metodo del boton de cancelar
     * @param event evento
     */
    @FXML
    private void cancelarButtonClick(ActionEvent event) {
            try {
            Stage stage = (Stage) cancelarButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 330, 500);
            stage.setTitle("registro");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    /**
     * Metodo para mostrar todas las alertas de esta pantalla
     * @param titulo de la alerta
     * @param mensaje de la alerta
     * @param tipo de alerta
     */
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
