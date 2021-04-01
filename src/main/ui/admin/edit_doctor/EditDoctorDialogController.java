package main.ui.admin.edit_doctor;

import com.jfoenix.controls.JFXDialog;
import database.DoctorDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditDoctorDialogController {

    @FXML
    private TextField speciality;

    @FXML
    private TextField email;

    @FXML
    private TextField contact;

    @FXML
    private TextField address;

    @FXML
    private TextField hospitalAffiliations;


    int doctorId;
    String specialityText, emailText, contactText, addressText, hospitalAffiliationsText;
    JFXDialog jfxDialog;
    EditDoctorController editDoctorController;

    public void setLabel(int doctorId, String speciality, String email, String contact, String address,
                         String hospitalAffiliations, JFXDialog dialog, EditDoctorController editDoctorController) {
        this.jfxDialog = dialog;
        this.specialityText = speciality;
        this.emailText = email;
        this.contactText  = contact;
        this.addressText = address;
        this.hospitalAffiliationsText = hospitalAffiliations;
        this.doctorId = doctorId;

        this.speciality.setText(speciality);
        this.address.setText(address);
        this.email.setText(email);
        this.contact.setText(contact);
        this.hospitalAffiliations.setText(hospitalAffiliations);
        this.editDoctorController = editDoctorController;
    }

    @FXML
    void onSaveClicked(ActionEvent event) {

        DoctorDao doctorDao = new DoctorDao();
        if(!specialityText.equals(speciality.getText())){
            doctorDao.updateDoctorAttribute("doctor_specialist",speciality.getText(),doctorId);
            editDoctorController.recieveTextBackDialog("doctor_specialist",speciality.getText());
        }
        if(!emailText.equals(email.getText())) {
            doctorDao.updateDoctorAttribute("doctor_email", email.getText(), doctorId);
            editDoctorController.recieveTextBackDialog("doctor_email", email.getText());
        }
        if(!contactText.equals(contact.getText())) {
            doctorDao.updateDoctorAttribute("doctor_phone", contact.getText(), doctorId);
            editDoctorController.recieveTextBackDialog("doctor_phone", contact.getText());
        }
        if(!hospitalAffiliationsText.equals(hospitalAffiliations.getText())) {
            doctorDao.updateDoctorAttribute("hospital_affiliations", hospitalAffiliations.getText(), doctorId);
            editDoctorController.recieveTextBackDialog("hospital_affiliations", hospitalAffiliations.getText());
        }
        if(!addressText.equals(address.getText())){
            doctorDao.updateDoctorAttribute("doctor_address",address.getText(),doctorId);
            editDoctorController.recieveTextBackDialog("doctor_address",address.getText());
        }
        jfxDialog.close();
    }


}
