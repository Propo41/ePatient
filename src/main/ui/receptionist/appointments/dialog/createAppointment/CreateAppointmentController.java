package main.ui.receptionist.appointments.dialog.createAppointment;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import database.AppointmentDao;
import database.DoctorDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import model.Appointment;
import model.Schedule;
import util.Util;

import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class CreateAppointmentController implements Initializable {
    @FXML
    private TextField patientTv;

    @FXML
    private TextField doctorTv;

    @FXML
    private TextField reasonTv;

    @FXML
    private Label selectedDateLabel;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXComboBox<String> availableTimePicker;
    private JFXDialog dialog;


    @FXML
    private StackPane stackPaneRoot;
    private String doctorId = "";
    private LocalDate selectedDate;
    private static final int VISITING_DURATION = 30; // 30 minutes assumed
    private static final String NO_SLOTS_FREE = "No available time for the day"; // 30 mins assumed
    private ArrayList<Schedule> visitingHours;
    private ArrayList<String> availableTime;
    private String selectedTime = NO_SLOTS_FREE;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        availableTime = new ArrayList<>();
        selectedDate = LocalDate.now();
        // disable previous dates from the datepicker dialog
        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0);
            }
        });
        visitingHours = new DoctorDao().getDoctorVisitingHours(Util.getInstance().getUserId());
        datePicker.setOnAction(event -> {
            selectedDate = datePicker.getValue();
            selectedDateLabel.setText(selectedDate.toString());
            System.out.println("date selected: " + selectedDate);
            availableTimePicker.getItems().clear();
            availableTime.clear();
            selectedTime = NO_SLOTS_FREE;
            this.doctorId = doctorTv.getText();
            if (!doctorTv.getText().equals("")) {
                initAvailableTime(selectedDate, doctorId);
            } else {
                System.out.println("You must enter the doctorId");
            }
        });



    }

    private void initAvailableTime(LocalDate date, String doctorId) {
        ArrayList<Appointment> schedule = new DoctorDao().getSchedule(String.valueOf(doctorId), date);
        Schedule s = isDoctorAvailable(date);
        int l = schedule.size();
        if (s != null) {

            String t = s.getStartTime();
            int i = 0;
            String formattedString = "";
            while (!t.equals(s.getEndTime())) {
                if (l == 0) {
                    availableTime.add(t + " - " + incrementTime(t));
                } else {
                    if (i < l) {
                        formattedString = Util.convert24to12format(schedule.get(i).getStartTime().toString());
                    }
                    if (t.equals(formattedString)) {
                        i++;
                    } else {
                        availableTime.add(t + " - " + incrementTime(t));
                    }
                }
                t = incrementTime(t);
            }


        } else {
            // doctor has no visiting hours on that day
            System.out.println("NO visiting hours for the day");
        }
        if (availableTime.size() == 0) {
            availableTimePicker.getItems().add(NO_SLOTS_FREE);
        } else {
            availableTimePicker.getItems().addAll(availableTime);
            availableTimePicker.setOnAction(event -> selectedTime = availableTimePicker.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void onCreateAppointmentClick(ActionEvent event) {
        if (!selectedTime.equals(NO_SLOTS_FREE)) {
            Time startTime = convertToTimeInstance(selectedTime.split("-")[0].trim());
            Time endTime = convertToTimeInstance(selectedTime.split("-")[1].trim());
            String patientId = patientTv.getText();
            String doctorId = doctorTv.getText();
            String reason = reasonTv.getText();
            LocalDate date = selectedDate;

            new AppointmentDao().createAppointment
                    (new Appointment(patientId, doctorId, startTime, endTime, reason, date));
            try {
                JFXDialogLayout content = new JFXDialogLayout();
                content.getStyleClass().add("jfx-dialog-overlay-pane");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../appointmentCreated/dialog_appointment_created.fxml"));
                loader.load();
                dialog = new JFXDialog(stackPaneRoot, loader.getRoot(), JFXDialog.DialogTransition.CENTER);
                dialog.getStyleClass().add("jfx-dialog-layout");
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private Time convertToTimeInstance(String time) {
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        Date date = Time.valueOf(LocalTime.now());
        try {
            date = parseFormat.parse(time);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Time.valueOf(displayFormat.format(date) + ":00");
    }

    // called implicitly from initAvailableTime()
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

    // called implicitly from initAvailableTime()
    private String incrementTime(String time) {
        String a = time.substring(0, 2);
        String b = time.substring(3, 5);
        String c = time.substring(6, 8);
        String l = time;
        int res = Integer.parseInt(b) + VISITING_DURATION;
        b = String.valueOf(res % 60);
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


}
