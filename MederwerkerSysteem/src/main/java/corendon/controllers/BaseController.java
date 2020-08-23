/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corendon.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

/**
 *
 * @author Joris Schelfhout - Studentnummer: 500777847
 */
public class BaseController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    public Parent loadView(String file) {
        try {
            return FXMLLoader.load(getClass().getResource(file));
        }
        catch (IOException exception) {
            System.out.println("The view has not been found or has some other errors contained within.");
            return null;
        }
    }
}
