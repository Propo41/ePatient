package main.ui.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectMSSQL {
    public void connectDB(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=ProjectDB;selectedMethod=cursor",
                    "sa",
                    "123456");
            System.out.println("DB NAME IS: " + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select FirstName FROM Customer");

            while (resultSet.next()){
                System.out.println("Customer name: " + resultSet.getString("FirstName"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
