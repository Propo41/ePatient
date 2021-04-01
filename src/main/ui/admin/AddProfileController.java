package main.ui.admin;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
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
import database.DatabaseHandler;
import main.ui.receptionist.test_report.AddSuccessDialogController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddProfileController implements Initializable {

    @FXML
    private TextField name, contact, visit_fee, speciality,email , hospitalAffiliations, mobileNumber, doctorAddress,
    password, retypePassword  , educationalBackground,  department, professionalExperience ,
            availableDuration, roomNumber ;

    DatabaseHandler connectMSSQL;

    StackPane stackPane;

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

        visit_fee.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    visit_fee.setText(newValue.replaceAll("[^\\d]", ""));
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

    }


    @FXML
    void saveClicked(ActionEvent event) {
        checkemptyField();
    }

    private void checkemptyField() {
        if(name.getText().equals("")){
            openEmptyDialogWarning("Name field cannot be empty");
            return;
        }

        if(visit_fee.getText().equals("")){
            openEmptyDialogWarning("Visit Fee field cannot be empty");
            return;
        }

        if(email.getText().equals("")){
            openEmptyDialogWarning("Email field cannot be empty");
            return;
        }

        if(mobileNumber.getText().equals("")){
            openEmptyDialogWarning("Mobile Number field cannot be empty");
            return;
        }

        if(department.getText().equals("")){
            openEmptyDialogWarning("Department field cannot be empty");
            return;
        }

        if(speciality.getText().equals("")){
            openEmptyDialogWarning("Speciality field cannot be empty");
            return;
        }

        if(professionalExperience.getText().equals("")){
            openEmptyDialogWarning("Professional Experience field cannot be empty");
            return;
        }

        if(roomNumber.getText().equals("")){
            openEmptyDialogWarning("Room Number field cannot be empty");
            return;
        }

        if(educationalBackground.getText().equals("")){
            openEmptyDialogWarning("Educational Background field cannot be empty");
            return;
        }


        if(password.getText().equals("")){
            openEmptyDialogWarning("Password field cannot be empty");
            return;
        }

        if(retypePassword.getText().equals("")){
            openEmptyDialogWarning("Re-type password field cannot be empty");
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
        connectMSSQL = new DatabaseHandler();
        boolean queryResult = connectMSSQL.insertDoctorProfile(name.getText(), email.getText(), mobileNumber.getText(),
                doctorAddress.getText(), department.getText(), speciality.getText(), visit_fee.getText(),
                hospitalAffiliations.getText(), professionalExperience.getText(), roomNumber.getText(),
                educationalBackground.getText(), password.getText());
        if(queryResult){
            openSuccessDialog();
            resetValues();
        }
    }

    private void resetValues() {
        name.setText("");
        contact.setText("");
        visit_fee.setText("");
        speciality.setText("");
        email.setText("");
        hospitalAffiliations.setText("");
        mobileNumber.setText("");
        doctorAddress.setText("");
        password.setText("");
        retypePassword.setText("");
        educationalBackground.setText("");
        department.setText("");
        professionalExperience.setText("");
        availableDuration.setText("");
        roomNumber.setText("");
    }

    private void openEmptyDialogWarning(String emptyMessage){
        try {
            JFXDialogLayout content = new JFXDialogLayout();
            content.getStyleClass().add("jfx-dialog-overlay-pane");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/ui/admin/InputErrorDialog.fxml"));
            loader.load();
            JFXDialog dialog = new JFXDialog(stackPane, loader.getRoot(), JFXDialog.DialogTransition.CENTER);
            dialog.getStyleClass().add("jfx-dialog-layout");
            InputErrorDialogController inputErrorDialogController = loader.getController();
            inputErrorDialogController.setErrorMessage(emptyMessage,dialog);
            dialog.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void openSuccessDialog() {
        try {
            JFXDialogLayout content = new JFXDialogLayout();
            content.getStyleClass().add("jfx-dialog-overlay-pane");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/ui/admin/InsertionProfileDialog.fxml"));
            loader.load();
            JFXDialog dialog = new JFXDialog(stackPane, loader.getRoot(), JFXDialog.DialogTransition.CENTER);
            dialog.getStyleClass().add("jfx-dialog-layout");
            int answer = connectMSSQL.getProfileId();
            InsertionProfileDialogController insertionProfileDialogController = loader.getController();
            insertionProfileDialogController.setIdNumber(answer,dialog);
            dialog.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void init(StackPane myStackPane) {
        this.stackPane = myStackPane;
    }

}
