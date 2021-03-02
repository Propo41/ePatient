package main.ui.doctor;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import main.ui.database.DoctorDao;
import util.Util;

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
    void onMoreClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DoctorDao doctorDao = new DoctorDao(Util.getInstance().getUserId());
        String totalAppointments = doctorDao.getTotalAppointments();
        totalAppointmentTv.setText(totalAppointments);


       //ArrayList<String> list = mssql.getPatientHistory();

      //  String name = mssql.getDoctorName();
       /* if (name != null) {
            doctorNameTv.setText(name.toUpperCase());
        }*/

        createCard();


    }

    private void createCard() {
        HBox hBox = new HBox();
        hBox.getStyleClass().add("card-background");
        hBox.setPadding(new Insets(10.0d, 20.0d, 10.0d, 20.0d));
        hBox.setSpacing(20);
        hBox.setPrefHeight(100);

        ImageView icon = new ImageView(new Image("https://icons.iconarchive.com/icons/double-j-design/origami-colored-pencil/128/blue-home-icon.png"));
        icon.setFitWidth(70);
        icon.setFitHeight(70);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setSpacing(5);
        vBox.setPrefWidth(500);

        Label nameLabel = new Label("Hello");
        nameLabel.getStyleClass().add("text-sub-heading");
        Label dateLabel = new Label("24 March 2010");
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
        root.getChildren().add(hBox);
    }
}
