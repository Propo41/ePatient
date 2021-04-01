package model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class Appointment {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private String patientName;
    private String reason;
    private LocalDate date;
    private Time startTime;
    private Time endTime;
    private String doctorName;

    public Appointment() {
    }

    public Appointment(String patientId, String doctorId,
                       Time startTime, Time endTime,
                       String reason, LocalDate date) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
        this.date = date;

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

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
