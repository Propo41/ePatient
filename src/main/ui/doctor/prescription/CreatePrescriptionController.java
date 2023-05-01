package main.ui.doctor.prescription;

import com.jfoenix.controls.JFXComboBox;
import database.DoctorDao;
import database.PrescriptionDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.*;
import util.Util;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreatePrescriptionController implements Initializable {

    /**
     * Health Condition
     * */
    private String healthCondition;
    @FXML
    private Tab healthCondTab;
    @FXML
    private TextArea healthCommentTv;
    @FXML
    private JFXComboBox<String> healthCondCb;
    @FXML
    private TextArea dietSuggestionTv;
    @FXML
    private TextArea examRoutineTv;

    /**
     * Observed Diseases
     * */
    @FXML
    private JFXComboBox<String> diseaseTypeCb;
    @FXML
    private TextArea diseaseCommentsTv;
    @FXML
    private TextField diseaseNameTv;
    @FXML
    private ListView<Disease> diseasesListView;
    private String diseaseType;
    private ObservableList<Disease> diseaseObservableList;
    private int diseaseSelectedItemIndex = -1;


    /**
     * Medical Tests
     * */
    @FXML
    private ListView<MedicalTest> testsListView;
    @FXML
    private TextField testNameTv;
    @FXML
    private TextArea testDescriptionTv;
    private ObservableList<MedicalTest> medicalTestObservableList;
    private int medicalTestsSelectedItemIndex = -1;


    /**
     * Suggested Medicines
     * */
    @FXML
    private ListView<Medicine> medicineListView;
    @FXML
    private TextField medicineNameTv;
    @FXML
    private TextArea medicineCommentTv;
    @FXML
    private TextField medicineDurationTv;
    private ObservableList<Medicine> medicineObservableList;
    private int medicineSelectedItemIndex = -1;


    @FXML
    private TextField printPrescription;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // DISEASES
        diseaseObservableList = FXCollections.observableList(new ArrayList<>());
        diseasesListView.setItems(diseaseObservableList);
        diseasesListView.setOnMouseClicked(event -> diseaseSelectedItemIndex = diseasesListView.getSelectionModel().getSelectedIndex());

        // MEDICAL TESTS
        medicalTestObservableList = FXCollections.observableList(new ArrayList<>());
        testsListView.setItems(medicalTestObservableList);
        testsListView.setOnMouseClicked(event -> medicalTestsSelectedItemIndex = testsListView.getSelectionModel().getSelectedIndex());

        // MEDICINE
        medicineObservableList = FXCollections.observableList(new ArrayList<>());
        medicineListView.setItems(medicineObservableList);
        medicineListView.setOnMouseClicked(event -> medicineSelectedItemIndex = medicineListView.getSelectionModel().getSelectedIndex());

        // INITIALIZING COMBO BOXES
        healthCondCb.getItems().addAll("a", "b", "c", "d", "e", "Severe");
        healthCondCb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                healthCondition = healthCondCb.getSelectionModel().getSelectedItem();
            }
        });

        diseaseTypeCb.getItems().addAll("a", "b", "c", "d", "e", "Severe");
        diseaseTypeCb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                diseaseType = diseaseTypeCb.getSelectionModel().getSelectedItem();
            }
        });

    }

    @FXML
    void onSaveClick(ActionEvent event) {
        // insert into database after checking for error
        Prescription prescription = new Prescription();
        prescription.setAppointmentId(Util.getInstance().getCurrentDoctorPatientQueue().getValue());
        prescription.setPatientId(Util.getInstance().getCurrentDoctorPatientQueue().getKey());
        prescription.setDoctorId(Util.getInstance().getUserId());
        prescription.setHealthCondition(new HealthCondition(
                dietSuggestionTv.getText(),
                examRoutineTv.getText(),
                healthCondition,
                healthCommentTv.getText()
        ));
        prescription.setDiseases(diseaseObservableList);
        prescription.setMedicines(medicineObservableList);
        prescription.setMedicalTests(medicalTestObservableList);

        new PrescriptionDao().createNewPrescription(prescription);
    }

    @FXML
    void onDeleteDiseaseItemClick(ActionEvent event) {
        diseaseObservableList.remove(diseaseSelectedItemIndex);
    }

    @FXML
    void onAddDiseaseClick(ActionEvent event) {
        diseaseObservableList.add(new Disease(diseaseNameTv.getText(), diseaseType, diseaseCommentsTv.getText()));
        diseaseNameTv.clear();
        diseaseCommentsTv.clear();

        printPrescription.setText("Disease: " + diseasesListView.get(diseaseSelectedItemIndex) + "\n" +
                                    "Medicine: " + medicineListView.get(medicineSelectedItemIndex));
        
    }

    @FXML 
    void onPrintPrescriptionItemClick(ActionEvent event){
        PrintEvent(printPrescription);
    }
    
    @FXML
    void onDeleteTestItemClick(ActionEvent event) {
        medicalTestObservableList.remove(medicalTestsSelectedItemIndex);
    }

    @FXML
    void onMedicineDeleteItemClick(ActionEvent event) {
        medicineObservableList.remove(medicineSelectedItemIndex);
    }

    @FXML
    void onTestsAddClick(ActionEvent event) {
        medicalTestObservableList.add(new MedicalTest(testNameTv.getText(), testDescriptionTv.getText()));
        testNameTv.clear();
        testDescriptionTv.clear();
    }

    @FXML
    void onMedicineAddClick(ActionEvent event) {
        medicineObservableList.add(new Medicine(medicineNameTv.getText(),
                medicineDurationTv.getText(), medicineCommentTv.getText()));
        medicineNameTv.clear();
        medicineDurationTv.clear();
        medicineCommentTv.clear();
    }

}



