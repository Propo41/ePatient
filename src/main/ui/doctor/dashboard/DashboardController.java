package main.ui.doctor.dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.ui.database.DoctorDao;
import util.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private VBox root;

    @FXML
    private JFXListView<?> visitingHoursListView;

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
        String totalAppointments = doctorDao.getTotalAppointments();
        totalAppointmentTv.setText(totalAppointments);


        //ArrayList<String> list = mssql.getPatientHistory();

        // create recent patients list
        for (int i = 0; i < 10; i++) {
            HBox hBox = createCard("Gabbie", "12 March 2021");
            HBox btnContainer = (HBox) hBox.getChildren().get(2);
            JFXButton button = (JFXButton) btnContainer.getChildren().get(0);
            int finalI = i;
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("clicked index: " + finalI);
                }
            });
            patientListView.getItems().add(hBox);
        }


    }


   /* @FXML
    void onMoreClick(ActionEvent event) {

        // loader.setController(new DialogMyPatientsController("message"));
        try {
            // final FXMLLoader loader = new FXMLLoader(getClass().getResource("Dialog.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dialog_my_patients.fxml"));
            loader.setController(new DialogMyPatientsController("message"));

            Parent root = loader.load();
            Scene scene = new Scene(root, 250, 150);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initOwner(totalAppointmentTv.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }*/

    public HBox createCard(String name, String date) {
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
        viewMoreBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });

        hBox1.getChildren().add(viewMoreBtn);

        hBox.getChildren().addAll(icon, vBox, hBox1);
        return hBox;

    }

}
