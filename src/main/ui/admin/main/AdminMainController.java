package main.ui.admin.main;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import main.ui.admin.add_doctor.AddProfileController;


import java.net.URL;
import java.util.ResourceBundle;

public class AdminMainController implements Initializable {


    @FXML
    private StackPane myStackPane;

    @FXML
    private AnchorPane frameLayout;

    @FXML
    private JFXButton navSupportBtn;

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
    private JFXButton navViewDoctorButton;

    @FXML
    private JFXButton navAddADoctorBtn;



    @FXML
    private ListView<HBox> listView;

    private JFXButton guiButtonCurrent;
    private JFXButton guiButtonPrevious;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        guiButtonCurrent = navPatientBtn;
        guiButtonPrevious = navPatientBtn;

        // initially load view patients UI
        try {
            frameLayout.getChildren().clear();
            VBox root = FXMLLoader.load(getClass().getResource("/main/ui/admin/view_doctor/view_doctors.fxml"));
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
    void onPatientsClick(ActionEvent event) {
        if (!guiButtonCurrent.equals(navPatientBtn)) {
            guiButtonCurrent = navPatientBtn;
            guiChangeButtonStyle();
            guiButtonPrevious = navPatientBtn;

            try {
                frameLayout.getChildren().clear();
                VBox root = FXMLLoader.load(getClass().getResource("/main/ui/admin/view_patients/view_patients.fxml"));
                root = (VBox) makeResponsive(root, "vbox");
                frameLayout.getChildren().add(root);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

/*    @FXML
    void onDashboardClick(ActionEvent event) {
        if (!guiButtonCurrent.equals(navDashboardBtn)) {
            guiButtonCurrent = navDashboardBtn;
            guiChangeButtonStyle();
            guiButtonPrevious = navDashboardBtn;
            try {
                frameLayout.getChildren().clear();
                VBox root = FXMLLoader.load(getClass().getResource("/main/ui/admin/dashboard/admin_dashboard.fxml"));
                root = (VBox) makeResponsive(root, "vbox");
                frameLayout.getChildren().add(root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

    @FXML
    void onLogOutClick(ActionEvent event) {

    }

    @FXML
    void onViewDoctorClick(ActionEvent event){
        if (!guiButtonCurrent.equals(navViewDoctorButton)) {
            guiButtonCurrent = navViewDoctorButton;
            guiChangeButtonStyle();
            guiButtonPrevious = navViewDoctorButton;
            try {
                frameLayout.getChildren().clear();
                VBox root = FXMLLoader.load(getClass().getResource("/main/ui/admin/view_doctor/view_doctors.fxml"));
                root = (VBox) makeResponsive(root, "vbox");
                frameLayout.getChildren().add(root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onAddDoctorClick(ActionEvent event) {
        if (!guiButtonCurrent.equals(navAddADoctorBtn)) {
            guiButtonCurrent = navAddADoctorBtn;
            guiChangeButtonStyle();
            guiButtonPrevious = navAddADoctorBtn;

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/ui/admin/add_doctor/addProfile.fxml"));
                Parent root = (Parent) fxmlLoader.load();

                AddProfileController addProfileController = fxmlLoader.getController();
                addProfileController.init(myStackPane, addProfileController);
                frameLayout.getChildren().clear();
                ScrollPane root1 = fxmlLoader.load(getClass().getResource("/main/ui/admin/add_doctor/addProfile.fxml"));

                root1 = (ScrollPane) makeResponsive(root, "ScrollPane");
                frameLayout.getChildren().add(root1);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

    @FXML
    void onReportBugClick(ActionEvent event) {

    }

    @FXML
    void onSettingsClick(ActionEvent event) {

    }

    @FXML
    void onSupportClick(ActionEvent event) {

    }

    @FXML
    void onAppointmentsClick(ActionEvent event) {

    }


    private void guiChangeButtonStyle() {
        guiButtonCurrent.getStyleClass().clear();
        guiButtonCurrent.getStyleClass().add("nav-button-selected");
        guiButtonPrevious.getStyleClass().clear();
        guiButtonPrevious.getStyleClass().add("nav-button-unselected");
    }


}
