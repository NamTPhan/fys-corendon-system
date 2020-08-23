/*
 * Class to view a lost luggage from the dashboard table
 */
package corendon.controllers;

import corendon.queries.MyJDBC;
import corendon.utilities.SetStage;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nam Phan - Studentnummer: 500769669
 */
public class ViewLostLuggageController extends BaseController implements Initializable {

    @FXML
    private ChoiceBox statusChoiceDropdown, airportListDropdown;

    @FXML
    private TextField createLostFirstname, createLostLastname, createLostEmail, createLostPhone, createLostAddress, createLostZip, createLostState, createLostResidence,
            createLostCountry, createLostType, createLostBrand, createLostColor, createLostLabel, createLostFlightNumber, createLostDestination, createLostExtraInfo;

    @FXML
    private String viewLostFirstname, viewLostLastname, viewLostEmail, viewLostAddress, viewLostPlace, viewLostPostalCode, viewLostPhone, viewLostState, viewLostCountry,
            viewLostCreateAt, viewLostType, viewLostBrand, viewLostColor, viewLostLabelNr, viewLostFlightNr, viewLostDestination, viewLostExtraInfo, viewLostStatusName, getAirportName;

    @FXML
    private Label createLostLuggageWarning, labelCreatedAt, labelIssueId;

    @FXML
    private String inputFirstname, inputLastname, inputEmail, inputPhone, inputAddress, inputZip, inputState, inputResidence, inputCountry, inputType, inputBrand, inputColor,
            inputLabelNumber, inputFlightNumber, inputDestination, inputExtraInfo;

    @FXML
    private FontAwesomeIconView updateCheckmarkIcon;

    @FXML
    private Button updateLostLuggageBtn;

    private int issuesId, viewLostStatus, getAirportId;

    //Get the current timestamp
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    /**
     * Get the correct client_id from the chosen issue at the dashboard
     */
    public int selectedIssueLuggage = DashboardController.selectedIssue.client_id;

    //Select database for queries
    MyJDBC myJDBC = new MyJDBC("fys");

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String getStatusId = "SELECT status_id FROM issues WHERE client_id = " + selectedIssueLuggage;
            String getStatusIdSql = myJDBC.executeStringQuery(getStatusId);
            int convertStatusId = Integer.parseInt(getStatusIdSql);

            statusChoiceDropdown.getItems().add("UNSOLVED CASE");
            statusChoiceDropdown.getItems().add("SOLVED CASE");
            statusChoiceDropdown.getSelectionModel().select((convertStatusId == 2 ? 1 : 0));

