package main.ui.test;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TestController implements Initializable {
    @FXML
    ListView<String> listView;

    @FXML
    BorderPane bp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.getItems().add("Hewlo");
        listView.getItems().add("Hewlo");
        listView.getItems().add("Hewlo");
        listView.getItems().add("Hewlo");
        listView.getItems().add("Hewlo");
        listView.getItems().add("Hewlo");

        bp.getStyleClass().add("env-pane");


    }
}
