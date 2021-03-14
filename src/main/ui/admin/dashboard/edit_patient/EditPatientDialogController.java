package main.ui.admin.dashboard.edit_patient;

import com.jfoenix.controls.JFXDialog;
import database.DoctorDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditPatientDialogController {

    @FXML
    private TextField speciality;

    @FXML
    private TextField email;

    @FXML
    private TextField contact;

    @FXML
    private TextField languages;

    @FXML
    private TextField hospitalAffiliations;


    int doctorId;
    String specialityText, emailText, contactText, languagesText, hospitalAffiliationsText;
    JFXDialog jfxDialog;
    public void setLabel(int doctorId, String speciality, String email, String contact, String languages,
                         String hospitalAffiliations, JFXDialog dialog) {
        this.jfxDialog = dialog;
        this.specialityText = speciality;
        this.emailText = email;
        this.contactText  = contact;
        this.languagesText = languages;
        this.doctorId = doctorId;

        this.speciality.setText(speciality);
        this.languages.setText(languages);
        this.email.setText(email);
        this.contact.setText(contact);
        this.hospitalAffiliations.setText(hospitalAffiliations);

    }

    @FXML
    void onSaveClicked(ActionEvent event) {
        DoctorDao doctorDao = new DoctorDao();
        if(!specialityText.equals(speciality.getText())){
            doctorDao.updateDoctorAttribute("doctor_specialist",speciality.getText(),doctorId);
        }else if(!emailText.equals(email.getText())){
            doctorDao.updateDoctorAttribute("doctor_email",email.getText(),doctorId);
        }else if(!contactText.equals(contact.getText())){
            doctorDao.updateDoctorAttribute("doctor_mobile",contact.getText(),doctorId);
        }else if(!hospitalAffiliationsText.equals(hospitalAffiliations.getText())){
            doctorDao.updateDoctorAttribute("hospital_affiliations" ,hospitalAffiliations.getText(),doctorId);
        }else if(!languagesText.equals(languagesText)){
            doctorDao.updateDoctorAttribute("languages",languages.getText(),doctorId);
        }
        jfxDialog.close();
    }


}
