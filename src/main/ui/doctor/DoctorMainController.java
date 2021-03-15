package main.ui.doctor;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import database.DoctorQueueDaoDao;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import database.DoctorDao;
import javafx.util.Duration;
import javafx.util.Pair;
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

    @FXML
    private TextField patientIdTv;

    @FXML
    private TextField appointmentIdTv;


    @FXML
    private StackPane stackPaneRoot;

    private JFXButton guiButtonCurrent;
    private JFXButton guiButtonPrevious;
    private Timeline timeline;
    private JFXDialog dialog;

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
            VBox root = FXMLLoader.load(getClass().getResource("dashboard/dashboard.fxml"));
            root = (VBox) makeResponsive(root, "vbox");
            frameLayout.getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // createCardItems(4);
        startQueryingService();


    }


    private void startQueryingService() {
        DoctorQueueDaoDao doctorQueueDao = new DoctorQueueDaoDao();
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(Util.QUERY_DELAY), e -> {
                    // query database here
                    // if data is found, then show alert dialog and then stop service.
                    System.out.println("service running...");
                    Pair<String, String> res = doctorQueueDao.fetchQueue(Util.getInstance().getUserId());
                    if (res != null) {
                        // update ui
                        // stop service
                        Util util = Util.getInstance();
                        if (!util.getCurrentDoctorPatientQueue().equals(res)) {
                            openNewPatientDialog(res);
                            stopQueryingService();
                            Util.getInstance().setCurrentDoctorPatientQueue(res);
                        }

                    }
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void openNewPatientDialog(Pair<String, String> res) {
        try {
            JFXDialogLayout content = new JFXDialogLayout();
            content.getStyleClass().add("jfx-dialog-overlay-pane");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dialog_new_patient_prompt.fxml"));
            loader.load();
            dialog = new JFXDialog(stackPaneRoot, loader.getRoot(), JFXDialog.DialogTransition.CENTER);
            dialog.getStyleClass().add("jfx-dialog-layout");
            DialogNewPatientPromptController dialogController = loader.getController();
            dialogController.setIds(res);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopQueryingService() {
        System.out.println("service stopped...");
        timeline.stop();
    }

    private Parent makeResponsive(Parent root, String node) {
        switch (node) {
            case "vbox":
                VBox vBox = (VBox) root;
                // to make the contents of the frame responsive
                vBox.setPrefWidth(930);
                vBox.setPrefHeight(847);
                AnchorPane.setTopAnchor(vBox, 0.0d);
                AnchorPane.setBottomAnchor(vBox, 0.0d);
                AnchorPane.setLeftAnchor(vBox, 0.0d);
                AnchorPane.setRightAnchor(vBox, 0.0d);
                return vBox;
            case "scrollpane":
                ScrollPane scrollPane = (ScrollPane) root;
                // to make the contents of the frame responsive
                scrollPane.setPrefWidth(930);
                scrollPane.setPrefHeight(847);
                AnchorPane.setTopAnchor(scrollPane, 0.0d);
                AnchorPane.setBottomAnchor(scrollPane, 0.0d);
                AnchorPane.setLeftAnchor(scrollPane, 0.0d);
                AnchorPane.setRightAnchor(scrollPane, 0.0d);
                return scrollPane;
        }
        return null;
    }

    @FXML
    void onNextPatientClick(ActionEvent event) {
        startQueryingService();
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
                VBox root = FXMLLoader.load(getClass().getResource("prescription/prescription.fxml"));
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
