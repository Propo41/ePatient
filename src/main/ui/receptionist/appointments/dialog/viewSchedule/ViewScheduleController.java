package main.ui.receptionist.appointments.dialog.viewSchedule;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import database.AppointmentDao;
import database.DoctorDao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Appointment;
import model.Schedule;
import util.Util;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class ViewScheduleController implements Initializable {
    private static final int VISITING_DURATION = 30; // 30 mins assumed
    @FXML
    private ListView<HBox> scheduleListView;
    @FXML
    private Label label;

    private LocalDate selectedDate;

    @FXML
    private JFXDatePicker datePicker;
    private String doctorId;
    private ArrayList<Schedule> visitingHours;

    @FXML
    void onDismissClick(ActionEvent event) {
        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedDate = LocalDate.now();
        datePicker.setOnAction(event -> {
            selectedDate = datePicker.getValue();
            initItems(selectedDate);
            System.out.println("date selected: " + selectedDate);

        });

        // getting doctor visiting hours
        visitingHours = new DoctorDao().getDoctorVisitingHours(Util.getInstance().getUserId());
        initItems(selectedDate);

    }


    private Schedule isDoctorAvailable(LocalDate date) {
        String dayPicked = date.getDayOfWeek().name().toLowerCase();
        for (Schedule s : visitingHours) {
            System.out.println("visiting hours: " + s.getDay());
            if (s.getDay().toLowerCase().equals(dayPicked)) {
                return s;
            }
        }
        return null;
    }

    private String incrementTime(String time) {
        String a = time.substring(0, 2);
        String b = time.substring(3, 5);
        String c = time.substring(6, 8);
        int res = Integer.parseInt(b) + VISITING_DURATION;
        b = String.valueOf(res % 60);
        String l;
        if (b.equals("0")) {
            b = "00";
        }
        if (res >= 60) {
            if (Integer.parseInt(a) == 11) {
                a = "12";
                if (c.equals("PM")) {
                    c = "AM";
                } else {
                    c = "PM";
                }
                return a + ":" + b + " " + c;
            } else if (Integer.parseInt(a) == 12) {
                return "01" + ":" + b + " " + c;
            } else {
                String y = (Integer.parseInt(a) + 1) + ":" + b + " " + c;
                String[] str = y.split(":");
                if (str[0].length() == 1) {
                    a = "0" + str[0];
                    l = a + ":" + str[1];
                    return l;
                }
                return y;
            }
        } else {
            return a + ":" + b + " " + c;
        }

    }

    private void initItems(LocalDate date) {
        scheduleListView.getItems().clear();
        ArrayList<Appointment> schedule = new DoctorDao().getSchedule(doctorId, date);
        System.out.println("schedule: " + schedule);
        Schedule s = isDoctorAvailable(date);
        if (s != null) {
            System.out.println("doctor starts: " + s.getStartTime());
            System.out.println("doctor ends: " + s.getEndTime());
            String t = s.getStartTime();
            int l = schedule.size();
            int i = 0;
            String formattedString = "";

            while (!t.equals(s.getEndTime())) {
                if (l == 0) {
                    HBox hBox = createCard(null, t, true);
                    scheduleListView.getItems().add(hBox);
                } else {
                    if (i < l) {
                        formattedString = Util.convert24to12format(schedule.get(i).getStartTime().toString());
                    }
                    if (t.equals(formattedString)) {
                        HBox hBox = createCard(schedule.get(i), t, false);
                        scheduleListView.getItems().add(hBox);
                        i++;
                    } else {
                        HBox hBox = createCard(null, t, true);
                        scheduleListView.getItems().add(hBox);
                    }
                }


                t = incrementTime(t);
            }

        } else {
            // doctor has no visiting hours on that day
            System.out.println("NO visiting hours for the day");
        }


    }

    public HBox createCard(Appointment schedule, String time, boolean isFree) {
        HBox hBoxParent = new HBox();
        hBoxParent.setSpacing(5);
        Label timeLabel = new Label(time);
        timeLabel.getStyleClass().add("card-title");
        HBox hBox = new HBox();
        hBox.getStyleClass().add("card-background");
        hBox.setPadding(new Insets(10.0d, 20.0d, 10.0d, 20.0d));
        hBox.setSpacing(20);
        hBox.setPrefHeight(100);
        hBox.setPrefWidth(300);
        HBox.setHgrow(hBox, Priority.ALWAYS);
        if (!isFree) {
            ImageView icon = new ImageView();
            icon.getStyleClass().add("user-icon");
            icon.setFitWidth(70);
            icon.setFitHeight(70);

            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER_LEFT);
            vBox.setSpacing(5);
            vBox.setPrefWidth(500);

            Label nameLabel = new Label(schedule.getPatientName());
            nameLabel.getStyleClass().add("text-sub-heading");

            Label reasonLabel = new Label("Reason: " + schedule.getReason());
            reasonLabel.getStyleClass().add("text-sub-heading-light");

            vBox.getChildren().addAll(nameLabel, reasonLabel);
            hBox.getChildren().addAll(icon, vBox);
        }

        hBoxParent.getChildren().addAll(timeLabel, hBox);

        return hBoxParent;

    }

    public void setContent(String doctorId) {
        this.doctorId = doctorId;

    }
}
