package database;

import com.zaxxer.hikari.HikariDataSource;
import database.interfaces.IDoctorDao;
import model.Patient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DoctorDao implements IDoctorDao {

    private String doctorId;
    private HikariDataSource dataSource;
    private Connection connection;


    public DoctorDao(String doctorId) {
        this.doctorId = doctorId;
        connection = DatabaseHandler.getConnection();

    }

    @Override
    public String getTotalAppointments() {
        String query = "select count(doctor_id) as 'count' from Appointment where doctor_id='" + doctorId + "'";
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

    @Override
    public String getName() {
        String query = "select doctor_name from Doctor where doctor_id='" + doctorId + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            return resultSet.getString("doctor_name");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public ArrayList<Patient> getRecentPatientList() {
        return null;
    }
}
