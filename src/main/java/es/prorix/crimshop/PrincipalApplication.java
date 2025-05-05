package es.prorix.crimshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrincipalApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/prorix/crimshop/login.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("CRIM Shop - Iniciar Sesión");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
