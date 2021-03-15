package database.interfaces;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IDoctorDao {

    String getTotalAppointments(String doctorId);
    String getName(String doctorId);
    ArrayList<Patient> getRecentPatientList(String doctorId);
    ArrayList<Schedule> getDoctorVisitingHours(String doctorId);
    Doctor getDoctorProfile(String doctorId);
    String getTotalVisits(String doctorId);
    String getTotalBill(String doctorId);
    Patient getPatientProfile(String patientId);
    ArrayList<Patient> getPatientList(String keyword);
    ArrayList<Appointment> getAppointmentList(String doctorId, LocalDate date);

    void updateDoctorAttribute(String attribute, String data, int doctorId);

}
