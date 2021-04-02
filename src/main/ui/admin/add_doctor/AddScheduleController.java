package main.ui.admin.add_doctor;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.Disease;
import model.MyTime;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddScheduleController implements Initializable {

    @FXML
    private JFXDatePicker dateSelector;

    @FXML
    private JFXTimePicker startTimeSelector;

    @FXML
    private JFXTimePicker endTimeSelector;

    @FXML
    private ListView<MyTime> timeListView;
    private ObservableList<MyTime> timeObservableList;
    private ArrayList<MyTime> timeArrayList;
    private int selectedItemIndex = -1;
    private AddProfileController addProfileController;
    private JFXDialog dialog;

    public void init(AddProfileController addProfileController, JFXDialog dialog) {
        this.addProfileController = addProfileController;
        this.dialog = dialog;
        timeArrayList = new ArrayList<>();
    }

    @FXML
    void onAddClick(ActionEvent event) {
        if(dateSelector.getValue() != null) {
            startTimeSelector.getValue();
            System.out.println("date picked: " + dateSelector.getValue().getDayOfWeek());
            endTimeSelector.getValue();
            timeObservableList.add(new MyTime(dateSelector.getValue(),
                    startTimeSelector.getValue(),
                    endTimeSelector.getValue()));
            timeArrayList.add(new MyTime(dateSelector.getValue(),
                    startTimeSelector.getValue(),
                    endTimeSelector.getValue()));
            dateSelector.setValue(null);
            startTimeSelector.setValue(null);
            endTimeSelector.setValue(null);

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timeObservableList = FXCollections.observableList(new ArrayList<>());
        timeListView.setItems(timeObservableList);
        timeListView.setOnMouseClicked(event -> selectedItemIndex = timeListView.getSelectionModel().getSelectedIndex());
    }

    @FXML
    void onTimeRemove(ActionEvent event) {
        if(selectedItemIndex!=-1) {
            timeObservableList.remove(selectedItemIndex);
            timeArrayList.remove(selectedItemIndex);
            selectedItemIndex = -1;
        }
    }


    @FXML
    void onSaveClick(ActionEvent event) {
        addProfileController.saveSchedule(timeArrayList);
        dialog.close();
    }

}
