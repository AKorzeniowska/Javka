package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    static Stage currentStage=null;

    public static void sceneSetter(String fxmlAddress){
        Parent root=null;
        try {
            root=FXMLLoader.load(Main.class.getResource(fxmlAddress));
        } catch (IOException e) {
            System.out.println("IOException: wrong .fxml address");
        }
        currentStage.setTitle("Profesjonalna Aplikacja; 3000");
        currentStage.setScene(new Scene(root, 380, 400));
        currentStage.show();
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Profesjonalna Aplikacja; 3000");
        primaryStage.setScene(new Scene(root, 380, 400));
        currentStage=primaryStage;
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
