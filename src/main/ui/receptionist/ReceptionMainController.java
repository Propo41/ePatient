package main.ui.receptionist;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import database.DoctorDao;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.ui.receptionist.patient.add_patient.AddPatientController;
import main.ui.receptionist.test_report.ViewPrescriptionController;
import util.Util;

import java.net.URL;
import java.util.ResourceBundle;

public class ReceptionMainController implements Initializable {

    @FXML
    private StackPane myStackPane;

    @FXML
    private AnchorPane frameLayout;

    @FXML
    private JFXButton navSupportBtn;

    @FXML
    private JFXButton navAppointmentsBtn;

    @FXML
    private JFXButton navViewAllTestReportBtn;

    @FXML
    private JFXButton navSettingsBtn;

    @FXML
    private JFXButton navReportBug;

    @FXML
    private JFXButton navAddPatientBtn;

    @FXML
    private JFXButton navViewPatientBtn;

    @FXML
    private StackPane stackPaneRoot;

    @FXML
    private JFXButton navTestReportsBtn;

    @FXML
    private Label receptionNameTv;

    @FXML
    private JFXButton navLogOutBtn;

    private JFXButton guiButtonCurrent;
    private JFXButton guiButtonPrevious;
    private Timeline timeline;
    private JFXDialog dialog;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        guiButtonCurrent = navAppointmentsBtn;
        guiButtonPrevious = navAppointmentsBtn;

        DoctorDao doctorDao = new DoctorDao();
        String name = doctorDao.getName(Util.getInstance().getUserId());
        receptionNameTv.setText(name);

        // initially load appointments UI
        try {
            frameLayout.getChildren().clear();
            VBox root = FXMLLoader.load(getClass().getResource("appointments/appointments.fxml"));
            root = (VBox) makeResponsive(root, "vbox");
            frameLayout.getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        }else if(node.equals("ScrollPane")){
            ScrollPane scrollPane = (ScrollPane) root;
            // to make the contents of the frame responsive
            scrollPane.setPrefWidth(930);
            scrollPane.setPrefHeight(847);
            AnchorPane.setTopAnchor(scrollPane, 0.0d);
            AnchorPane.setBottomAnchor(scrollPane, 0.0d);
            AnchorPane.setLeftAnchor(scrollPane, 0.0d);
            AnchorPane.setRightAnchor(scrollPane, 0.0d);
            return scrollPane;
        }else if(node.equals("BorderPane")){
            BorderPane borderPane = (BorderPane) root;
            // to make the contents of the frame responsive
            borderPane.setPrefWidth(930);
            borderPane.setPrefHeight(847);
            AnchorPane.setTopAnchor(borderPane, 0.0d);
            AnchorPane.setBottomAnchor(borderPane, 0.0d);
            AnchorPane.setLeftAnchor(borderPane, 0.0d);
            AnchorPane.setRightAnchor(borderPane, 0.0d);
            return borderPane;
        }
        return null;
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
    void navViewAllTestReportBtnClick(ActionEvent event) {
        if (!guiButtonCurrent.equals(navViewAllTestReportBtn)) {
            guiButtonCurrent = navViewAllTestReportBtn;
            guiChangeButtonStyle();
            guiButtonPrevious = navViewAllTestReportBtn;
            try {
                frameLayout.getChildren().clear();
                VBox root = FXMLLoader.load(getClass().getResource("test_report_history/view_test_report_history.fxml"));
                root = (VBox) makeResponsive(root, "vbox");
                frameLayout.getChildren().add(root);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    void onTestReportsClick(ActionEvent event) {
        if (!guiButtonCurrent.equals(navTestReportsBtn)) {
            guiButtonCurrent = navTestReportsBtn;
            guiChangeButtonStyle();
            guiButtonPrevious = navTestReportsBtn;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/ui/receptionist/test_report/view_test_report.fxml"));
                Parent root = (Parent) fxmlLoader.load();

                ViewPrescriptionController viewPrescriptionController = fxmlLoader.getController();
                viewPrescriptionController.init(myStackPane);
                frameLayout.getChildren().clear();
                VBox root1 = fxmlLoader.load(getClass().getResource("test_report/view_test_report.fxml"));

                root1 = (VBox) makeResponsive(root, "vbox");
                frameLayout.getChildren().add(root1);


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    @FXML
    void onAddPatientsClick(ActionEvent event) {
        if (!guiButtonCurrent.equals(navAddPatientBtn)) {
            guiButtonCurrent = navAddPatientBtn;
            guiChangeButtonStyle();
            guiButtonPrevious = navAddPatientBtn;

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/ui/receptionist/patient/add_patient/add_patient.fxml"));
                Parent root = (Parent) fxmlLoader.load();

                AddPatientController addPatientController = fxmlLoader.getController();
                addPatientController.init(myStackPane);
                frameLayout.getChildren().clear();
                ScrollPane root1 = fxmlLoader.load(getClass().getResource("/main/ui/receptionist/patient/add_patient/add_patient.fxml"));

                root1 = (ScrollPane) makeResponsive(root, "ScrollPane");
                frameLayout.getChildren().add(root1);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    void onViewPatientsClick(ActionEvent event) {
        if (!guiButtonCurrent.equals(navViewPatientBtn)) {
            guiButtonCurrent = navViewPatientBtn;
            guiChangeButtonStyle();
            guiButtonPrevious = navViewPatientBtn;
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
        Stage stage = (Stage) guiButtonCurrent.getScene().getWindow();
        stage.close();
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/main/ui/main/main.fxml"));
            Parent parent = loader.load();
            Scene loginScene = new Scene(parent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void guiChangeButtonStyle() {
        guiButtonCurrent.getStyleClass().clear();
        guiButtonCurrent.getStyleClass().add("nav-button-selected");

        guiButtonPrevious.getStyleClass().clear();
        guiButtonPrevious.getStyleClass().add("nav-button-unselected");
    }



}
