package main.ui.admin.view_doctor;

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
import util.Util;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class viewDoctorsController implements Initializable {
    @FXML
    private ListView<HBox> patientListView;

    @FXML
    private TextField patientSearchTv;

    @FXML
    private Label searchResultNumber;

    private DatabaseHandler connectMSSQL;
    private ArrayList<String> doctorNameList;
    private ArrayList<String> doctorSpealityList;
    private ArrayList<Integer> doctorIdList;
    private int numberOfDoctorSearched;
    private int counter=0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectMSSQL = new DatabaseHandler();
        patientSearchTv.setStyle("-fx-background-image: url('/resources/icons/ic_search.png');");
        //image.setImage(new Image("/resources/icons/ic_search.png"));
        searchResultNumber.setText("");
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
        counter = 0;
        numberOfDoctorSearched = 0;
        doctorNameList = new ArrayList<String>();
        doctorSpealityList = new ArrayList<String>();
        doctorIdList = new ArrayList<Integer>();
        ResultSet resultSet = connectMSSQL.getDoctorMainAdmin(patientSearchTv.getText());
        //numberOfDoctorSearched = resultSet.getFetchSize();
        while (resultSet.next()) {
            doctorNameList.add(resultSet.getString("doctor_specialist"));
            doctorSpealityList.add(resultSet.getString("doctor_name"));
            doctorIdList.add(Integer.parseInt(resultSet.getString("doctor_id")));
            numberOfDoctorSearched++;
        }
        searchResultNumber.setText(numberOfDoctorSearched + " SEARCH RESULTS FOUND");
        createCardItems(numberOfDoctorSearched);

    }

    private void createCardItems(int items){
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
            int a = counter;
            Button button = (Button) vBox.getChildren().get(3);
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/ui/admin/edit_doctor/edit_doctor.fxml"));
                        Parent root = (Parent) fxmlLoader.load();

                        EditDoctorController editDoctorController = fxmlLoader.getController();
                        editDoctorController.setDoctorNumber(doctorIdList.get(a));

                        Stage stage = new Stage();
                        stage.setTitle(doctorNameList.get(a));
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

        Label nameLabel = new Label(doctorNameList.get(counter));
        nameLabel.getStyleClass().add("text-sub-heading-bold");
        nameLabel.setWrapText(true);
        nameLabel.setTextAlignment(TextAlignment.CENTER);

        Label subtitleLabel = new Label(doctorSpealityList.get(counter));
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


                /*Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("view_patient.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("$NameOfPatient");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, Util.DIALOG_SCREEN_WIDTH, Util.DIALOG_SCREEN_HEIGHT));
                    stage.show();
                    // Hide this current window (if this is what you want)
                    //((Node)(event.getSource())).getScene().getWindow().hide();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }*/

            }
        });

        JFXButton deletebtn = new JFXButton();
        deletebtn.setText("DELETE");
        deletebtn.setPrefWidth(220);
        deletebtn.getStyleClass().add("button-secondary-small");
        deletebtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

        //ahnaf codes

        /*                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("view_prescription_history.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("My New Stage Title");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, Util.DIALOG_SCREEN_WIDTH, Util.DIALOG_SCREEN_HEIGHT));
                    stage.show();
                    // Hide this current window (if this is what you want)
                    //((Node)(event.getSource())).getScene().getWindow().hide();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }*/


            }
        });

        vBox.getChildren().add(icon);
        vBox.getChildren().add(nameLabel);
        vBox.getChildren().add(subtitleLabel);
        vBox.getChildren().add(viewBtn);
        vBox.getChildren().add(deletebtn);
        counter++;
        return vBox;
    }
}

