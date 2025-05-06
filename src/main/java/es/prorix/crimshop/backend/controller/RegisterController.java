package es.prorix.crimshop.backend.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import es.prorix.crimshop.PrincipalApplication;
import es.prorix.crimshop.database.ConexionBD;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Clase controladora de la pantalla de registro de usuarios
 * @author prorix
 * @version 1.0.0
 */
public class RegisterController implements Initializable {

    @FXML
    public TextField usernameField;
    @FXML
    public TextField emailField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public PasswordField confirmPasswordField;
    @FXML
    public Button registerButton;
    @FXML
    public Hyperlink loginLink;
    @FXML
    public Text textMensaje;


    /**
     * Metodo inicializador
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Metodo del boton de registro
     */
    @FXML
    public void registerButtonClick(){
        String nombre = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (nombre.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            textMensaje.setText("Por favor, completa todos los campos.");
            return;
        }

        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            textMensaje.setText("El correo electrónico no tiene un formato válido.");
            return;
        }
        if (!password.equals(confirmPassword)) {
            textMensaje.setText("Las contraseñas no coinciden.");
            return;
        }

        try (Connection conn = ConexionBD.getConexion()) {
            String consulta = "SELECT * FROM Usuarios WHERE email = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(consulta);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();


            if (rs.next()) {
                textMensaje.setText("Ya existe un usuario con ese correo.");
            } else {
                String insertar = "INSERT INTO usuarios(nombreUsuario, email, contrasenia) VALUES (?, ?, ?)";
                PreparedStatement preparedStatementInsertar = conn.prepareStatement(insertar);
                preparedStatementInsertar.setString(1, nombre);
                preparedStatementInsertar.setString(2, email);
                preparedStatementInsertar.setString(3, password);
                
                int filas = preparedStatementInsertar.executeUpdate();
                if (filas > 0) {
                    textMensaje.setText("Usuario registrado correctamente.");
                    limpiarCampos();
                } else {
                    textMensaje.setText("Error al registrar el usuario.");
                }
            }
        } catch (Exception e) {
            textMensaje.setText("Error con la base de datos: " + e.getMessage());
        }finally{
            System.out.println("Conexion con la bbdd cerrada");
            ConexionBD.cerrarConexion();
        }

        

    }

    /**
     * Metodo que limpia los campos
     */
    private void limpiarCampos() {
        usernameField.clear();
        emailField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
    }

    /**
     * Metodo para volver a la pantalla de login
     */
    @FXML
    public void loginLinkClick(){
            try {
            Stage stage = (Stage) loginLink.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }



