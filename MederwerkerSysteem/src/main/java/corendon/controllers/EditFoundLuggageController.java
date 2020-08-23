package corendon.controllers;

import corendon.queries.Luggage;
import corendon.queries.MyJDBC;
import corendon.utilities.SetStage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditFoundLuggageController implements Initializable {
    @FXML
    private TextField inputType, inputBrand, inputColor, inputLabelNumber, inputFlightNumber, inputDestination, inputExtraInformation;
    
    @FXML
    private ChoiceBox dropDownSolved;
    
    @FXML
    private Button confirmBtn, declineBtn;
    
    MyJDBC myJDBC = new MyJDBC("fys");
    
    /**
     *  Get the correct luggage_id from the chosen issue at the dashboard
     */
    private int dashboardSelectedLuggageId = Integer.parseInt(DashboardController.selectedIssue.id);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dropDownSolved.setItems(FXCollections.observableArrayList("Unsolved", "Solved"));
        Luggage luggage = getLuggageById();

        setFields(luggage, getLuggageIssueSatus());
    }
    
    @FXML
    public void confirmEdit(ActionEvent event) throws IOException, Exception {
        Luggage currentLuggage = retreiveDataFromFields();
        int statusIndex = dropDownSolved.getSelectionModel().getSelectedIndex();
        
        String updateLuggageQuery = currentLuggage.updateLuggageQuery(dashboardSelectedLuggageId, currentLuggage);
        String updateIssuesQuery = "UPDATE Issues SET status_id = " + (statusIndex == 1 ? 2 : 1) + " WHERE luggage_id = " + dashboardSelectedLuggageId + ";";
        
        myJDBC.executeUpdateQuery(updateLuggageQuery);
        myJDBC.executeUpdateQuery(updateIssuesQuery);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new SetStage(stage, "/fxml/HomeLayout.fxml");
    }
    
    @FXML
    public void close (ActionEvent event) throws IOException, Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new SetStage(stage, "/fxml/HomeLayout.fxml");
    }
    
    public int getLuggageIssueSatus() {
        return Integer.parseInt(myJDBC.executeStringQuery("SELECT status_id FROM issues WHERE luggage_id = " + dashboardSelectedLuggageId + ";"));
    }
    
    @FXML
    public void setLuggageFound() {
        setIssueStatus(dashboardSelectedLuggageId, true);
    }
    
    private void setIssueStatus(int luggageId, boolean found) {
        int statusId = found ? 2 : 1;
        myJDBC.executeUpdateQuery("UPDATE Issues SET status_id = '" + statusId + "' WHERE luggage_id = '" + luggageId + "';");
    }
    
    private Luggage retreiveDataFromFields() {
        Luggage luggage = new Luggage(inputType.getText(), 
                inputBrand.getText(), 
                inputColor.getText(), 
                inputLabelNumber.getText(),
                inputFlightNumber.getText(),
                inputDestination.getText(), 
                inputExtraInformation.getText());
        return luggage;
    }
    
    private Luggage getLuggageById() {
        try {
            String query = "SELECT * FROM luggage WHERE id = " + dashboardSelectedLuggageId + ";";
            ResultSet resultSet = myJDBC.executeResultSetQuery(query);
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                String brand = resultSet.getString("brand");
                String color = resultSet.getString("color");
                String labelNumber = resultSet.getString("label_number");
                String flightNumber = resultSet.getString("flight_number");
                String destination = resultSet.getString("destination");
                String extraInformation = resultSet.getString("extra_information");
                        
                return new Luggage(type, brand, color, labelNumber, flightNumber, destination, extraInformation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditFoundLuggageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Luggage();
    }
    
    private void setFields(Luggage luggage, int issueStatus) {
        inputType.setText(luggage.type);
        inputBrand.setText(luggage.brand);
        inputColor.setText(luggage.color);
        inputLabelNumber.setText(luggage.labelNumber);
        inputFlightNumber.setText(luggage.flightNumber);
        inputDestination.setText(luggage.destination);
        inputExtraInformation.setText(luggage.extraInfo);
        dropDownSolved.getSelectionModel().select(issueStatus == 2 ? 1 : 0);
    }
}
