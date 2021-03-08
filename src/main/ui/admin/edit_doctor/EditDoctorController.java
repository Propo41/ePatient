package main.ui.admin.edit_doctor;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditDoctorController  implements Initializable {
    @FXML
    private Label doctor_name;

    @FXML
    private StackPane myStackPane;

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
    private TextArea professionalSummary;

    @FXML
    private ListView<HBox> educationalBackgroundList;

    @FXML
    private ListView<HBox> professionalExperienceList;

    @FXML
    private ListView<HBox> avaiableDurationList;

    @FXML
    private ListView<HBox> chamberAddressList;

    DatabaseHandler connectMSSQL;
    public int doctorNumber;

    public void setDoctorNumber(int doctor){
        connectMSSQL = new DatabaseHandler();
        specialist2.setText("Specialist in : " + connectMSSQL.getSingleInfo(doctor, "doctor_specialist"));
        hospitalAffiliations.setText(connectMSSQL.getSingleInfo(doctor,"hospital_affiliations"));
        email.setText(connectMSSQL.getSingleInfo(doctor,"doctor_email"));
        contact.setText(connectMSSQL.getSingleInfo(doctor, "doctor_mobile"));
        professionalSummary.setText(connectMSSQL.getSingleInfo(doctor,"professional_experience"));
        address.setText(connectMSSQL.getSingleInfo(doctor,"doctor_address"));
        System.out.println(doctor);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int i = 0; i < 10; i++) {
            HBox hBox = createCard();
            HBox btnContainer = (HBox) hBox.getChildren().get(2);
            ImageView button = (ImageView) btnContainer.getChildren().get(0);
            int finalI = i;
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("Tile pressed " + finalI);
                    educationalBackgroundList.getItems().remove(finalI);
                }
            });
            educationalBackgroundList.getItems().add(hBox);
        }

        for (int i = 0; i < 10; i++){
            HBox hBox = createCard();
            HBox btnContainer = (HBox) hBox.getChildren().get(2);
            ImageView button = (ImageView) btnContainer.getChildren().get(0);
            int finalI = i;
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("Tile pressed " + finalI);
                    professionalExperienceList.getItems().remove(finalI);
                }
            });
            professionalExperienceList.getItems().add(hBox);
        }

        for (int i = 0; i < 10; i++) {
            HBox hBox = createCard();
            HBox btnContainer = (HBox) hBox.getChildren().get(2);
            ImageView button = (ImageView) btnContainer.getChildren().get(0);
            int finalI = i;
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("Tile pressed " + finalI);
                    avaiableDurationList.getItems().remove(finalI);
                }
            });
            avaiableDurationList.getItems().add(hBox);
        }


        for (int i = 0; i < 10; i++) {
            HBox hBox = createCard();
            HBox btnContainer = (HBox) hBox.getChildren().get(2);
            ImageView button = (ImageView) btnContainer.getChildren().get(0);
            int finalI = i;
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("Tile pressed " + finalI);
                    chamberAddressList.getItems().remove(finalI);
                }
            });
            chamberAddressList.getItems().add(hBox);
        }

    }



    @FXML
    void educationalBackgroundClick(MouseEvent event) {
        System.out.println("Add Educational Background");
    }

    @FXML
    void professionalExperienceClick(MouseEvent event) {

    }

    @FXML
    void onDismissClick(ActionEvent event) {

    }

    @FXML
    void onEditClick(ActionEvent event) {

/* dialog but root must be a stack pane
        JFXDialog jfxDialog = new JFXDialog();
        JFXDialogLayout content= new JFXDialogLayout();

        VBox root2 = new VBox();
        try {
            root2 = FXMLLoader.load(getClass().getResource("/main/ui/admin/view_doctor/view_doctors.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        content.setBody(root2);
        jfxDialog.setContent(content);
        jfxDialog.setDialogContainer(myStackPane);

        jfxDialog.show();
*/

    }


    public HBox createCard() {
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
        Label nameLabel = new Label("AMI AUST E PORI");
        nameLabel.getStyleClass().add("text-card-subtitle");
        nameLabel.setAlignment(Pos.CENTER);
        vBox1.getChildren().addAll(nameLabel);

        ImageView icon2 = new ImageView();
        icon2.getStyleClass().add("delete-icon");
        icon2.setFitWidth(30);
        icon2.setFitHeight(30);

        HBox hBox1 = new HBox();
        HBox.setHgrow(hBox1, Priority.ALWAYS);
        hBox1.setAlignment(Pos.CENTER_RIGHT);
        hBox1.getChildren().add(icon2);

        hBox.getChildren().addAll(vBox, vBox1, hBox1);

        return hBox;

    }


}
