package database.interfaces;

import model.Doctor;
import model.Patient;

import java.util.ArrayList;
import java.util.HashMap;

public interface IPatientDao {
    ArrayList<Patient> getPatientList();
    String getName(String patientId);
    HashMap<String, Boolean> getMedicalHistory(String patientId);
    Patient getPatientProfile(String patientId);
    ArrayList<Patient> getPatientBasicInfo(String name);
    void deleteTuple(String doctor, String doctor_id, String s);
    void addPatient(Patient patient, String joiningDate, String dateOfBirth);
    int addMedicalHistory(HashMap<String, Integer> hashMap);
    void deleteChildForPatient(String tableName, String attributeSelection,String id);
}
