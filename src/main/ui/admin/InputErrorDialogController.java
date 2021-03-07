package main.ui.admin;

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

    public void setErrorMessage(String emptyField) {
        inputErrorLabel.setText(emptyField);
    }

    @FXML
    void dismissDialog(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

}
