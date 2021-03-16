package main.ui.receptionist.appointments;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;
import database.AppointmentDao;
import database.DoctorDao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import main.ui.doctor.prescription.ViewPatientController;
import main.ui.doctor.prescription.ViewPrescriptionHistoryController;
import model.Appointment;
import model.Patient;
import util.Util;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {
    @FXML
    private HBox datePickerRoot;

    @FXML
    private Label resultsFoundLabel;

    @FXML
    private TextField doctorSearchTv;

    @FXML
    private ListView<HBox> viewAppointmentsListView;

    @FXML
    private ListView<HBox> createAppointmentListView;

    @FXML
    private StackPane stackPaneRoot;

    @FXML
    private JFXTabPane tabPane;

    @FXML
    private Tab healthCondTab;

    @FXML
    private TextField patientSearchTv;

    private LocalDate selectedDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patientSearchTv.setStyle("-fx-background-image: url('/resources/icons/ic_search.png');");
        selectedDate = LocalDate.now();

        JFXDatePicker datePicker = new JFXDatePicker();
        datePicker.getStyleClass().add("date-picker-style-white-bg");
        HBox.setHgrow(datePicker, Priority.NEVER);
        datePickerRoot.getChildren().add(datePicker);

        // disable previous dates from the datepicker dialog
        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0);
            }
        });

        datePicker.setOnAction(event -> {
            selectedDate = datePicker.getValue();
            initList(selectedDate);
            System.out.println("date selected: " + selectedDate);

        });

        initList(selectedDate);

    }

    private void initList(LocalDate date) {
        viewAppointmentsListView.getItems().clear();
        ArrayList<Appointment> appointments = new AppointmentDao().getAppointmentInfo(date);
        System.out.println("list obtained size: " + appointments.size());
        // create appointment list
        for (Appointment appointment : appointments) {
            HBox hBox = createCard(appointment);
            viewAppointmentsListView.getItems().add(hBox);
        }

    }


    @FXML
    void onSearchViewAppointmentsClick(ActionEvent event) {
        String keyword = patientSearchTv.getText();
        viewAppointmentsListView.getItems().clear();
        if (!keyword.isEmpty()) {
            ArrayList<Appointment> appointments = new AppointmentDao().getAppointmentInfoByKeyword(
                    patientSearchTv.getText(), selectedDate);
            for (Appointment appointment : appointments) {
                HBox hBox = createCard(appointment);
                viewAppointmentsListView.getItems().add(hBox);
            }
        } else {
            initList(selectedDate);
        }

    }

    @FXML
    void onSearchCreateAppointmentClick(ActionEvent event) {

    }

    @FXML
    void onCreateAppointmentClick(ActionEvent event) {

    }

    public HBox createCard(Appointment appointment) {
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

        Label nameLabel = new Label(appointment.getPatientName());
        nameLabel.getStyleClass().add("text-sub-heading");
        Label dateLabel = new Label(Util.formatDate(appointment.getDate()));
        dateLabel.getStyleClass().add("text-sub-heading-light");
        Label timeLabel = new Label(
                Util.convert24to12format(appointment.getStartTime().toString()) + " - " +
                        Util.convert24to12format(appointment.getEndTime().toString()));
        timeLabel.getStyleClass().add("text-sub-heading-light");
        vBox.getChildren().addAll(nameLabel, dateLabel, timeLabel);

        HBox hBox1 = new HBox();
        HBox.setHgrow(hBox1, Priority.ALWAYS);
        hBox1.setAlignment(Pos.CENTER_RIGHT);

        VBox vBox1 = new VBox();
        hBox1.getChildren().add(vBox1);
        vBox1.setSpacing(5);

        JFXButton approveBtn = new JFXButton("APPROVE");
        approveBtn.getStyleClass().add("button-text-only-small");
        vBox1.getChildren().add(approveBtn);

        JFXButton cancelBtn = new JFXButton("CANCEL");
        approveBtn.getStyleClass().add("button-text-only-small-red");
        vBox1.getChildren().add(cancelBtn);

        approveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        hBox.getChildren().addAll(icon, vBox, hBox1);
        return hBox;

    }

}
