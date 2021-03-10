package main.ui.doctor.patients;

import com.jfoenix.controls.JFXButton;
import database.DoctorDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Patient;
import util.Util;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class ViewPatientController implements Initializable {

    @FXML
    private Label contactLabel;

    @FXML
    private Label bloodGroupLabel;

    @FXML
    private ListView<HBox> medicalHistoryListView;

    @FXML
    private Label genderLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label heightLabel;

    @FXML
    private TextArea surgicalHistoryTv;

    @FXML
    private Label weightLabel;

    @FXML
    private JFXButton closeButton;

    @FXML
    private Label emergencyContactLabel;

    @FXML
    private Label patientNameLabel;

    @FXML
    private ListView<HBox> socialHistoryListView;

    @FXML
    private Label ageLabel;

    @FXML
    private Label dobLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label patientIdLabel;

    private String patientId;


    @FXML
    void onCloseClick(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private HBox createList(String name) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(20);

        ImageView imageView = new ImageView();
        imageView.getStyleClass().add("bullet-icon");
        hBox.getChildren().add(imageView);

        Label label = new Label(name);
        label.getStyleClass().add("text-label-ash-background");
        hBox.getChildren().add(label);

        return hBox;
    }

    public void setContent(String patientId) {
        DoctorDao doctorDao = new DoctorDao();
        Patient patient = doctorDao.getPatientProfile(patientId);
        contactLabel.setText(patient.getContact());
        bloodGroupLabel.setText(patient.getBloodGroup());
        genderLabel.setText(patient.getGender());
        addressLabel.setText(patient.getAddress());
        heightLabel.setText(patient.getHeight());
        weightLabel.setText(patient.getWeight());
        emergencyContactLabel.setText(patient.getEmergencyContact());
        patientNameLabel.setText(patient.getName());
        ageLabel.setText(patient.getAge());
        dobLabel.setText(Util.formatDate(patient.getBirthDate()));
        emailLabel.setText(patient.getEmail());
        patientIdLabel.setText("ID: " + patient.getId());
        surgicalHistoryTv.setText(patient.getSurgicalHistory());

        for (Map.Entry<String, Boolean> entry : patient.getSocialHistory().entrySet()) {
            String key = entry.getKey();
            Boolean status = entry.getValue();
            HBox hBox = createList(key.toUpperCase() + ": " + (status ? "YES" : "NO"));
            socialHistoryListView.getItems().add(hBox);
        }

        for (Map.Entry<String, Boolean> entry : patient.getMedicalHistory().entrySet()) {
            String key = entry.getKey();
            Boolean status = entry.getValue();
            HBox hBox = createList(key.toUpperCase() + ": " + (status ? "YES" : "NO"));
            medicalHistoryListView.getItems().add(hBox);
        }

    }
}

