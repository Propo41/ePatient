package main.ui.admin.edit_patient;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.sun.org.apache.xpath.internal.operations.Bool;
import database.DoctorDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.HashMap;

public class AddSocialHistoryDialogController {
    @FXML
    private JFXComboBox alcohol_use;

    @FXML
    private JFXComboBox tobacco_use;

    @FXML
    private JFXComboBox caffeine_use;

    @FXML
    private JFXComboBox drugs_use;

    @FXML
    private JFXComboBox excercise;

    @FXML
    private JFXComboBox sleep;

    String patientId;
    DoctorDao doctorDao;
    JFXDialog dialog;
    EditPatientController editPatientController;
    ArrayList<String> optionList;

    /**
     Todo : DISPLAY PREVIOUS/DATABASE SELECTIONS ON COMBO BOXES
     */


    public void setTitle(String label, EditPatientController editPatientController, String patientId, JFXDialog dialog,
                         HashMap<String, Boolean> socialHistory) {


        this.patientId = patientId;
        this.doctorDao = new DoctorDao();
        this.dialog = dialog;
        this.editPatientController = editPatientController;
        optionList = new ArrayList<>();
        initcomboBoxes();
    }

    private void initcomboBoxes() {
        optionList.add("OPTION 1");
        optionList.add("OPTION 2");
        optionList.add("OPTION 3");
        optionList.add("OPTION 4");
        ObservableList<String> list = FXCollections.observableArrayList(optionList);
        alcohol_use.setItems(list);
        tobacco_use.setItems(list);
        caffeine_use.setItems(list);
        drugs_use.setItems(list);
        excercise.setItems(list);
        sleep.setItems(list);
    }

    @FXML
    void onSaveClicked(ActionEvent event) {
        checkSelection(alcohol_use);
        checkSelection(tobacco_use);
        checkSelection(caffeine_use);
        checkSelection(drugs_use);
        checkSelection(excercise);
        checkSelection(sleep);
        editPatientController.setMedicalHistory();
        dialog.close();
    }

    void checkSelection(JFXComboBox comboBox){
        if(!comboBox.getSelectionModel().isEmpty()){
            String s = comboBox.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < optionList.size(); i++){
                if(s.equals(optionList.get(i))){
                    doctorDao.updateSingleAttribute("SocialHistory",comboBox.getId(),optionList.get(i),patientId);
                }
            }

        }

    }

}
