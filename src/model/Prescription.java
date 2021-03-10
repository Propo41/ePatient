package model;

public class Prescription {
    private String patientId;
    private String doctorId;
    private String doctorName;
    private String reason;
    private String dateOfPrescription;
    private String prescriptionId;


    public Prescription() {
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
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

    public String getDateOfPrescription() {
        return dateOfPrescription;
    }

    public void setDateOfPrescription(String dateOfPrescription) {
        this.dateOfPrescription = dateOfPrescription;
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }
}
