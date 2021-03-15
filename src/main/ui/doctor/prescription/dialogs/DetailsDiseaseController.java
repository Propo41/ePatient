package main.ui.doctor.prescription.dialogs;

import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Disease;

public class DetailsDiseaseController {

    @FXML
    private TextArea diseaseCommentsTv;

    @FXML
    private TextField diseaseNameTv;

    @FXML
    private TextField diseaseTypeTv;

    private JFXDialog dialog;

    @FXML
    void onCloseClick(ActionEvent event) {

        dialog.close();
    }
    public void setContent(Disease d, JFXDialog dialog) {
        diseaseNameTv.setText(d.getDiseaseName());
        diseaseCommentsTv.setText(d.getDescription());
        diseaseTypeTv.setText(d.getDiseaseType());
        this.dialog = dialog;

    }
}
