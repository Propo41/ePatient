package main.ui.doctor.prescription;

import com.jfoenix.controls.JFXButton;
import database.DoctorDao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Patient;
import util.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PrescriptionController implements Initializable {
    @FXML
    private ListView<HBox> patientListView;

    @FXML
    private TextField patientSearchTv;

    @FXML
    private VBox root;

    @FXML
    private Label resultsFoundLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patientSearchTv.setStyle("-fx-background-image: url('/resources/icons/ic_search.png');");
        //  image.setImage(new Image("/resources/icons/ic_search.png"));


    }

    @FXML
    void onSearchClick(ActionEvent event) {
        String keyword = patientSearchTv.getText();
        patientListView.getItems().clear();
        resultsFoundLabel.setVisible(true);
        if(!keyword.isEmpty()){
            DoctorDao doctorDao = new DoctorDao();
            ArrayList<Patient> patientList = doctorDao.getPatientList(patientSearchTv.getText());
            resultsFoundLabel.setText(patientList.size() + " SEARCH RESULTS FOUND");
            createCardItems(patientList);
        }else{
            resultsFoundLabel.setText("0 SEARCH RESULTS FOUND");


        }
    }

    @FXML
    void onCreatePrescriptionClick(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("create_prescription.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Create a New Prescription");
            stage.setResizable(false);
            stage.setScene(new Scene(loader.load(), Util.DIALOG_SCREEN_WIDTH, Util.DIALOG_SCREEN_HEIGHT));
           // CreatePrescriptionController controller = loader.getController();
          //  controller.setContent(patient.getId());
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * creates the desired number of items, passed as a parameter
     */
    private void createCardItems( ArrayList<Patient> patientList) {
        int maxItemsPerRow = 5;
        int rows = patientList.size() / 5;
        while (rows != 0) {
            createCardsPerRow(maxItemsPerRow, patientList);
            rows--;
        }
        createCardsPerRow(patientList.size() % maxItemsPerRow, patientList);
    }

    /**
     * creates i number of items, with each row having $maxItemsPerRow items
     * called implicitly from createCardItems()
     */
    private void createCardsPerRow(int i, ArrayList<Patient> patientList) {
        i--; // used since the array index starts from 0
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        while(i!=-1){
            VBox vBox = createCard(patientList.get(i));
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
    private VBox createCard(Patient patient) {
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

        Label nameLabel = new Label(patient.getName());
        nameLabel.getStyleClass().add("text-sub-heading-bold");
        nameLabel.setWrapText(true);
        nameLabel.setTextAlignment(TextAlignment.CENTER);

        Label subtitleLabel = new Label("PATIENT SINCE " + Util.formatDate(patient.getJoinedDate()));
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
                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("view_patient.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle(patient.getName());
                    stage.setResizable(false);
                    stage.setScene(new Scene(loader.load(), Util.DIALOG_SCREEN_WIDTH, Util.DIALOG_SCREEN_HEIGHT));
                    ViewPatientController controller = loader.getController();
                    controller.setContent(patient.getId());
                    stage.show();
                }catch (Exception e){
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
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("view_prescription_history.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle(patient.getName());
                    stage.setResizable(false);
                    stage.setScene(new Scene(loader.load(), Util.DIALOG_SCREEN_WIDTH, Util.DIALOG_SCREEN_HEIGHT));
                    ViewPrescriptionHistoryController controller = loader.getController();
                    controller.createPrescriptionHistory(patient.getId());
                    stage.show();
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