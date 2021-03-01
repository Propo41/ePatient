package main.ui.login;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.ui.database.ConnectMSSQL;
import main.ui.doctor.DoctorMainController;

public class LogInController {

    @FXML
    private TextField userId;

    @FXML
    private TextField userPassword;

    private String loginType;

    @FXML
    void loginClicked(ActionEvent event) {
        System.out.println("Login Type " + loginType);
        ConnectMSSQL connectMSSQL = new ConnectMSSQL(userId.getText(), userPassword.getText(), loginType);
        System.out.println(userId.getText() + " " + userPassword.getText());
        // if user is verified, continue to dashboard
        if (connectMSSQL.logInForm()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/main/ui/doctor/doctor_main.fxml"));
                Parent parent = loader.load();
                Scene loginScene = new Scene(parent);

                DoctorMainController doctorMainController = loader.getController();
               // dashboardController.setDoctorId(userId.getText());

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(loginScene);
                window.setTitle("ePatient");
                window.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }


}
