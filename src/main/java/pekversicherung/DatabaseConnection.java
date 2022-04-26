package pekversicherung;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "pekversicherung";
        String databaseUser = "root";
        String databasePassword = "795326";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("jdbc.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch (Exception e){
            e.printStackTrace();
        }

        return databaseLink;
    }
}