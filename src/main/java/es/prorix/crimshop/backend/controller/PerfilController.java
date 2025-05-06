package es.prorix.crimshop.backend.controller;

import java.io.IOException;

import es.prorix.crimshop.backend.model.UsuarioModel;
import es.prorix.crimshop.config.UsuarioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Clase controladora de la pantalla de perfil del usuario
 * @author prorix
 * @version 1.0.0
 */
public class PerfilController {
    @FXML
    private Label labelNombreUsuario;

    @FXML
    private Label labelEmailUsuario;

    @FXML
    private Button regresarButton;

    /**
     * Metodo del boton de volver una pantalla atras
     * @param event event
     * @throws IOException
     */
    @FXML
    protected void regresarButtonClick(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/prorix/crimshop/productos.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 500, 590));
        stage.show(); 
    }

    /**
     * Metodo inicializador
     */
    public void initialize(){
        cargarUsuario();
    }

    /**
     * Metodo que carga un usuario
     */
    public void cargarUsuario(){
        UsuarioModel usuarioActual = UsuarioService.getUsuarioActual();
        labelNombreUsuario.setText(usuarioActual.getNombreUsuario());
        labelEmailUsuario.setText(usuarioActual.getEmail());
    }
}
