package main.ui.receptionist.test_report;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AddTestReportDialogController {

    @FXML
    private ListView<HBox> testReportList;
    StackPane stackPane;

    public void init(StackPane stackPane) {
        this.stackPane = stackPane;

        for (int i = 0; i < 10; i++) {
            HBox hBox = createCard("Hello World");
            HBox btnContainer = (HBox) hBox.getChildren().get(2);
            ImageView button = (ImageView) btnContainer.getChildren().get(0);
            int finalI = i;

            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event){
                    System.out.println("Tile pressed " + finalI);
                    try {
                    JFXDialogLayout content = new JFXDialogLayout();
                    content.getStyleClass().add("jfx-dialog-overlay-pane");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/ui/receptionist/test_report/add_success_dialog.fxml"));
                    loader.load();
                    JFXDialog dialog = new JFXDialog(stackPane, loader.getRoot(), JFXDialog.DialogTransition.CENTER);
                    dialog.getStyleClass().add("jfx-dialog-layout");
                    AddSuccessDialogController addSuccessDialogController = loader.getController();
                    addSuccessDialogController.init(dialog);
                    dialog.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            testReportList.getItems().add(hBox);
        }
    }

    @FXML
    void onCloseClicked(ActionEvent event) {

    }

    public HBox createCard(String education) {
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
        Label nameLabel = new Label(education);
        nameLabel.getStyleClass().add("text-card-subtitle");
        nameLabel.setAlignment(Pos.CENTER);
        vBox1.getChildren().addAll(nameLabel);

        ImageView icon2 = new ImageView();
        icon2.getStyleClass().add("pdf-upload-icon");
        icon2.setFitWidth(25);
        icon2.setFitHeight(25);

        HBox hBox1 = new HBox();
        HBox.setHgrow(hBox1, Priority.ALWAYS);
        hBox1.setAlignment(Pos.CENTER_RIGHT);
        hBox1.getChildren().add(icon2);

        hBox.getChildren().addAll(vBox, vBox1, hBox1);
        return hBox;
    }


}
