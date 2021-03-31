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
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import main.ui.doctor.prescription.ViewPatientController;
import main.ui.doctor.prescription.ViewPrescriptionHistoryController;
import main.ui.receptionist.appointments.dialog.ViewScheduleController;
import model.Appointment;
import model.Doctor;
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

    @FXML
    private JFXDatePicker datePicker;


    @FXML
    private Tab createAppointmentTab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patientSearchTv.setStyle("-fx-background-image: url('/resources/icons/ic_search.png');");
        doctorSearchTv.setStyle("-fx-background-image: url('/resources/icons/ic_search.png');");

        selectedDate = LocalDate.now();
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

        initList(selectedDate); // loads the default date

      /*  tabPane.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            String tabName = tabPane.getSelectionModel().getSelectedItem().getText();

        });*/


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
        ArrayList<Doctor> doctorList = new DoctorDao().getDoctorList(doctorSearchTv.getText());
        // resultsFoundLabel.setText(doctorList.size() + " SEARCH RESULTS FOUND");
        createCardItems(doctorList);

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
        vBox1.setAlignment(Pos.CENTER_LEFT);

        JFXButton approveBtn = new JFXButton("APPROVE");
        approveBtn.getStyleClass().add("button-text-only-small");
        vBox1.getChildren().add(approveBtn);

        JFXButton cancelBtn = new JFXButton("CANCEL");
        cancelBtn.getStyleClass().add("button-text-only-small-red");
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


    /**
     * creates the desired number of items, passed as a parameter
     */
    private void createCardItems(ArrayList<Doctor> doctorList) {
        int maxItemsPerRow = 5;
        int rows = doctorList.size() / 5;
        while (rows != 0) {
            createCardsPerRow(maxItemsPerRow, doctorList);
            rows--;
        }
        createCardsPerRow(doctorList.size() % maxItemsPerRow, doctorList);
    }

    /**
     * creates i number of items, with each row having $maxItemsPerRow items
     * called implicitly from createCardItems()
     */
    private void createCardsPerRow(int i, ArrayList<Doctor> doctorList) {
        i--; // used since the array index starts from 0
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        while (i != -1) {
            VBox vBox = createCardGrid(doctorList.get(i));
            // vBox.setMaxWidth(290);
            hBox.getChildren().add(vBox);
            i--;
        }
        createAppointmentListView.getItems().add(hBox);

    }

    /*
     * creates a single card item
     * called implicitly from createCardsPerRow()
     */
    private VBox createCardGrid(Doctor doctor) {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("card-background");
        vBox.setPadding(new Insets(20.0d, 20.0d, 20.0d, 20.0d));
        vBox.setSpacing(8);
        vBox.setPrefWidth(220);
        vBox.setAlignment(Pos.TOP_CENTER);
        HBox.setHgrow(vBox, Priority.SOMETIMES);

        ImageView icon = new ImageView();
        icon.getStyleClass().add("user-icon");
        icon.setFitWidth(70);
        icon.setFitHeight(70);

        VBox labelContainer = new VBox();
        VBox.setVgrow(labelContainer, Priority.ALWAYS);
        labelContainer.setAlignment(Pos.TOP_CENTER);

        Label nameLabel = new Label(doctor.getName());
        nameLabel.getStyleClass().add("text-sub-heading-bold");
        nameLabel.setWrapText(true);
        nameLabel.setTextAlignment(TextAlignment.CENTER);

        Label subtitleLabel = new Label("Specialist in " + doctor.getSpecialist());
        subtitleLabel.getStyleClass().add("text-card-subtitle");
        subtitleLabel.setWrapText(true);
        subtitleLabel.setTextAlignment(TextAlignment.CENTER);

        labelContainer.getChildren().add(nameLabel);
        labelContainer.getChildren().add(subtitleLabel);

        JFXButton viewBtn = new JFXButton();
        viewBtn.setText("VIEW SCHEDULE");
        viewBtn.setPrefWidth(220);
        viewBtn.getStyleClass().add("button-primary-small");
        viewBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("./dialog/view_schedule.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Schedule | " + doctor.getName());
                    stage.setResizable(false);
                    stage.setScene(new Scene(loader.load(), Util.DIALOG_SCREEN_WIDTH, Util.DIALOG_SCREEN_HEIGHT));
                    ViewScheduleController controller = loader.getController();
                    controller.setContent(doctor.getDoctorId());
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JFXButton prescriptionBtn = new JFXButton();
        prescriptionBtn.setText("COPY ID");
        prescriptionBtn.setPrefWidth(220);
        prescriptionBtn.getStyleClass().add("button-tertiary-small");
        prescriptionBtn.setOnAction(event -> {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(doctor.getDoctorId());
            clipboard.setContent(content);

        });

        vBox.getChildren().add(icon);
        vBox.getChildren().add(labelContainer);
        vBox.getChildren().add(viewBtn);
        vBox.getChildren().add(prescriptionBtn);

        return vBox;
    }

}
