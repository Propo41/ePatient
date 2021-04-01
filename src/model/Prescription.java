package model;

import javafx.collections.ObservableList;

import java.sql.Date;
import java.util.ArrayList;

public class Prescription {
    private String patientId;
    private String patientName;
    private String doctorId;
    private String doctorName;
    private String appointmentId;
    private String reason;
    private String comment;
    private Date dateOfPrescription;
    private String prescriptionId;
    private ObservableList<MedicalTest> medicalTests;
    private ObservableList<Disease> diseases;
    private ObservableList<Medicine> medicines;
    private HealthCondition healthCondition;

    public Prescription() {
    }

    public Prescription(String patientId, String doctorId, String doctorName,
                        String reason, Date dateOfPrescription, String prescriptionId) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.reason = reason;
        this.dateOfPrescription = dateOfPrescription;
        this.prescriptionId = prescriptionId;
    }

    public String getPatientName(){
        return this.patientName;
    }

    public void setPatientName(String patientName){
        this.patientName = patientName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getAppointmentId() {
        return Integer.parseInt(appointmentId);
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public HealthCondition getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(HealthCondition healthCondition) {
        this.healthCondition = healthCondition;
    }

    public ObservableList<MedicalTest> getMedicalTests() {
        return medicalTests;
    }

    public void setMedicalTests(ObservableList<MedicalTest> medicalTests) {
        this.medicalTests = medicalTests;
    }

    public ObservableList<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(ObservableList<Disease> diseases) {
        this.diseases = diseases;
    }

    public ObservableList<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(ObservableList<Medicine> medicines) {
        this.medicines = medicines;
    }

    public int getPatientId() {
        return Integer.parseInt(patientId);
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return Integer.parseInt(doctorId);
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getDateOfPrescription() {
        return dateOfPrescription;
    }

    public void setDateOfPrescription(Date dateOfPrescription) {
        this.dateOfPrescription = dateOfPrescription;
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }
}
