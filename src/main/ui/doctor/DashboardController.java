package main.ui.doctor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class DashboardController {
    @FXML
    public ListView<String> visitingHoursListView;
    @FXML
    public Label totalVisitsTv;
    @FXML
    public Label totalAppointmentTv;
    @FXML
    public Label totalBilledTv;
    @FXML
    public ListView<String> recentPatientsListView;
    @FXML
    public Label doctorNameTv;

    public void onMoreClick(ActionEvent event){
        System.out.println("more dialog shown");

    }


}
