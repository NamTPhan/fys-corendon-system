package com.mycompany.languageselect;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class userLanguageController implements Initializable {

    @FXML
    private AnchorPane languageSubScreen;

    @FXML
    private Button languageChinese, languageDutch, languageEnglish, languageFrench,
            languageGerman, languageJapanese, languageSpanish, languageTurkish;

    @FXML
    private Label languageSelected;

    @FXML
    private void setLanguage(ActionEvent event) {
        if (event.getSource() == languageChinese) {
            languageSelected.setText("Language set to Chinese");
        } else if (event.getSource() == languageDutch) {
            languageSelected.setText("Language set to Dutch");
        } else if (event.getSource() == languageEnglish) {
            languageSelected.setText("Language set to English");
        } else if (event.getSource() == languageFrench) {
            languageSelected.setText("Language set to French");
        } else if (event.getSource() == languageGerman) {
            languageSelected.setText("Language set to German");
        } else if (event.getSource() == languageJapanese) {
            languageSelected.setText("Language set to Japanese");
        } else if (event.getSource() == languageSpanish) {
            languageSelected.setText("Language set to Spanish");
        } else if (event.getSource() == languageTurkish) {
            languageSelected.setText("Language set to Turkish");
        } else {
            languageSelected.setText("Select language");
        }
    }

    @FXML
    private void closeSubScreen(ActionEvent event) {
        languageSubScreen.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
