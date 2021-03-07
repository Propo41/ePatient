package main.ui.doctor.patients;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewPatientController implements Initializable {

    @FXML
    private Label contactLabel;

    @FXML
    private Label bloodGroupLabel;

    @FXML
    private ListView<HBox> medicalHistoryListView;

    @FXML
    private Label genderLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label heightLabel;

    @FXML
    private TextArea historyTv;

    @FXML
    private Label weightLabel;

    @FXML
    private JFXButton closeButton;

    @FXML
    private Label emergencyContactLabel;

    @FXML
    private Label patientNameLabel;

    @FXML
    private ListView<HBox> socialHistoryListView;

    @FXML
    private Label ageLabel;

    @FXML
    private Label dobLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label patientIdLabel;

    @FXML
    void onCloseClick(ActionEvent event) {

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 7; i++) {
            HBox hBox = createList("Alcohol: Yes");
            socialHistoryListView.getItems().add(hBox);
        }

        for (int i = 0; i < 7; i++) {
            HBox hBox = createList("Tonsillitis");
            medicalHistoryListView.getItems().add(hBox);
        }
    }

    private HBox createList(String name) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(20);

        ImageView imageView = new ImageView();
        imageView.getStyleClass().add("bullet-icon");
        hBox.getChildren().add(imageView);

        Label label = new Label(name);
        label.getStyleClass().add("text-label-ash-background");
        hBox.getChildren().add(label);

        return hBox;
    }
}
