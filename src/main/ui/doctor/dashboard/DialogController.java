package main.ui.doctor.dashboard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DialogController implements Initializable {
    @FXML
    Label label;
    //String asd;

    public void setLabel(String asd) {
       // this.asd = asd;
        label.setText(asd);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
