module pek.versicherung {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens pekversicherung to javafx.fxml;
    exports pekversicherung;
    exports pekversicherung.Controller;
    opens pekversicherung.Controller to javafx.fxml;

    requires java.sql;
}