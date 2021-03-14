package database;

import database.interfaces.IDoctorQueue;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DoctorQueueDao implements IDoctorQueue {
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

}
