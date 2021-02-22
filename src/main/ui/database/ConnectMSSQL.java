package main.ui.database;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectMSSQL {

    private String username;
    private String password;
    private String userType;

    public ConnectMSSQL(String username, String password, String userType){
        this.username = username.toLowerCase();
        this.password = password.toLowerCase();
        this.userType = userType.toLowerCase();

        connectDB();
    }

    private void connectDB(){
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void logInForm(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=ProjectDB;selectedMethod=cursor",
                    "sa",
                    "123456");

           // System.out.println("DB NAME IS: " + connection.getMetaData().getDatabaseProductName());
            String query="";

            if(userType.equals("doctor")) {
                query = "select doctor_name, doctor_password from Doctor where doctor_id= " + username;
            }else if(userType.equals("receptionist")){
                query = "select receptionist_name, receptionist_pass from Receptionist where receptionist_id= " + username;
            }else if(userType.equals("admin")){
                query = "select admin_name, admin_pass from admin where admin_id= " + username;
            }

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()){

                if(userType.equals("doctor")) {

                    if (resultSet.getString("doctor_password").equals(password)) {
                        System.out.println("Logged In");
                    } else {
                        System.out.println("Wrong Credentials");
                    }
                }

                if(userType.equals("receptionist")) {
                    if (resultSet.getString("receptionist_pass").equals(password)) {
                        System.out.println("Logged In");
                    } else {
                        System.out.println("Wrong Credentials");
                    }
                }

                if(userType.equals("admin")) {
                    if (resultSet.getString("admin_pass").equals(password)) {
                        System.out.println("Logged In");
                    } else {
                        System.out.println("Wrong Credentials");
                    }
                }



            }



        }catch (Exception e){
            e.printStackTrace();
        }

    }




}
