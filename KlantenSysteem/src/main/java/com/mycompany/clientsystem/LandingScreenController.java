/*
 * Start screen for the clientsystem
 */
package com.mycompany.clientsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 *
 * @author Noa
 */
public class LandingScreenController implements Initializable {

    @FXML
    private ChoiceBox choicebox;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, Exception {

        //Get current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        //Set the second screen fxml document on stage.
        new SetStage(stage, "/fxml/optionScreen.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set dropdown languagess
        choicebox.setValue("English");
        choicebox.getItems().add("English");
        choicebox.getItems().add("Dutch");
        choicebox.getItems().add("Turkish");
        choicebox.getItems().add("Chinese");
    }

}
