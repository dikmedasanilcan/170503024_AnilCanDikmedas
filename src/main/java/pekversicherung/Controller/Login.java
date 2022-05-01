package pekversicherung.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
            main.getAccount().anMelden(username, password);
        }
        else{
            System.out.println("Falsche ID oder Passwort!");
        }

    }

    private Stage myStage;
    public void setMyStage(Stage stage){
        myStage = stage;
    }

}