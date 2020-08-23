/*
 * Register lost luggage class
 */
package com.mycompany.clientsystem;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Oussama Idbid - Studentnummer: 500785055
 */
public class RegisterLostLuggageController implements Initializable {
    
    //gets the current time
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    //Variable setup
    String firstName, lastName, emailAddress, phoneNumber, address, city, state, postalCode, choiceCountry, labelNumber, flightNumber, destination, type, brand, specialChar, color;

    int intLabelNumber;

    @FXML
    private AnchorPane anchorPaneClient, anchorPaneLabel, anchorPaneLuggage, anchorPaneSummary, anchorPaneConfirmation;

    @FXML
    private TextField first_name, last_name, email_address, phone_number, address_, city_, state_, postal_code, label_number, flight_number, destination_, type_, brand_,colorString;

    @FXML
    private ChoiceBox choice_country;
    @FXML
    private TextArea special_char;
    @FXML
    private ColorPicker color_;
    @FXML
    private ImageView emailConfirm1, emailConfirm2, checkLogo1, checkLogo2;
    @FXML
    private GridPane gridFaqScreen;

    @FXML
    private Label warningLabel1, warningLabel2, warningLabel3,email_addressSummary, phone_numberSummary, address_summary,
            postal_codeSummary, state_summary, city_summary, choice_countrySummary, type_summary, brand_summary, color_summary, label_numberSummary, flight_numberSummary,
            destination_summary, special_charSummary, confirmText1, confirmText2,fullnameSummary;

    //Method to show warning message when one or more textfields are empty
    private static Label warningLabelValidation(Label label) {
        label.setVisible(true);
        label.setTextFill(Color.RED);
        label.setText("Please fill in every field");
        FadeTransition ft = new FadeTransition(Duration.millis(3000), label);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.play();

        return label;
    }
    

    //Method used to change screen
    private static AnchorPane[] changeScreen(AnchorPane hide, AnchorPane show) {

        hide.setVisible(false);
        show.setVisible(true);

        return new AnchorPane[]{hide, show};
    }

