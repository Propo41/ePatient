package main.ui.admin.add_doctor;

import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class InputErrorDialogController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private Label inputErrorLabel;
    private JFXDialog dialog;

    public void setErrorMessage(String emptyField, JFXDialog dialog) {
        inputErrorLabel.setText(emptyField);
        this.dialog = dialog;
    }

    @FXML
    void dismissDialog(ActionEvent event) {
        dialog.close();
    }

}
