package database;

import com.zaxxer.hikari.HikariDataSource;
import database.interfaces.IDoctorDao;
import model.Patient;
import model.Schedule;
import util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
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

    @Override
    public ArrayList<Schedule> getDoctorVisitingHours() {
        ArrayList<Schedule> visitingHours = new ArrayList<>();
        String query = "select * from Schedule where doctor_id='" + doctorId + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String startTime = Util.convert24to12format(resultSet.getString("doctor_schedule_start_time"));
                String endTime = Util.convert24to12format(resultSet.getString("doctor_schedule_end_time"));

                String day = resultSet.getString("schedule_day").toUpperCase();
                visitingHours.add(new Schedule(
                        day,
                        startTime,
                        endTime));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return visitingHours;
    }

    @Override
    public String getTotalVisits() {
        String query = "select count(doctor_id) as 'visits' from Appointment where doctor_id='" + 1 + "' and appointment_status='1'";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            return resultSet.getString("visits");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
