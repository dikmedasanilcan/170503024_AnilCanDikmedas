package pekversicherung;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pekversicherung.Controller.Login;
import pekversicherung.Model.Account;

import java.io.IOException;
import java.sql.*;

public class Main extends Application {
    private static Stage globalStage;

    private static Account account;
    private static Connection connection;

    @Override
    public void start(Stage stage) throws IOException {
        globalStage = stage;
        stage.setTitle("Pek Versicherung");
        loadLogin();
    }

    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        connection = db.getConnection();

        account = new Account();

        testDB();

        launch();
    }

    public Account getAccount(){
        return account;
    }

    public static Connection getConnection(){
        return connection;
    }

    public void loadLogin() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Views/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 620);
        globalStage.setScene(scene);
        globalStage.show();

        Login controllerLogin = fxmlLoader.getController();
        controllerLogin.setMyStage(globalStage);
    }

    public void loadDashboard() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Views/Dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 620);
        globalStage.setScene(scene);
    }

    public static void testDB(){
        String insertSql = "INSERT INTO customer (id, firstName, lastName, phoneNumber, address) VALUES "
                + "('1', 'Anıl Can', 'Dikmedaş', '05111111111', 'A Mah. B Sk. No:1 D:1');";

        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS))
        {
            statement.execute();
            resultSet = statement.getGeneratedKeys();

            while(resultSet.next()){
                System.out.println("Generated: " + resultSet.getString(1));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            e.getCause();
        }
    }
}