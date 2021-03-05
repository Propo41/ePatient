package main.ui.database;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.ui.login.LogInController;
import util.Util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectMSSQL {

    private String userID;
    private String password;
    private String userType;
    private Connection connection;

    public ConnectMSSQL(String userID, String password, String userType) {
        this.userID = userID.toLowerCase();
        this.password = password.toLowerCase();
        this.userType = userType.toLowerCase();
        connectDB();
    }

    public ConnectMSSQL(){
        connectDB();
    }


    private void connectDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=testdatabase;selectedMethod=cursor",
                    "sa",
                    "123456");
            System.out.println("DB NAME IS: " + connection.getMetaData().getDatabaseProductName());
            Util util = Util.getInstance();
            util.setConnectMSSQL(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean insertDoctorProfile(String name, String email, String mobileNumber, String doctorAddresss,
                                String department, String speciality, String languages, String hospitalAffiliations,
                                String professionalExperience, String roomNumber,
                                String educationalBackground, String password){

        String query = "INSERT INTO Doctor (doctor_name, doctor_email, doctor_mobile, doctor_address, department, doctor_specialist, " +
                "languages, hospital_affiliations, professional_experience, room_number, educaional_background, doctor_password) " +
                "VALUES ('" +  name + "', '" + email + "','" + mobileNumber + "','" + doctorAddresss + "','" + languages +
        "','" + department + "','" + speciality + "','" + hospitalAffiliations + "','" + professionalExperience +"','" +
               Integer.parseInt(roomNumber) + "','" + educationalBackground + "','" + password +"')";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public int getProfileId(){
        String val="";
        try {
            String query = " select TOP 1 doctor_id from Doctor order by doctor_id desc";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                val = resultSet.getString("doctor_id");
            }
        }catch (Exception ex){

        }

        return Integer.parseInt(val);
    }



    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean logInForm() {
        try {
            String query = "";
            switch (userType) {
                case "doctor":
                    query = "select doctor_name, doctor_password from Doctor where doctor_id=" + userID;
                    break;
                case "receptionist":
                    query = "select receptionist_name, receptionist_pass from Receptionist where receptionist_id=" + userID;
                    break;
                case "admin":
                    query = "select admin_name, admin_pass from admin where admin_id=" + userID;
                    break;
            }

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                switch (userType) {
                    case "doctor":
                        if (resultSet.getString("doctor_password").equals(password)) {
                            System.out.println("Logged In");
                        } else {
                            System.out.println("Wrong Credentials");
                            return false;
                        }
                        break;
                    case "receptionist":
                        if (resultSet.getString("receptionist_pass").equals(password)) {
                            System.out.println("Logged In");
                        } else {
                            System.out.println("Wrong Credentials");
                            return false;
                        }
                        break;
                    case "admin":
                        if (resultSet.getString("admin_pass").equals(password)) {
                            System.out.println("Logged In");
                        } else {
                            System.out.println("Wrong Credentials");
                            return false;
                        }
                        break;
                }

            }

            return true;


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public String getTotalAppointments() {
        String query = "select count(doctor_id) as 'count' from Appointment where doctor_id='" + userID + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("count"));
                return resultSet.getString("count");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "0";
    }


}
