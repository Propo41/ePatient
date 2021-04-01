package main.ui.receptionist.test_report;

import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;

public class AddSuccessDialogController {

    private JFXDialog jfxDialog;
    public void dismissDialog(ActionEvent actionEvent) {
        jfxDialog.close();
    }

    public void init(JFXDialog dialog) {
        this.jfxDialog= dialog;
    }

}
