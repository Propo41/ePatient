package main.ui.login;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import database.DatabaseHandler;
import util.Util;

public class LogInController {

    @FXML
    private TextField userId;

    @FXML
    private TextField userPassword;


    @FXML
    void loginClicked(ActionEvent event) {
        String userType = Util.getInstance().getUserType();
        System.out.println("Login Type: " + userType);

        DatabaseHandler databaseHandler = new DatabaseHandler();
        boolean status = databaseHandler.logInForm(userId.getText(), userPassword.getText(), userType);
        System.out.println(userId.getText() + " " + userPassword.getText());
        // if user is verified, continue to dashboard
        if (status) {
            try {
                FXMLLoader loader = new FXMLLoader();
                if(userType.equals(Util.TYPE_ADMIN)){
                    loader.setLocation(getClass().getResource("/main/ui/admin/main/admin_main.fxml"));

                }else if(userType.equals(Util.TYPE_DOCTOR)){
                    loader.setLocation(getClass().getResource("/main/ui/doctor/doctor_main.fxml"));

                }else{
                   //loader.setLocation(getClass().getResource("/main/ui/doctor/reception_main.fxml"));

                }
                Parent parent = loader.load();
                Scene loginScene = new Scene(parent);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(loginScene);
                window.setTitle("ePatient");
                window.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            System.out.println("Incorrect Credentials");
        }


    }



}
