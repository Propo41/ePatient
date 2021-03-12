package database.interfaces;

import model.Patient;

import java.util.ArrayList;

public interface IPatientDao {
    ArrayList<Patient> getPatientList();
    String getName(String patientId);
}
