package main.ui.doctor.appointments;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.skins.JFXDatePickerSkin;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import database.DoctorDao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Appointment;
import util.Util;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {
    @FXML
    private HBox datePickerRoot;
    @FXML
    private Label selectedDateLabel;
    @FXML
    private ListView<HBox> prescriptionList;

    private LocalDate selectedDate;
    private DoctorDao doctorDao;

    @FXML
    void onSearchClick(ActionEvent event) {
        prescriptionList.getItems().clear();
        initList(selectedDate);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        doctorDao = new DoctorDao();
        JFXDatePicker datePicker = new JFXDatePicker();
        datePicker.getStyleClass().add("date-picker-style");
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
            selectedDateLabel.setText(selectedDate.toString());
        });

        initList(LocalDate.now());

    }

    private void initList(LocalDate date) {
        ArrayList<Appointment> appointments = doctorDao.getAppointmentList(
                Util.getInstance().getUserId(),
                date);
        // create appointment list
        for (Appointment appointment : appointments) {
            HBox hBox = createCard(
                    appointment.getPatientName(),
                    Util.formatDate(appointment.getDate()),
                    Util.convert24to12format(appointment.getStartTime().toString()) + " - " +
                            Util.convert24to12format(appointment.getEndTime().toString()),
                    appointment.getReason());
            prescriptionList.getItems().add(hBox);
        }

    }

    public HBox createCard(String name, String date, String time, String reason) {
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

        Label nameLabel = new Label(name);
        nameLabel.getStyleClass().add("text-sub-heading");
        Label dateLabel = new Label(date);
        dateLabel.getStyleClass().add("text-sub-heading-light");
        Label timeLabel = new Label(time);
        timeLabel.getStyleClass().add("text-sub-heading-light");
        vBox.getChildren().addAll(nameLabel, dateLabel, timeLabel);

        HBox hBox1 = new HBox();
        HBox.setHgrow(hBox1, Priority.ALWAYS);
        hBox1.setAlignment(Pos.CENTER_RIGHT);

        Label reasonLabel = new Label(reason);
        reasonLabel.getStyleClass().add("text-sub-heading-light");
        reasonLabel.setPrefWidth(200);
        reasonLabel.setWrapText(true);


        hBox1.getChildren().add(reasonLabel);

        hBox.getChildren().addAll(icon, vBox, hBox1);
        return hBox;

    }
}
