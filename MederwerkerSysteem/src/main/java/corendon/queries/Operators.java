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
 * @author JP
 */
public class Operators {

    //variables
    private int id;
    private String name, email, password, defaultLanguage;
    private boolean isAdmin;
    private Timestamp createdAt, updatedAt;

    public Operators(int id, String name, String email, String password, String defaultLanguage, boolean isAdmin, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.defaultLanguage = defaultLanguage;
        this.isAdmin = isAdmin;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
     public Operators(int id, String name, String email, String defaultLanguage, boolean isAdmin, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.defaultLanguage = defaultLanguage;
        this.isAdmin = isAdmin;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
     
    public Operators(int id, String name, String email, Boolean is_admin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isAdmin = is_admin;
    }
     
    public static Operators getByCredentials(String email, String password) {
        String query = "SELECT * FROM users "
                + "WHERE email = '" + email + "' && password = '" + password + "';";
        MyJDBC db = new MyJDBC();
        try {
            ResultSet resultSet = db.executeResultSetQuery(query);
            resultSet.next();
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String useremail = resultSet.getString("email");
            Boolean is_admin = resultSet.getBoolean("is_admin");

            return new Operators(id, name, useremail, is_admin);
        }
        catch (SQLException ex) {
            db.error(ex);
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    

}
