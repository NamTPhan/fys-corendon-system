package com.mycompany.clientsystem;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Nam
 */
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/landingScreen.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/statuscheckcode.css");
        
        stage.setTitle("Corendon - Register & Status Luggage");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
