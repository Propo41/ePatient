package main.ui.doctor.prescription;

import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewPrescriptionController implements Initializable {
    @FXML
    private ListView<String> observedDiseases;

    @FXML
    private ListView<String> testsListView;

    @FXML
    private ListView<String> medicinesListView;

    @FXML
    private TextArea healthCommentTv;

    @FXML
    private TextField medicineNameTv;

    @FXML
    private TextArea diseaseCommentsTv;

    @FXML
    private TextField diseaseNameTv;

    @FXML
    private TextArea dietSuggestionTv;

    @FXML
    private Label prescriptionIdLabel;

    @FXML
    private Label dateOfPrescriptionLabel;

    @FXML
    private TextArea medicineCommentTv;

    @FXML
    private Tab healthCondTab;

    @FXML
    private Label doctorLabel;

    @FXML
    private TextArea testDescriptionTv;

    @FXML
    private TextField medicineDurationTv;

    @FXML
    private Label patientNameLabel;

    @FXML
    private Label healthConditionTv;

    @FXML
    private Label diseaseTypeTv;

    @FXML
    private TextField testNameTv;

    @FXML
    private JFXTabPane tabPane;


    @FXML
    private Label patientIdLabel;

    @FXML
    private TextArea examRoutineTv;

    private String prescriptionId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void setContent(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }
}
