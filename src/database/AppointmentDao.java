package database;

import database.interfaces.IAppointmentDao;
import model.Appointment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class AppointmentDao implements IAppointmentDao {
    private Connection connection;

    @Override
    public ArrayList<Appointment> getAppointmentInfo(LocalDate date) {
        // by default it orders in ascending order
        connection = DatabaseHandler.getConnection();
        String query = "select * " +
                "from (Appointment left join Doctor " +
                "    on Appointment.doctor_id = Doctor.doctor_id) " +
                "    left join Patient on Appointment.patient_id = Patient.patient_id " +
                "where appointment_status = 0 " +
                "  and date_of_appointment = '" + date + "' " +
                "ORDER BY Appointment.start_time";
        ArrayList<Appointment> appointmentsList = new ArrayList<>();
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    Appointment appointment = new Appointment();
                    appointment.setPatientId(resultSet.getString("patient_id"));
                    appointment.setDoctorId(resultSet.getString("doctor_id"));
                    appointment.setAppointmentId(resultSet.getString("appointment_id"));
                    appointment.setDoctorName(resultSet.getString("doctor_name"));
                    appointment.setPatientName(resultSet.getString("patient_name"));
                    appointment.setReason(resultSet.getString("reason"));
                    appointment.setDate(resultSet.getDate("date_of_appointment").toLocalDate());
                    appointment.setStartTime(resultSet.getTime("start_time"));
                    appointment.setEndTime(resultSet.getTime("end_time"));
                    appointmentsList.add(appointment);
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
        return appointmentsList;
    }

    @Override
    public ArrayList<Appointment> getAppointmentInfo(String doctorId, LocalDate date) {
        connection = DatabaseHandler.getConnection();
        String query = "select Patient.patient_name, Appointment.reason, Appointment.date_of_appointment, " +
                "Appointment.start_time, Appointment.end_time " +
                "from Appointment left join Patient " +
                "on Appointment.patient_id = Patient.patient_id " +
                "where date_of_appointment = '" + date + "' and doctor_id = " + doctorId + " and appointment_status = 0";
        ArrayList<Appointment> appointmentsList = new ArrayList<>();
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    Appointment appointment = new Appointment();
                    appointment.setPatientName(resultSet.getString("patient_name"));
                    appointment.setReason(resultSet.getString("reason"));
                    appointment.setDate(resultSet.getDate("date_of_appointment").toLocalDate());
                    appointment.setStartTime(resultSet.getTime("start_time"));
                    appointment.setEndTime(resultSet.getTime("end_time"));
                    appointmentsList.add(appointment);
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
        return appointmentsList;

    }

    @Override
    public ArrayList<Appointment> getAppointmentInfoByKeyword(String keyword, LocalDate date) {
        String query = "select * " +
                "from (Appointment left join Doctor " +
                "    on Appointment.doctor_id = Doctor.doctor_id) " +
                "         left join Patient " +
                "                   on Appointment.patient_id = Patient.patient_id " +
                "where ( " +
                "    appointment_status = 0 and date_of_appointment = '" + date + "') " +
                "  and (Patient.patient_id like '%" + keyword + "%' OR patient_name like '%" + keyword + "%' " +
                "    ) " +
                "ORDER BY Appointment.date_of_appointment DESC";

        connection = DatabaseHandler.getConnection();
        ArrayList<Appointment> appointmentsList = new ArrayList<>();
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    Appointment appointment = new Appointment();
                    appointment.setPatientId(resultSet.getString("patient_id"));
                    appointment.setDoctorId(resultSet.getString("doctor_id"));
                    appointment.setAppointmentId(resultSet.getString("appointment_id"));
                    appointment.setDoctorName(resultSet.getString("doctor_name"));
                    appointment.setPatientName(resultSet.getString("patient_name"));
                    appointment.setReason(resultSet.getString("reason"));
                    appointment.setDate(resultSet.getDate("date_of_appointment").toLocalDate());
                    appointment.setStartTime(resultSet.getTime("start_time"));
                    appointment.setEndTime(resultSet.getTime("end_time"));
                    appointmentsList.add(appointment);
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
        return appointmentsList;
    }
}
