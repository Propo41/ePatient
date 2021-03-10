package database.interfaces;

import model.Appointment;
import model.Doctor;
import model.Patient;
import model.Schedule;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IDoctorDao {

    String getTotalAppointments(String doctorId);
    String getName(String doctorId);
    ArrayList<Patient> getRecentPatientList(String doctorId);
    ArrayList<Schedule> getDoctorVisitingHours(String doctorId);
    Doctor getDoctorProfile( String doctorId);
    String getTotalVisits(String doctorId);
    String getTotalBill(String doctorId);
    Patient getPatientProfile(String patientId);
    ArrayList<Patient> getPatientList(String keyword);
    ArrayList<Appointment> getAppointmentList(String doctorId, LocalDate date);
    ArrayList<Object> getPrescriptionHistory(String patientId);
}
