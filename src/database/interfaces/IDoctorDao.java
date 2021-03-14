package database.interfaces;

import model.*;

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
    /**
     * uses keyword to find prescription history of patient.
     * @param keyword doctor name or Id
     * @param patientId patient's id
     * @return a list of objects that include <String> and <Prescription>.
    <String> is used for header whereas <Prescription>
     */
    ArrayList<Object> getPrescriptionHistory(String patientId, String keyword);

    String createNewPrescription(Prescription prescription);

}
