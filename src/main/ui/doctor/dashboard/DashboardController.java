package main.ui.doctor.dashboard;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import database.DoctorDao;
import javafx.stage.Stage;
import main.ui.doctor.patients.ViewPatientController;
import model.Patient;
import model.Schedule;
import util.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private VBox root;

    @FXML
    private ListView<HBox> visitingHoursListView;

    @FXML
    private Label totalVisitsTv;

    @FXML
    private Label totalAppointmentTv;

    @FXML
    private JFXButton showMorePatientsBtn;

    @FXML
    private Label totalBilledTv;

    @FXML
    private ListView<HBox> patientListView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DoctorDao doctorDao = new DoctorDao(Util.getInstance().getUserId());
        // getting total appointments
        String totalAppointments = doctorDao.getTotalAppointments();
        totalAppointmentTv.setText(totalAppointments);
        totalVisitsTv.setText(doctorDao.getTotalVisits());
        totalBilledTv.setText(doctorDao.getTotalBill());

        ArrayList<Patient> recentPatientList = doctorDao.getRecentPatientList();
        // create recent patients list
        for (int i = 0; i < recentPatientList.size(); i++) {
            Patient patient = recentPatientList.get(i);
            HBox hBox = createCard(patient.getName(), Util.formatDate(patient.getJoinedDate()));
            HBox btnContainer = (HBox) hBox.getChildren().get(2);
            JFXButton button = (JFXButton) btnContainer.getChildren().get(0);
            int index = i;
            button.setOnAction(event ->
                    viewMore(recentPatientList.get(index)));
            patientListView.getItems().add(hBox);
        }

        // getting doctor visiting hours
        ArrayList<Schedule> visitingHours = doctorDao.getDoctorVisitingHours();
        for (Schedule visitingHour : visitingHours) {
            HBox hBox = createVisitingHours(visitingHour);
            visitingHoursListView.getItems().add(hBox);
        }


    }

    private void viewMore(Patient patient) {
        Parent root;
        try {
             root = FXMLLoader.load(getClass().getResource("../patients/view_patient.fxml"));
            // root = FXMLLoader.load(getClass().getResource("../patients/view_patient.fxml"));
          /*  ViewPatientController controller = new ViewPatientController(patient.getId());
            controller.setContent("a");
*/
            Stage stage = new Stage();
            stage.setTitle(patient.getName());
            stage.setResizable(false);
            stage.setScene(new Scene(root, Util.DIALOG_SCREEN_WIDTH, Util.DIALOG_SCREEN_HEIGHT));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private HBox createVisitingHours(Schedule schedule) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(20);

        ImageView imageView = new ImageView();
        imageView.getStyleClass().add("bullet-icon");
        hBox.getChildren().add(imageView);

        Label label = new Label(
                schedule.getDay() + ": " +
                        schedule.getStartTime() + " - " +
                        schedule.getEndTime());
        label.getStyleClass().add("text-sub-heading");
        hBox.getChildren().add(label);

        return hBox;
    }


    public HBox createCard(String name, String date) {
        name = name.toUpperCase();
        date = date.toUpperCase();

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
        vBox.getChildren().addAll(nameLabel, dateLabel);


        HBox hBox1 = new HBox();
        HBox.setHgrow(hBox1, Priority.ALWAYS);
        hBox1.setAlignment(Pos.CENTER_RIGHT);

        JFXButton viewMoreBtn = new JFXButton();
        viewMoreBtn.setText("VIEW MORE");
        viewMoreBtn.setPrefHeight(50);
        viewMoreBtn.setPrefWidth(200);

        viewMoreBtn.getStyleClass().add("button-text-only-small");
        hBox1.getChildren().add(viewMoreBtn);

        hBox.getChildren().addAll(icon, vBox, hBox1);
        return hBox;

    }


}
