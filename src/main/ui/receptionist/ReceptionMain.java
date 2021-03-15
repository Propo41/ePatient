package main.ui.receptionist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Util;

// for test only. delete it
public class ReceptionMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // for dummy testing. delete this
        Util.getInstance().setUserId("1");
        Util.getInstance().setUserType("doctor");

        Parent root = FXMLLoader.load(getClass().getResource("reception_main.fxml"));
        primaryStage.setTitle("a World");
        Scene scene = new Scene(root, Util.SCREEN_WIDTH, Util.SCREEN_HEIGHT); // ratio is 1.4:1
        scene.getStylesheets().addAll(getClass().getResource("/resources/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(Util.SCREEN_HEIGHT);
        primaryStage.setMinWidth(Util.SCREEN_WIDTH);
        primaryStage.setTitle("ePatient");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
