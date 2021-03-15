package main.ui.doctor.prescription;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTabPane;
import database.PrescriptionDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import main.ui.doctor.prescription.dialogs.DetailsDiseaseController;
import main.ui.doctor.prescription.dialogs.DetailsMedicineController;
import main.ui.doctor.prescription.dialogs.DetailsTestController;
import model.Disease;
import model.MedicalTest;
import model.Medicine;
import model.Prescription;
import util.Util;

public class ViewPrescriptionController{
    @FXML
    private ListView<HBox> observedDiseases;

    @FXML
    private ListView<HBox> testsListView;

    @FXML
    private ListView<HBox> medicinesListView;

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
    private StackPane stackPaneRoot;

    @FXML
    private Label patientIdLabel;

    @FXML
    private TextArea examRoutineTv;

    private String prescriptionId;


    public void setContent(String prescriptionId, String patientName) {
        this.prescriptionId = prescriptionId;
        Prescription p = new PrescriptionDao().getPrescriptionDetails(prescriptionId);

        healthConditionTv.setText(p.getHealthCondition().getHealthCondition());
        healthCommentTv.setText(p.getHealthCondition().getComments());
        dietSuggestionTv.setText(p.getHealthCondition().getFoodSuggestion());
        examRoutineTv.setText(p.getHealthCondition().getExerciseRoutine());
        patientIdLabel.setText("PATIENT ID: " + p.getPatientId() + "");

        dateOfPrescriptionLabel.setText("DATE OF PRESCRIPTION: "+Util.formatDate(p.getDateOfPrescription()));
        prescriptionIdLabel.setText("PRESCRIPTION ID: "+p.getPrescriptionId());
        doctorLabel.setText("DOCTOR ID: " + p.getDoctorId() + "");
        patientNameLabel.setText(patientName);


        for (Disease disease : p.getDiseases()) {
            HBox hBox = createList(disease);
            observedDiseases.getItems().add(hBox);
        }

        for (MedicalTest test : p.getMedicalTests()) {
            HBox hBox = createList(test);
            testsListView.getItems().add(hBox);
        }

        for (Medicine medicine : p.getMedicines()) {
            HBox hBox = createList(medicine);
            medicinesListView.getItems().add(hBox);
        }
    }

    private HBox createList(Object o) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(20);

        ImageView imageView = new ImageView();
        imageView.getStyleClass().add("bullet-icon");
        hBox.getChildren().add(imageView);

        Label label = new Label("");
        label.getStyleClass().add("text-label-ash-background");
        hBox.getChildren().add(label);

        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(hBox1, Priority.ALWAYS);
        JFXButton imgView = new JFXButton();
        imgView.getStyleClass().add("button-inline-listview");
        hBox1.getChildren().add(imgView);

        hBox.getChildren().add(hBox1);

        if (o.getClass().getSimpleName().equals(Disease.class.getSimpleName())) {
            Disease d = (Disease) o;
            label.setText(d.getDiseaseName());
            imgView.setOnMouseClicked(event -> {
                openDialogDisease(d);
            });
        } else if (o.getClass().getSimpleName().equals(Medicine.class.getSimpleName())) {
            Medicine m = (Medicine) o;
            label.setText(m.getMedicineName());
            imgView.setOnMouseClicked(event -> {
                openDialogMedicine(m);
            });
        } else {
            MedicalTest t = (MedicalTest) o;
            label.setText(t.getTestName());
            imgView.setOnMouseClicked(event -> {
                openDialogTest(t);
            });
        }

        return hBox;
    }

    private void openDialogMedicine(Medicine m) {
        try {
            JFXDialogLayout content = new JFXDialogLayout();
            content.getStyleClass().add("jfx-dialog-overlay-pane");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogs/details_medicine.fxml"));
            loader.load();
            JFXDialog dialog = new JFXDialog(stackPaneRoot, loader.getRoot(), JFXDialog.DialogTransition.CENTER);
            dialog.getStyleClass().add("jfx-dialog-layout");
            DetailsMedicineController dialogController = loader.getController();
            dialogController.setContent(m, dialog);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openDialogTest(MedicalTest t) {
        try {
            JFXDialogLayout content = new JFXDialogLayout();
            content.getStyleClass().add("jfx-dialog-overlay-pane");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogs/details_tests.fxml"));
            loader.load();
            JFXDialog dialog = new JFXDialog(stackPaneRoot, loader.getRoot(), JFXDialog.DialogTransition.CENTER);
            dialog.getStyleClass().add("jfx-dialog-layout");
            DetailsTestController dialogController = loader.getController();
            dialogController.setContent(t, dialog);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openDialogDisease(Disease d) {
        try {
            JFXDialogLayout content = new JFXDialogLayout();
            content.getStyleClass().add("jfx-dialog-overlay-pane");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogs/details_disease.fxml"));
            loader.load();
            JFXDialog dialog = new JFXDialog(stackPaneRoot, loader.getRoot(), JFXDialog.DialogTransition.CENTER);
            dialog.getStyleClass().add("jfx-dialog-layout");
            DetailsDiseaseController dialogController = loader.getController();
            dialogController.setContent(d, dialog);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
