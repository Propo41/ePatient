package main.ui.doctor.prescription;

import com.jfoenix.controls.JFXButton;
import database.DoctorDao;
import database.PatientDao;
import database.PrescriptionDao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Prescription;
import util.Util;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewPrescriptionHistoryController implements Initializable {
    @FXML
    private JFXButton searchButton;

    @FXML
    private Label patientNameLabel;

    @FXML
    private ListView<HBox> prescriptionList;

    @FXML
    private Label resultFoundLabel;

    @FXML
    private TextField doctorNameTv;
    private String patientId;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        doctorNameTv.setStyle("-fx-background-image: url('/resources/icons/ic_search.png');");
        prescriptionList.getStyleClass().add("list-bg-color");
    }


    @FXML
    void onSearchClick(ActionEvent event) {
        prescriptionList.getItems().clear();
        if (doctorNameTv.getText().isEmpty()) {
            createPrescriptionHistory(patientId);
            resultFoundLabel.setVisible(false);
        } else {
            ArrayList<Object> _prescriptionList = new PrescriptionDao().getPrescriptionHistory(patientId, doctorNameTv.getText());
            resultFoundLabel.setVisible(true);
            resultFoundLabel.setText((_prescriptionList.size() == 0 ? "0" : _prescriptionList.size() - 1) + " RESULTS FOUND");
            // create patient's prescription history list
            for (Object o : _prescriptionList) {
                HBox hBox = createCard(o);
                prescriptionList.getItems().add(hBox);
            }
        }

    }

    /**
     * called from PatientsController
     */
    public void createPrescriptionHistory(String patientId) {
        this.patientId = patientId;
        ArrayList<Object> _prescriptionList = new PrescriptionDao().getPrescriptionHistory(patientId, "");
        // create patient's prescription history list
        for (Object o : _prescriptionList) {
            HBox hBox = createCard(o);
            prescriptionList.getItems().add(hBox);
        }

        PatientDao patientDao = new PatientDao();
        patientNameLabel.setText(patientDao.getName(patientId));


    }

    public HBox createCard(Object object) {
        HBox hBox = new HBox();
        if (object.getClass().getSimpleName().equals(Prescription.class.getSimpleName())) {
            // this is content
            Prescription prescription = (Prescription) object;
            hBox.getStyleClass().add("card-background");
            hBox.setPadding(new Insets(10.0d, 20.0d, 10.0d, 20.0d));
            hBox.setSpacing(20);
            hBox.setPrefHeight(100);
            hBox.setPrefWidth(300);

            ImageView icon = new ImageView();
            icon.getStyleClass().add("user-icon");
            icon.setFitWidth(70);
            icon.setFitHeight(70);

            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER_LEFT);
            vBox.setSpacing(5);
            vBox.setPrefWidth(500);

            Label reasonLabel = new Label("REASON: " + prescription.getReason());
            reasonLabel.getStyleClass().add("text-sub-heading");
            Label dateLabel = new Label(Util.formatDate(prescription.getDateOfPrescription()));
            dateLabel.getStyleClass().add("text-sub-heading-light");
            vBox.getChildren().addAll(reasonLabel, dateLabel);

            HBox hBox1 = new HBox();
            HBox.setHgrow(hBox1, Priority.ALWAYS);
            hBox1.setAlignment(Pos.CENTER_RIGHT);

            JFXButton viewDetailsBtn = new JFXButton();
            viewDetailsBtn.setText("VIEW DETAILS");
            viewDetailsBtn.setPrefHeight(50);
            viewDetailsBtn.setPrefWidth(200);
            viewDetailsBtn.getStyleClass().add("button-text-only-small");
            viewDetailsBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println(prescription.getPrescriptionId());
                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("view_prescription.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle(prescription.getPrescriptionId());
                        stage.setResizable(false);
                        stage.setScene(new Scene(loader.load(), Util.DIALOG_SCREEN_WIDTH, Util.DIALOG_SCREEN_HEIGHT));
                        ViewPrescriptionController controller = loader.getController();
                        controller.setContent(prescription.getPrescriptionId(), patientNameLabel.getText());
                        stage.show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            hBox1.getChildren().add(viewDetailsBtn);
            hBox.getChildren().addAll(icon, vBox, hBox1);
        } else {
            // this is header
            String headerName = (String) object;
            Label nameLabel = new Label(headerName);
            nameLabel.getStyleClass().add("text-sub-heading");
            hBox.getChildren().add(nameLabel);
            hBox.setAlignment(Pos.CENTER_LEFT);
        }
        return hBox;
    }

}
