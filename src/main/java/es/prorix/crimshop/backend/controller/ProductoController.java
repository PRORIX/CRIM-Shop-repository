package es.prorix.crimshop.backend.controller;

import es.prorix.crimshop.config.ConfigManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ProductoController {
    @FXML
    protected Label nombreProducto;

    @FXML
    protected Text descProducto;

    @FXML
    protected Label textPrecio;

    @FXML
    protected ImageView image1;

    @FXML
    protected ImageView image2;

    @FXML
    protected Button regresarButton;

    @FXML
    protected Button agregarButton;

    @FXML
    protected Button perfilButton;

    @FXML
    protected ChoiceBox cantidadBox;

    private String producto;

    public void setProducto(String producto){
        this.producto = producto;
        cargarProducto();
    }

    

    
    public void cargarProducto(){
        ConfigManager.cargarProperties();
        String titulo = producto + ".nombre";
        String desc = producto+".desc";
        String precio = producto+".precio";

        String textoTitulo = ConfigManager.getProperty(titulo);
        String textoDesc = ConfigManager.getProperty(desc);
        String textoPrecio = ConfigManager.getProperty(precio);

        nombreProducto.setText(textoTitulo);
        descProducto.setText(textoDesc);
        textPrecio.setText(textoPrecio);

    }

    @FXML
    protected void regresarButtonClick(){}

    @FXML
    protected void perfilButtonClick(){}

    @FXML
    protected void agregarButtonClick(){}
}
