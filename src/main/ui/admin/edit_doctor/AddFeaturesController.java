package main.ui.admin.edit_doctor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddFeaturesController {

    @FXML
    private Label addTitle;

    @FXML
    private TextField addField;

    private String attribute;

    EditDoctorController editDoctorController;
    public void setTitle(String label, EditDoctorController editDoctorController) {
        addTitle.setText("ADD " + label.toUpperCase());
        addField.setPromptText("ENTER " + label.toUpperCase());
        this.editDoctorController = editDoctorController;
        this.attribute = label;
    }

    @FXML
    void onAddClicked(ActionEvent event) {
        editDoctorController.recieveTextBack(addField.getText(),attribute);
        addField.setText("");
    }


}
