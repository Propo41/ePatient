package main.ui.doctor;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import database.DoctorDao;
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

    @FXML
    private ListView<HBox> listView;

    private JFXButton guiButtonCurrent;
    private JFXButton guiButtonPrevious;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        guiButtonCurrent = navDashboardBtn;
        guiButtonPrevious = navDashboardBtn;

        DoctorDao doctorDao = new DoctorDao();
        String name = doctorDao.getName(Util.getInstance().getUserId());
        doctorNameTv.setText(name);

        // initially load dashboard UI
        try {
            frameLayout.getChildren().clear();
            StackPane root = FXMLLoader.load(getClass().getResource("dashboard/dashboard.fxml"));
            root = (StackPane) makeResponsive(root, "stackpane");
            frameLayout.getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // createCardItems(4);

    }

    private Parent makeResponsive(Parent root, String node) {
        if (node.equals("vbox")) {
            VBox vBox = (VBox) root;
            // to make the contents of the frame responsive
            vBox.setPrefWidth(930);
            vBox.setPrefHeight(847);
            AnchorPane.setTopAnchor(vBox, 0.0d);
            AnchorPane.setBottomAnchor(vBox, 0.0d);
            AnchorPane.setLeftAnchor(vBox, 0.0d);
            AnchorPane.setRightAnchor(vBox, 0.0d);
            return vBox;
        }else if(node.equals("scrollpane")){
            ScrollPane scrollPane = (ScrollPane) root;
            // to make the contents of the frame responsive
            scrollPane.setPrefWidth(930);
            scrollPane.setPrefHeight(847);
            AnchorPane.setTopAnchor(scrollPane, 0.0d);
            AnchorPane.setBottomAnchor(scrollPane, 0.0d);
            AnchorPane.setLeftAnchor(scrollPane, 0.0d);
            AnchorPane.setRightAnchor(scrollPane, 0.0d);
            return scrollPane;
        }else if(node.equals("stackpane")){
            StackPane stackPane = (StackPane) root;
            // to make the contents of the frame responsive
            stackPane.setPrefWidth(930);
            stackPane.setPrefHeight(847);
            AnchorPane.setTopAnchor(stackPane, 0.0d);
            AnchorPane.setBottomAnchor(stackPane, 0.0d);
            AnchorPane.setLeftAnchor(stackPane, 0.0d);
            AnchorPane.setRightAnchor(stackPane, 0.0d);
            return stackPane;
        }
        return null;
    }

    @FXML
    void onDashboardClick(ActionEvent event) {
        if (!guiButtonCurrent.equals(navDashboardBtn)) {
            guiButtonCurrent = navDashboardBtn;
            guiChangeButtonStyle();
            guiButtonPrevious = navDashboardBtn;
            try {
                frameLayout.getChildren().clear();
                VBox root = FXMLLoader.load(getClass().getResource("dashboard/dashboard.fxml"));
                root = (VBox) makeResponsive(root, "vbox");
                frameLayout.getChildren().add(root);
            } catch (Exception e) {
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
            try {
                frameLayout.getChildren().clear();
                VBox root = FXMLLoader.load(getClass().getResource("patients/patients.fxml"));
                root = (VBox) makeResponsive(root, "vbox");
                frameLayout.getChildren().add(root);
            } catch (Exception e) {
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
            try {
                frameLayout.getChildren().clear();
                VBox root = FXMLLoader.load(getClass().getResource("profile/profile.fxml"));
                root = (VBox) makeResponsive(root, "vbox");
                frameLayout.getChildren().add(root);

            } catch (Exception e) {
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
            try {
                frameLayout.getChildren().clear();
                VBox root = FXMLLoader.load(getClass().getResource("appointments/appointments.fxml"));
                root = (VBox) makeResponsive(root, "vbox");
                frameLayout.getChildren().add(root);

            } catch (Exception e) {
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
