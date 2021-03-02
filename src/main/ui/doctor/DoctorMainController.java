package main.ui.doctor;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.ui.database.DoctorDao;
import util.Util;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorMainController implements Initializable {
    @FXML
    private AnchorPane frameLayout;

    @FXML
    private JFXButton navSupportBtn;

    @FXML
    private JFXButton navDashboardBtn;

    @FXML
    private JFXButton navAppointmentsBtn;

    @FXML
    private JFXButton navSettingsBtn;

    @FXML
    private JFXButton navReportBug;

    @FXML
    private JFXButton navPatientBtn;

    @FXML
    private JFXButton navMyProfileBtn;

    @FXML
    private JFXButton navLogOutBtn;

    @FXML
    private Label doctorNameTv;

    private JFXButton guiButtonCurrent;
    private JFXButton guiButtonPrevious;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        guiButtonCurrent = navDashboardBtn;
        guiButtonPrevious = navDashboardBtn;

        DoctorDao doctorDao = new DoctorDao(Util.getInstance().getUserId());
        String name = doctorDao.getName();
        doctorNameTv.setText(name);

        // initially load dashboard UI
        try{
            frameLayout.getChildren().clear();
            frameLayout.getChildren().add(FXMLLoader.load(getClass().getResource("dashboard.fxml")));
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @FXML
    void onDashboardClick(ActionEvent event) {
        if (!guiButtonCurrent.equals(navDashboardBtn)) {
            guiButtonCurrent = navDashboardBtn;
            guiChangeButtonStyle();
            guiButtonPrevious = navDashboardBtn;
            try{
                frameLayout.getChildren().clear();
                frameLayout.getChildren().add(FXMLLoader.load(getClass().getResource("dashboard.fxml")));
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @FXML
    void onPatientsClick(ActionEvent event) {
        if (!guiButtonCurrent.equals(navPatientBtn)) {
            guiButtonCurrent = navPatientBtn;
            guiChangeButtonStyle();
            guiButtonPrevious = navPatientBtn;
            try{
                frameLayout.getChildren().clear();
                frameLayout.getChildren().add(FXMLLoader.load(getClass().getResource("patients.fxml")));
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    @FXML
    void onProfileClick(ActionEvent event) {
        if (!guiButtonCurrent.equals(navMyProfileBtn)) {
            guiButtonCurrent = navMyProfileBtn;
            guiChangeButtonStyle();
            guiButtonPrevious = navMyProfileBtn;
            try{
                frameLayout.getChildren().clear();
                frameLayout.getChildren().add(FXMLLoader.load(getClass().getResource("profile.fxml")));
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }

    @FXML
    void onAppointmentsClick(ActionEvent event) {
        if (!guiButtonCurrent.equals(navAppointmentsBtn)) {
            guiButtonCurrent = navAppointmentsBtn;
            guiChangeButtonStyle();
            guiButtonPrevious = navAppointmentsBtn;
            try{
                frameLayout.getChildren().clear();
                frameLayout.getChildren().add(FXMLLoader.load(getClass().getResource("appointments.fxml")));
            }catch (Exception e){
                e.printStackTrace();

            }
        }
    }

    @FXML
    void onSettingsClick(ActionEvent event) {

    }

    @FXML
    void onReportBugClick(ActionEvent event) {

    }

    @FXML
    void onSupportClick(ActionEvent event) {

    }

    @FXML
    void onLogOutClick(ActionEvent event) {

    }

    private void guiChangeButtonStyle() {
        guiButtonCurrent.getStyleClass().clear();
        guiButtonCurrent.getStyleClass().add("nav-button-selected");

        guiButtonPrevious.getStyleClass().clear();
        guiButtonPrevious.getStyleClass().add("nav-button-unselected");
    }

}
