package main.ui.admin.edit_doctor;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class EditDoctorController  implements Initializable {
    @FXML
    private Label doctor_name;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 10; i++) {
            HBox hBox = createCard("TOM JARKINS",
                    "12 MARCH 2021",
                    "REASON: THROAT PAIN",
                    false,
                    "HEADER NAME");
            HBox btnContainer = (HBox) hBox.getChildren().get(2);
            JFXButton button = (JFXButton) btnContainer.getChildren().get(0);
            int finalI = i;
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("clicked index: " + (finalI) ) ;
                    educationalBackgroundList.getItems().remove(finalI);
                    //error here when deleting
                }
            });
            educationalBackgroundList.getItems().add(hBox);
        }

    }


    @FXML
    void educationalBackgroundClick(MouseEvent event) {
        System.out.println("Add Educational Background");
    }


    public HBox createCard(String name, String date, String reason, boolean isHeader, String header) {
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
        Label nameLabel = new Label(name);
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
        JFXButton viewMoreBtn = new JFXButton();
        viewMoreBtn.setGraphic(icon2);
        viewMoreBtn.setPrefHeight(30);
        viewMoreBtn.setPrefWidth(30);
        viewMoreBtn.setAlignment(Pos.CENTER);
        viewMoreBtn.getStyleClass().add("button-text-only-small");
        viewMoreBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });

        hBox1.getChildren().add(viewMoreBtn);

        hBox.getChildren().addAll(vBox, vBox1, hBox1);

        return hBox;

    }


}
