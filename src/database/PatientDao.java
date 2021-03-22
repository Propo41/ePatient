package database;

import database.interfaces.IPatientDao;
import model.Doctor;
import model.Patient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class PatientDao implements IPatientDao {

    private Connection connection;

    @Override
    public ArrayList<Patient> getPatientList() {
        return null;
    }

    @Override
    public String getName(String patientId) {
        String query = "select patient_name from Patient where patient_id='" + patientId + "'";
        connection = DatabaseHandler.getConnection();
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                resultSet.next();
                return resultSet.getString("patient_name");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public HashMap<String, Boolean> getMedicalHistory(String patientId) {
        connection = DatabaseHandler.getConnection();
        String query ="select * from MedicalHistory where patient_id = " + patientId;

        HashMap<String, Boolean> medicalHistory = new HashMap<>();
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {

                    medicalHistory.put("arthritis", resultSet.getBoolean("arthritis"));
                    medicalHistory.put("asthma", resultSet.getBoolean("ashtma"));
                    medicalHistory.put("cancer", resultSet.getBoolean("cancer"));
                    medicalHistory.put("diabetes", resultSet.getBoolean("diabetes"));
                    medicalHistory.put("hepatitis", resultSet.getBoolean("hepatitis"));
                    medicalHistory.put("high blood pressure", resultSet.getBoolean("high_blood_pressure"));
                    medicalHistory.put("high cholesterol", resultSet.getBoolean("high_cholesterol"));
                    medicalHistory.put("hiv", resultSet.getBoolean("hiv"));
                    medicalHistory.put("kidney disease", resultSet.getBoolean("kidney_disease"));
                    medicalHistory.put("lung disease", resultSet.getBoolean("lung_disease"));
                    medicalHistory.put("pneumonia", resultSet.getBoolean("pneumonia"));
                    medicalHistory.put("sinus", resultSet.getBoolean("sinus"));
                    medicalHistory.put("stroke", resultSet.getBoolean("stroke"));
                    medicalHistory.put("thyroid problems", resultSet.getBoolean("thyroid_problems"));
                    medicalHistory.put("tonsilities", resultSet.getBoolean("tonsilities"));
                    medicalHistory.put("tuberculosis", resultSet.getBoolean("tuberculosis"));

                }

                return medicalHistory;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    @Override
    public Patient getPatientProfile(String patientId) {
        connection = DatabaseHandler.getConnection();
        String query = "select * " +
                "from (Patient left join MedicalHistory " +
                "on Patient.patient_id = MedicalHistory.patient_id) " +
                "left join SurgicalHistory " +
                "on SurgicalHistory.patient_id = Patient.patient_id " +
                "left join SocialHistory " +
                "on SocialHistory.patient_id = Patient.patient_id " +
                "where Patient.patient_id = " + patientId;

        Patient patient = new Patient();
        HashMap<String, Boolean> medicalHistory = new HashMap<>();
        HashMap<String, Boolean> socialHistory = new HashMap<>();
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    patient.setName(resultSet.getString("patient_name"));
                    patient.setId(resultSet.getString("patient_id"));
                    patient.setEmail(resultSet.getString("patient_email"));
                    patient.setContact(resultSet.getString("patient_contact"));
                    patient.setAddress(resultSet.getString("patient_address"));
                    patient.setGender(resultSet.getString("patient_gender"));
                    patient.setBloodGroup(resultSet.getString("patient_blood_group"));
                    patient.setAge(resultSet.getString("patient_age"));
                    patient.setHeight(resultSet.getString("patient_height"));
                    patient.setWeight(resultSet.getString("patient_weight"));
                    patient.setBirthDate(resultSet.getDate("patient_dob"));
                    patient.setJoinedDate(resultSet.getDate("joined_date"));
                    patient.setEmergencyContact(resultSet.getString("patient_emergency_contact"));
                    patient.setSurgicalHistory(resultSet.getString("descrption"));

                    medicalHistory.put("arthritis", resultSet.getBoolean("arthritis"));
                    medicalHistory.put("asthma", resultSet.getBoolean("ashtma"));
                    medicalHistory.put("cancer", resultSet.getBoolean("cancer"));
                    medicalHistory.put("diabetes", resultSet.getBoolean("diabetes"));
                    medicalHistory.put("hepatitis", resultSet.getBoolean("hepatitis"));
                    medicalHistory.put("high blood pressure", resultSet.getBoolean("high_blood_pressure"));
                    medicalHistory.put("high cholesterol", resultSet.getBoolean("high_cholesterol"));
                    medicalHistory.put("hiv", resultSet.getBoolean("hiv"));
                    medicalHistory.put("kidney disease", resultSet.getBoolean("kidney_disease"));
                    medicalHistory.put("lung disease", resultSet.getBoolean("lung_disease"));
                    medicalHistory.put("pneumonia", resultSet.getBoolean("pneumonia"));
                    medicalHistory.put("sinus", resultSet.getBoolean("sinus"));
                    medicalHistory.put("stroke", resultSet.getBoolean("stroke"));
                    medicalHistory.put("thyroid problems", resultSet.getBoolean("thyroid_problems"));
                    medicalHistory.put("tonsilities", resultSet.getBoolean("tonsilities"));
                    medicalHistory.put("tuberculosis", resultSet.getBoolean("tuberculosis"));

                    socialHistory.put("alcohol use", resultSet.getBoolean("alcohol_use"));
                    socialHistory.put("caffeine use", resultSet.getBoolean("caffeine_use"));
                    socialHistory.put("drugs use", resultSet.getBoolean("drugs_use"));
                    socialHistory.put("exercise", resultSet.getBoolean("excercise"));
                    socialHistory.put("tobacco use", resultSet.getBoolean("tobacco_use"));

                    patient.setMedicalHistory(medicalHistory);
                    patient.setSocialHistory(socialHistory);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return patient;
    }


    public ArrayList<Patient> getPatientBasicInfo(String name) {
        String query = "select patient_name,joined_date,patient_id from Patient where patient_name like '%"+ name + "%' ";
        connection = DatabaseHandler.getConnection();
        if (connection != null){
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                ArrayList<Patient> patientArrayList = new ArrayList<>();

                while (resultSet.next()) {
                    Patient patient = new Patient();
                    patient.setName(resultSet.getString("patient_name"));
                    patient.setJoinedDate(resultSet.getDate("joined_date"));
                    patient.setId(resultSet.getString("patient_id"));
                    patientArrayList.add(patient);
                }

                return patientArrayList;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    public void deleteTuple(String tableName, String attributeSelection,String id) {
        connection = DatabaseHandler.getConnection();
        String query = "delete from "  + tableName + " where " +  attributeSelection +  " = " +  id;
        if (connection != null) {
            try{
                Statement statement = connection.createStatement();
                statement.execute(query);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }

    @Override
    public void addPatient(Patient patient, String joiningDate, String dateOfBirth) {

        String query ="INSERT INTO Patient (patient_name, patient_email, patient_contact, patient_address, " +
                "patient_gender, patient_blood_group, patient_age, patient_height, patient_weight, joined_date, " +
                "patient_emergency_contact, patient_dob) VALUES ( '" + patient.getName() + "','" + patient.getEmail()
                +"','"+ patient.getContact() +"','"+ patient.getAddress() + "','" + patient.getGender() + "','" +
                patient.getBloodGroup() + "','" + patient.getAge() + "','" + patient.getHeight() + "','" +
                patient.getWeight() + "','" + "2008-11-11" + "','" + patient.getEmergencyContact()
                + "','" + " 2008-11-11 " + "')";


        connection = DatabaseHandler.getConnection();
        if (connection != null) {
            try{
                Statement statement = connection.createStatement();
                statement.execute(query);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return;

    }

    @Override
    public int addMedicalHistory( HashMap<String, Integer> hashMap) {
        String val="";

        String query1 = " select TOP 1 patient_id from Patient order by patient_id desc";
        connection = DatabaseHandler.getConnection();
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query1)) {
                resultSet.next();
                val=  resultSet.getString("patient_id");
                insertMedicalHistory(hashMap, Integer.parseInt(val));
                return Integer.parseInt(val);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return 0;

    }

    private void insertMedicalHistory(HashMap<String, Integer> hashMap, int value) {

        String query = "INSERT INTO MedicalHistory (patient_id, high_blood_pressure, high_cholesterol, kidney_disease, thyroid_problems, tuberculosis, sinus, tonsilities, lung_disease, ashtma, seasonal_allergies , arthritis, cancer,stroke, diabetes, pneumonia, hiv,hepatitis) " +
                " VALUES (" + value + "," +
                hashMap.get("high_blood_pressure").toString()
                + "," +  hashMap.get("high_cholesterol").toString()
                + "," + hashMap.get("kidney_disease").toString()
                + "," + hashMap.get("thyroid_problems").toString()
                + "," + hashMap.get("tuberculosis").toString()
                + "," + hashMap.get("sinus").toString()
                + "," + hashMap.get("tonsilities").toString()
                + "," + hashMap.get("lung_disease").toString()
                + "," + hashMap.get("ashtma").toString()
                // + "," + hashMap.get("seasonal_allergies").toString()
                + "," + "0"
                + "," + hashMap.get("arthritis").toString()
                + "," + hashMap.get("cancer").toString()
                + "," + hashMap.get("stroke").toString()
                + "," + hashMap.get("diabetes").toString()
                + "," + hashMap.get("pneumonia").toString()
                + "," + hashMap.get("hiv").toString()
                + "," + hashMap.get("hepatitis").toString()
                + ")";


        connection = DatabaseHandler.getConnection();
        if (connection != null) {
            try{
                Statement statement = connection.createStatement();
                statement.execute(query);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }


}

