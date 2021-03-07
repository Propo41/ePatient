package main.ui.doctor.profile;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private Label contactLabel;

    @FXML
    private TextArea summaryTv;

    @FXML
    private TextArea professionalExpTv;

    @FXML
    private VBox root;

    @FXML
    private TextArea hospitalAffiliationTv;

    @FXML
    private Label addressLabel;

    @FXML
    private Label specialityLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private TextArea eduBackgroundTv;

    @FXML
    private ListView<HBox> professionalExpListView;

    @FXML
    private ListView<HBox> eduBackgroundListView;

    @FXML
    private ListView<HBox> hospitalAffListView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 7; i++) {
            HBox hBox = createList("SUNDAY, 3:30PM - 4:30PM");
            eduBackgroundListView.getItems().add(hBox);
        }

        for (int i = 0; i < 7; i++) {
            HBox hBox = createList("SUNDAY, 3:30PM - 4:30PM");
            professionalExpListView.getItems().add(hBox);
        }

        for (int i = 0; i < 7; i++) {
            HBox hBox = createList("SUNDAY, 3:30PM - 4:30PM");
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
