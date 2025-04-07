package es.prorix.crimshop.backend.controller;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import es.prorix.crimshop.backend.controller.abstractas.AbstractController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends AbstractController implements Initializable {

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cambioIdioma();
    }

    
}


