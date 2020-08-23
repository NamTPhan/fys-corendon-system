package corendon.controllers;

import corendon.queries.Luggage;
import corendon.queries.MyJDBC;
import corendon.utilities.SetStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewFoundLuggageController implements Initializable {
    @FXML
    private TextField inputType, inputBrand, inputColor, inputLabelNumber, inputFlightNumber, inputDestination, inputExtraInformation;
    @FXML
    private Button confirmBtn, declineBtn;
    @FXML
    private Text textType, textBrand, textColor, textLabelNumber, textFlightNumber, textDestination, textExtraInformation;
    
    MyJDBC myJDBC = new MyJDBC("fys");
    
    //Get the correct client_id from the chosen issue at the dashboard
    public String selectedIssueLuggage = DashboardController.selectedIssue.id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.setData();
    }
    
    @FXML
    public void setData(){
        Luggage currentLuggage = new Luggage(myJDBC,Integer.parseInt(this.selectedIssueLuggage));
        
        // Set value of each field
        textType.setText(currentLuggage.type);
        textBrand.setText(currentLuggage.brand);
        textColor.setText(currentLuggage.color);
        textLabelNumber.setText(currentLuggage.labelNumber);
        textFlightNumber.setText(currentLuggage.flightNumber);
        textDestination.setText(currentLuggage.destination);
        textExtraInformation.setText(currentLuggage.extraInfo);
    }
    
    @FXML
    public void backButton(ActionEvent event) throws IOException, Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new SetStage(stage, "/fxml/HomeLayout.fxml");
    }
}
