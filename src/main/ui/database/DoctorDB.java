package main.ui.database;

import util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DoctorDB {
    Connection connection;
    String doctorId;

    public DoctorDB(String doctorId) {
        this.doctorId = doctorId;
        connection = Util.getInstance().getConnection();

    }

  /*  public String getTotalAppointments() {
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
    }*/

    public ArrayList<String> getVisitingHours() {
        return new ArrayList<String>();
    }
}
