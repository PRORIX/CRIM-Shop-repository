package es.prorix.crimshop.backend.controller;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import es.prorix.crimshop.PrincipalApplication;
import es.prorix.crimshop.backend.controller.abstractas.AbstractController;
import es.prorix.crimshop.backend.controller.abstractas.IdiomaAbstractController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController extends IdiomaAbstractController implements Initializable {

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cambioIdioma();
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


