package main.ui.receptionist.patient.add_patient;

import java.io.IOException;
import java.sql.Date;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import database.PatientDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.ECMAException;
import main.ui.admin.InputErrorDialogController;
import main.ui.admin.InsertionProfileDialogController;
import main.ui.admin.edit_doctor.EditDoctorController;
import main.ui.admin.edit_patient.AddMedicalHistoryDialogController;
import model.Patient;
import util.Util;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddPatientController implements Initializable {


    @FXML
    private TextField name;

    @FXML
    private TextField weight;

    @FXML
    private TextField age;

    @FXML
    private TextField bloodGroup;

    @FXML
    private TextField email;

    @FXML
    private TextField dateOfBirth;

    @FXML
    private TextField medicalInfo;

    @FXML
    private TextField gender;

    @FXML
    private TextField height;

    @FXML
    private TextField contact;

    @FXML
    private TextField emergencyContact;

    @FXML
    private TextField address;

    @FXML
    private TextField joiningDate;

    @FXML
    private TextField socialHistory;

    @FXML
    private TextField surgicalHistory;

    private HashMap<String, Boolean> socialHistoryText;
    private HashMap<String, Boolean> medicalInfoText;
    HashMap<String, Integer> medicalHistoryMap;
    Patient patient;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public static final Pattern VALID_BLOOD_GROUP =
            Pattern.compile("(A|B|AB|O)(\\+|-)", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_GENDER =
            Pattern.compile("(MALE|FEMALE)", Pattern.CASE_INSENSITIVE);


    @FXML
    void onAddMedicalInfo(ActionEvent event) {
        openMedicalInfo();
    }

    void openMedicalInfo(){
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/ui/receptionist/patient/add_patient/medicalHistoryDialog.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            MedicalHistoryDialogController dialogController = fxmlLoader.getController();
            dialogController.setTitle(medicalHistoryMap);

            Stage stage = new Stage();
            stage.setTitle("");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onAddSocialHistory(ActionEvent event) {
        if(!socialHistory.getText().equals("")){
            socialHistoryText.put(socialHistory.getText(),true);
            socialHistory.setText("");
        }
    }

    @FXML
    void onCreateButtonClicked(ActionEvent event) throws ParseException {

        if(name.getText().equals("")){
            openEmptyDialogWarning("Name field cannot be emtpy");
            return;
        }

        if(email.getText().equals("")){
            openEmptyDialogWarning("Email field cannot be emtpy");
            return;
        }

        if(weight.getText().equals("")){
            openEmptyDialogWarning("Weight field cannot be emtpy");
            return;
        }

        if(age.getText().equals("")){
            openEmptyDialogWarning("Age field cannot be emtpy");
            return;
        }

        if(bloodGroup.getText().equals("")){
            openEmptyDialogWarning("BloodGroup cannot be emtpy");
            return;
        }

        if(gender.getText().equals("")){
            openEmptyDialogWarning("Gender field cannot be emtpy");
            return;
        }

        if(height.getText().equals("")){
            openEmptyDialogWarning("Height field cannot be emtpy");
            return;
        }

        if(contact.getText().equals("")){
            openEmptyDialogWarning("Contact field cannot be emtpy");
            return;
        }

        if(address.getText().equals("")){
            openEmptyDialogWarning("Address field cannot be emtpy");
            return;
        }

        if(dateOfBirth.getText().equals("")){
            openEmptyDialogWarning("Date of Birth field cannot be emtpy");
            return;
        }

        if(joiningDate.getText().equals("")){
            openEmptyDialogWarning("Joining date field cannot be emtpy");
            return;
        }

        if(medicalHistoryMap.size()==0){
            openMedicalInfo();
            return;
        }

        if(!validateEmail(email.getText())){
            openEmptyDialogWarning("Incorrect Email format");
            return;
        }

        if (!validateBloodGroup(bloodGroup.getText())) {
            openEmptyDialogWarning("Invalid Blood Group format");
            return;
        }

        if (!isDateValid(dateOfBirth.getText())) {
            openEmptyDialogWarning("Invalid Date format in Date Of Birth");
            return;
        }

        if (!validateGender(gender.getText())) {
            openEmptyDialogWarning("Invalid Gender");
            return;
        }

        insertData();
    }

    private void insertData() throws ParseException {

        patient = new Patient();
        patient.setName(name.getText());
        patient.setContact(contact.getText());
        patient.setEmail(email.getText());
        patient.setAddress(address.getText());
        patient.setGender(gender.getText());
        patient.setBloodGroup(bloodGroup.getText());
        patient.setAge(age.getText());
        patient.setHeight(height.getText());
        patient.setWeight(weight.getText());

        patient.setMedicalHistory(medicalInfoText);
        patient.setSocialHistory(socialHistoryText);
        patient.setSurgicalHistory(surgicalHistory.getText());
        patient.setEmergencyContact(emergencyContact.getText());

        new PatientDao().addPatient(patient, joiningDate.getText(), dateOfBirth.getText());
        int createdPatientId = new PatientDao().addMedicalHistory(medicalHistoryMap);
        openSuccessDialog(createdPatientId);
    }



    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validateGender(String emailStr) {
        Matcher matcher = VALID_GENDER.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validateBloodGroup(String bloodGroup) {
        Matcher matcher = VALID_BLOOD_GROUP.matcher(bloodGroup);
        return matcher.find();
    }

    final static String DATE_FORMAT = "dd/MM/yyyy";

    public boolean isDateValid(String date)
    {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        joiningDate.setText(dtf.format(now));

        height.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    height.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


        contact.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    contact.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        emergencyContact.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    emergencyContact.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        weight.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    weight.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        age.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    age.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        addTextLimiter(bloodGroup,3);
        addTextLimiter(contact,13);
        addTextLimiter(emergencyContact,13);

        socialHistoryText = new HashMap<>();
        medicalInfoText = new HashMap<>();
        medicalHistoryMap = new HashMap<>();

    }

    public void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }


    private void openEmptyDialogWarning(String emptyMessage){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/ui/admin/InputErrorDialog.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            InputErrorDialogController inputErrorDialogController = fxmlLoader.getController();
            inputErrorDialogController.setErrorMessage(emptyMessage);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
        }catch (Exception ex){

        }
    }


    private void openSuccessDialog(int createdPatientId) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/ui/admin/InsertionProfileDialog.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            InsertionProfileDialogController insertionProfileDialogController = fxmlLoader.getController();
            insertionProfileDialogController.setIdNumber(createdPatientId);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
            resetValues();
        }catch (Exception ex){

        }

    }

    private void resetValues() {
        socialHistoryText = new HashMap<>();
        medicalInfoText = new HashMap<>();
        medicalHistoryMap = new HashMap<>();
        patient = new Patient();
        name.setText("");
        gender.setText("");
        weight.setText("");
        height.setText("");
        age.setText("");
        contact.setText("");
        bloodGroup.setText("");
        emergencyContact.setText("");
        email.setText("");
        address.setText("");
        surgicalHistory.setText("");
        joiningDate.setText("");
    }


}
