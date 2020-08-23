package corendon.controllers;

import corendon.queries.Clients;
import corendon.queries.Luggage;
import corendon.queries.MatchLuggage;
import corendon.queries.MyJDBC;
import corendon.utilities.SetStage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Nam Phan - Studentnummer: 500769669
 */
public class AddLostLuggageController implements Initializable {

    //Get the current timestamp
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    
    private final ArrayList<MatchLuggage> matchLuggageList = new ArrayList();
    private int countMatchLuggage = 1;

    @FXML
    private TextField createLostFirstname, createLostLastname, createLostEmail, createLostPhone, createLostAddress, createLostZip, createLostState, createLostResidence,
            createLostCountry, createLostType, createLostBrand, createLostColor, createLostLabel, createLostFlightNumber, createLostDestination, createLostExtraInfo;

    @FXML
    private String inputFirstname, inputLastname, inputEmail, inputPhone, inputAddress, inputZip, inputState, inputResidence, inputCountry, inputType, inputBrand, inputColor,
            inputLabelNumber, inputFlightNumber, inputDestination, inputExtraInfo, getLastRefNumber, getLastLuggageId, getLastClientId;

    @FXML
    private Label createLostLuggageWarning, setLabelFullname, setLabelAddress, setLabelPlace, setLabelEmail, setLabelPostal, setLabelCountry, setLabelPhone, setLabelState,
            setLabelType, setLabelNumber, setLabelExtra, setLabelBrand, setLabelFlight, setLabelColor, setLabelDestination, setLabelCreated, setRefNumber,
            refNumber1, typeLug1, brandLug1, labelNumb1, flightNumb1, refNumber2, typeLug2, brandLug2, labelNumb2, flightNumb2, refNumber3, typeLug3, brandLug3, labelNumb3,
            flightNumb3, refNumber4, typeLug4, brandLug4, labelNumb4, flightNumb4;

    @FXML
    private Button createLostLuggageBtn;

    @FXML
    private GridPane gridCreateLostLuggage, gridDetailLostLuggage;

    //Select database for queries
    MyJDBC myJDBC = new MyJDBC("fys");

