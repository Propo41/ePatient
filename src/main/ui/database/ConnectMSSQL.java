package main.ui.database;

import com.sun.org.apache.xpath.internal.operations.Bool;

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

    public void logInForm(String userID, String userType, String userPass){
        userID = userID.toLowerCase();
        userType = userType.toLowerCase();
        userPass = userPass.toLowerCase();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=testdatabase;selectedMethod=cursor",
                    "sa",
                    "123456");

           // System.out.println("DB NAME IS: " + connection.getMetaData().getDatabaseProductName());
            String query="";

            if(userType == "doctor" ) {
                query = "select doctor_name, doctor_password from Doctor where doctor_id= " + userID;
            }else if(userType == "receptionist"){
                query = "select receptionist_name, receptionist_pass from Receptionist where receptionist_id= " + userID;
            }else if(userType == "admin"){
                query = "select admin_name, admin_pass from admin where admin_id= " + userID;
            }

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()){

                if(userType == "doctor") {

                    if (resultSet.getString("doctor_password").equals(userPass)) {
                        System.out.println("Logged In");
                    } else {
                        System.out.println("Wrong Credentials");
                    }
                }

                if(userType == "receptionist") {
                    if (resultSet.getString("receptionist_pass").equals(userPass)) {
                        System.out.println("Logged In");
                    } else {
                        System.out.println("Wrong Credentials");
                    }
                }

                if(userType=="admin") {
                    if (resultSet.getString("admin_pass").equals(userPass)) {
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