            getDataClientLuggage(selectedIssueLuggage);
            setDataClientLuggage();
            getAllAirportsDatabase();

        } catch (SQLException ex) {
            Logger.getLogger(ViewLostLuggageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateLostLuggageBtn(ActionEvent event) throws IOException, Exception {

        getDataClientLuggage();

        //Check if all required fields are filled
        if (inputFirstname.isEmpty() || inputLastname.isEmpty() || inputEmail.isEmpty() || inputPhone.isEmpty() || inputAddress.isEmpty() || inputZip.isEmpty()
                || inputState.isEmpty() || inputResidence.isEmpty() || inputCountry.isEmpty() || inputType.isEmpty() || inputBrand.isEmpty() || inputColor.isEmpty()
                || inputLabelNumber.isEmpty() || inputFlightNumber.isEmpty() || inputDestination.isEmpty()) {

            createLostLuggageWarning.setText("Please fill in all the required fields!");
            createLostLuggageWarning.setTextFill(Color.RED);
            createLostLuggageWarning.setVisible(true);
        } else {
            //Update an issue, only airport and status
            String updateIssue = "UPDATE issues SET airport_id = " + airportListDropdown.getSelectionModel().getSelectedIndex()
                    + ", status_id = " + (statusChoiceDropdown.getSelectionModel().getSelectedIndex() + 1) + " WHERE client_id = " + selectedIssueLuggage + " AND status_id = 1;";
            myJDBC.executeUpdateQuery(updateIssue);

            //Update client information
            String updateClientData = "UPDATE clients SET first_name = '" + inputFirstname + "', last_name = '" + inputLastname + "', email = '" + inputEmail
                    + "', address = '" + inputAddress + "', place = '" + inputResidence + "', postal_code = '" + inputZip + "', phone_number = '" + inputPhone
                    + "', state = '" + inputState + "', country = '" + inputCountry + "', updated_at = '" + timestamp + "' WHERE id = " + selectedIssueLuggage;
            myJDBC.executeUpdateQuery(updateClientData);

            //Update luggage information
            String updateLuggageData = "UPDATE luggage INNER JOIN issues ON luggage.id = issues.luggage_id"
                    + " SET type = '" + inputType + "', brand = '" + inputBrand + "', color = '" + inputColor
                    + "', label_number = '" + inputLabelNumber + "', flight_number = '" + inputFlightNumber + "', destination = '" + inputDestination + "', extra_information = '" + inputExtraInfo
                    + "', luggage.updated_at = '" + timestamp + "' WHERE client_id = " + selectedIssueLuggage;
            myJDBC.executeUpdateQuery(updateLuggageData);

            createLostLuggageWarning.setText("Lost luggage succesfully updated!");
            createLostLuggageWarning.setTextFill(Color.GREEN);
            createLostLuggageWarning.setVisible(true);
            updateCheckmarkIcon.setVisible(false);
            updateLostLuggageBtn.setVisible(false);
        }

    }

    @FXML
    private void cancelCreateLostLuggage(ActionEvent event) throws IOException, Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new SetStage(stage, "/fxml/HomeLayout.fxml");
    }

    /**
     *
     * @param selectedIssueLuggage Get the correct client_id from the chosen
     * issue at the dashboard
     * @throws SQLException
     */
    public void getDataClientLuggage(int selectedIssueLuggage) throws SQLException {

        //SQL query to get all the data of the luggage and client
        String getAllDataClientLuggage = "SELECT clients.first_name, clients.last_name, clients.email, clients.address, clients.place, clients.postal_code,\n"
                + "clients.phone_number, clients.state, clients.country, clients.created_at, luggage.type, luggage.brand, luggage.color, luggage.label_number,\n"
                + "luggage.flight_number, luggage.destination, luggage.extra_information, issues.status_id, statuses.name, issues.id\n"
                + "FROM issues\n"
                + "INNER JOIN clients ON issues.client_id = clients.id\n"
                + "INNER JOIN luggage ON issues.luggage_id = luggage.id\n"
                + "INNER JOIN statuses ON issues.status_id = statuses.id\n"
                + "WHERE client_id = " + selectedIssueLuggage + " AND issues.status_id = 1";

        //ResultSet
        ResultSet rs = myJDBC.executeResultSetQuery(getAllDataClientLuggage);

        while (rs.next()) {
            issuesId = rs.getInt("issues.id");
            viewLostStatus = rs.getInt("issues.id");
            viewLostStatusName = rs.getString("statuses.name");
            viewLostFirstname = rs.getString("clients.first_name");
            viewLostLastname = rs.getString("clients.last_name");
            viewLostEmail = rs.getString("clients.email");
            viewLostAddress = rs.getString("clients.address");
            viewLostPlace = rs.getString("clients.place");
            viewLostPostalCode = rs.getString("clients.postal_code");
            viewLostPhone = rs.getString("clients.phone_number");
            viewLostState = rs.getString("clients.state");
            viewLostCountry = rs.getString("clients.country");
            viewLostCreateAt = rs.getString("clients.created_at");
            viewLostType = rs.getString("luggage.type");
            viewLostBrand = rs.getString("luggage.brand");
            viewLostColor = rs.getString("luggage.color");
            viewLostLabelNr = rs.getString("luggage.label_number");
            viewLostFlightNr = rs.getString("luggage.flight_number");
            viewLostDestination = rs.getString("luggage.destination");
            viewLostExtraInfo = rs.getString("luggage.extra_information");
        }
    }

    /**
     *
     * @throws SQLException
     */
    public void getAllAirportsDatabase() throws SQLException {

        String selectAllAirports = "SELECT id, name FROM airports";

        airportListDropdown.getItems().add("0: NOT FOUND YET");

        //ResultSet
        ResultSet rs = myJDBC.executeResultSetQuery(selectAllAirports);

        while (rs.next()) {
            getAirportId = rs.getInt("id");
            getAirportName = rs.getString("name");

            airportListDropdown.getItems().add(getAirportId + ": " + getAirportName);
        }
    }

    /**
     * Set all information from the database
     */
    public void setDataClientLuggage() {
        createLostFirstname.setText(viewLostFirstname);
        createLostLastname.setText(viewLostLastname);
        createLostEmail.setText(viewLostEmail);
        createLostPhone.setText(viewLostPhone);
        createLostAddress.setText(viewLostAddress);
        createLostZip.setText(viewLostPostalCode);
        createLostState.setText(viewLostState);
        createLostResidence.setText(viewLostPlace);
        createLostCountry.setText(viewLostCountry);
        createLostType.setText(viewLostType);
        createLostBrand.setText(viewLostBrand);
        createLostColor.setText(viewLostColor);
        createLostLabel.setText(viewLostLabelNr);
        createLostFlightNumber.setText(viewLostFlightNr);
        createLostDestination.setText(viewLostDestination);
        createLostExtraInfo.setText(viewLostExtraInfo);
        labelCreatedAt.setText(viewLostCreateAt);
        labelIssueId.setText(String.valueOf(issuesId));
    }

    /**
     * Get all values from the text fields
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
}
