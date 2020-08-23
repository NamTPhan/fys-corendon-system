package com.mycompany.clientsystem;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Nam Phan - Studentnummer: 500769669
 */
public class StatusCheckCodeController implements Initializable {

    //All variables
    @FXML
    private ChoiceBox choiceboxPickup;

    @FXML
    private GridPane gridOptionScreen, gridReferenceCode, gridFoundLuggage, gridPickupLocation, gridConfirmLuggage, gridFaqScreen;

    @FXML
    private Label referenceCodeWarning, textFoundLocation, textLuggageFound;

    @FXML
    private String stringInputReferenceCode, resultCode, getLuggageLocation, getHomeAddressClient;
    private String checkReferenceCodeSql, checkLuggageLocationSql, checkLuggageHomeAddressSql;

    @FXML
    private TextField submitReferenceCode;

    @FXML
    private Button continueToPickup, notFoundReturnHome;

    @FXML
    private ImageView notFoundIcon;

    @FXML
    private int submitRefCodeOnlyOnce = 0;
    
    @FXML
    private FontAwesomeIconView faIconNotFound, faIconFound;

    //Select database for queries
    MyJDBC myJDBC = new MyJDBC("fys");

    @FXML
    private void statusCheckNextAction(ActionEvent event) throws Exception {

        stringInputReferenceCode = submitReferenceCode.getText();

        //Check if input field is empty
        if (stringInputReferenceCode.isEmpty()) {
            referenceCodeWarning.setText("Reference code can't be empty!");
            referenceCodeWarning.setTextFill(Color.RED);
            referenceCodeWarning.setVisible(true);
        } else {

            //Check if luggage is missing and registered as an issue
            checkReferenceCodeSql = "SELECT luggage.label_number FROM luggage\n"
                    + "INNER JOIN issues ON luggage.id = issues.luggage_id\n"
                    + "WHERE label_number = '" + stringInputReferenceCode + "';";

            //Return luggage number
            resultCode = myJDBC.executeStringQuery(checkReferenceCodeSql);

            //Show next screen if reference code is valid
            if (resultCode != null) {

                //Select the location where the luggage is found
                checkLuggageLocationSql = "SELECT concat(airports.location, \", \", airports.state, \", \", airports.country)\n"
                        + "FROM luggage\n"
                        + "INNER JOIN issues ON luggage.id = issues.luggage_id\n"
                        + "INNER JOIN airports ON issues.airport_id = airports.id\n"
                        + "WHERE label_number = '" + resultCode + "';";

                //Return location of the luggage
                getLuggageLocation = myJDBC.executeStringQuery(checkLuggageLocationSql);

                //Find the home address of client who's luggage is missing
                checkLuggageHomeAddressSql = "SELECT concat(clients.address, \", \", clients.place, \", \", clients.postal_code, \", \" , clients.state, \", \" + clients.country)\n"
                        + "FROM luggage\n"
                        + "INNER JOIN issues ON issues.luggage_id = luggage.id\n"
                        + "INNER JOIN clients ON issues.client_id = clients.id\n"
                        + "WHERE label_number = '" + resultCode + "';";

                //Return home address of the client
                getHomeAddressClient = myJDBC.executeStringQuery(checkLuggageHomeAddressSql);

                //Add home address to the dropdown of the pickup screen & prevent adding it multiple times
                if (submitRefCodeOnlyOnce != 1) {
                    choiceboxPickup.getItems().add(1, getHomeAddressClient);
                    submitRefCodeOnlyOnce++;
                }

                //If the luggage is not found show the not found icon and message
                if (getLuggageLocation == null) {
                    faIconFound.setVisible(false);
                    notFoundIcon.setVisible(true);
                    faIconNotFound.setVisible(true);
                    textLuggageFound.setText("Your luggage has not been found");
                    textFoundLocation.setText("Please check again later");
                    continueToPickup.setVisible(false);
                    notFoundReturnHome.setVisible(true);
                } else {
                    textFoundLocation.setText("Your luggage has been found at " + getLuggageLocation);
                }
                
                referenceCodeWarning.setText("Reference code confirmed!");
                referenceCodeWarning.setTextFill(Color.GREEN);
                referenceCodeWarning.setVisible(true);
                gridFoundLuggage.setVisible(true);
            } else {
                referenceCodeWarning.setText("Reference code is invalid!");
                referenceCodeWarning.setTextFill(Color.RED);
                referenceCodeWarning.setVisible(true);
            }
        }
    }

    @FXML
    private void statusCheckSelectAction(ActionEvent event) throws IOException, Exception {
        gridPickupLocation.setVisible(true);
    }

    @FXML
    private void statusCheckReturnHome(ActionEvent event) throws IOException, Exception {
        //Get current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        //Set the third screen fxml document on stage.
        new SetStage(stage, "/fxml/landingScreen.fxml");
    }
    
    @FXML
    private void returnOptionScreen(ActionEvent event) throws IOException, Exception {
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

    @FXML
    private void pickupLocationConfirm(ActionEvent event) throws IOException, Exception {
        gridConfirmLuggage.setVisible(true);
    }

    @FXML
    private void backToFirstStatusCheck(ActionEvent event) throws IOException, Exception {
        gridFoundLuggage.setVisible(false);
        gridReferenceCode.setVisible(true);
    }

    @FXML
    private void backToFirstStatusCheck2(ActionEvent event) throws IOException, Exception {
        gridPickupLocation.setVisible(false);
        gridFoundLuggage.setVisible(false);
        gridReferenceCode.setVisible(true);
    }

    @FXML
    private void backToFoundLuggage(ActionEvent event) throws IOException, Exception {
        gridPickupLocation.setVisible(false);
        gridFoundLuggage.setVisible(true);
    }
    
    @FXML
    private void statusCheckAction(ActionEvent event) throws IOException, Exception {
        gridReferenceCode.setVisible(true);
    }
    
    @FXML
    private void statusRegisterLuggage(ActionEvent event) throws IOException, Exception {
        //Get current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        

        //Set the second screen fxml document on stage.
        new SetStage(stage, "/fxml/registerLostLuggageScreen.fxml"); 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Add pickup selections to dropdown
        choiceboxPickup.setValue("Pick-up at Schiphol");
        choiceboxPickup.getItems().add("Pick-up at Schiphol");
        choiceboxPickup.getItems().add("Other delivery location");
    }
}
