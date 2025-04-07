package es.prorix.crimshop.backend.controller;

import java.net.URL;
import java.util.ResourceBundle;

import es.prorix.crimshop.backend.controller.abstractas.IdiomaAbstractController;
import javafx.fxml.Initializable;

public class RegisterController extends IdiomaAbstractController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    cambioIdioma();
    }
}
