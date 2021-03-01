package main.ui.doctor;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import main.ui.database.ConnectMSSQL;
import main.ui.database.DoctorDB;
import util.Util;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private Pair<JFXButton, Boolean> guiButtonCurrent;
    private Pair<JFXButton, Boolean> guiButtonPrevious;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        guiButtonCurrent = new Pair<>(navDashboardBtn, true);
        guiButtonPrevious = new Pair<>(navDashboardBtn, true);

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
        if (!guiButtonCurrent.getKey().equals(navDashboardBtn)) {
            guiButtonCurrent = new Pair<>(navDashboardBtn, true);
            guiChangeButtonStyle();
            guiButtonPrevious = new Pair<>(navDashboardBtn, true);
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
        if (!guiButtonCurrent.getKey().equals(navPatientBtn)) {
            guiButtonCurrent = new Pair<>(navPatientBtn, true);
            guiChangeButtonStyle();
            guiButtonPrevious = new Pair<>(navPatientBtn, true);
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
        if (!guiButtonCurrent.getKey().equals(navMyProfileBtn)) {
            guiButtonCurrent = new Pair<>(navMyProfileBtn, true);
            guiChangeButtonStyle();
            guiButtonPrevious = new Pair<>(navMyProfileBtn, true);
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
        if (!guiButtonCurrent.getKey().equals(navAppointmentsBtn)) {
            guiButtonCurrent = new Pair<>(navAppointmentsBtn, true);
            guiChangeButtonStyle();
            guiButtonPrevious = new Pair<>(navAppointmentsBtn, true);
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
        guiButtonCurrent.getKey().getStyleClass().clear();
        guiButtonCurrent.getKey().getStyleClass().add("nav-button-selected");

        guiButtonPrevious.getKey().getStyleClass().clear();
        guiButtonPrevious.getKey().getStyleClass().add("nav-button-unselected");
    }

}
