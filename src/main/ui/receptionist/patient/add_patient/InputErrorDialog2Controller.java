package main.ui.receptionist.patient.add_patient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class InputErrorDialog2Controller {

    @FXML
    private Label inputErrorLabel;

    public void setErrorMessage(String emptyField) {
        inputErrorLabel.setText(emptyField);
    }

    @FXML
    void dismissDialog(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

}
