package main.ui.admin.edit_patient;

import com.jfoenix.controls.JFXDialog;
import database.DoctorDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class EditPatientDialogController {

    @FXML
    private TextField weight;

    @FXML
    private TextField height;

    @FXML
    private TextField contact;

    @FXML
    private TextField email;

    @FXML
    private TextField address;


    int patientId;
    String weightText, heightText, contactText, emailText, addressText;
    JFXDialog jfxDialog;
    EditPatientController editPatientController;
    public void setLabel(int patientId, String weight, String height, String contact, String email,
                         String address, JFXDialog dialog, EditPatientController editPatientController) {
        this.jfxDialog = dialog;
        this.weightText = weight;
        this.emailText = email;
        this.contactText  = contact;
        this.addressText = address;
        this.heightText = height;
        this.patientId = patientId;
        this.editPatientController = editPatientController;
        this.weight.setText(weight);
        this.height.setText(height);
        this.email.setText(email);
        this.contact.setText(contact);
        this.address.setText(address);

    }

    @FXML
    void onSaveClicked(ActionEvent event) throws SQLException {
        DoctorDao doctorDao = new DoctorDao();
        if(!weightText.equals(weight.getText())){
            doctorDao.updateSingleAttribute("Patient","patient_weight",weight.getText(),patientId+"");
            editPatientController.updateUI("patient_weight",weight.getText());
        }else if(!heightText.equals(height.getText())){
            doctorDao.updateSingleAttribute("Patient","patient_height",height.getText(),patientId+"");
            editPatientController.updateUI("patient_height",height.getText());
        }else if(!contactText.equals(contact.getText())){
            doctorDao.updateSingleAttribute("Patient","patient_contact",contact.getText(),patientId+"");
            editPatientController.updateUI("patient_contact",contact.getText());
        }else if(!addressText.equals(address.getText())){
            doctorDao.updateSingleAttribute("Patient","patient_address" ,address.getText(),patientId+"");
            editPatientController.updateUI("patient_address",address.getText());
        }else if(!emailText.equals(email.getText())){
            doctorDao.updateSingleAttribute("Patient","patient_email",email.getText(),patientId+"");
            editPatientController.updateUI("patient_email",email.getText());
        }
        jfxDialog.close();
    }





}
