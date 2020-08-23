package corendon.controllers;

import corendon.queries.Operators;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import corendon.queries.MyJDBC;
import corendon.utilities.SetStage;

public class OperatorsController implements Initializable {

    @FXML
    private TableView operatorsTableView;

    private final ObservableList<Operators> operatorsList
            = FXCollections.observableArrayList();

    @FXML
    private Button editOperatorDetailsBtn, deleteOperatorBtn;

    @FXML
    private GridPane popupGrid;

    @FXML
    private TextField inputName, inputLang, inputEmail;

    @FXML
    private RadioButton inputIsAdmin;

    Stage stage;
    Parent root;

    private static Operators selectedOperator;
    
    private MyJDBC myJDBC = new MyJDBC();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //intializeren van method die tabel vult met data
            buildData();
        } catch (SQLException ex) {
            Logger.getLogger(OperatorsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //aanpassen van Operator details bij klik op knop
        editOperatorDetailsBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                try {
                    if (editSelectedOperator()) {
                        //notificatie popup van update
                        Alert alert = new Alert(AlertType.INFORMATION, "Successfully updated the operator.");
                        alert.showAndWait();
                        popupGrid.setVisible(false);
                    }

                    //refreshen van Operator Table View
                    buildData();
                } catch (SQLException ex) {
                    Logger.getLogger(OperatorsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //Verwijderen Operator details bij klik op knop
        deleteOperatorBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                //bevestiging van delete
                Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this operator?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    try {
                        deleteSelectedOperator();

                        //refreshen van Operator Table View
                        buildData();
                        popupGrid.setVisible(false);
                    } catch (SQLException ex) {
                        Logger.getLogger(OperatorsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        //custom selectie model, get de row bij dubbelklik:
        operatorsTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    Node node = ((Node) event.getTarget()).getParent();

                    if (selectOperator(node)) {
                        //popup met details weergeven
                        popupGrid.setVisible(true);
                    }
                }
            }
        });
    }

    //detailview popup verbergen
    @FXML
    private void handlebackToOverviewBtn(ActionEvent event) {
        popupGrid.setVisible(false);
    }

    //resetten van een wachtwoord voor een user
    @FXML
    private void handleResetPasswordBtn(ActionEvent event) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //alleen toegestaan om wachtwoord te resetten als het je eigen wachtwoord is of als je admin status hebt.
        
        //uncomment onderstaande code na het aanpassen van <class>
        //if (<class>.loggedInUser.getIsAdmin() == true || <class>.loggedInUser.getId() == selectedOperator.getId()){
        Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to reset the password for this user?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            //reset password
            resetOperatorPassword();

        } // }
        else {
            //ingelogde user niet bevoegd
            Alert alertNA = new Alert(AlertType.INFORMATION, "You are not authorized to reset the password of this user.");
            alertNA.showAndWait();
        }
    }
    
    //add operator scherm weergeven
    @FXML
    private void handleAddOperatorBtn(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new SetStage(stage, "/fxml/AddOperator.fxml");
    }
    

    //Opbouwen van de overzicht tabel met operatorgegevens
    public void buildData() throws SQLException {
        //leegmaken van table view en operatorslist als er gerefresht wordt
        operatorsTableView.getItems().clear();
        operatorsList.clear();

        
        //SQL Query om data op te halen uit database
        String sqlQuery = "SELECT id, name,email, default_language,is_admin,created_at,updated_at from users ORDER BY updated_at desc";
        //ResultSet
        ResultSet rs = this.myJDBC.connection.createStatement().executeQuery(sqlQuery);

        //per rs data toevoegen aan operatorsList
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String defaultLanguage = rs.getString("default_language");
            boolean isAdmin = rs.getBoolean("is_admin");
            Timestamp createdAt = rs.getTimestamp("created_at");
            Timestamp updatedAt = rs.getTimestamp("updated_at");

            operatorsList.add(new Operators(id, name, email, defaultLanguage, isAdmin, createdAt, updatedAt));
        }

        //associeëren van de data collectie met de table view
        operatorsTableView.setItems(this.operatorsList);

        System.out.println("items set");

        //associeëren van elke tableview column met zn data property
        for (int cnr = 0; cnr < operatorsTableView.getColumns().size(); cnr++) {
            TableColumn tc = (TableColumn) operatorsTableView.getColumns().get(cnr);
            System.out.println("Tablecolumn:" + tc);
            String propertyName = tc.getId();
            System.out.println("Propertyname:" + propertyName);
            if (propertyName != null && !propertyName.isEmpty()) {
                //hier wordt er vanuit gegaan dat de Operators class definitie getters en setters heeft met namen die matchen met
                //de property naam in het fx:id veld van de tabel column in de FXML view
                tc.setCellValueFactory(new PropertyValueFactory<>(propertyName));
                System.out.println("Attached column " + propertyName + " in tableview to matching attribute");
            }
        }
    }

    //het selecteren van een operator en z'n data weergeven binnen de detail view
    private boolean selectOperator(Node node) {
        TableRow row;

        if (node instanceof TableRow) {
            row = (TableRow) node;

            //get details from operatorsList where id is same as selected row and fill in the input fields in popupGrid
            selectedOperator = operatorsList.get(row.getIndex());

            inputName.setText(selectedOperator.getName());
            inputEmail.setText(selectedOperator.getEmail());
            inputLang.setText(selectedOperator.getDefaultLanguage());

            inputIsAdmin.setSelected(selectedOperator.getIsAdmin());

            return true;
        } else {
            return false;
        }
    }

    //Updaten van geselecteerde operator.
    private boolean editSelectedOperator() throws SQLException {  

        // opbouwen van SQL update preparedstatement
        String query = "UPDATE users SET name = ?, email = ?, default_language = ?, is_admin = ?, updated_at = ? WHERE id= ?;";
        PreparedStatement preparedStmt = this.myJDBC.connection.prepareStatement(query);

        preparedStmt.setString(1, inputName.getText());
        preparedStmt.setString(2, inputEmail.getText());
        preparedStmt.setString(3, inputLang.getText());
        preparedStmt.setBoolean(4, inputIsAdmin.isSelected());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        preparedStmt.setTimestamp(5, timestamp);
        preparedStmt.setInt(6, selectedOperator.getId());

        System.out.println("The prepared statement: " + preparedStmt);
        // uitvoeren van de preparedstatement
        this.myJDBC.executePreparedStatementUpdateQuery(preparedStmt);

        System.out.println("Updated Operator:" + selectedOperator.getId());
        return true;
    }

    //Verwijderen van geselecteerde operator.
    private void deleteSelectedOperator() throws SQLException {

        String sqlQuery = "DELETE FROM users WHERE id = ?;";
        PreparedStatement preparedStmt = this.myJDBC.connection.prepareStatement(sqlQuery);
        preparedStmt.setInt(1, selectedOperator.getId());

        System.out.println("The prepared statement: " + preparedStmt);
        // Uitvoeren van preparedstatement
        this.myJDBC.executePreparedStatementQuery(preparedStmt);

        //als succesvol popup weergeven.
        System.out.println("Deleted operator:" + selectedOperator.getId());
    }

    //methode om het wachtwoord van een operator te wijzigen
    private void resetOperatorPassword() throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        

        String query = "UPDATE users SET password = ? WHERE id= ?;";
        PreparedStatement preparedStmt = this.myJDBC.connection.prepareStatement(query);;

        String randomPassword = generateRandomPassword();

        //eventueel randomPassword variabele onderstaand hashen in setSting(1 
        preparedStmt.setString(1, hashPassword(randomPassword));
        preparedStmt.setInt(2, selectedOperator.getId());

        this.myJDBC.executePreparedStatementUpdateQuery(preparedStmt);


        //view popup met random password
        Alert alert = new Alert(AlertType.INFORMATION, "The generated password is: " + randomPassword
                + "\n\nPlease remember to write down this new password.");
        alert.showAndWait();

    }

    //methode om random password te genereren
    private String generateRandomPassword() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // lengte van de random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    //methode om een wachtwoord te hashen naar MD5
    private String hashPassword(String unhashedPass) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] bytesOfMessage = unhashedPass.getBytes("UTF-8");

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(bytesOfMessage);
        BigInteger bigInt = new BigInteger(1,digest);
        
        //32 maken voor volledige hash string
        String hashedPass = bigInt.toString(16);
        
        return hashedPass;
    }

}
