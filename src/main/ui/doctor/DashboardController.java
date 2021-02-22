package main.ui.doctor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import main.ui.database.ConnectMSSQL;
import main.ui.database.DoctorDB;
import util.Util;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
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

    private String doctorId;

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void onMoreClick(ActionEvent event){
        System.out.println("more dialog shown");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       /* DoctorDB doctorDB = new DoctorDB(doctorId);
        String totalAppointments = doctorDB.getTotalAppointments();*/
        Util util = Util.getInstance();
        ConnectMSSQL mssql = util.getConnectMSSQL();
        String totalAppointments = mssql.getTotalAppointments();
        totalAppointmentTv.setText(totalAppointments);
    }
}
