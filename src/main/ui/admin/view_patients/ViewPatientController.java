package main.ui.admin.view_patients;

import com.jfoenix.controls.JFXButton;
import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.ui.admin.edit_doctor.EditDoctorController;
import util.Util;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewPatientController implements Initializable {
    @FXML
    private ListView<HBox> patientListView;

    @FXML
    private TextField patientSearchTv;

    @FXML
    private Label searchResultNumber;

    private DatabaseHandler connectMSSQL;
    private ArrayList<String> patientNameList;
    private ArrayList<String> patientSince;
    private ArrayList<Integer> doctorIdList;
    private int numberOfDoctorSearched;
    int count;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectMSSQL = new DatabaseHandler();
        patientSearchTv.setStyle("-fx-background-image: url('/resources/icons/ic_search.png');");
        //image.setImage(new Image("/resources/icons/ic_search.png"));
        searchResultNumber.setText("");
        count = 0;
    }

    /*
     * creates the desired number of items, passed as a parameter
     */


    @FXML
    void onSearchBtnClick(ActionEvent event) throws SQLException {
        String str = patientSearchTv.getText();
        if(str.equals("")){
           return;
        }
        patientListView.getItems().clear();
        count = 0;
        numberOfDoctorSearched = 0;
        patientNameList = new ArrayList<String>();
        patientSince = new ArrayList<String>();
        doctorIdList = new ArrayList<Integer>();
        ResultSet resultSet = connectMSSQL.getDoctorMainAdmin(patientSearchTv.getText());
        //numberOfDoctorSearched = resultSet.getFetchSize();
        while (resultSet.next()) {
            patientNameList.add(resultSet.getString("doctor_specialist"));
            patientSince.add(resultSet.getString("doctor_name"));
            doctorIdList.add(Integer.parseInt(resultSet.getString("doctor_id")));
            numberOfDoctorSearched++;
        }
        searchResultNumber.setText(numberOfDoctorSearched + " SEARCH RESULTS FOUND");
        createCardItems(numberOfDoctorSearched);

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

    /*
     * creates i number of items, with each row having $maxItemsPerRow items
     * called implicitly from createCardItems()
     */
    private void createCardsPerRow(int i) {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        while(i!=0){
            VBox vBox = createCard();
            // vBox.setMaxWidth(290);
            hBox.getChildren().add(vBox);
            i--;
        }
        patientListView.getItems().add(hBox);

    }

    /*
     * creates a single card item
     * called implicitly from createCardsPerRow()
     */
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

        Label nameLabel = new Label(patientNameList.get(index));
        nameLabel.getStyleClass().add("text-sub-heading-bold");
        nameLabel.setWrapText(true);
        nameLabel.setTextAlignment(TextAlignment.CENTER);

        Label subtitleLabel = new Label(patientSince.get(index));
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
                System.out.println(index);
                System.out.println(doctorIdList.get(index));
                System.out.println(patientNameList.get(index));
                try {


                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/ui/admin/edit_doctor/edit_doctor.fxml"));
                    Parent root = (Parent) fxmlLoader.load();

                    try {
                        EditDoctorController editDoctorController = fxmlLoader.getController();
                        editDoctorController.setDoctorNumber(doctorIdList.get(index),editDoctorController);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    Stage stage = new Stage();
                    stage.setTitle(patientNameList.get(index));
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