    //Method to check if textField is empty or not
    private static boolean nullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }


    @FXML
    private void moveToLabel(ActionEvent event) {

        firstName = first_name.getText();
        lastName = last_name.getText();
        emailAddress = email_address.getText();
        phoneNumber = phone_number.getText();
        address = address_.getText();
        city = city_.getText();
        state = state_.getText();
        postalCode = postal_code.getText();
        choiceCountry = choice_country.getSelectionModel().toString();

            
            

        if (nullOrEmpty(firstName) || nullOrEmpty(lastName)
                || nullOrEmpty(emailAddress) || nullOrEmpty(phoneNumber)
                || nullOrEmpty(address) || nullOrEmpty(city)
                || nullOrEmpty(postalCode) || nullOrEmpty(state) || choice_country.getSelectionModel().getSelectedItem().toString().matches("Country")) {

            warningLabelValidation(warningLabel1);

        } else {
            changeScreen(anchorPaneClient, anchorPaneLabel);
        }
    }

    @FXML
    private void backToTraveler(ActionEvent event) {
        changeScreen(anchorPaneLabel, anchorPaneClient);
    }

    @FXML
    private void moveToLuggage(ActionEvent event) {

        labelNumber = label_number.getText();
        flightNumber = flight_number.getText();
        destination = destination_.getText();

        if (nullOrEmpty(labelNumber) || nullOrEmpty(flightNumber) || nullOrEmpty(destination)) {

            warningLabelValidation(warningLabel2);

        } else {
            changeScreen(anchorPaneLabel, anchorPaneLuggage);
        }
    }

    @FXML
    private void backToLabel(ActionEvent event) {
        changeScreen(anchorPaneLuggage, anchorPaneLabel);
    }
    
    @FXML
    private void returnOptionScreen(ActionEvent event) throws IOException, Exception {
        //Get current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        //Set the third screen fxml document on stage.
        new SetStage(stage, "/fxml/optionScreen.fxml");
    }

    @FXML
    private void moveToSummary(ActionEvent event) throws Exception {

        type = type_.getText();
        brand = brand_.getText();
        specialChar = special_char.getText();
//        color = toRGBCode(color_.getValue());
        color = colorString.getText();

        if (nullOrEmpty(type) || nullOrEmpty(brand) || nullOrEmpty(color)  ) {

            warningLabelValidation(warningLabel3);

        } else {
            changeScreen(anchorPaneLuggage, anchorPaneSummary);

            fullnameSummary.setText(firstName + " " + lastName);
            email_addressSummary.setText(emailAddress);
            phone_numberSummary.setText(phoneNumber);
            address_summary.setText(address);
            postal_codeSummary.setText(postalCode);
            state_summary.setText(state);
            city_summary.setText(city);
            choice_countrySummary.setText(choice_country.getSelectionModel().getSelectedItem().toString());
            type_summary.setText(type);
            brand_summary.setText(brand);
            label_numberSummary.setText(labelNumber);
            flight_numberSummary.setText(flightNumber);
            destination_summary.setText(destination);
            special_charSummary.setText(specialChar);
            color_summary.setText(color);
        }
    }



    @FXML
    private void backToEdit(ActionEvent event) {
        changeScreen(anchorPaneSummary, anchorPaneClient);
    }

    @FXML
    private void moveToConfirmation(ActionEvent event) {
        //initializes database connection
        MyJDBC myJDBC = new MyJDBC("fys");

        String labelNumberQuery = "select label_number from luggage where label_number = '" + label_number.getText() + "';";

        String checkLabelNumber = myJDBC.executeStringQuery(labelNumberQuery);
        //checks if filled in label number already exists, if not inserts filled in information into database
        if (checkLabelNumber == null) {
            //Insert query for cliënt information
            String clientQuery = "INSERT INTO clients(first_name,last_name,email,address,place,postal_code,phone_number,state,country,created_at,updated_at) VALUES ('" + first_name.getText() + "','" + last_name.getText()
                    + "','" + email_address.getText() + "','" + address_.getText() + "','" + city_.getText() + "','" + postal_code.getText() + "','" + phone_number.getText() + "','" + state_.getText() + "','" + choice_country.getSelectionModel().getSelectedItem().toString() + "'"
                    + ",'" + dtf.format(now) + "','" + dtf.format(now) + "') ";
            //Insert query for luggage information
            String luggageQuery = "INSERT INTO luggage(type,brand,color,label_number,flight_number,destination,extra_information,created_at,updated_at) VALUES('" + type_.getText() + "','" + brand_.getText() + "','" + colorString.getText() + "','" + label_number.getText() + "','" + flight_number.getText() + "','" + destination_.getText() + "','" + special_char.getText() + "','" + dtf.format(now) + "','" + dtf.format(now) + "')";

            //Insert client and luggage information to database
            myJDBC.executeUpdateQuery(clientQuery);
            myJDBC.executeUpdateQuery(luggageQuery);

            //Get data for insert issue
            String getIdLastInsertedLuggageSql = "SELECT MAX(id) FROM luggage;";
            String getLastLuggageId = myJDBC.executeStringQuery(getIdLastInsertedLuggageSql);

            String getIdLastInsertedClientSql = "SELECT MAX(id) FROM clients;";
            String getLastClientId = myJDBC.executeStringQuery(getIdLastInsertedClientSql);

            //Create new issue for the submitted lost luggage
            String insertNewIssueSql = "INSERT INTO issues(luggage_id, airport_id, status_id, client_id, created_at, updated_at) VALUES('" + getLastLuggageId + "','" + 0 + "','" + 1 + "','" + getLastClientId + "','" + dtf.format(now) + "','" + dtf.format(now) + "')";

            //Insert new issue(combination of client and luggage information)
            myJDBC.executeUpdateQuery(insertNewIssueSql);
            myJDBC.close();

            changeScreen(anchorPaneSummary, anchorPaneConfirmation);

            emailConfirm1.setVisible(true);
            emailConfirm2.setVisible(true);

        } else {
            changeScreen(anchorPaneSummary, anchorPaneConfirmation);
            

            checkLogo1.setVisible(true);
            checkLogo2.setVisible(true);

            confirmText1.setText("Your luggage has already been registered");
            confirmText2.setText("Check the status of your luggage by going back to the home screen");
        }
    }

    @FXML
    private void backToHome(ActionEvent event) throws IOException, Exception {
        
        if(emailConfirm1.isVisible() && emailConfirm2.isVisible()){
            emailConfirm1.setVisible(false);
            emailConfirm2.setVisible(false);            
            
        }
        if(checkLogo1.isVisible() && checkLogo2.isVisible()){
            checkLogo1.setVisible(false);
            checkLogo1.setVisible(false);
        }
            
        
        //Get current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        //Set the third screen fxml document on stage.
        new SetStage(stage, "/fxml/landingScreen.fxml");
    }
    
    @FXML
    private void showFaqScreen(ActionEvent event) throws IOException, Exception {
        gridFaqScreen.setVisible(true);
    }
    
    @FXML
    private void hideFaqScreen(ActionEvent event) throws IOException, Exception {
        gridFaqScreen.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        choice_country.setValue("Country");
        choice_country.getItems().addAll("Country","Afghanistan", "Albania", "Algeria",
                "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica",
                "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia",
                "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados",
                "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia",
                "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil",
                "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria",
                "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde",
                "Cayman Islands", "Central African Republic", "Chad", "Chile", "China",
                "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo",
                "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire",
                "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica",
                "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea",
                "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France",
                "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia",
                "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala",
                "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)",
                "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland",
                "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of",
                "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia",
                "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of",
                "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius",
                "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco",
                "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand",
                "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau",
                "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar",
                "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines",
                "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore",
                "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands",
                "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands",
                "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of",
                "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu",
                "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan",
                "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara",
                "Yemen", "Yugoslavia", "Zambia", "Zimbabwe");
    }
}
