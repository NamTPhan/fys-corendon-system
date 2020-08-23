/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corendon.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author joris
 */
public class Luggage {
    public String type, brand, color, labelNumber, flightNumber, destination, extraInfo;
    private Timestamp createdAt;
    
    private static final String DEFAULT_TYPE = null,
                                DEFAULT_BRAND = null,
                                DEFAULT_COLOR = null,
                                DEFAULT_LABEL_NUMBER = null,
                                DEFAULT_FLIGHT_NUMBER = null,
                                DEFAULT_DESTINATION = null,
                                DEFAULT_EXTRA_INFORMATION = null;
    private static final boolean DEFAULT_COMPENSATION = false;


    /**
     * All parameters contains luggage information from the client
     * @param type
     * @param brand
     * @param color
     * @param labelNumber
     * @param flightNumber
     * @param destination
     * @param extraInfo
     * @param createdAt
     */
    public Luggage(String type, String brand, String color, String labelNumber, String flightNumber, String destination, String extraInfo, Timestamp createdAt) {
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.labelNumber = labelNumber;
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.extraInfo = extraInfo;
        this.createdAt = createdAt;
    }
    
    public Luggage(String type, String brand, String color, String labelNumber, String flightNumber, String destination, String extraInfo) {
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.labelNumber = labelNumber;
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.extraInfo = extraInfo;
    }
    
    public Luggage() {
        this(DEFAULT_TYPE, DEFAULT_BRAND, DEFAULT_COLOR, DEFAULT_LABEL_NUMBER, DEFAULT_FLIGHT_NUMBER, DEFAULT_DESTINATION, DEFAULT_EXTRA_INFORMATION);
    }
    
    public Luggage(MyJDBC myJDBC, int luggageId) {
        this.type = myJDBC.executeStringQuery("SELECT type FROM luggage WHERE id = " + luggageId + ";");
        this.brand = myJDBC.executeStringQuery("SELECT brand FROM luggage WHERE id = " + luggageId + ";");
        this.color = myJDBC.executeStringQuery("SELECT color FROM luggage WHERE id = " + luggageId + ";");
        this.labelNumber = myJDBC.executeStringQuery("SELECT label_number FROM luggage WHERE id = " + luggageId + ";");
        this.flightNumber = myJDBC.executeStringQuery("SELECT flight_number FROM luggage WHERE id = " + luggageId + ";");
        this.destination = myJDBC.executeStringQuery("SELECT destination FROM luggage WHERE id = " + luggageId + ";");
        this.extraInfo = myJDBC.executeStringQuery("SELECT extra_information FROM luggage WHERE id = " + luggageId + ";");
    }


    /**
     *
     * @param type of the luggage
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @param brand of the luggage
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     *
     * @param color of the luggage
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     *
     * @param labelNumber of the luggage
     */
    public void setLabelNumber(String labelNumber) {
        this.labelNumber = labelNumber;
    }

    /**
     *
     * @param flightNumber of the luggage
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     *
     * @param destination of the luggage
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     *
     * @param extraInfo of the luggage
     */
    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    /**
     *
     * @param createdAt
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return type of the luggage
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return brand of the luggage
     */
    public String getBrand() {
        return brand;
    }

    /**
     *
     * @return color of the luggage
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @return label number of the luggage
     */
    public String getLabelNumber() {
        return labelNumber;
    }

    /**
     *
     * @return flight number of the luggage
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     *
     * @return destination of the luggage
     */
    public String getDestination() {
        return destination;
    }

    /**
     *
     * @return extra information of the luggage
     */
    public String getExtraInfo() {
        return extraInfo;
    }

