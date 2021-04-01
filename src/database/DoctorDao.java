package database;

import com.zaxxer.hikari.HikariDataSource;
import database.interfaces.IDoctorDao;
import javafx.collections.ObservableList;
import model.*;
import util.Util;

import java.sql.*;
import java.util.ArrayList;

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
    public String getTotalAppointments(String doctorId) {
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


    public ArrayList<Doctor> getDoctorBasicInfo(String name) {

        String query = "select doctor_specialist,doctor_name,doctor_id from Doctor where doctor_name like '%"+ name + "%' ";
        connection = DatabaseHandler.getConnection();
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                ArrayList<Doctor> doctorArrayList = new ArrayList<>();
                int index = 0;
                while (resultSet.next()) {
                    Doctor doctor = new Doctor();
                    doctor.setSpecialist(resultSet.getString("doctor_specialist"));
                    doctor.setName(resultSet.getString("doctor_name"));
                    doctor.setDoctorId(resultSet.getString("doctor_id"));
                    doctorArrayList.add(doctor);
                    index++;
                }
                return doctorArrayList;
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
    public ArrayList<MedicalTestDetails> getTestReport(String name) {
        String query = "select T.test_name, Pat.patient_name, D.doctor_name,A.date_of_appointment \n" +
                "from Test as T inner join Prescription as P\n" +
                "on T.prescription_id = P.prescription_id inner join Appointment as A\n" +
                "on A.appointment_id = P.appointment_id inner join Patient as Pat \n" +
                "on A.patient_id = Pat.patient_id inner join Doctor as D\n" +
                "on A.doctor_id = D.doctor_id where (Pat.patient_name like '%" +name + "%') " +
                "or (T.test_id like '%"+name+"%')";

        connection = DatabaseHandler.getConnection();
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                ArrayList<MedicalTestDetails> medicalArrayList = new ArrayList<>();
                int index = 0;
                while (resultSet.next()){
                    MedicalTestDetails medicalTestDetails = new MedicalTestDetails();
                    medicalTestDetails.setTestDate(resultSet.getString("date_of_appointment"));
                    medicalTestDetails.setTestName(resultSet.getString("test_name"));
                    medicalTestDetails.setDoctorName(resultSet.getString("doctor_name"));
                    medicalTestDetails.setPatientName(resultSet.getString("patient_name"));
                    medicalArrayList.add(medicalTestDetails);
                    index++;
                }
                return medicalArrayList;
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
    public String getName(String doctorId) {
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
    public ArrayList<Patient> getRecentPatientList(String doctorId) {
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
    public ArrayList<Schedule> getDoctorVisitingHours(String doctorId) {
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
    public Doctor getDoctorProfile(String doctorId) {
        connection = DatabaseHandler.getConnection();
        String query = "select * from Doctor where doctor_id=" + doctorId;
        Doctor doctor = new Doctor();

        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                resultSet.next();
                doctor = new Doctor();
                doctor.setDoctorId(resultSet.getString("doctor_id"));
                doctor.setName(resultSet.getString("doctor_name"));
                doctor.setPhone(resultSet.getString("doctor_phone"));
                doctor.setAddress(resultSet.getString("doctor_address"));
                doctor.setEmail(resultSet.getString("doctor_email"));
                doctor.setDepartment(resultSet.getString("department"));
                doctor.setSpecialist(resultSet.getString("doctor_specialist"));
                doctor.setAffiliations(resultSet.getString("hospital_affiliations"));
                doctor.setProfessionalExperience(resultSet.getString("professional_experience"));
                doctor.setEducationalBackground(resultSet.getString("educaional_background"));
                doctor.setVisitFee(resultSet.getString("visit_fee"));
                doctor.setJoinedDate(resultSet.getDate("joined_date"));
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

        return doctor;
    }

    @Override
    public String getTotalVisits(String doctorId) {
        connection = DatabaseHandler.getConnection();
        String query = "select count(doctor_id) as 'visits' from Appointment " +
                "where doctor_id='" + doctorId + "' and Appointment.appointment_status='1'";
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
    public String getTotalBill(String doctorId) {
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
    public ArrayList<Patient> getPatientList(String keyword) {
        connection = DatabaseHandler.getConnection();
        String query = "select * from Patient " +
                "where " +
                "(patient_name LIKE '%" + keyword + "%'" +
                " OR patient_id LIKE '% " + keyword + "%')";
        ArrayList<Patient> patientList = new ArrayList<>();

        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    Patient patient = new Patient();
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
    public void updateDoctorAttribute(String attribute, String data, int doctorId) {
        connection = DatabaseHandler.getConnection();
        String query = "update Doctor Set " + attribute + " = '" + data + "' where doctor_id= " + doctorId;
        System.out.println(query);
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
    public void updateSingleAttribute(String tableName,String attribute, String data, String patientId){
        connection = DatabaseHandler.getConnection();
        String query = "update "+  tableName + " Set " + attribute + " = '" + data + "' where patient_id = " + patientId;
        System.out.println(query);
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
