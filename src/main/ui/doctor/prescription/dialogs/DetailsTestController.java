package main.ui.doctor.prescription.dialogs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import model.MedicalTest;
import model.Medicine;

import java.util.Map;

public class DetailsTestController {

    @FXML
    private Label testReportNameLabel;

    @FXML
    private ImageView testReportImg;

    @FXML
    private JFXButton testReportBtn;

    @FXML
    private TextField testDescriptionTv;


    @FXML
    private TextField testNameTv;

    private JFXDialog dialog;
    @FXML
    void onCloseClick(ActionEvent event) {
        dialog.close();
    }

    public void setContent(MedicalTest m, JFXDialog dialog) {
        testDescriptionTv.setText(m.getTestDescription());
        testNameTv.setText(m.getTestName());
        testReportNameLabel.setText("Name of file");
        testReportImg.getStyleClass().add("bullet-icon");
        testReportBtn.getStyleClass().add("button-pdf");
        this.dialog = dialog;

    }


}
