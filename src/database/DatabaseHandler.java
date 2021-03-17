package database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import model.Doctor;
import util.Util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DatabaseHandler {

    private Connection connection;
    private static final HikariDataSource dataSource;
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ePatient;selectedMethod=cursor";

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername("sa");
        config.setPassword("123456");
        config.addDataSourceProperty("minimumIdle", "5");
        config.addDataSourceProperty("maximumPoolSize", "25");
        dataSource = new HikariDataSource(config);
    }


    public static Connection getConnection()   {
        try {
            return dataSource.getConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
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
            connection = dataSource.getConnection();
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


    public ResultSet getPatientMainAdmin(String name){
        String query = "select patient_name,joined_date,patient_id from Patient where patient_name like '%"+ name + "%' ";
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }



    public boolean logInForm(String userID, String password, String userType) {
        if (connection == null) {
            try {
                connection = dataSource.getConnection();
                System.out.println("Connected DB NAME IS: " + connection.getMetaData().getDatabaseProductName());
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

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
            Util util = Util.getInstance();
            util.setUserId(userID);

            return true;


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
