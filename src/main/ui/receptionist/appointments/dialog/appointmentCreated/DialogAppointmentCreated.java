package main.ui.receptionist.appointments.dialog.appointmentCreated;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DialogAppointmentCreated {
    @FXML
    private Label label;

    @FXML
    void onDismissCreated(ActionEvent event) {
        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();
    }
}
