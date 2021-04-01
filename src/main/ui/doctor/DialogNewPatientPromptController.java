package main.ui.doctor;

import com.jfoenix.controls.JFXDialog;
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
    private JFXDialog dialog;

    private DoctorMainController dependencyParentController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void onCreatePrescriptionClick(ActionEvent event) {
        dialog.close();
        dependencyParentController.initPrescriptionWindow();
    }

    public void setIds(Pair<String, String> res, JFXDialog dialog, DoctorMainController dependencyParentController) {
        this.dialog = dialog;
        patientIdTv.setText("ID: " + res.getKey());
        appointmentIdTv.setText("ID: " + res.getValue());
        this.dependencyParentController = dependencyParentController;
    }


}
