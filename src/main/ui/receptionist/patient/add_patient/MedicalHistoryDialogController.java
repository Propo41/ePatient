package main.ui.receptionist.patient.add_patient;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import database.DoctorDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import main.ui.admin.edit_patient.EditPatientController;

import java.util.HashMap;

public class MedicalHistoryDialogController {
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

    HashMap<String, Integer> medicalHistoryMap;

    public void setTitle(HashMap<String, Integer> medicalHistoryMap) {
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
        high_blood_pressure.getSelectionModel().select(getInitialSelection("high_blood_pressure"));
        high_cholesterol.getSelectionModel().select(getInitialSelection("high_cholesterol"));
        kidney_disease.getSelectionModel().select(getInitialSelection("kidney_disease"));
        tuberculosis.getSelectionModel().select(getInitialSelection("tuberculosis"));
        thyroid_problems.getSelectionModel().select(getInitialSelection("thyroid_problems"));
        sinus.getSelectionModel().select(getInitialSelection("sinus"));
        tonsilities.getSelectionModel().select(getInitialSelection("tonsilities"));
        lung_disease.getSelectionModel().select(getInitialSelection("lung_disease"));
         seasonal_allergies.getSelectionModel().select(getInitialSelection("seasonal_allergies"));
        arthritis.getSelectionModel().select(getInitialSelection("arthritis"));
        ashtma.getSelectionModel().select(getInitialSelection("ashtma"));
        cancer.getSelectionModel().select(getInitialSelection("cancer"));
        diabetes.getSelectionModel().select(getInitialSelection("diabetes"));
        pneumonia.getSelectionModel().select(getInitialSelection("pneumonia"));
        hiv.getSelectionModel().select(getInitialSelection("hiv"));
        hepatitis.getSelectionModel().select(getInitialSelection("hepatitis"));
        stroke.getSelectionModel().select(getInitialSelection("stroke"));
    }

    private int getInitialSelection(String str){
        try {
            int value = medicalHistoryMap.get(str);
            return value;
        }catch (Exception ex){
            return 1;
        }
    }

    @FXML
    void onSaveClicked(ActionEvent event) {
        checkSelection(high_blood_pressure, "high_blood_pressure");
        checkSelection(high_cholesterol, "high_cholesterol");
        checkSelection(kidney_disease, "kidney_disease");
        checkSelection(thyroid_problems, "thyroid_problems");
        checkSelection(tuberculosis, "tuberculosis");
        checkSelection(sinus, "sinus");
        checkSelection(tonsilities, "tonsilities" );
        checkSelection(lung_disease, "lung_disease");
        checkSelection(seasonal_allergies, "seasonal_allergies");
        checkSelection(arthritis, "arthritis");
        checkSelection(ashtma,"ashtma" );
        checkSelection(cancer, "cancer");
        checkSelection(stroke, "stroke");
        checkSelection(diabetes, "diabetes");
        checkSelection(pneumonia, "pneumonia");
        checkSelection(hiv, "hiv");
        checkSelection(hepatitis, "hepatitis");
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    void checkSelection(JFXComboBox comboBox, String attribute){
        System.out.println(comboBox.getSelectionModel().getSelectedItem().toString() + " " + attribute);
        String s = comboBox.getSelectionModel().getSelectedItem().toString();
        if(s.equals("Yes")){
            medicalHistoryMap.put(attribute, 0);
        }else{
            medicalHistoryMap.put(attribute, 1);
        }

    }



}
