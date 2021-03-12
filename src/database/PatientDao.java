package database;

import database.interfaces.IPatientDao;
import model.Patient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PatientDao implements IPatientDao {


    private Connection connection;

    @Override
    public ArrayList<Patient> getPatientList() {
        return null;
    }

    @Override
    public String getName(String patientId) {
        String query = "select patient_name from Patient where patient_id='" + patientId + "'";
        connection = DatabaseHandler.getConnection();
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                resultSet.next();
                return resultSet.getString("patient_name");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}

