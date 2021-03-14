package main.ui.doctor.profile;

import database.DoctorDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Doctor;
import util.Util;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private Label contactLabel;

    @FXML
    private ListView<HBox> professionalExpListView;

    @FXML
    private Label visitFeeLabel;

    @FXML
    private ListView<HBox> eduBackgroundListView;

    @FXML
    private VBox root;

    @FXML
    private Label addressLabel;

    @FXML
    private ListView<HBox> hospitalAffListView;

    @FXML
    private Label specialityLabel;

    @FXML
    private Label emailLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DoctorDao doctorDao = new DoctorDao();
        Doctor doctor = doctorDao.getDoctorProfile(Util.getInstance().getUserId());
        String [] eduList = Util.splitString(doctor.getEducationalBackground());
        String [] exp = Util.splitString(doctor.getProfessionalExperience());
        String [] hosAff = Util.splitString(doctor.getAffiliations());

        contactLabel.setText(doctor.getPhone());
        specialityLabel.setText(doctor.getSpecialist());
        addressLabel.setText(doctor.getAddress());
        emailLabel.setText(doctor.getEmail());
        visitFeeLabel.setText(doctor.getVisitFee());

        for (String s : eduList) {
            HBox hBox = createList(s.trim());
            eduBackgroundListView.getItems().add(hBox);
        }

        for (String s: exp) {
            HBox hBox = createList(s.trim());
            professionalExpListView.getItems().add(hBox);
        }

        for (String s: hosAff) {
            HBox hBox = createList(s.trim());
            hospitalAffListView.getItems().add(hBox);
        }
    }

    private HBox createList(String name) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(20);

        ImageView imageView = new ImageView();
        imageView.getStyleClass().add("bullet-icon");
        hBox.getChildren().add(imageView);

        Label label = new Label(name);
        label.getStyleClass().add("text-label-ash-background");
        hBox.getChildren().add(label);

        return hBox;
    }

}
