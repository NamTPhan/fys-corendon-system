/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corendon.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author joris
 */
public class Lost {
    
    public int id_string;
    public String color;
    public String brand;
    public String type;
    public int luggage_id;
    public int client_id;
    public String created_at_string;
    
    public Lost(int id, String color, String brand, String type, int luggage_id, int client_id, String created_at) {
        this.id_string = id;
        this.color = color;
        this.brand = brand;
        this.type = type;
        this.luggage_id = luggage_id;
        this.client_id = client_id;
        this.created_at_string = created_at;
    }
    
    public static  ObservableList<Lost> latest(int status_id , int limit) {
                String query = (""
                + "SELECT issue.id, luggage.color, luggage.brand, luggage.type, issue.client_id, issue.luggage_id, issue.created_at "
                + "FROM issues AS issue "
                + "INNER JOIN luggage "
                + "ON issue.luggage_id = luggage.id "
                + "WHERE issue.status_id = " + status_id
                + " AND client_id IS NULL "
                + "ORDER BY issue.created_at DESC "
                + "LIMIT " + limit
                
                );
        MyJDBC db = new MyJDBC();
        try {
            ObservableList<Lost> list = FXCollections.observableArrayList();
            ResultSet resultSet = db.executeResultSetQuery(query);
            while (resultSet.next()) {
                int id_string = resultSet.getInt("id");
                String color = resultSet.getString("color");
                String brand = resultSet.getString("brand");
                String type = resultSet.getString("type");
                int luggage_id = resultSet.getInt("luggage_id");
                int client_id = resultSet.getInt("client_id");
                String created_at_string = resultSet.getString("created_at");
                
                list.add(new Lost(id_string, color, brand, type, luggage_id, client_id, created_at_string));

            }
            return list;
        }
        catch (SQLException ex) {
            db.error(ex);
            return null;
        }
    }
    
    public static  ObservableList<Lost> latest() {
        return latest(1,5);
    }
    
//    public String getRecentlyContents()
//    {
//        return StringUtils.capitalize(this.type);
//    }

    public int getId_string() {
        return id_string;
    }

    public String getCreated_at_string() {
        return created_at_string;
    }
    
}
