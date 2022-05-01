package pekversicherung;

import pekversicherung.Model.Customer;

import java.sql.*;

public class AddCustomer {

    public static void createNewCustomer(Customer customer){
        System.out.println("createNewCustomer from AddCustomer");
        Connection connection = Main.getConnection();
        String insertSql = "INSERT INTO customer (id, firstName, lastName, phoneNumber, address) VALUES "
                       + "('"+customer.getId()+"', '"+customer.getFirstName()+"', '"+customer.getLastName()+"', '"+customer.getPhoneNumber()+"', '"+customer.getAddress()+"');";

        ResultSet resultSet = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            while(resultSet.next()){
                System.out.println("createNewCustomer, Generated Customer Index: " + resultSet.getString(1));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
