package main.ui.admin.view_patients;

import com.jfoenix.controls.JFXButton;
import database.DatabaseHandler;
import database.DoctorDao;
import database.PatientDao;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.ui.admin.edit_doctor.EditDoctorController;
import main.ui.admin.edit_patient.EditPatientController;
import model.Patient;
import util.Util;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewPatientController implements Initializable {
    @FXML
    private ListView<HBox> patientListView;

    @FXML
    private TextField patientSearchTv;

    @FXML
    private Label searchResultNumber;

    int count;

    ArrayList<Patient> patientArrayList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        patientSearchTv.setStyle("-fx-background-image: url('/resources/icons/ic_search.png');");
        //image.setImage(new Image("/resources/icons/ic_search.png"));
        searchResultNumber.setText("");
        count = 0;
    }


    @FXML
    void onSearchBtnClick(ActionEvent event) throws SQLException {
        getPatients();
    }

    private void getPatients() {
        patientArrayList = new ArrayList<>();
        String str = patientSearchTv.getText();
        if(str.equals("")){
            return;
        }
        patientListView.getItems().clear();
        count = 0;
        patientArrayList = new PatientDao().getPatientBasicInfo(patientSearchTv.getText());
        searchResultNumber.setText(patientArrayList.size() + " SEARCH RESULTS FOUND");
        createCardItems(patientArrayList.size());
    }

    private void createCardItems(int items) {
        int maxItemsPerRow = 5;
        int rows = items / 5;
        while (rows != 0) {
            createCardsPerRow(maxItemsPerRow);
            rows--;
        }
        createCardsPerRow(items % maxItemsPerRow);
    }


    private VBox createCard() {
        int index = count;
        count++;
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

        Label nameLabel = new Label(patientArrayList.get(index).getName());
        nameLabel.getStyleClass().add("text-sub-heading-bold");
        nameLabel.setWrapText(true);
        nameLabel.setTextAlignment(TextAlignment.CENTER);

        Label subtitleLabel = new Label("Patient Since -\n" + patientArrayList.get(index).getJoinedDate());
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

                try {

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/ui/admin/edit_patient/edit_patient.fxml"));
                    Parent root = (Parent) fxmlLoader.load();

                    try {
                        EditPatientController editPatientController = fxmlLoader.getController();
                        editPatientController.setPatientNumber( Integer.parseInt(patientArrayList.get(index).getId())
                                ,editPatientController);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    Stage stage = new Stage();
                    stage.setTitle(patientArrayList.get(index).getName());
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root, Util.DIALOG_SCREEN_WIDTH, Util.DIALOG_SCREEN_HEIGHT));
                    stage.show();

                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        JFXButton prescriptionBtn = new JFXButton();
        prescriptionBtn.setText("DELETE");
        prescriptionBtn.setPrefWidth(220);
        prescriptionBtn.getStyleClass().add("button-tertiary-small");
        prescriptionBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new PatientDao().deleteChildForPatient("Disease","patient_id",
                        patientArrayList.get(index).getId()+"");
                new PatientDao().deleteChildForPatient("Recommendations","patient_id",
                        patientArrayList.get(index).getId()+"");
                new PatientDao().deleteTuple("Prescription","patient_id",
                        patientArrayList.get(index).getId()+"");
                new PatientDao().deleteTuple("Appointment","patient_id",
                        patientArrayList.get(index).getId()+"");
                new PatientDao().deleteTuple("Patient","patient_id",
                        patientArrayList.get(index).getId()+"");
                getPatients();

            }
        });

        vBox.getChildren().add(icon);
        vBox.getChildren().add(nameLabel);
        vBox.getChildren().add(subtitleLabel);
        vBox.getChildren().add(viewBtn);
        vBox.getChildren().add(prescriptionBtn);

        return vBox;
    }


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
}

