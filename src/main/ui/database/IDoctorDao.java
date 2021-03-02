package main.ui.database;

import main.ui.model.Patient;

import java.util.ArrayList;

public interface IDoctorDao {

    String getTotalAppointments();
    String getName();
    ArrayList<Patient> getRecentPatientList();
}
