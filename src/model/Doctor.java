package model;

import java.sql.Date;
import java.util.ArrayList;

public class Doctor {
    private String name;
    private String doctorId;
    private String email;
    private String phone;
    private String address;
    private String department;
    private String specialist;
    private String affiliations;
    private String educationalBackground;
    private String roomNumber;
    private String professionalExperience;
    private String visitFee;
    private Date joinedDate;
    private ArrayList<Schedule> visitingHours;


    public Doctor() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getAffiliations() {
        return affiliations;
    }

    public void setAffiliations(String affiliations) {
        this.affiliations = affiliations;
    }

    public String getEducationalBackground() {
        return educationalBackground;
    }

    public void setEducationalBackground(String educationalBackground) {
        this.educationalBackground = educationalBackground;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getProfessionalExperience() {
        return professionalExperience;
    }

    public void setProfessionalExperience(String professionalExperience) {
        this.professionalExperience = professionalExperience;
    }

    public String getVisitFee() {
        return visitFee;
    }

    public void setVisitFee(String visitFee) {
        this.visitFee = visitFee;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }
}
