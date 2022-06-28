package pekversicherung.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import pekversicherung.DatabaseLogin;
import pekversicherung.Main;

import java.io.IOException;

public class Login {

    @FXML
    private TextField field_id;

    @FXML
    private PasswordField field_password;

    @FXML
    private Button btnAnmelden;

    @FXML
    void handeLoginClick(ActionEvent event) throws IOException {
        String username = field_id.getText();
        String password = field_password.getText();
        if (DatabaseLogin.checkLogin(username, password)){
            Main main = new Main();
            main.loadDashboard();
            Main.getAccount().login(username, password);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Falsche ID oder Passwort!");
            alert.setContentText("Bitte versuche es erneut.");
            alert.showAndWait();

        }

    }

    private Stage myStage;
    public void setMyStage(Stage stage){
        myStage = stage;
    }

}