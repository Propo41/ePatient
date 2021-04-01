package model;

import javafx.beans.property.SimpleStringProperty;

public class MedicalTestDetails {
    private String testName;
    private String testDescription;
    private String testDate;
    private String testReport;
    private String doctorName;
    private String patientName;

    public MedicalTestDetails(){

    }

    public MedicalTestDetails(String testName, String testDescription, String testDate, String testReport) {
        this.testName = testName;
        this.testDescription = testDescription;
        this.testDate = testDate;
        this.testReport = testReport;
    }

    public MedicalTestDetails(String testName, String testDescription) {
        this.testName = testName;
        this.testDescription = testDescription;
    }

    public String getDoctorName(){
        return this.doctorName;
    }

    public String getPatientName(){
        return this.patientName;
    }

    public void setDoctorName(String doctorName){
        this.doctorName = doctorName;
    }

    public void setPatientName(String patientName){
        this.patientName = patientName;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public String getTestName() {
        return testName;
    }

    public String testNameProperty() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

}
