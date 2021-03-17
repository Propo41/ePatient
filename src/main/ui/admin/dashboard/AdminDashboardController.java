package main.ui.admin.dashboard;

import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.ui.admin.InputErrorDialogController;
import main.ui.admin.InsertionProfileDialogController;
import model.Doctor;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminDashboardController {

    @FXML
    private ListView<HBox> doctorListView;

    @FXML
    private TextField doctorSearchTv;

    @FXML
    private Label searchResultNumber;

    int count;
    ArrayList<Doctor> doctorArrayList;

    @FXML
    private TextField name, contact, languages, speciality,email , hospitalAffiliations, mobileNumber, doctorAddress,
            password, retypePassword  , educationalBackground,  department, professionalExperience ,
            availableDuration, roomNumber ;

    DatabaseHandler connectMSSQL;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    @FXML
    void onSearchBtnClick(ActionEvent event) {

    }

    @FXML
    void saveClicked(ActionEvent event) {
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

    private void insertData() {
        connectMSSQL = new DatabaseHandler();
        boolean queryResult = connectMSSQL.insertDoctorProfile(name.getText(), email.getText(), mobileNumber.getText(),
                doctorAddress.getText(), department.getText(), speciality.getText(), languages.getText(),
                hospitalAffiliations.getText(), professionalExperience.getText(), roomNumber.getText(),
                educationalBackground.getText(), password.getText());
        System.out.println(queryResult);
        openSuccessDialog();
    }

    private void openSuccessDialog() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/ui/admin/InsertionProfileDialog.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            int answer = connectMSSQL.getProfileId();
            InsertionProfileDialogController insertionProfileDialogController = fxmlLoader.getController();
            insertionProfileDialogController.setIdNumber(answer);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();

        }catch (Exception ex){

        }

    }

}
