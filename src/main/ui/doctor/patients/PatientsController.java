package main.ui.doctor.patients;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import util.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientsController implements Initializable {
    @FXML
    private ListView<HBox> patientListView;

    @FXML
    private TextField patientSearchTv;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patientSearchTv.setStyle("-fx-background-image: url('/resources/icons/ic_search.png');");
      //  image.setImage(new Image("/resources/icons/ic_search.png"));

        createCardItems(8);
    }


    /*
     * creates the desired number of items, passed as a parameter
     */
    private void createCardItems(int items) {
        int maxItemsPerRow = 5;
        int rows = (int) Math.ceil(items / 5.0);
        while (rows != 0) {
            createCardsPerRow(maxItemsPerRow);
            rows--;
        }
        createCardsPerRow(items % maxItemsPerRow);
    }

    /*
     * creates i number of items, with each row having $maxItemsPerRow items
     * called implicitly from createCardItems()
     */
    private void createCardsPerRow(int i) {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        while(i!=0){
            VBox vBox = createCard();
           // vBox.setMaxWidth(290);
            hBox.getChildren().add(vBox);
            i--;
        }
        patientListView.getItems().add(hBox);

    }

    /*
     * creates a single card item
     * called implicitly from createCardsPerRow()
     */
    private VBox createCard() {
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

        Label nameLabel = new Label("Gabbie Carter");
        nameLabel.getStyleClass().add("text-sub-heading-bold");
        nameLabel.setWrapText(true);
        nameLabel.setTextAlignment(TextAlignment.CENTER);

        Label subtitleLabel = new Label("PATIENT SINCE MARCH 10, 2020");
        subtitleLabel.getStyleClass().add("text-card-subtitle");
        subtitleLabel.setWrapText(true);
        subtitleLabel.setTextAlignment(TextAlignment.CENTER);

        JFXButton viewBtn = new JFXButton();
        viewBtn.setText("VIEW");
        viewBtn.setPrefWidth(220);
        viewBtn.getStyleClass().add("button-primary-small");
        viewBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("view_patient.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("$NameOfPatient");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, Util.DIALOG_SCREEN_WIDTH, Util.DIALOG_SCREEN_HEIGHT));
                    stage.show();
                    // Hide this current window (if this is what you want)
                    //((Node)(event.getSource())).getScene().getWindow().hide();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        JFXButton prescriptionBtn = new JFXButton();
        prescriptionBtn.setText("PRESCRIPTIONS");
        prescriptionBtn.setPrefWidth(220);
        prescriptionBtn.getStyleClass().add("button-tertiary-small");
        prescriptionBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("view_prescription.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("My New Stage Title");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, Util.DIALOG_SCREEN_WIDTH, Util.DIALOG_SCREEN_HEIGHT));
                    stage.show();
                    // Hide this current window (if this is what you want)
                    //((Node)(event.getSource())).getScene().getWindow().hide();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        vBox.getChildren().add(icon);
        vBox.getChildren().add(nameLabel);
        vBox.getChildren().add(subtitleLabel);
        vBox.getChildren().add(viewBtn);
        vBox.getChildren().add(prescriptionBtn);

        return vBox;
    }
}

