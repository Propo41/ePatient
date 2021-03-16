package main.ui.doctor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.util.Pair;

import java.net.URL;
import java.util.ResourceBundle;

public class DialogNewPatientPromptController implements Initializable {

    @FXML
    private TextField patientIdTv;

    @FXML
    private TextField appointmentIdTv;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void onCreatePrescriptionClick(ActionEvent event) {

    }


    public void setIds(Pair<String, String> res) {
        patientIdTv.setText("ID: " + res.getKey());
        appointmentIdTv.setText("ID: " + res.getValue());
    }


}
