package main.ui.admin.view_doctor;

import com.jfoenix.controls.JFXButton;
import database.DatabaseHandler;
import database.DoctorDao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.ui.admin.edit_doctor.EditDoctorController;
import model.Doctor;
import util.Util;

import javax.print.Doc;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewDoctorsController implements Initializable {
    @FXML
    private ListView<HBox> doctorListView;

    @FXML
    private TextField doctorSearchTv;

    @FXML
    private Label searchResultNumber;

    int count;
    ArrayList<Doctor> doctorArrayList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        doctorSearchTv.setStyle("-fx-background-image: url('/resources/icons/ic_search.png');");
        //image.setImage(new Image("/resources/icons/ic_search.png"));
        searchResultNumber.setText("");
        count = 0;
    }


    @FXML
    void onSearchBtnClick(ActionEvent event) throws SQLException {
        getDoctors();
    }

    private void getDoctors() throws SQLException {
        doctorArrayList = new ArrayList<>();
        String str = doctorSearchTv.getText();
        if(str.equals("")){
            return;
        }
        doctorListView.getItems().clear();
        count = 0;
        doctorArrayList = new DoctorDao().getDoctorBasicInfo(doctorSearchTv.getText());
        searchResultNumber.setText(doctorArrayList.size() + " SEARCH RESULTS FOUND");
        createCardItems(doctorArrayList.size());
    }

    private void createCardItems(int items) {
        int maxItemsPerRow = 5;
        int rows = items / 5;
        while (rows != 0) {
            createCardsPerRow(maxItemsPerRow);
            rows--;
        }
        createCardsPerRow(items % maxItemsPerRow);
    }

    private void createCardsPerRow(int i) {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        while(i!=0){
            VBox vBox = createCard();
            // vBox.setMaxWidth(290);
            hBox.getChildren().add(vBox);
            i--;
        }
        doctorListView.getItems().add(hBox);
    }


    private VBox createCard() {
        int index = count;
        count++;
        VBox vBox = new VBox();
        vBox.getStyleClass().add("card-background");
        vBox.setPadding(new Insets(20.0d, 20.0d, 20.0d, 20.0d));
        vBox.setSpacing(8);
        vBox.setPrefWidth(220);
        vBox.setAlignment(Pos.TOP_CENTER);
        HBox.setHgrow(vBox, Priority.SOMETIMES);

        ImageView icon = new ImageView();
        icon.getStyleClass().add("user-icon");
        icon.setFitWidth(70);
        icon.setFitHeight(70);

        Label nameLabel = new Label(doctorArrayList.get(index).getSpecialist());
        nameLabel.getStyleClass().add("text-sub-heading-bold");
        nameLabel.setWrapText(true);
        nameLabel.setTextAlignment(TextAlignment.CENTER);

        Label subtitleLabel = new Label(doctorArrayList.get(index).getName());
        subtitleLabel.getStyleClass().add("text-card-subtitle");
        subtitleLabel.setWrapText(true);
        subtitleLabel.setTextAlignment(TextAlignment.CENTER);

        JFXButton viewBtn = new JFXButton();
        viewBtn.setText("VIEW");
        viewBtn.setPrefWidth(220);
        viewBtn.getStyleClass().add("button-primary-small");

        viewBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/ui/admin/edit_doctor/edit_doctor.fxml"));
                    Parent root = (Parent) fxmlLoader.load();

                    try {
                        EditDoctorController editDoctorController = fxmlLoader.getController();
                        editDoctorController.setDoctorNumber(Integer.parseInt(doctorArrayList.get(index).getDoctorId())
                                ,editDoctorController);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    Stage stage = new Stage();
                    stage.setTitle(doctorArrayList.get(index).getName());
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root, Util.DIALOG_SCREEN_WIDTH, Util.DIALOG_SCREEN_HEIGHT));
                    stage.show();

                    // Hide this current window (if this is what you want)
                    //((Node)(event.getSource())).getScene().getWindow().hide();

                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        JFXButton prescriptionBtn = new JFXButton();
        prescriptionBtn.setText("DELETE");
        prescriptionBtn.setPrefWidth(220);
        prescriptionBtn.getStyleClass().add("button-tertiary-small");
        prescriptionBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new DoctorDao().deleteChildForDoctor("Recommendations","doctor_id",
                        doctorArrayList.get(index).getDoctorId()+"");
                new DoctorDao().deleteTuple("Prescription","doctor_id",
                        doctorArrayList.get(index).getDoctorId()+"");
                new DoctorDao().deleteTuple("Doctor","doctor_id",
                        doctorArrayList.get(index).getDoctorId()+"");
                try {
                    getDoctors();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        vBox.getChildren().add(icon);
        vBox.getChildren().add(nameLabel);
        vBox.getChildren().add(subtitleLabel);
        vBox.getChildren().add(viewBtn);
        vBox.getChildren().add(prescriptionBtn);

        return vBox;
    }
}

