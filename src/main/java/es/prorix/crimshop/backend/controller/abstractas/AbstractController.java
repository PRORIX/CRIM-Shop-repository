package es.prorix.crimshop.backend.controller.abstractas;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public abstract class AbstractController {

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
    public Button registerButton;
    @FXML
    public Hyperlink forgotPasswordLink;

    public ResourceBundle currentBundle;

    String selected;

    // Metodos abstractos

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

    public void loadLanguage(String langCode) {
        Locale locale = new Locale(langCode);
        currentBundle = ResourceBundle.getBundle("lang.messages", locale);

        title.setText(currentBundle.getString("textTitulo"));
        usernameField.setPromptText(currentBundle.getString("promptTextTextFieldUsuario"));
        passwordField.setPromptText(currentBundle.getString("promptTextTextFieldContrasenia"));
        loginButton.setText(currentBundle.getString("textButtonIniciarSesion"));
        registerButton.setText(currentBundle.getString("textButtonCrearCuentaNueva"));
        forgotPasswordLink.setText(currentBundle.getString("textHyperLinkOlvidasteTuContra"));
    }
}
