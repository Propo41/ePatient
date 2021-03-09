package database;

import database.interfaces.IDoctorDao;
import model.Patient;
import model.Schedule;
import util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDao implements IDoctorDao {

    private String doctorId;
    private Connection connection;
    private int totalVisits;


    public DoctorDao() {
    }

    public DoctorDao(String doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String getTotalAppointments() {
        connection = DatabaseHandler.getConnection();
        String query = "select count(doctor_id) as 'count' from Appointment where doctor_id='" + doctorId + "'";

        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                resultSet.next();
                totalVisits = Integer.parseInt(resultSet.getString("count"));
                return resultSet.getString("count");

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

        return "0";


    }

    @Override
    public String getName() {
        String query = "select doctor_name from Doctor where doctor_id='" + doctorId + "'";
        connection = DatabaseHandler.getConnection();
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                resultSet.next();
                return resultSet.getString("doctor_name");
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
    public ArrayList<Patient> getRecentPatientList() {
        connection = DatabaseHandler.getConnection();
        ArrayList<Patient> patientList = new ArrayList<>();
        String query = "" +
                "select top 5 Patient.patient_name, Appointment.date_of_appointment, Patient.patient_id " +
                "from Appointment left join Patient " +
                "on Appointment.patient_id = Patient.patient_id " +
                "where doctor_id = " + doctorId + " and Appointment.appointment_status = 1 " +
                "ORDER BY Appointment.date_of_appointment DESC";

        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    Patient patient = new Patient(
                            resultSet.getString("patient_name"),
                            resultSet.getDate("date_of_appointment"),
                            resultSet.getString("patient_id"));
                    patientList.add(patient);
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

        return patientList;
    }

    @Override
    public ArrayList<Schedule> getDoctorVisitingHours() {
        connection = DatabaseHandler.getConnection();

        ArrayList<Schedule> visitingHours = new ArrayList<>();
        String query = "select * from Schedule where doctor_id='" + doctorId + "'";
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    String startTime = Util.convert24to12format(resultSet.getString("doctor_schedule_start_time"));
                    String endTime = Util.convert24to12format(resultSet.getString("doctor_schedule_end_time"));

                    String day = resultSet.getString("schedule_day").toUpperCase();
                    visitingHours.add(new Schedule(
                            day,
                            startTime,
                            endTime));
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

        return visitingHours;
    }

    @Override
    public String getTotalVisits() {
        connection = DatabaseHandler.getConnection();
        String query = "select count(doctor_id) as 'visits' from Appointment where doctor_id='" + 1 + "' and Appointment.appointment_status='1'";
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                resultSet.next();
                totalVisits = Integer.parseInt(resultSet.getString("visits"));
                return resultSet.getString("visits");

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
    public String getTotalBill() {
        connection = DatabaseHandler.getConnection();
        String query = "select Doctor.visit_fee from Doctor where doctor_id = " + doctorId;
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                resultSet.next();
                return String.valueOf(Integer.parseInt(resultSet.getString("visit_fee")) * totalVisits);
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
                "inner join SurgicalHistory " +
                "on SurgicalHistory.patient_id = Patient.patient_id " +
                "inner join SocialHistory " +
                "on SocialHistory.patient_id = Patient.patient_id " +
                "where Patient.patient_id = " + patientId;
        Patient patient = new Patient();
        HashMap<String, Boolean> medicalHistory  = new HashMap<>();
        HashMap<String, Boolean> socialHistory  = new HashMap<>();
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                while(resultSet.next()){
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
                    patient.setSurgicalHistory(resultSet.getString("description"));

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
}
