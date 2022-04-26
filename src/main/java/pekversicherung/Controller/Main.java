package pekversicherung.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Main {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Willkommen bei Pek Versicherung!");
    }
}
