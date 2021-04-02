package database.interfaces;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

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
    void updateDoctorAttribute(String attribute, String data, int doctorId);
    ArrayList<Doctor> getDoctorList(String keyword);
    ArrayList<Appointment> getSchedule(String doctorId, LocalDate date);

    void updateSingleAttribute(String tableName,String attribute, String data, String patientId);
    void deleteTuple(String tableName, String attributeSelection,String id);
    ArrayList<Doctor> getDoctorBasicInfo(String name);
    ArrayList<MedicalTestDetails> getTestReport(String name);
    void insertSchedule(MyTime timeArrayList, int doctorId);
    void deleteChildForDoctor(String tableName, String attributeSelection,String id);
}
