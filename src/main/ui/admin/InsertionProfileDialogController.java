package main.ui.admin;

import com.jfoenix.controls.JFXDialog;
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
    private JFXDialog dialog;


    public void setIdNumber(int idNumberOfNewUser, JFXDialog dialog) {
        idNumber.setText("Your account ID is "+ idNumberOfNewUser+"");
        this.dialog = dialog;
    }

    @FXML
    void dismissDialog(ActionEvent event) {
        dialog.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setIdNumber(int answer){
        System.out.println("InComplete work");
    }

}
