module pek.versicherung {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens pekversicherung to javafx.fxml;
    opens pekversicherung.model to javafx.base;
    exports pekversicherung;
    exports pekversicherung.controller;
    opens pekversicherung.controller to javafx.fxml;

    requires java.sql;
    requires mysql.connector.java;
}