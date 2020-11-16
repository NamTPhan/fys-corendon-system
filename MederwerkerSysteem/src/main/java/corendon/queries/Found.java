/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corendon.queries;

//import com.sun.xml.internal.ws.util.StringUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Joris Schelfhout - Studentnummer: 500777847
 */
public class Found {

    public int id;
    public String color;
    public String brand;
    public String type;
    public int luggage_id;
    public String created_at;

    public Found(int id, String color, String brand, String type, int luggage_id, String created_at) {
        this.id = id;
        this.color = color;
        this.brand = brand;
        this.type = type;
        this.luggage_id = luggage_id;
        this.created_at = created_at;
    }

    public static ObservableList<Found> latest(int status_id, int limit) {
        String query = (""
                + "SELECT issue.id, luggage.color, luggage.brand, luggage.type, issue.luggage_id, issue.created_at "
                + "FROM issues AS issue "
                + "INNER JOIN luggage "
                + "ON issue.luggage_id = luggage.id "
                + "WHERE issue.status_id = " + status_id
                + " AND client_id IS NOT NULL "
                + "ORDER BY issue.created_at DESC "
                + "LIMIT " + limit);
        MyJDBC db = new MyJDBC();
        try {
            ObservableList<Found> list = FXCollections.observableArrayList();
            ResultSet resultSet = db.executeResultSetQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String color = resultSet.getString("color");
                String brand = resultSet.getString("brand");
                String type = resultSet.getString("type");
                int luggage_id = resultSet.getInt("luggage_id");
                String created_at = resultSet.getString("created_at");

                list.add(new Found(id, color, brand, type, luggage_id, created_at));

            }
            return list;
        } catch (SQLException ex) {
            db.error(ex);
            return null;
        }
    }

    public static ObservableList<Found> latest() {
        return latest(1, 5);
    }

//    public String getRecentlyContent() {
//        return StringUtils.capitalize(this.type + ", " + this.brand);
//    }

    public String getId() {
        return "" + this.id;
    }

    public String getCreated_at() {
        return created_at;
    }
    
    
}
