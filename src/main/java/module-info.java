//module com.specter.elearning {
//    requires javafx.controls;
//    requires javafx.fxml;
//    requires javafx.web;
//
//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires net.synedra.validatorfx;
//    requires org.kordamp.ikonli.javafx;
//    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;
//    requires com.almasb.fxgl.all;
//
//    opens com.specter.elearning to javafx.fxml;
//    exports com.specter.elearning;
//}

module com.specter.elearning {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    // If you use other libs (bcrypt, sqlite) add requires here, or remove module-info.java
    // requires at.favre.lib; // example for bcrypt if modular name matches

    // Allow JavaFX to construct your Application (reflective access)
    opens com.specter.elearning.app to javafx.graphics;
    // Allow FXMLLoader to instantiate controllers and access @FXML fields/methods
    opens com.specter.elearning.controller to javafx.fxml;
    // If your model uses property classes referenced by FXML, open models to javafx.fxml or javafx.base if needed
    opens com.specter.elearning.model to javafx.base, javafx.fxml;

    // If you need to expose APIs compile-time to other modules (rare for this project), use exports.
    // exports com.specter.elearning.util; // only if other modules compile against it
}
