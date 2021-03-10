package model;

import java.util.Date;
import java.util.HashMap;

public class Patient {
    private String name;
    private String id;
    private String contact;
    private String emergencyContact;
    private String email;
    private String address;
    private String gender;
    private String bloodGroup;
    private String age;
    private String height;
    private String weight;
    private Date joinedDate;
    private Date birthDate;
    private HashMap<String, Boolean> medicalHistory;
    private HashMap<String, Boolean> socialHistory;
    private String surgicalHistory;


    public Patient(String name, String contact,
                   String email, String address,
                   String gender, String bloodGroup,
                   String age, String height,
                   String weight) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public Patient() {
    }

    public Patient(String name, Date joinedDate, String id) {
        this.name = name;
        this.joinedDate = joinedDate;
        this.id = id;
    }


    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public HashMap<String, Boolean> getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(HashMap<String, Boolean> medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public HashMap<String, Boolean> getSocialHistory() {
        return socialHistory;
    }

    public void setSocialHistory(HashMap<String, Boolean> socialHistory) {
        this.socialHistory = socialHistory;
    }

    public String getSurgicalHistory() {
        return surgicalHistory;
    }

    public void setSurgicalHistory(String surgicalHistory) {
        this.surgicalHistory = surgicalHistory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
