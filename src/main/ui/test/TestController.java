package main.ui.test;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class TestController implements Initializable {
    @FXML
    ListView<String> listView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.getItems().add("Hewlo");
        listView.getItems().add("Hewlo");
        listView.getItems().add("Hewlo");
        listView.getItems().add("Hewlo");
        listView.getItems().add("Hewlo");
        listView.getItems().add("Hewlo");

    }
}
