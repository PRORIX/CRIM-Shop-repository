package es.prorix.crimshop.backend.controller;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Label title;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Hyperlink forgotPasswordLink;

    private ResourceBundle currentBundle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.getItems().addAll("Español", "English");
        comboBox.setValue("Español"); // idioma por defecto
        loadLanguage("es");

        comboBox.setOnAction(e -> {
            String selected = comboBox.getValue();
            if (selected.equals("English")) {
                loadLanguage("en");
            } else {
                loadLanguage("es");
            }
        });
    }

    private void loadLanguage(String langCode) {
        Locale locale = new Locale(langCode);
        currentBundle = ResourceBundle.getBundle("lang.messages", locale);

        // Aplicar traducciones
        title.setText(currentBundle.getString("textTitulo"));
        usernameField.setPromptText(currentBundle.getString("promptTextTextFieldUsuario"));
        passwordField.setPromptText(currentBundle.getString("promptTextTextFieldContrasenia"));
        loginButton.setText(currentBundle.getString("textButtonIniciarSesion"));
        registerButton.setText(currentBundle.getString("textButtonCrearCuentaNueva"));
        forgotPasswordLink.setText(currentBundle.getString("textHyperLinkOlvidasteTuContra"));
    }
}