    @FXML
    private void createLostLuggageInDatabase(ActionEvent event) throws SQLException {

        getDataClientLuggage();

        //Check if the label number already exists
        String chkLabelnumber = "SELECT label_number FROM luggage WHERE label_number = '" + inputLabelNumber + "';";
        String getLabelnumber = myJDBC.executeStringQuery(chkLabelnumber);

        //Check if all required fields are filled and check if the label number already exists
        if (inputFirstname.isEmpty() || inputLastname.isEmpty() || inputEmail.isEmpty() || inputPhone.isEmpty() || inputAddress.isEmpty() || inputZip.isEmpty()
                || inputState.isEmpty() || inputResidence.isEmpty() || inputCountry.isEmpty() || inputType.isEmpty() || inputBrand.isEmpty() || inputColor.isEmpty()
                || inputLabelNumber.isEmpty() || inputFlightNumber.isEmpty() || inputDestination.isEmpty()) {

            createLostLuggageWarning.setText("Please fill in all the required fields!");
            createLostLuggageWarning.setTextFill(Color.RED);
            createLostLuggageWarning.setVisible(true);
        } else if (getLabelnumber != null) {
            createLostLuggageWarning.setText("This luggage has already been registered!");
            createLostLuggageWarning.setTextFill(Color.RED);
            createLostLuggageWarning.setVisible(true);
        } else {

            //Create new objects
            Clients clientinfo = new Clients(inputFirstname, inputLastname, inputEmail, inputPhone, inputAddress, inputZip, inputState, inputResidence, inputCountry, timestamp, timestamp);
            Luggage luggageinfo = new Luggage(inputType, inputBrand, inputColor, inputLabelNumber, inputFlightNumber, inputDestination, inputExtraInfo, timestamp);

            //Insert all client data to the database
            String insertNewClientSql = "INSERT INTO clients(first_name,last_name,email,address,place,postal_code,phone_number,state,country,created_at) VALUES ('" + clientinfo.getFirstName() + "','" + clientinfo.getLastName()
                    + "','" + clientinfo.getEmail() + "','" + clientinfo.getAddres() + "','" + clientinfo.getPlace() + "','" + clientinfo.getPostalCode() + "','" + clientinfo.getPhoneNumber() + "','" + clientinfo.getState() + "','" + clientinfo.getCountry() + "'"
                    + ",'" + timestamp + "') ";

            //Insert all luggage data to the database
            String insertNewLuggageSql = "INSERT INTO luggage(type,brand,color,label_number,flight_number,destination,extra_information,created_at) "
                    + "VALUES('" + luggageinfo.getType() + "','" + luggageinfo.getBrand() + "','" + luggageinfo.getColor() + "','" + luggageinfo.getLabelNumber() + "','"
                    + luggageinfo.getFlightNumber() + "','" + luggageinfo.getDestination() + "','" + luggageinfo.getExtraInfo() + "','" + timestamp + "')";

            myJDBC.executeUpdateQuery(insertNewClientSql);
            myJDBC.executeUpdateQuery(insertNewLuggageSql);
            
            //Get data for insert issue
             String getIdLastInsertedLuggageSql = "SELECT MAX(id) FROM luggage;";
             getLastLuggageId = myJDBC.executeStringQuery(getIdLastInsertedLuggageSql);
             
             String getIdLastInsertedClientSql = "SELECT MAX(id) FROM clients;";
             getLastClientId = myJDBC.executeStringQuery(getIdLastInsertedClientSql);
            
             //Create new issue for the submitted lost luggage
            String insertNewIssueSql = "INSERT INTO issues(luggage_id, airport_id, status_id, client_id, created_at, updated_at)"
                    + "VALUES(" + getLastLuggageId + "," + 0 + ", " + 1 + ", " + getLastClientId + ", '" + timestamp + "', '" + timestamp + "')";
             myJDBC.executeUpdateQuery(insertNewIssueSql);

            createLostLuggageBtn.setVisible(false);

            setDataClientLuggage();

            //Set reference number of the current issue
            String getIdLastInsertedIssueSql = "SELECT MAX(id) FROM issues;";
            getLastRefNumber = myJDBC.executeStringQuery(getIdLastInsertedIssueSql);
            setRefNumber.setText(getLastRefNumber);

            //Start finding matches with the lost luggage
            String getMatchingLuggage = "SELECT issues.id, luggage.type, luggage.brand, luggage.flight_number, luggage.destination\n"
                    + "FROM luggage\n"
                    + "INNER JOIN issues ON issues.luggage_id = luggage.id\n"
                    + "WHERE type = '" + inputType + "' AND brand = '" + inputBrand + "' OR (destination = '" + inputDestination + "' AND flight_number = '" + inputFlightNumber + "')\n"
                    + "LIMIT 4;";

            //ResultSet
            ResultSet rs = myJDBC.executeResultSetQuery(getMatchingLuggage);

            while (rs.next()) {
                int issuesId = rs.getInt("issues.id");
                String typeLuggage = rs.getString("luggage.type");
                String brandLuggage = rs.getString("luggage.brand");
                String destinationLuggage = rs.getString("luggage.destination");
                String flightnumberLuggage = rs.getString("luggage.flight_number");

                //Create a new object for each matching luggage
                matchLuggageList.add(new MatchLuggage(issuesId, typeLuggage, brandLuggage, destinationLuggage, flightnumberLuggage));
            }

            for (MatchLuggage luggage : matchLuggageList) {
                switch (countMatchLuggage) {
                    case 1:
                        refNumber1.setText("Reference number: " + String.valueOf(luggage.getIssuesId()));
                        typeLug1.setText("Type luggage: " + luggage.getTypeLuggage());
                        brandLug1.setText("Brand luggage: " + luggage.getBrandLuggage());
                        labelNumb1.setText("Destination: " + luggage.getDestinationLuggage());
                        flightNumb1.setText("Flight nr: " + luggage.getFlightnumberLuggage());
                        countMatchLuggage++;
                        break;
                    case 2:
                        refNumber2.setText("Reference number: " + String.valueOf(luggage.getIssuesId()));
                        typeLug2.setText("Type luggage: " + luggage.getTypeLuggage());
                        brandLug2.setText("Brand luggage: " + luggage.getBrandLuggage());
                        labelNumb2.setText("Destination: " + luggage.getDestinationLuggage());
                        flightNumb2.setText("Flight nr: " + luggage.getFlightnumberLuggage());
                        countMatchLuggage++;
                        break;
                    case 3:
                        refNumber3.setText("Reference number: " + String.valueOf(luggage.getIssuesId()));
                        typeLug3.setText("Type luggage: " + luggage.getTypeLuggage());
                        brandLug3.setText("Brand luggage: " + luggage.getBrandLuggage());
                        labelNumb3.setText("Destination: " + luggage.getDestinationLuggage());
                        flightNumb3.setText("Flight nr: " + luggage.getFlightnumberLuggage());
                        countMatchLuggage++;
                        break;
                    case 4:
                        refNumber4.setText("Reference number: " + String.valueOf(luggage.getIssuesId()));
                        typeLug4.setText("Type luggage: " + luggage.getTypeLuggage());
                        brandLug4.setText("Brand luggage: " + luggage.getBrandLuggage());
                        labelNumb4.setText("Destination: " + luggage.getDestinationLuggage());
                        flightNumb4.setText("Flight nr: " + luggage.getFlightnumberLuggage());
                        break;
                    default:
                        break;
                }
            }

            gridCreateLostLuggage.setVisible(false);
            gridDetailLostLuggage.setVisible(true);
        }
//        myJDBC.close();
    }

