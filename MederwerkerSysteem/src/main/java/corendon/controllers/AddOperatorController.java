package corendon.controllers;

import corendon.queries.MyJDBC;
import corendon.utilities.SetStage;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 *
 * @author Noa Timisela
 */

public class AddOperatorController implements Initializable {
    String name, lastName, language, emailAddress, password, now;
    int isAdmin;
    
    private static boolean fieldEmpty(String str) {
        return str == null || str.isEmpty();
    }
    
    @FXML
    private AnchorPane addOperatorSub;
    
    @FXML
    private Label errorMessage;
    
    @FXML
    private TextField fxFirstName, fxLastName, email_address;
    
    @FXML
    private ChoiceBox fxOperatorLanguage;
    
    @FXML
    private CheckBox fxAdminCheckBox;
    
    //set screen invisible when canceled
    @FXML
    public void handleCloseButtonAction(ActionEvent event) throws IOException, Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new SetStage(stage, "/fxml/HomeLayout.fxml");
    }
    
    @FXML
    public void submitMethod(ActionEvent event) throws IOException, Exception {
        
        MyJDBC myJDBC = new MyJDBC("fys");
        
        //get the user input
        name = fxFirstName.getText() + " " + fxLastName.getText();
        language = fxOperatorLanguage.getValue().toString();
        emailAddress = email_address.getText();
        password = fxFirstName.getText();
        
        //check if the operator has an admin status
        isAdmin = fxAdminCheckBox.isSelected() ? 1 : 0;
        now = new Timestamp(System.currentTimeMillis()).toString();
        
        if (fieldEmpty(fxFirstName.toString()) || fieldEmpty(fxLastName.toString()) || fieldEmpty(emailAddress) 
            || fieldEmpty(fxOperatorLanguage.getSelectionModel().getSelectedItem().toString())) {
            
            errorMessage.setText("All fields are required");
            errorMessage.setTextFill(Color.RED);
            errorMessage.setVisible(true); 
            
        } else if (emailAddress != null) {
            errorMessage.setText("E-mailadres already exists");
            errorMessage.setTextFill(Color.RED);
            errorMessage.setVisible(true);
            
        } else {
            errorMessage.setVisible(false);
            
            //If everything is correct, send the user input to the database
            String addNewOperator = "INSERT INTO users(name,default_language,email,password,is_admin, created_at, updated_at) VALUES ('" + name + "','" + language + "','" + emailAddress + "','" + password + "'," + isAdmin + ",'" + now + "','" + now + "');";
            myJDBC.executeUpdateQuery(addNewOperator);
        }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fxOperatorLanguage.setValue("Select language");
        fxOperatorLanguage.getItems().add("English");
        fxOperatorLanguage.getItems().add("Dutch");
        fxOperatorLanguage.getItems().add("Turkish");
        fxOperatorLanguage.getItems().add("Chinese");
    }    
}
