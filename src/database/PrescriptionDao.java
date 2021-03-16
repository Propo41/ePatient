package database;

import database.interfaces.IPrescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PrescriptionDao implements IPrescription {
    private Connection connection;


    @Override
    public Prescription getPrescriptionDetails(String prescriptionId) {
        String query = "select * " +
                "from (Prescription left join Recommendations " +
                "on Prescription.prescription_id = Recommendations.prescription_id) " +
                "left join Appointment " +
                "on Prescription.appointment_id = Appointment.appointment_id " +
                "where Prescription.prescription_id = " + prescriptionId;
        connection = DatabaseHandler.getConnection();
        Prescription prescription = new Prescription();
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    prescription.setPrescriptionId(prescriptionId);
                    prescription.setDoctorId(resultSet.getString("doctor_id"));
                    prescription.setPatientId(resultSet.getString("patient_id"));
                    prescription.setAppointmentId(resultSet.getString("appointment_id"));
                    prescription.setDateOfPrescription(resultSet.getDate("date_of_appointment"));

                    HealthCondition healthCondition = new HealthCondition(
                            resultSet.getString("food_chart"),
                            resultSet.getString("exercise_routine"),
                            resultSet.getString("patient_health_condition"),
                            resultSet.getString("health_comment"));
                    prescription.setHealthCondition(healthCondition);
                    prescription.setDiseases(getDiseasesInfo(prescriptionId));
                    prescription.setMedicalTests(getMedicalTestsInfo(prescriptionId));
                    prescription.setMedicines(getMedicineInfo(prescriptionId));
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
        return prescription;
    }

    @Override
    public ObservableList<Disease> getDiseasesInfo(String prescriptionId) {
        String query = "select * from Disease where prescription_id = " + prescriptionId;
        connection = DatabaseHandler.getConnection();
        ObservableList<Disease> diseases = FXCollections.observableList(new ArrayList<>());
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    diseases.add(new Disease(
                            resultSet.getString("disease_name"),
                            resultSet.getString("disease_type"),
                            resultSet.getString("disease_description")));
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
        return diseases;
    }

    @Override
    public ObservableList<MedicalTest> getMedicalTestsInfo(String prescriptionId) {
        String query = "select * from Test where prescription_id = " + prescriptionId;
        connection = DatabaseHandler.getConnection();
        ObservableList<MedicalTest> tests = FXCollections.observableList(new ArrayList<>());
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    tests.add(new MedicalTest(
                            resultSet.getString("test_name"),
                            resultSet.getString("test_description"),
                            resultSet.getString("test_date"),
                            resultSet.getString("test_report")));
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
        return tests;
    }

    @Override
    public ObservableList<Medicine> getMedicineInfo(String prescriptionId) {
        String query = "select * from Medicine where prescription_id = " + prescriptionId;
        connection = DatabaseHandler.getConnection();
        ObservableList<Medicine> medicines = FXCollections.observableList(new ArrayList<>());
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    medicines.add(new Medicine(
                            resultSet.getString("medicine_name"),
                            resultSet.getString("medicine_duration"),
                            resultSet.getString("medicine_comment")));
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
        return medicines;
    }

    @Override
    public ArrayList<Object> getPrescriptionHistory(String patientId, String keyword) {
        connection = DatabaseHandler.getConnection();
        String query = "select Appointment.patient_id, Doctor.doctor_id, Doctor.doctor_name, " +
                "Appointment.reason, Appointment.date_of_appointment, Prescription.prescription_id " +
                "from (Prescription inner join Appointment " +
                "on Prescription.appointment_id = Appointment.appointment_id) " +
                "inner join Doctor " +
                "on Doctor.doctor_id = Appointment.doctor_id " +
                "where (" +
                "Appointment.patient_id = " + patientId + " and" +
                " Appointment.appointment_status = 1 and " +
                "(Doctor.doctor_id LIKE '%" + keyword + "%' OR " +
                "Doctor.doctor_name LIKE '%" + keyword + "%' " +
                ")) ";
        ArrayList<Object> appointmentsList = new ArrayList<>();
        HashMap<String, Boolean> isHeader = new HashMap<>();
        if (connection != null) {
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    String doctorName = resultSet.getString("doctor_name");
                    String doctorId = resultSet.getString("doctor_id");
                    String reason = resultSet.getString("reason");
                    Date dateOfAppointment = resultSet.getDate("date_of_appointment");
                    String prescriptionId = resultSet.getString("prescription_id");

                    // if header not initially put, make it a new header
                    if (isHeader.get(doctorName) == null) {
                        appointmentsList.add(doctorName);
                        appointmentsList.add(new Prescription(patientId, doctorId,
                                doctorName, reason, dateOfAppointment, prescriptionId));
                        isHeader.put(doctorName, true);
                    } else {
                        appointmentsList.add(new Prescription(patientId, doctorId,
                                doctorName, reason, dateOfAppointment, prescriptionId));
                    }
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
        return appointmentsList;

    }

    @Override
    public String createNewPrescription(Prescription prescription) {
        connection = DatabaseHandler.getConnection();

        StringBuilder query = new StringBuilder("" +
                "insert into Prescription (patient_id, doctor_id, appointment_id, patient_health_condition, health_comment) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "Declare @pres_id int " +
                "SELECT @pres_id=prescription_id FROM Prescription WHERE prescription_id = SCOPE_IDENTITY() " +
                "insert into Recommendations (exercise_routine, food_chart, prescription_id) " +
                "VALUES (?, ?, @pres_id) ");

        query.append("insert into Medicine (prescription_id, medicine_name, duration, medicine_comment) values");
        ObservableList<Medicine> medicines = prescription.getMedicines();
        for (int i = 0; i < medicines.size(); i++) {
            if (i == medicines.size() - 1) {
                query.append(" (@pres_id, ?, ?, ?) ");
            } else {
                query.append(" (@pres_id, ?, ?, ?), ");
            }
        }

        query.append("insert into Test (prescription_id, test_name, test_description) values");
        ObservableList<MedicalTest> medicalTests = prescription.getMedicalTests();
        for (int i = 0; i < medicalTests.size(); i++) {
            if (i == medicalTests.size() - 1) {
                query.append(" (@pres_id, ?, ?) ");

            } else {
                query.append(" (@pres_id, ?, ?), ");
            }
        }

        query.append("insert into Disease (prescription_id, disease_name, disease_type, disease_description) values");
        ObservableList<Disease> diseases = prescription.getDiseases();
        for (int i = 0; i < diseases.size(); i++) {
            if (i == diseases.size() - 1) {
                query.append(" (@pres_id, ?, ?, ?) ");

            } else {
                query.append(" (@pres_id, ?, ?, ?), ");
            }
        }

        System.out.println(query);

        if (connection != null) {
            try (PreparedStatement preparedStmt = connection.prepareStatement(query.toString())) {
                preparedStmt.setInt(1, prescription.getPatientId());
                preparedStmt.setInt(2, prescription.getDoctorId());
                preparedStmt.setInt(3, prescription.getAppointmentId());
                preparedStmt.setString(4, prescription.getHealthCondition().getHealthCondition());
                preparedStmt.setString(5, prescription.getHealthCondition().getComments());

                preparedStmt.setString(6, prescription.getHealthCondition().getExerciseRoutine());
                preparedStmt.setString(7, prescription.getHealthCondition().getFoodSuggestion());

                // inserting medicine list
                int i = 8;
                for (Medicine medicine : medicines) {
                    preparedStmt.setString(i, medicine.getMedicineName());
                    i++;
                    preparedStmt.setString(i, medicine.getMedicineDuration());
                    i++;
                    preparedStmt.setString(i, medicine.getComment());
                    i++;
                }

                // inserting tests list
                for (MedicalTest medicalTest : medicalTests) {
                    preparedStmt.setString(i, medicalTest.getTestName());
                    i++;
                    preparedStmt.setString(i, medicalTest.getTestDescription());
                    i++;
                }

                // inserting diseases list
                for (Disease disease : diseases) {
                    preparedStmt.setString(i, disease.getDiseaseName());
                    i++;
                    preparedStmt.setString(i, disease.getDiseaseType());
                    i++;
                    preparedStmt.setString(i, disease.getDescription());
                    i++;
                }

                preparedStmt.execute();

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

}
