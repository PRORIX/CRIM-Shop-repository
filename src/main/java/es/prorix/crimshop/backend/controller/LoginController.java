package es.prorix.crimshop.backend.controller;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import es.prorix.crimshop.PrincipalApplication;
import es.prorix.crimshop.backend.model.UsuarioModel;
import es.prorix.crimshop.config.UsuarioService;
import es.prorix.crimshop.database.ConexionBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Clase controladora de la pantalla de login
 * 
 * @author prorix
 * @veresultSetion 1.0.0
 */
public class LoginController implements Initializable {

    @FXML
    public ComboBox<String> comboBox;
    @FXML
    public Label title;
    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button loginButton;
    @FXML
    public Button openRegisterButton;
    @FXML
    public Hyperlink forgotPasswordLink;
    @FXML
    public Label mensajeLabel;

    String selected;

    public ResourceBundle currentBundle;

    /**
     * Metodo del boton de iniciar sesion
     * 
     * @param event evento
     */
    @FXML
    private void loginButtonClick(ActionEvent event) {
        String user = usernameField.getText().trim();
        String contrasena = passwordField.getText();

        if (user.isEmpty() || contrasena.isEmpty()) {
            mensajeLabel.setText("Introduce el correo y la contraseña.");
            return;
        }

        Connection conn = ConexionBD.getConexion();

        try {
            String consulta = "SELECT * FROM usuarios WHERE nombreUsuario = ? AND contrasenia = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(consulta);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, contrasena);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                String emailUsuario = resultSet.getString("email");

                UsuarioModel usuario = new UsuarioModel(user, contrasena, emailUsuario);
                UsuarioService.setUsuarioActual(usuario);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/prorix/crimshop/productos.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root, 500, 590));
                stage.show();
            } else {
                mensajeLabel.setText("Usuario o contraseña incorrectos.");
            }
        } catch (SQLException e) {
            mensajeLabel.setText("Error al conectar: " + e.getMessage());
        } catch (Exception e) {
            mensajeLabel.setText("Error al cargar perfil: " + e.getMessage());
        } finally {
            ConexionBD.cerrarConexion();
        }
    }

    /**
     * Metodo de cambio de idioma
     */
    public void cambioIdioma() {
        comboBox.getItems().addAll("Español", "English", "Français");
        comboBox.setValue("Español");
        loadLanguage("es");

        comboBox.setOnAction(e -> {
            selected = comboBox.getValue();
            if (selected.equals("English")) {
                loadLanguage("en");
            } else if (selected.equals("Français")) {
                loadLanguage("fr");
            } else {
                loadLanguage("es");
            }
        });
    }

    /**
     * Metodo para cargar el idioma
     * 
     * @param langCode codigo de lengua
     */
    public void loadLanguage(String langCode) {
        Locale locale = new Locale(langCode);
        currentBundle = ResourceBundle.getBundle("lang.messages", locale);

        title.setText(currentBundle.getString("textTitulo"));
        usernameField.setPromptText(currentBundle.getString("promptTextTextFieldUsuario"));
        passwordField.setPromptText(currentBundle.getString("promptTextTextFieldContrasenia"));
        loginButton.setText(currentBundle.getString("textButtonIniciarSesion"));
        openRegisterButton.setText(currentBundle.getString("textButtonCrearCuentaNueva"));
        forgotPasswordLink.setText(currentBundle.getString("textHyperLinkOlvidasteTuContra"));
    }

    /**
     * Metodo para cambiar la vista
     * 
     * @param fxml  destino
     * @param boton boton que hizo la accion
     */
    public void cambiarVista(String fxml, Button boton) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/prorix/crimshop/view/" + fxml));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) boton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo inicializador
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cambioIdioma();
    }

    /**
     * Metodo del vinculo de olvidar la contrasenia
     */
    @FXML
    protected void forgotPasswordLinkClick() {
        try {
            Stage stage = (Stage) openRegisterButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("recuperarContrasenia.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Recuperar contraseña");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo del boton de abrir la pantalla de registro
     * 
     * @throws Exception
     */
    @FXML
    protected void openRegisterButtonClick() throws Exception {
        try {
            Stage stage = (Stage) openRegisterButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("register.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("registro");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
