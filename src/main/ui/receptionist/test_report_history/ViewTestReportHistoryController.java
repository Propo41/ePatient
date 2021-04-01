package main.ui.receptionist.test_report_history;

import com.jfoenix.controls.JFXButton;
import database.DoctorDao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.MedicalTestDetails;
import model.Patient;
import util.Util;

import java.util.ArrayList;

public class ViewTestReportHistoryController {

    @FXML
    private TextField testReportSearchTv;

    @FXML
    private Label searchResultNumber;

    @FXML
    private ListView<HBox> testReportListView;

    @FXML
    void onSearchBtnClick(ActionEvent event) {
        testReportSearchTv.setStyle("-fx-background-image: url('/resources/icons/ic_search.png');");
        testReportListView.getItems().clear();
        ArrayList<MedicalTestDetails> recentPatientList = new DoctorDao().getTestReport(testReportSearchTv.getText());
        searchResultNumber.setText(recentPatientList.size() + " SEARCH RESULTS FOUND");
        for (int i = 0; i < recentPatientList.size(); i++){
            MedicalTestDetails medicalTestDetails = recentPatientList.get(i);
            HBox hBox = createCard(medicalTestDetails.getDoctorName(),medicalTestDetails.getPatientName(),
                    medicalTestDetails.getTestDate(), i);
            testReportListView.getItems().add(hBox);
        }
    }

    public HBox createCard(String patientName, String doctorName, String date, int index){
        patientName = patientName.toUpperCase();
        doctorName = doctorName.toUpperCase();

        HBox hBox = new HBox();
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

        Label nameLabel = new Label(patientName);
        nameLabel.getStyleClass().add("text-sub-heading");
        Label dateLabel = new Label(doctorName);
        dateLabel.getStyleClass().add("text-sub-heading-light");
        vBox.getChildren().addAll(nameLabel, dateLabel);

        HBox hBox1 = new HBox();
        HBox.setHgrow(hBox1, Priority.ALWAYS);
        hBox1.setAlignment(Pos.CENTER_RIGHT);

        JFXButton viewMoreBtn = new JFXButton();
        viewMoreBtn.setPrefHeight(50);
        viewMoreBtn.setPrefWidth(50);
        viewMoreBtn.setPadding(new Insets(0, 20, 0, 0));
        viewMoreBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Pressed " + index);
            }
        });

        Image img = new Image("/resources/icons/ic_pdf.png");
        ImageView view = new ImageView(img);
        viewMoreBtn.setGraphic(view);
        nameLabel.getStyleClass().add("text-sub-heading");

        //hBox1.getChildren().add(newLabel);
        Label newLabel = new Label(date);
        newLabel.getStyleClass().add("text-sub-heading");
        newLabel.setPrefWidth(170);
        newLabel.setPadding(new Insets(0,15,0,0));
        hBox1.getChildren().addAll(newLabel,viewMoreBtn);
        hBox.getChildren().addAll(icon, vBox, hBox1);
        return hBox;
    }

}
