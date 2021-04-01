package database;

import database.interfaces.IDoctorQueueDao;
import javafx.util.Pair;
import model.Appointment;

import java.sql.*;
import java.util.ArrayList;

public class DoctorQueueDao implements IDoctorQueueDao {
    private Connection connection;

    @Override
    public Pair<String, String> fetchQueue(String doctorId) {
        String query = "select patient_id, appointment_id from DoctorQueue where doctor_id='" + doctorId + "'";
        connection = DatabaseHandler.getConnection();
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next() && !resultSet.getString("patient_id").equals("-1")
                        && !resultSet.getString("appointment_id").equals("-1")) {
                    return new Pair<>(
                            resultSet.getString("patient_id"),
                            resultSet.getString("appointment_id"));
                }

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

    @Override
    public void insertIntoQueue(String doctorId, String patientId, String appointmentId) {
        connection = DatabaseHandler.getConnection();
        String query = "if exists(SELECT * from DoctorQueue where doctor_id= " + doctorId + ")" +
                "begin " +
                "UPDATE DoctorQueue set patient_id= " + patientId + " where doctor_id= " + doctorId + " " +
                "end " +
                "else " +
                "begin " +
                "insert into DoctorQueue(doctor_id, patient_id, appointment_id) " +
                "values(" + doctorId + "," + patientId + "," + appointmentId + ") " +
                "end ";

        if (connection != null) {
            try (PreparedStatement preparedStmt = connection.prepareStatement(query)) {
                preparedStmt.execute();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
