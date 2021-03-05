package main.ui.doctor;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.ui.database.ConnectMSSQL;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddProfileController implements Initializable {

    @FXML
    private TextField name, contact, languages, speciality,email , hospitalAffiliations, mobileNumber, doctorAddress,
    password, retypePassword  , educationalBackground,  department, professionalExperience ,
            availableDuration, roomNumber ;

    ConnectMSSQL connectMSSQL;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roomNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    roomNumber.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


        mobileNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    mobileNumber.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }


    @FXML
    void saveClicked(ActionEvent event) {
        checkInputFields();
    }

    private void checkInputFields() {
        checkemptyField();
    }

    private void checkemptyField() {
        if(name.getText().equals("")){
            openEmptyDialogWarning("Name field cannot be emtpy");
            return;
        }

        if(email.getText().equals("")){
            openEmptyDialogWarning("Email field cannot be emtpy");
            return;
        }

        if(mobileNumber.getText().equals("")){
            openEmptyDialogWarning("Mobile Number field cannot be emtpy");
            return;
        }

        if(department.getText().equals("")){
            openEmptyDialogWarning("Department field cannot be emtpy");
            return;
        }

        if(speciality.getText().equals("")){
            openEmptyDialogWarning("Speciality field cannot be emtpy");
            return;
        }

        if(professionalExperience.getText().equals("")){
            openEmptyDialogWarning("Professional Experience field cannot be emtpy");
            return;
        }

        if(roomNumber.getText().equals("")){
            openEmptyDialogWarning("Room Number field cannot be emtpy");
            return;
        }

        if(educationalBackground.getText().equals("")){
            openEmptyDialogWarning("Educational Background field cannot be emtpy");
            return;
        }


        if(password.getText().equals("")){
            openEmptyDialogWarning("Password field cannot be emtpy");
            return;
        }

        if(retypePassword.getText().equals("")){
            openEmptyDialogWarning("Re-type password field cannot be emtpy");
            return;
        }

        if(! (password.getText().equals(retypePassword.getText())) ){
            openEmptyDialogWarning("Mismatch passwords, Please type again");
            return;
        }

        if(!validate(email.getText())){
            openEmptyDialogWarning("Incorrect Email format");
            return;
        }

        insertData();
    }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }


    private void insertData() {
        connectMSSQL = new ConnectMSSQL();
        boolean queryResult = connectMSSQL.insertDoctorProfile(name.getText(), email.getText(), mobileNumber.getText(),
                doctorAddress.getText(), department.getText(), speciality.getText(), languages.getText(),
                hospitalAffiliations.getText(), professionalExperience.getText(), roomNumber.getText(),
                educationalBackground.getText(), password.getText());
        System.out.println(queryResult);
        openSuccessDialog();
    }

    private void openEmptyDialogWarning(String emptyMessage){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/ui/doctor/InputError.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            InputErrorController inputErrorController = fxmlLoader.getController();
            inputErrorController.setErrorMessage(emptyMessage);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
        }catch (Exception ex){

        }

    }


    private void openSuccessDialog() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/ui/doctor/InsertionProfileDialog.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            int answer = connectMSSQL.getProfileId();
            InsertionProfileDialogController insertionProfileDialogController = fxmlLoader.getController();
            insertionProfileDialogController.setIdNumber(answer);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
            System.out.println("here");
        }catch (Exception ex){

        }

    }




}
