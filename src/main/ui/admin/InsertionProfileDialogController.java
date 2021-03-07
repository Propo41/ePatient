package main.ui.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class InsertionProfileDialogController implements Initializable {

    @FXML
    private Label idNumber;

    public void setIdNumber(int idNumberOfNewUser) {
        idNumber.setText(idNumberOfNewUser+"");
    }

    @FXML
    void dismissDialog(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
