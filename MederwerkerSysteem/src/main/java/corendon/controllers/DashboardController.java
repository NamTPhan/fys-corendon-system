/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corendon.controllers;

import corendon.queries.Found;
import corendon.queries.IssueTableRow;
import corendon.queries.Lost;
import corendon.queries.Luggage;
import corendon.utilities.ExcelHandler;
import corendon.utilities.SetStage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author Joris Schelfhout - Studentnummer: 500777847
 */
public class DashboardController extends BaseController implements Initializable {

    @FXML
    private Button search;
    @FXML
    private ChoiceBox columns;
    @FXML
    private TextField query;
    @FXML
    private TableView issueTable;
    @FXML
    private TableView recentlyLost;
    @FXML
    private TableView recentlyFound;
        
    private static Stage myStage;
    
    public static IssueTableRow selectedIssue;
    
    private ObservableList<IssueTableRow> issueList
            = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set items for dropdown
        columns.setItems(FXCollections.observableArrayList("brand", "color", "label_number", "flight_number", "type", "reference_number"));
        columns.setValue("type");
        //Get all issues for display in table
        issueList = Luggage.getAllForIssueTable();
        
        issueTable.setItems(issueList);
        recentlyLost.setItems(Lost.latest(1, 5));
        recentlyFound.setItems(Found.latest(1, 5));
        
        bindColumnValues(issueTable);
        bindColumnValues(recentlyLost);
        bindColumnValues(recentlyFound);
        
        issueTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Clicked on row");
                if (event.getClickCount() == 2) {
                    Node node = ((Node) event.getTarget()).getParent();
                    try {
                        selectIssue(node, event);
                    } catch (Exception ex) {
                        Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }    

    @FXML
    private void updateIssueTable(ActionEvent event) {
        issueTable.getItems().clear();
        if (query.getText().length() < 1) {
            issueTable.setItems(Luggage.getAllForIssueTable());
        } else {
            issueTable.setItems(Luggage.searchAllForIssueTable(query.getText(), columns.getSelectionModel().getSelectedItem().toString()));
        }    
    }
    
    @FXML
    private void toImportFound(ActionEvent event) throws SQLException, InvalidFormatException, IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel Worksheet", "*.xlsx"));

        File fileName = fileChooser.showOpenDialog(myStage);

        System.out.println("Selected file: " + fileName.getPath());

        ExcelHandler.saveToDatabase(fileName, 0);
    }
    
    @FXML
    private void toImportLost(ActionEvent event) throws SQLException, InvalidFormatException, IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel Worksheet", "*.xlsx"));

        File fileName = fileChooser.showOpenDialog(myStage);

        System.out.println("Selected file: " + fileName.getPath());

        ExcelHandler.saveToDatabase(fileName, 1);
    }
    
    private void bindColumnValues(TableView table)
    {
        for (int cnr = 0; cnr < table.getColumns().size(); cnr++) {
            TableColumn tc = (TableColumn) table.getColumns().get(cnr);
            String propertyName = tc.getId();
            if (propertyName != null && !propertyName.isEmpty()) {
                //hier wordt er vanuit gegaan dat de Clients class definitie getters en setters heeft met namen die matchen met
                //de property naam in het fx:id veld van de tabel column in de FXML view
                tc.setCellValueFactory(new PropertyValueFactory<>(propertyName));
                System.out.println("Attached column " + propertyName + " in tableview to matching attribute");
            }
        }
    }
    
    //het selecteren van een issue en z'n data weergeven binnen de detail view
    private boolean selectIssue(Node node, MouseEvent event) throws Exception {
        TableRow row;

        if (node instanceof TableRow) {
            row = (TableRow) node;
            //get details from clientsList where id is same as selected row and fill in the input fields in popupGrid
            selectedIssue = issueList.get(row.getIndex());
            //Is it an lost or found case? show correct pane
            if (selectedIssue.client_id > 0) {
                System.out.println("Showing screen for Lost item");
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                new SetStage(stage, "/fxml/ViewLostLuggage.fxml");
            } else {
                System.out.println("Showing screen for Found item");
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                new SetStage(stage, "/fxml/EditFoundLuggage.fxml");
            }

            return true;
        } else {
            return false;
        }
    }
    
    public void registerLost(ActionEvent event) throws IOException, Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new SetStage(stage, "/fxml/AddLostLuggage.fxml");
    }
    
    public void registerFound(ActionEvent event) throws IOException, Exception {
        //Switch to found
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new SetStage(stage, "/fxml/AddFoundLuggage.fxml");
    }
}
