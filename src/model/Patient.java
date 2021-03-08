package model;

import java.util.Date;

public class Patient {
    private String name;
    private String id;
    private String mobile;
    private String email;
    private String address;
    private String gender;
    private String bloodGroup;
    private String age;
    private String height;
    private String weight;
    private Date date;


    public Patient(String name, String mobile,
                   String email, String address,
                   String gender, String bloodGroup,
                   String age, String height,
                   String weight) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public Patient(String name, Date date, String id) {
        this.name = name;
        this.date = date;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