    /**
     *
     * @return
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public static ObservableList<IssueTableRow> getAllForIssueTable() {        
        String query = (""
                + "SELECT luggage.id, issue.id AS reference_number, type, brand, color, label_number, issue.client_id AS client_id "
                + "FROM luggage "
                + "INNER JOIN issues as issue "
                + "ON luggage.id = issue.luggage_id "
                + "WHERE status_id = 1;"
                );
        MyJDBC db = new MyJDBC();
        try {
            ObservableList<IssueTableRow> list = FXCollections.observableArrayList();
            ResultSet resultSet = db.executeResultSetQuery(query);
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String type = resultSet.getString("type");
                String brand = resultSet.getString("brand");
                String color = resultSet.getString("color");
                String label_number = resultSet.getString("label_number");
                String reference_number = resultSet.getString("reference_number");
                int client_id = resultSet.getInt("client_id");
                
                list.add(new IssueTableRow(id, type, color, brand, label_number, reference_number, client_id));
            }
            
            return list;
        }
        catch (SQLException ex) {
            db.error(ex);
            return null;
        }
    }
    
    public static ObservableList<IssueTableRow> searchAllForIssueTable(String value, String column) {
        if (column == "reference_number") {
            column = "issue.id";
        }
        String query = (""
                + "SELECT luggage.id, issue.id AS reference_number, type, brand, color, label_number, issue.client_id AS client_id "
                + "FROM luggage "
                + "INNER JOIN issues as issue "
                + "ON luggage.id = issue.luggage_id "
                + "WHERE status_id = 1 AND " + column + " = '" + value + "'"
                );
        MyJDBC db = new MyJDBC();
        try {
            ObservableList<IssueTableRow> list = FXCollections.observableArrayList();
            ResultSet resultSet = db.executeResultSetQuery(query);
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String type = resultSet.getString("type");
                String brand = resultSet.getString("brand");
                String color = resultSet.getString("color");
                String label_number = resultSet.getString("label_number");
                String reference_number = resultSet.getString("reference_number");
                int client_id = resultSet.getInt("client_id");
                
                list.add(new IssueTableRow(id, type, color, brand, label_number, reference_number, client_id));
            }
            
            return list;
        }
        catch (SQLException ex) {
            db.error(ex);
            return null;
        }
    }
    
    public String insertIssuesQuery(int luggageId) {
        StringBuilder query = new StringBuilder("INSERT INTO Issues ");
        query.append("(luggage_id, airport_id, status_id, client_id, created_at, updated_at) ");
        
        query.append("VALUES ('").append(luggageId).append("', ");
        query.append("").append(0).append(", ");
        query.append("").append(1).append(", ");
        query.append("").append("null").append(", ");
        query.append("'").append(new Timestamp(System.currentTimeMillis())).append("', ");
        query.append("'").append(new Timestamp(System.currentTimeMillis())).append("');");
        
        return query.toString();
    }

    
    public int GetLuggageById(MyJDBC myJDBC, int luggageId){
        return 0;
    }
    
    public String insertLuggageQuery(){
        StringBuilder query = new StringBuilder("INSERT INTO Luggage ");
        query.append("(type, brand, color, label_number, flight_number, destination, extra_information, created_at, updated_at) ");
        
        query.append("VALUES ('").append(type).append("', ");
        query.append("'").append(brand).append("', ");
        query.append("'").append(color).append("', ");
        query.append("'").append(labelNumber).append("', ");
        query.append("'").append(flightNumber).append("', ");
        query.append("'").append(destination).append("', ");
        query.append("'").append(extraInfo).append("', ");
        query.append("'").append(new Timestamp(System.currentTimeMillis())).append("', ");
        query.append("'").append(new Timestamp(System.currentTimeMillis())).append("');");

        return query.toString();
    }
    
    public String updateLuggageQuery(int luggageId, Luggage luggage){
        StringBuilder query = new StringBuilder("UPDATE Luggage ");
        
        query.append("SET type = '").append(luggage.type).append("', ");
        query.append("brand = '").append(luggage.brand).append("', ");
        query.append("color = '").append(luggage.color).append("', ");
        query.append("label_number = '").append(luggage.labelNumber).append("', ");
        query.append("flight_number = '").append(luggage.flightNumber).append("', ");
        query.append("destination = '").append(luggage.destination).append("', ");
        query.append("extra_information = '").append(luggage.extraInfo).append("', ");
        query.append("updated_at = '").append(new Timestamp(System.currentTimeMillis())).append("' ");
        query.append("WHERE ").append("id = " + luggageId + ";");
        
        return query.toString();
    }
}
