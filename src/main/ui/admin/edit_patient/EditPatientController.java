package main.ui.admin.edit_patient;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import database.DoctorDao;
import database.PatientDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Patient;

import java.sql.SQLException;
import java.util.*;

public class EditPatientController {

    @FXML
    private StackPane myStackPane;

    @FXML
    private Label patientName;

    @FXML
    private Label patientId;

    @FXML
    private Label dateOfBirth;

    @FXML
    public Label weight;

    @FXML
    private Label age;

    @FXML
    private Label height;

    @FXML
    private Label gender;

    @FXML
    private Label contact;

    @FXML
    private Label email;

    @FXML
    private Label address;

    @FXML
    private TextArea history;

    @FXML
    private ListView<HBox> socialHistoryListView;

    @FXML
    private ListView<HBox> medicalHistoryListView;

    @FXML
    private JFXButton dismissButton;

    EditPatientController editPatientController;

    public int selectedPatientId;
    Patient patient;
    HashMap<String, Boolean> medicalHistoryMap = new HashMap<>();
    HashMap<String, Boolean> socialHistoryMap = new HashMap<>();
    ArrayList<String> medicalHistoryKey;
    ArrayList<Boolean> medicalHistoryValue;
    ArrayList<String> socialHistoryKey;
    ArrayList<Boolean> socialHistoryValue;

    public void setPatientNumber(int patientId1, EditPatientController editPatientController) throws SQLException {
      //  System.out.println(doctor);

        medicalHistoryKey = new ArrayList<>();
        medicalHistoryValue = new ArrayList<>();
        socialHistoryKey = new ArrayList<>();
        socialHistoryValue = new ArrayList<>();

        selectedPatientId = patientId1;
        this.editPatientController = editPatientController;
        patient = new PatientDao().getPatientProfile(selectedPatientId+"");

        patientName.setText(patient.getName());
        patientId.setText("Patient ID: " + patient.getId());
        dateOfBirth.setText(patient.getBirthDate().toString());
        height.setText(patient.getHeight());
        weight.setText(patient.getWeight());
        age.setText(patient.getAge());
        contact.setText(patient.getContact());
        email.setText(patient.getEmail());
        gender.setText(patient.getGender());
        address.setText(patient.getAddress());
        history.setText(patient.getSurgicalHistory());

        medicalHistoryMap = patient.getMedicalHistory();
        socialHistoryMap = patient.getSocialHistory();

        Set set = medicalHistoryMap.entrySet();
        Iterator iterator = set.iterator();

        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            if (Boolean.parseBoolean(mentry.getValue().toString())) {
                medicalHistoryKey.add(mentry.getKey().toString());
            }
        }

        for (int i = 0; i < medicalHistoryKey.size(); i++) {
            HBox hBox = createCard(medicalHistoryKey.get(i).trim());
            medicalHistoryListView.getItems().add(hBox);
        }

