package database.interfaces;

import model.Patient;
import model.Schedule;

import java.util.ArrayList;

public interface IDoctorDao {

    String getTotalAppointments();
    String getName();
    ArrayList<Patient> getRecentPatientList();
    ArrayList<Schedule> getDoctorVisitingHours();
    String getTotalVisits();

}
