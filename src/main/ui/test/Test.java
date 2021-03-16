package main.ui.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.*;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 1200, 857); // ratio is 1.4:1
        scene.getStylesheets().addAll(getClass().getResource("/resources/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(857);
        primaryStage.setMaxHeight(1200);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);


        int[] list = new int[]{1, 2, 3};
        System.out.println(Arrays.toString(solution(list, 0)));
    }


    public static int[] solution(int[] data, int n) {
       /* if(Arrays.equals(data, new int[]{1, 2, 2, 3, 3, 3, 4, 5, 5})){
            data = new int[]{1, 4};
            return data;
        }*/

        HashMap<Integer, Integer> count = new HashMap<>();
        for (Integer integer : data) {
            count.put(integer, count.get(integer) == null ? 1 : count.get(integer) + 1);
        }

        ArrayList<Integer> arrayList2 = new ArrayList<>();
        for(Integer i: data){
            if(! (count.get(i)>n)){
                arrayList2.add(i);
            }
        }

        data = arrayList2.stream().mapToInt(i -> i).toArray();
        return data;
    }
}
