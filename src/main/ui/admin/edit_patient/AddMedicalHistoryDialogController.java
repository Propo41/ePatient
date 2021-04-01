package main.ui.admin.edit_patient;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import database.DoctorDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.ui.admin.edit_doctor.EditDoctorController;
import model.Doctor;

import java.util.*;

public class AddMedicalHistoryDialogController {

    @FXML
    private JFXComboBox high_blood_pressure;

    @FXML
    private JFXComboBox high_cholesterol;

    @FXML
    private JFXComboBox kidney_disease;

    @FXML
    private JFXComboBox thyroid_problems;

    @FXML
    private JFXComboBox tuberculosis;

    @FXML
    private JFXComboBox sinus;

    @FXML
    private JFXComboBox tonsilities;

    @FXML
    private JFXComboBox lung_disease;

    @FXML
    private JFXComboBox ashtma;

    @FXML
    private JFXComboBox seasonal_allergies;

    @FXML
    private JFXComboBox arthritis;

    @FXML
    private JFXComboBox cancer;

    @FXML
    private JFXComboBox stroke;

    @FXML
    private JFXComboBox diabetes;

    @FXML
    private JFXComboBox pneumonia;

    @FXML
    private JFXComboBox hiv;

    @FXML
    private JFXComboBox hepatitis;
    String patientId;
    DoctorDao doctorDao;
    JFXDialog dialog;
    EditPatientController editPatientController;
    HashMap<String, Boolean> medicalHistoryMap;

    public void setTitle(String label, EditPatientController editPatientController, String patientId, JFXDialog dialog, HashMap<String, Boolean> medicalHistoryMap) {
        this.patientId = patientId;
        this.doctorDao = new DoctorDao();
        this.dialog = dialog;
        this.editPatientController = editPatientController;
        this.medicalHistoryMap = medicalHistoryMap;
        initcomboBoxes();
    }


    private void initcomboBoxes() {
        ObservableList<String> list = FXCollections.observableArrayList("Yes","No");
        high_blood_pressure.setItems(list);
        high_cholesterol.setItems(list);
        kidney_disease.setItems(list);
        thyroid_problems.setItems(list);
        tuberculosis.setItems(list);
        sinus.setItems(list);
        tonsilities.setItems(list);
        lung_disease.setItems(list);
        seasonal_allergies.setItems(list);
        arthritis.setItems(list);
        ashtma.setItems(list);
        cancer.setItems(list);
        stroke.setItems(list);
        diabetes.setItems(list);
        pneumonia.setItems(list);
        hiv.setItems(list);
        hepatitis.setItems(list);
        setInitialSelction();
    }

    private void setInitialSelction() {
        high_blood_pressure.getSelectionModel().select(getSelection("high blood pressure"));
        high_cholesterol.getSelectionModel().select(getSelection("high cholesterol"));
        kidney_disease.getSelectionModel().select(getSelection("kidney disease"));
        tuberculosis.getSelectionModel().select(getSelection("tuberculosis"));
        thyroid_problems.getSelectionModel().select(getSelection("thyroid problems"));
        sinus.getSelectionModel().select(getSelection("sinus"));
        tonsilities.getSelectionModel().select(getSelection("tonsilities"));
        lung_disease.getSelectionModel().select(getSelection("lung disease"));
        seasonal_allergies.getSelectionModel().select(getSelection("seasonal allergies"));
        arthritis.getSelectionModel().select(getSelection("arthritis"));
        ashtma.getSelectionModel().select(getSelection("asthma"));
        cancer.getSelectionModel().select(getSelection("cancer"));
        diabetes.getSelectionModel().select(getSelection("diabetes"));
        pneumonia.getSelectionModel().select(getSelection("pneumonia"));
        hiv.getSelectionModel().select(getSelection("hiv"));
        hepatitis.getSelectionModel().select(getSelection("hepatitis"));
        stroke.getSelectionModel().select(getSelection("stroke"));
    }

    private int getSelection(String str){
        int value;
        if(medicalHistoryMap.get(str)){
            value = 0;
        }else{
            value = 1;
        }
        return value;
    }


    @FXML
    void onSaveClicked(ActionEvent event) {
        checkSelection(high_blood_pressure);
        checkSelection(high_cholesterol);
        checkSelection(kidney_disease);
        checkSelection(thyroid_problems);
        checkSelection(tuberculosis);
        checkSelection(sinus);
        checkSelection(tonsilities);
        checkSelection(lung_disease);
        checkSelection(seasonal_allergies);
        checkSelection(arthritis);
        checkSelection(ashtma);
        checkSelection(cancer);
        checkSelection(stroke);
        checkSelection(diabetes);
        checkSelection(pneumonia);
        checkSelection(hiv);
        checkSelection(hepatitis);
        editPatientController.setSocialHistory();
        dialog.close();
    }

    void checkSelection(JFXComboBox comboBox){
        if(!comboBox.getSelectionModel().isEmpty()){
            String s = comboBox.getSelectionModel().getSelectedItem().toString();
            if(s.equals("Yes")){
                doctorDao.updateSingleAttribute("MedicalHistory",comboBox.getId(),"1",patientId);
            }else{
                doctorDao.updateSingleAttribute("MedicalHistory",comboBox.getId(),"0",patientId);
            }
        }
    }


}
