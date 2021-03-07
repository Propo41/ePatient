package main.ui.doctor.appointments;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.skins.JFXDatePickerSkin;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {
    @FXML
    private HBox datePickerRoot;

    @FXML
    private Label selectedDateLabel;

    @FXML
    private JFXButton searchButton;

    @FXML
    private ListView<HBox> prescriptionList;

    @FXML
    void onSearchClick(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JFXDatePicker datePicker = new JFXDatePicker();
        datePicker.getStyleClass().add("date-picker-style");
        HBox.setHgrow(datePicker, Priority.NEVER);
        datePickerRoot.getChildren().add(datePicker);

        datePicker.setOnAction(event -> {
            LocalDate date = datePicker.getValue();
            selectedDateLabel.setText(date.toString());
        });

        // create recent patients list
        for (int i = 0; i < 10; i++) {
            HBox hBox = createCard("Gabbie", "12 March 2021", "9:00AM - 9:00PM",
                    "REASON: THROAT PAIN");
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
