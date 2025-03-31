module es.prorix.crimshop{
    requires javafx.controls;
    requires javafx.fxml;
    
    requires org.controlsfx.controls;
    requires javafx.graphics;
    requires java.xml;

    opens es.prorix.crimshop to javafx.fxml;
    exports es.prorix.crimshop;
    exports es.prorix.crimshop.backend.controller;

    opens es.prorix.crimshop.backend.controller to javafx.fxml;
}