package main.ui.doctor.patients;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewPrescriptionController implements Initializable {
    @FXML
    private JFXButton searchButton;

    @FXML
    private Label patientNameLabel;

    @FXML
    private ListView<HBox> prescriptionList;

    @FXML
    private Label resultFoundLabel;

    @FXML
    private TextField doctorNameTv;

    @FXML
    void onSearchClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        doctorNameTv.setStyle("-fx-background-image: url('/resources/icons/ic_search.png');");

        // create patient's prescription history list
        for (int i = 0; i < 10; i++) {
            HBox hBox = createCard("TOM JARKINS",
                    "12 MARCH 2021",
                    "REASON: THROAT PAIN",
                    false,
                    "HEADER NAME");
            HBox btnContainer = (HBox) hBox.getChildren().get(2);
            JFXButton button = (JFXButton) btnContainer.getChildren().get(0);
            int finalI = i;
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("clicked index: " + finalI);
                }
            });
            prescriptionList.getItems().add(hBox);
        }
    }

    public HBox createCard(String name, String date, String reason, boolean isHeader, String header) {
        HBox hBox = new HBox();
        if (!isHeader) {
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
            Label reasonLabel = new Label(reason);
            reasonLabel.getStyleClass().add("text-sub-heading-light");
            reasonLabel.setWrapText(true);
            vBox.getChildren().addAll(nameLabel, dateLabel, reasonLabel);

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
        } else {
            Label nameLabel = new Label(header);
            nameLabel.getStyleClass().add("text-sub-heading");
            hBox.getChildren().add(nameLabel);
            hBox.setAlignment(Pos.CENTER_LEFT);
        }
        return hBox;


    }

}
