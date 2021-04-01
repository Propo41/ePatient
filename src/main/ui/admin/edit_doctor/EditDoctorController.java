package main.ui.admin.edit_doctor;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import database.DoctorDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import model.Doctor;

import java.io.IOException;
import java.sql.SQLException;

public class EditDoctorController {

    @FXML
    private Label doctor_name;

    @FXML
    private StackPane myStackPane;

    @FXML
    private Button dismissButton;

    @FXML
    private Label specialist2;

    @FXML
    private Label hospitalAffiliations;

    @FXML
    private Label contact;

    @FXML
    private Label email;

    @FXML
    private Label address;

    @FXML
    private ListView<HBox> educationalBackgroundList;

    @FXML
    private ListView<HBox> professionalExperienceList;

    @FXML
    private ListView<HBox> avaiableDurationList;

    EditDoctorController editDoctorController;

    public int selectedDoctorId;
    String educationalBackground, professionalExperience;
    Doctor doctor;
    String[] educationList;
    String[] experienceList;

    public void setDoctorNumber(int doctorId, EditDoctorController editDoctorController) throws SQLException {

        this.selectedDoctorId = doctorId;
        this.editDoctorController = editDoctorController;
        DoctorDao doctorDao = new DoctorDao();

        this.doctor = doctorDao.getDoctorProfile(selectedDoctorId+"");
        this.doctor_name.setText(doctor.getName());
        this.specialist2.setText(doctor.getSpecialist());
        this.hospitalAffiliations.setText(doctor.getAffiliations());
        this.email.setText(doctor.getEmail());
        this.contact.setText(doctor.getPhone());
        this.address.setText(doctor.getAddress());
        this.professionalExperience = doctor.getProfessionalExperience();
        this.educationalBackground = doctor.getEducationalBackground();

        if(!educationalBackground.equals(",")) {

            educationList = educationalBackground.split(",");
            for (int i = 0; i < educationList.length ; i++) {
                HBox hBox = createCard(educationList[i].trim());
                educationalBackgroundList.getItems().add(hBox);
            }

        }

        if(!professionalExperience.equals("") ) {
            experienceList = professionalExperience.split(",");
            for (int i = 0; i < experienceList.length; i++){
                HBox hBox = createCard(experienceList[i].trim());
                professionalExperienceList.getItems().add(hBox);
            }
        }

        for (int i = 0; i < 2; i++) {
            HBox hBox = createCard("habijabi");
            avaiableDurationList.getItems().add(hBox);
        }


    }


    public void recieveTextBack(String addItem, String attribute){
        DoctorDao doctorDao = new DoctorDao();
        if(attribute.equals("Educational Background")){
            HBox hBox = createCard(addItem);
            String newData = educationalBackground + "," +addItem;
            if(educationalBackground.length()==0){
                newData =  addItem;
            }
            doctorDao.updateDoctorAttribute("educaional_background",newData,selectedDoctorId);
            educationalBackgroundList.getItems().add(hBox);
        }else if(attribute.equals("Professional Experience")){
            HBox hBox = createCard(addItem);
            String newData = professionalExperience + "," +addItem;
            if(professionalExperience.length()==0){
                newData = addItem;
            }
            doctorDao.updateDoctorAttribute("professional_experience",newData,selectedDoctorId);
            professionalExperienceList.getItems().add(hBox);
        }

    }


    public void recieveTextBackDialog(String attribute, String addedItem){
        if(attribute.equals("doctor_specialist")){
            specialist2.setText(addedItem);
        }else if(attribute.equals("doctor_email")){
            email.setText(addedItem);
        }else if(attribute.equals("doctor_phone")){
            contact.setText(addedItem);
        }else if(attribute.equals("hospital_affiliations")){
            hospitalAffiliations.setText(addedItem);
        }else if(attribute.equals("doctor_address")){
            address.setText(addedItem);
        }

    }

    @FXML
    void addEducationalBackgroundClick(MouseEvent event) throws IOException {

        try{
            JFXDialogLayout content = new JFXDialogLayout();
            content.getStyleClass().add("jfx-dialog-overlay-pane");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/ui/admin/edit_doctor/add_features_doctor_dialog.fxml"));
            loader.load();
            JFXDialog dialog = new JFXDialog(myStackPane, loader.getRoot(), JFXDialog.DialogTransition.CENTER);
            dialog.getStyleClass().add("jfx-dialog-layout");
            AddFeaturesDoctorDialogController dialogController = loader.getController();
            dialogController.setTitle("Educational Background",editDoctorController);
            dialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void addProfessionalExperienceClick(MouseEvent event) {
        try{
            JFXDialogLayout content = new JFXDialogLayout();
            content.getStyleClass().add("jfx-dialog-overlay-pane");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/ui/admin/edit_doctor/add_features_doctor_dialog.fxml"));
            loader.load();
            JFXDialog dialog = new JFXDialog(myStackPane, loader.getRoot(), JFXDialog.DialogTransition.CENTER);
            dialog.getStyleClass().add("jfx-dialog-layout");
            AddFeaturesDoctorDialogController dialogController = loader.getController();
            dialogController.setTitle("Professional Experience",editDoctorController);
            dialog.show();
        }catch (Exception e){
            e.printStackTrace();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/ui/admin/edit_doctor/edit_doctor_dialog.fxml"));
            loader.load();
            JFXDialog dialog = new JFXDialog(myStackPane, loader.getRoot(), JFXDialog.DialogTransition.CENTER);
            dialog.getStyleClass().add("jfx-dialog-layout");
            EditDoctorDialogController dialogController = loader.getController();
            dialogController.setLabel(selectedDoctorId, doctor.getSpecialist(), doctor.getEmail(),
                    doctor.getPhone(), doctor.getAffiliations(), doctor.getAddress(), dialog,editDoctorController);
            //change affiliations here according to ui
            dialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public HBox createCard(String education) {
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
        Label nameLabel = new Label(education);
        nameLabel.getStyleClass().add("text-card-subtitle");
        nameLabel.setAlignment(Pos.CENTER);
        vBox1.getChildren().addAll(nameLabel);

        hBox.getChildren().addAll(vBox, vBox1);

        return hBox;

    }

    @FXML
    void onEducationalBackgroundDeleteClick(MouseEvent event) {
        educationalBackgroundList.getItems().clear();
        educationalBackground = "";
        new DoctorDao().updateDoctorAttribute("educaional_background", "",selectedDoctorId);
    }

    @FXML
    void onProfessionalExperienceDeleteClick(MouseEvent event) {
        professionalExperienceList.getItems().clear();
        professionalExperience = "";
        new DoctorDao().updateDoctorAttribute("professional_experience", "",selectedDoctorId);
    }

}
