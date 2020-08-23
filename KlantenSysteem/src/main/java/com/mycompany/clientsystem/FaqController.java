package com.mycompany.clientsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author n.timisela
 */
public class FaqController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private GridPane gridFaqScreen;
    
    @FXML
    private void hideFaq(ActionEvent event) {
        //Hide faq action
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * @var closeButton
     */
    @FXML
    public Button closeButton;

    /**
     *
     * @param event close window
     */
    @FXML
    public void handleCloseButtonAction(ActionEvent event) throws Exception {
        //Get current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        //Set the third screen fxml document on stage.
        new SetStage(stage, "/fxml/optionScreen.fxml");
    }
    
    @FXML
    private void showFaqScreen(ActionEvent event) throws IOException, Exception {
        gridFaqScreen.setVisible(true);
    }
    
    @FXML
    private void hideFaqScreen(ActionEvent event) throws IOException, Exception {
        gridFaqScreen.setVisible(false);
    }
    
}
