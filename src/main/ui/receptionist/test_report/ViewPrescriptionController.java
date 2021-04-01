package main.ui.receptionist.test_report;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import database.DoctorDao;
import database.PrescriptionDao;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.ui.admin.edit_doctor.EditDoctorController;
import main.ui.admin.edit_doctor.EditDoctorDialogController;
import model.Doctor;
import model.MedicalTest;
import model.Prescription;
import util.Util;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewPrescriptionController implements Initializable {

    @FXML
    private TextField testReportSearchTv;

    @FXML
    private Label searchResultNumber;

    @FXML
    private ListView<HBox> testReportListView;

    @FXML
    void onSearchBtnClick(ActionEvent event) {
        getTestReport();
    }

    int count;
    ArrayList<Prescription> prescriptionArrayList;
    StackPane stackPane;

    public void init(StackPane stackPane){
        this.stackPane = stackPane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        testReportSearchTv.setStyle("-fx-background-image: url('/resources/icons/ic_search.png');");
        //image.setImage(new Image("/resources/icons/ic_search.png"));
        searchResultNumber.setText("");
        count = 0;
    }

    private void getTestReport() {
        prescriptionArrayList = new ArrayList<>();
        String str = testReportSearchTv.getText();
        if(str.equals("")){
            return;
        }
        testReportListView.getItems().clear();
        count = 0;
        prescriptionArrayList = new PrescriptionDao().getMedicalTest(testReportSearchTv.getText());
        searchResultNumber.setText(prescriptionArrayList.size() + " SEARCH RESULTS FOUND");
        createCardItems(prescriptionArrayList.size());
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
        testReportListView.getItems().add(hBox);
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

        Label nameLabel = new Label(prescriptionArrayList.get(index).getPatientName());
        nameLabel.getStyleClass().add("text-card-subtitle");
        nameLabel.setWrapText(true);
        nameLabel.setTextAlignment(TextAlignment.CENTER);

        Label doctorLabel = new Label(prescriptionArrayList.get(index).getDoctorName());
        doctorLabel.getStyleClass().add("text-card-subtitle");
        doctorLabel.setWrapText(true);
        doctorLabel.setTextAlignment(TextAlignment.CENTER);

        Label dateLabel = new Label(prescriptionArrayList.get(index).getDateOfPrescription().toString());
        dateLabel.getStyleClass().add("text-card-subtitle");
        dateLabel.setWrapText(true);
        dateLabel.setTextAlignment(TextAlignment.CENTER);

        JFXButton viewBtn = new JFXButton();
        viewBtn.setText("VIEW");
        viewBtn.setPrefWidth(220);
        viewBtn.getStyleClass().add("button-primary-small");

        viewBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try{
                    JFXDialogLayout content = new JFXDialogLayout();
                    content.getStyleClass().add("jfx-dialog-overlay-pane");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/ui/receptionist/test_report/add_test_report_dialog.fxml"));
                    loader.load();
                    JFXDialog dialog = new JFXDialog(stackPane, loader.getRoot(), JFXDialog.DialogTransition.CENTER);
                    dialog.getStyleClass().add("jfx-dialog-layout");
                    AddTestReportDialogController addTestReportDialogController = loader.getController();
                    addTestReportDialogController.init(stackPane,index);
                    dialog.show();
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });

        vBox.getChildren().add(icon);
        vBox.getChildren().add(nameLabel);
        vBox.getChildren().add(doctorLabel);
        vBox.getChildren().add(dateLabel);
        vBox.getChildren().add(viewBtn);

        return vBox;
    }




}
