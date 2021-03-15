package main.ui.doctor.prescription.dialogs;

import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Medicine;

public class DetailsMedicineController{

    @FXML
    private TextField medicineNameTv;

    @FXML
    private TextArea commentTv;

    @FXML
    private TextField medicineDurationTv;

    private JFXDialog dialog;

    @FXML
    void onCloseClick(ActionEvent event) {
        dialog.close();

    }

    public void setContent(Medicine m, JFXDialog dialog) {
        medicineNameTv.setText(m.getMedicineName());
        commentTv.setText(m.getComment());
        medicineDurationTv.setText(m.getMedicineDuration());
        this.dialog = dialog;
    }
}
