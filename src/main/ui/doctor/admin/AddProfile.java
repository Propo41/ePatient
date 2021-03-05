package main.ui.doctor.admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddProfile extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("addProfile.fxml"));
        primaryStage.setTitle("A World");
        Scene scene = new Scene(root, 1200, 857); // ratio is 1.4:1
        scene.getStylesheets().addAll(getClass().getResource("/resources/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(857);
        primaryStage.setMinWidth(1200);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
