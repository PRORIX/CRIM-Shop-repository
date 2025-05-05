package es.prorix.crimshop.backend.controller;

import java.io.BufferedReader;
import java.io.FileReader;
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




    @FXML
    private void loginButtonClick(ActionEvent event) {
        String user = usernameField.getText().trim();
        String contrasena = passwordField.getText();

        if (user.isEmpty() || contrasena.isEmpty()) {
            mensajeLabel.setText("⚠️ Introduce el correo y la contraseña.");
            return;
        }

        Connection conn = ConexionBD.getConexion();

        try {
            String consulta = "SELECT * FROM usuarios WHERE nombreUsuario = ? AND contrasenia = ?";
            PreparedStatement stmt = conn.prepareStatement(consulta);
            stmt.setString(1, user);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                UsuarioModel usuario = new UsuarioModel(user, contrasena, "");
                UsuarioService.setUsuarioActual(usuario);

                // Cambiar de pantalla al perfil
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/prorix/crimshop/productos.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root, 500, 590));
                stage.show();
            } else {
                mensajeLabel.setText("❌ Usuario o contraseña incorrectos.");
            }
        } catch (SQLException e) {
            mensajeLabel.setText("❌ Error al conectar: " + e.getMessage());
        } catch (Exception e) {
            mensajeLabel.setText("❌ Error al cargar perfil: " + e.getMessage());
        } finally {
            ConexionBD.cerrarConexion();
        }
    }

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
        openRegisterButton.setText(currentBundle.getString("textButtonCrearCuentaNueva"));
        forgotPasswordLink.setText(currentBundle.getString("textHyperLinkOlvidasteTuContra"));
    }

    

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
    
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cambioIdioma();
    }

    public String[] autenticar(String user, String pass) {
    try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(";");
            if (datos[0].equals(user) && datos[1].equals(pass)) {
                return datos; // [usuario, password, rol]
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}

@FXML
protected void forgotPasswordLinkClick(){
    try {
        Stage stage = (Stage) openRegisterButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("recuperarContrasenia.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 330, 500);
        stage.setTitle("registro");
        stage.setScene(scene);
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @FXML
    protected void openRegisterButtonClick() throws Exception{
            try {
            Stage stage = (Stage) openRegisterButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("register.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 330, 500);
            stage.setTitle("registro");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}


