package es.prorix.crimshop.backend.controller;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import es.prorix.crimshop.PrincipalApplication;
import es.prorix.crimshop.config.ConfigManager;
import es.prorix.crimshop.config.UsuarioService;
import es.prorix.crimshop.database.ConexionBD;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Clase controladora de la pantalla de producto
 * 
 * @author prorix
 * @version 1.0.0
 */
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
    protected Button regresarButton;

    @FXML
    protected Button agregarButton;

    @FXML
    protected Button perfilButton;

    @FXML
    protected ChoiceBox<Integer> cantidadBox;

    private String producto;

    /**
     * Metodo que carga el producto elegido anteriormente
     * 
     * @param producto producto elegido
     */
    public void setProducto(String producto) {
        this.producto = producto;
        cargarProducto();
    }

    /**
     * Metodo que carga la informacion del producto elegido
     */
    public void cargarProducto() {
        ConfigManager.cargarProperties();
        String titulo = producto + ".nombre";
        String desc = producto + ".desc";
        String precio = producto + ".precio";

        String textoTitulo = ConfigManager.getProperty(titulo);
        String textoDesc = ConfigManager.getProperty(desc);
        String textoPrecio = ConfigManager.getProperty(precio);

        nombreProducto.setText(textoTitulo);
        descProducto.setText(textoDesc);
        textPrecio.setText(textoPrecio);

        Image img = new Image(
                getClass().getResource("/es/prorix/crimshop/imagenes/productos/" + producto + ".png").toExternalForm());
        image1.setImage(img);

        image1.setImage(img);

        prepararBox();
    }

    /**
     * Metodo que prepara los elementos del box
     */
    public void prepararBox() {
        cantidadBox.getItems().clear();
        cantidadBox.getItems().addAll(1, 2, 3);
        cantidadBox.setValue(1);
    }

    /**
     * Metodo del boton de regresar una pantalla aotras
     * 
     * @param event evento
     * @throws IOException
     */
    @FXML
    protected void regresarButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/prorix/crimshop/productos.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 500, 590));
        stage.show();
    }

    /**
     * Metodo del boton de acceder al perfil del usuario
     */
    @FXML
    protected void perfilButtonClick() {
        try {
            Stage stage = (Stage) perfilButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("perfil.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 300);
            stage.setTitle("perfil");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo del boton de agregar el producto al carrito
     */
    @FXML
    protected void agregarButtonClick() {
        int cantidad = cantidadBox.getValue();
        try {
            Connection conn = ConexionBD.getConexion();

            String sqlObtenerIdUsuario = "SELECT id FROM usuarios WHERE email = ?";
            PreparedStatement pStmtUsuario = conn.prepareStatement(sqlObtenerIdUsuario);
            pStmtUsuario.setString(1, UsuarioService.getUsuarioActual().getEmail());
            ResultSet rsUsuario = pStmtUsuario.executeQuery();
            int idUsuario = rsUsuario.getInt("id");

            String sqlObtenerIdProducto = "SELECT id FROM productos WHERE nombreCodigo = ?";
            PreparedStatement pStmtProducto = conn.prepareStatement(sqlObtenerIdProducto);
            pStmtProducto.setString(1, producto);
            ResultSet rsProducto = pStmtProducto.executeQuery();
            int idProducto = rsProducto.getInt("id");

            String sqlInsert = "INSERT INTO carrito (id_usuario, id_producto, cantidad) VALUES (?, ?, ?)";
            PreparedStatement pStmtInsert = conn.prepareStatement(sqlInsert);
            pStmtInsert.setInt(1, idUsuario);
            pStmtInsert.setInt(2, idProducto);
            pStmtInsert.setInt(3, cantidad);
            pStmtInsert.executeUpdate();

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Producto añadido");
            alerta.setHeaderText(null);
            alerta.setContentText("¡Producto añadido al carrito correctamente!");
            alerta.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionBD.cerrarConexion();
        }
    }

}
