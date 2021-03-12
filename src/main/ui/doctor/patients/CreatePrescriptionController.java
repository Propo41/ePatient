package main.ui.doctor.patients;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreatePrescriptionController implements Initializable {

    @FXML
    private TextArea healthCommentTv;

    @FXML
    private TextField medicineNameTv;

    @FXML
    private TextArea diseaseCommentsTv;

    @FXML
    private TextField diseaseNameTv;

    @FXML
    private TextArea dietSuggestionTv;

    @FXML
    private TextArea medicineCommentTv;

    @FXML
    private Tab healthCondTab;

    @FXML
    private JFXComboBox<String> healthCondCb;

    @FXML
    private TextArea testDescriptionTv;

    @FXML
    private JFXComboBox<String> diseaseTypeCb;

    @FXML
    private TextField medicineDurationTv;

    @FXML
    private TextField testNameTv;

    @FXML
    private JFXTabPane tabPane;

    @FXML
    private TextArea examRoutineTv;

    @FXML
    void onSaveClick(ActionEvent event) {

    }

    @FXML
    void onAddDiseaseClick(ActionEvent event) {

    }

    @FXML
    void onTestsAddClick(ActionEvent event) {

    }

    @FXML
    void onMedicineAddClick(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        healthCondCb.getItems().addAll("a", "b", "c", "d", "e", "Severe");
        healthCondCb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int selectedIndex = healthCondCb.getSelectionModel().getSelectedIndex();
                String selectedItem = healthCondCb.getSelectionModel().getSelectedItem();
                System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
            }
        });

        diseaseTypeCb.getItems().addAll("a", "b", "c", "d", "e", "Severe");
        diseaseTypeCb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int selectedIndex = diseaseTypeCb.getSelectionModel().getSelectedIndex();
                String selectedItem = diseaseTypeCb.getSelectionModel().getSelectedItem();
                System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
            }
        });

    }
}
