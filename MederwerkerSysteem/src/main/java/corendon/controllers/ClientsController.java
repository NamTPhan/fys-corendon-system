package corendon.controllers;

import corendon.queries.Clients;
import java.net.URL;
import java.util.ResourceBundle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import corendon.queries.MyJDBC;

public class ClientsController implements Initializable {

    @FXML
    private TableView clientsTableView;

    @FXML
    private Button backToOverviewBtn, editClientDetailsBtn, deleteClientBtn;

    @FXML
    private GridPane popupGrid;

    @FXML
    private TextField firstname, lastname, email, phone, address, postcalCode, state, residence, country;

    Stage stage;
    Parent root;

    private final ObservableList<Clients> clientsList
            = FXCollections.observableArrayList();

    private static Clients selectedClient;
    
    private MyJDBC myJDBC = new MyJDBC();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            //intializeren van method die tabel vult met data
            buildData();
        } catch (SQLException ex) {
            Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //aanpassen van Client details bij klik op knop
        editClientDetailsBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                try {
                    if (editSelectedClient()) {
                        //notificatie popup van update
                        Alert alert = new Alert(AlertType.INFORMATION, "Successfully updated the client.");
                        alert.showAndWait();
                        popupGrid.setVisible(false);
                    }

                    //refreshen van Client Table View
                    buildData();
                } catch (SQLException ex) {
                    Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //Verwijderen Client details bij klik op knop
        deleteClientBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                //bevestiging van delete
                Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this client?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    try {
                        deleteSelectedClient();
                        
                    //refreshen van Client Table View
                    buildData();
                    popupGrid.setVisible(false);
                    } catch (SQLException ex) {
                        Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        //custom selectie model, get de row bij dubbelklik:
        clientsTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    Node node = ((Node) event.getTarget()).getParent();

                    if (selectClient(node)) {
                        //popup met details weergeven
                        popupGrid.setVisible(true);
                    }
                }
            }
        });
    }

    @FXML
    private void handlebackToOverviewBtn(ActionEvent event) {
        popupGrid.setVisible(false);
    }

    private void buildData() throws SQLException {
        //leegmaken van table view en clientlist als er gerefresht wordt
        clientsTableView.getItems().clear();
        clientsList.clear();

        //SQL Query om data op te halen uit database
        String sqlQuery = "SELECT * from clients ORDER BY updated_at desc;";
        //ResultSet
        ResultSet rs = this.myJDBC.connection.createStatement().executeQuery(sqlQuery);

        //per rs data toevoegen aan clientsList
        while (rs.next()) { 
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            String address = rs.getString("address");
            String place = rs.getString("place");
            String postalCode = rs.getString("postal_code");
            String phoneNumber = rs.getString("phone_number");
            String state = rs.getString("state");
            String country = rs.getString("country");
            Timestamp createdAt = rs.getTimestamp("created_at");
            Timestamp updatedAt = rs.getTimestamp("updated_at");

            clientsList.add(new Clients(id, firstName, lastName, email, address, place, postalCode, phoneNumber, state, country, createdAt, updatedAt));
        }

        //associeëren van de data collectie met de table view
        clientsTableView.setItems(this.clientsList);

        //associeëren van elke tableview column met zn data property
        for (int cnr = 0; cnr < clientsTableView.getColumns().size(); cnr++) {
            TableColumn tc = (TableColumn) clientsTableView.getColumns().get(cnr);
            String propertyName = tc.getId();
            if (propertyName != null && !propertyName.isEmpty()) {
                //hier wordt er vanuit gegaan dat de Clients class definitie getters en setters heeft met namen die matchen met
                //de property naam in het fx:id veld van de tabel column in de FXML view
                tc.setCellValueFactory(new PropertyValueFactory<>(propertyName));
                System.out.println("Attached column " + propertyName + " in tableview to matching attribute");
            }
        }
    }

    //het selecteren van een client en z'n data weergeven binnen de detail view
    private boolean selectClient(Node node) {
        TableRow row;

        if (node instanceof TableRow) {
            row = (TableRow) node;
            //get details from clientsList where id is same as selected row and fill in the input fields in popupGrid
            selectedClient = clientsList.get(row.getIndex());

            firstname.setText(selectedClient.getFirstName());
            lastname.setText(selectedClient.getLastName());
            email.setText(selectedClient.getEmail());
            phone.setText(selectedClient.getPhoneNumber());
            address.setText(selectedClient.getAddres());
            state.setText(selectedClient.getState());
            residence.setText(selectedClient.getPlace());
            country.setText(selectedClient.getCountry());
            postcalCode.setText(selectedClient.getPostalCode());

            return true;
        } else {
            return false;
        }
    }

    //Updaten van geselecteerde client.
    private boolean editSelectedClient() throws SQLException {

        // opbouwen van SQL update preparedstatement
        String query = "UPDATE clients SET first_name = ?, last_name = ?, email = ?, address = ?, place = ?, postal_code = ?, phone_number = ?, state = ?, country = ?, updated_at = ? WHERE id= ?;";
        PreparedStatement preparedStmt = this.myJDBC.connection.prepareStatement(query);
        preparedStmt.setString(1, firstname.getText());
        preparedStmt.setString(2, lastname.getText());
        preparedStmt.setString(3, email.getText());
        preparedStmt.setString(4, address.getText());
        preparedStmt.setString(5, residence.getText());
        preparedStmt.setString(6, postcalCode.getText());
        preparedStmt.setString(7, phone.getText());
        preparedStmt.setString(8, state.getText());
        preparedStmt.setString(9, country.getText());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        preparedStmt.setTimestamp(10, timestamp);
        preparedStmt.setInt(11, selectedClient.getId());

        System.out.println("The prepared statement: " + preparedStmt);
        // uitvoeren van de preparedstatement
        this.myJDBC.executePreparedStatementUpdateQuery(preparedStmt);
        
        System.out.println("Updated client:" + selectedClient.getId());
        return true;
    }

    //Verwijderen van geselecteerde client.
    private void deleteSelectedClient() throws SQLException {

        String sqlQuery = "DELETE FROM clients WHERE id = ?;";
        PreparedStatement preparedStmt = this.myJDBC.connection.prepareStatement(sqlQuery);
        preparedStmt.setInt(1, selectedClient.getId());

        System.out.println("The prepared statement: " + preparedStmt);
        // Uitvoeren van preparedstatement
        this.myJDBC.executePreparedStatementQuery(preparedStmt);

        //als succesvol popup weergeven.

        System.out.println("Deleted client:" + selectedClient.getId());
    }

}