    @FXML
    private void cancelCreateLostLuggage(ActionEvent event) throws IOException, Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new SetStage(stage, "/fxml/HomeLayout.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Nothing to initialize for now
    }

    /**
     * Get all data from the inputs client and luggage
     */
    public void getDataClientLuggage() {
        inputFirstname = createLostFirstname.getText();
        inputLastname = createLostLastname.getText();
        inputEmail = createLostEmail.getText();
        inputPhone = createLostPhone.getText();
        inputAddress = createLostAddress.getText();
        inputZip = createLostZip.getText();
        inputState = createLostState.getText();
        inputResidence = createLostResidence.getText();
        inputCountry = createLostCountry.getText();
        inputType = createLostType.getText();
        inputBrand = createLostBrand.getText();
        inputColor = createLostColor.getText();
        inputLabelNumber = createLostLabel.getText();
        inputFlightNumber = createLostFlightNumber.getText();
        inputDestination = createLostDestination.getText();
        inputExtraInfo = createLostExtraInfo.getText();
    }

    /**
     * Show details lost luggage in an overview
     */
    public void setDataClientLuggage() {
        setLabelFullname.setText(inputFirstname + " " + inputLastname);
        setLabelAddress.setText(inputAddress);
        setLabelPlace.setText(inputResidence);
        setLabelEmail.setText(inputEmail);
        setLabelPostal.setText(inputZip);
        setLabelCountry.setText(inputCountry);
        setLabelPhone.setText(inputPhone);
        setLabelState.setText(inputState);
        setLabelType.setText(inputType);
        setLabelNumber.setText(inputLabelNumber);
        setLabelExtra.setText(inputExtraInfo);
        setLabelBrand.setText(inputBrand);
        setLabelFlight.setText(inputFlightNumber);
        setLabelColor.setText(inputColor);
        setLabelDestination.setText(inputDestination);
        setLabelCreated.setText(timestamp.toString());
    }
}
