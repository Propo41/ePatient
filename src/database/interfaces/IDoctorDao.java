package database.interfaces;

import model.Doctor;
import model.Patient;
import model.Schedule;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface IDoctorDao {

    String getTotalAppointments();
    String getName();
    ArrayList<Patient> getRecentPatientList();
    ArrayList<Schedule> getDoctorVisitingHours();
    String getTotalVisits();
    String getTotalBill();
    Patient getPatientProfile(String patientId);
    void updateDoctorAttribute(String attribute, String data, int doctorId);
    ArrayList<String> getDoctorInfo(int doctorId);
    Doctor getDoctorProfile(String doctorId);

}
