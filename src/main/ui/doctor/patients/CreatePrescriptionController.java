package main.ui.doctor.patients;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreatePrescriptionController {
    @FXML
    private TextField patientTv;

    @FXML
    private TextArea testsTv;

    @FXML
    private JFXButton addPrescriptionBtn;

    @FXML
    private TextArea descriptionTv;

    @FXML
    private TextArea medicinesTv;

    @FXML
    void onAddClick(ActionEvent event) {

    }
}