        set = socialHistoryMap.entrySet();
        iterator = set.iterator();

        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            socialHistoryKey.add(mentry.getKey().toString());
            socialHistoryValue.add(Boolean.parseBoolean(mentry.getValue().toString()));
        }

        for (int i = 0; i < socialHistoryKey.size(); i++) {
            HBox hBox = createCard(socialHistoryKey.get(i).trim());
            socialHistoryListView.getItems().add(hBox);
        }

    }

    public void setMedicalHistory(){
        medicalHistoryMap.clear();
        medicalHistoryListView.getItems().clear();
        medicalHistoryKey.clear();
        medicalHistoryMap = new PatientDao().getMedicalHistory(selectedPatientId+"");

        Set set = medicalHistoryMap.entrySet();
        Iterator iterator = set.iterator();

        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            if (Boolean.parseBoolean(mentry.getValue().toString())) {
                medicalHistoryKey.add(mentry.getKey().toString());
            }
        }

        for (int i = 0; i < medicalHistoryKey.size(); i++) {
            HBox hBox = createCard(medicalHistoryKey.get(i).trim());
            medicalHistoryListView.getItems().add(hBox);
        }

    }

    public void setSocialHistory(){
        medicalHistoryMap.clear();
        medicalHistoryListView.getItems().clear();
        medicalHistoryKey.clear();
        medicalHistoryMap = new PatientDao().getMedicalHistory(selectedPatientId+"");

        Set set = medicalHistoryMap.entrySet();
        Iterator iterator = set.iterator();

        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            if (Boolean.parseBoolean(mentry.getValue().toString())) {
                medicalHistoryKey.add(mentry.getKey().toString());
            }
        }

        for (int i = 0; i < medicalHistoryKey.size(); i++) {
            HBox hBox = createCard(medicalHistoryKey.get(i).trim());
            medicalHistoryListView.getItems().add(hBox);
        }

    }

    @FXML
    void onDismissClick(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void onEditClick(ActionEvent event) {

        try{
            JFXDialogLayout content = new JFXDialogLayout();
            content.getStyleClass().add("jfx-dialog-overlay-pane");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/ui/admin/edit_patient/edit_patient_dialog.fxml"));
            loader.load();
            JFXDialog dialog = new JFXDialog(myStackPane, loader.getRoot(), JFXDialog.DialogTransition.CENTER);
            dialog.getStyleClass().add("jfx-dialog-layout");
            EditPatientDialogController dialogController = loader.getController();
            dialogController.setLabel(selectedPatientId, patient.getWeight(), patient.getHeight(),
                    patient.getContact(), patient.getEmail() , patient.getAddress(), dialog,editPatientController);
            dialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public HBox createCard(String str) {
        HBox hBox = new HBox();

        ImageView icon = new ImageView();
        icon.getStyleClass().add("bullet-icon");
        icon.setFitWidth(10);
        icon.setFitHeight(10);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(5);
        vBox.setPrefWidth(50);
        vBox.getChildren().addAll(icon);

        VBox vBox1 = new VBox();
        vBox1.setAlignment(Pos.CENTER);
        Label nameLabel = new Label(str);
        nameLabel.getStyleClass().add("text-card-subtitle");
        nameLabel.setAlignment(Pos.CENTER);
        vBox1.getChildren().addAll(nameLabel);


        hBox.getChildren().addAll(vBox, vBox1);

        return hBox;
    }

    public void addAllergiesClick(MouseEvent mouseEvent) {

        try{
            JFXDialogLayout content = new JFXDialogLayout();
            content.getStyleClass().add("jfx-dialog-overlay-pane");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/ui/admin/edit_patient/add_medical_history_dialog.fxml"));
            loader.load();
            JFXDialog dialog = new JFXDialog(myStackPane, loader.getRoot(), JFXDialog.DialogTransition.CENTER);
            dialog.getStyleClass().add("jfx-dialog-layout");
            AddMedicalHistoryDialogController dialogController = loader.getController();
            dialogController.setTitle("Educational Background",editPatientController,patient.getId(),dialog,medicalHistoryMap);
            dialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addMedicalHistory(MouseEvent mouseEvent) {
        try{
            JFXDialogLayout content = new JFXDialogLayout();
            content.getStyleClass().add("jfx-dialog-overlay-pane");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/ui/admin/edit_patient/add_social_history_dialog.fxml"));
            loader.load();
            JFXDialog dialog = new JFXDialog(myStackPane, loader.getRoot(), JFXDialog.DialogTransition.CENTER);
            dialog.getStyleClass().add("jfx-dialog-layout");
            AddSocialHistoryDialogController dialogController = loader.getController();
            dialogController.setTitle("Educational Background",editPatientController,patient.getId(),dialog,
                    socialHistoryMap);
            dialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void updateUI(String attribute_name, String value) {
        if(attribute_name.equals("patient_weight")){
            weight.setText(value);
        }else if(attribute_name.equals("patient_height")){
            height.setText(value);
        }else if(attribute_name.equals("patient_contact")){
            contact.setText(value);
        }else if(attribute_name.equals("patient_address")){
            address.setText(value);
        }else if(attribute_name.equals("patient_email")){
            email.setText(value);
        }
    }

}
