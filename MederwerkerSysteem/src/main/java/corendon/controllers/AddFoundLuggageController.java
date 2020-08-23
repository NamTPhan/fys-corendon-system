package corendon.controllers;

import corendon.queries.Luggage;
import corendon.queries.MyJDBC;
import corendon.utilities.SetStage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddFoundLuggageController implements Initializable {
    @FXML
    private TextField inputType, inputBrand, inputColor, inputLabelNumber, inputFlightNumber, inputDestination, inputExtraInformation;
    @FXML
    private Button confirmBtn, declineBtn;
    
    MyJDBC myJDBC = new MyJDBC("fys");
    
    int availableLuggageId = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { }
    
    @FXML
    public void confirmCreate(ActionEvent event) throws IOException, SQLException {
        Luggage luggage = retreiveDataFromFields();
        
        // This luggage id is the first luggage id that is available
        availableLuggageId = Integer.parseInt(myJDBC.executeStringQuery("SELECT MAX(id) FROM luggage;")) + 1;
        
        myJDBC.executeUpdateQuery(luggage.insertLuggageQuery());
        myJDBC.executeUpdateQuery(luggage.insertIssuesQuery(availableLuggageId));
        
        showDataDialog(event);
    }
    
    @FXML
    public void declineCreate(ActionEvent event) throws IOException, Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new SetStage(stage, "/fxml/HomeLayout.fxml");
    }
    
    @FXML
    public void showDataDialog(ActionEvent event) throws IOException {
        System.out.println("Show data dialog");
        Parent dataViewParent = FXMLLoader.load(getClass().getResource("/fxml/ViewFoundLuggage.fxml"));
        Scene dataViewScene = new Scene(dataViewParent);
        
        // This line gets the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(dataViewScene);
        window.show();
        
        System.out.println("Get available luggage by id: " + availableLuggageId);
        Luggage currentLuggage = new Luggage(myJDBC, availableLuggageId);
        
        // Search for every field
        Text textType = (Text)dataViewScene.lookup("#textType");
        Text textBrand = (Text)dataViewScene.lookup("#textBrand");
        Text textColor = (Text)dataViewScene.lookup("#textColor");
        Text textLabelNumber = (Text)dataViewScene.lookup("#textLabelNumber");
        Text textFlightNumber = (Text)dataViewScene.lookup("#textFlightNumber");
        Text textDestination = (Text)dataViewScene.lookup("#textDestination");
        Text textExtraInformation = (Text)dataViewScene.lookup("#textExtraInformation");
        
        // Set value of each field
        textType.setText(currentLuggage.type);
        textBrand.setText(currentLuggage.brand);
        textColor.setText(currentLuggage.color);
        textLabelNumber.setText(currentLuggage.labelNumber);
        textFlightNumber.setText(currentLuggage.flightNumber);
        textDestination.setText(currentLuggage.destination);
        textExtraInformation.setText(currentLuggage.extraInfo);
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
}
