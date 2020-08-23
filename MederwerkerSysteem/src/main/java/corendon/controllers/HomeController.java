package corendon.controllers;

import corendon.utilities.SetStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Joris Schelfhout - Studentnummer: 500777847
 */
public class HomeController extends BaseController implements Initializable {

    @FXML
    private Button languageChinese, languageDutch, languageEnglish, languageFrench,
            languageGerman, languageJapanese, languageSpanish, languageTurkish, operatorsButton;

    @FXML
    private StackPane workspace;

    @FXML
    private Label welcomeMessage, languageSelected;

    @FXML
    private ImageView operatorsIcon;

    @FXML
    private Rectangle rectangleClients;

    @FXML
    private AnchorPane languageSubScreen;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.changeWorkspace(this.loadView("/fxml/Dashboard.fxml"));
        this.welcomeMessage.setText("Welcome, " + LoginController.currentUser.getName());
        if (!LoginController.currentUser.getIsAdmin()) {
            this.operatorsButton.setVisible(false);
            this.operatorsIcon.setVisible(false);
            this.rectangleClients.setVisible(false);
        }
    }

    public void switchToDashboardPage() {
        this.changeWorkspace(this.loadView("/fxml/Dashboard.fxml"));
    }

    public void switchToStatisticsPage() {
        this.changeWorkspace(this.loadView("/fxml/Statistics.fxml"));
    }

    public void switchToClientsPage() {
        this.changeWorkspace(this.loadView("/fxml/Clients.fxml"));
    }

    public void switchToOperatorsPage() {
        this.changeWorkspace(this.loadView("/fxml/Operators.fxml"));
    }

    public void changeWorkspace(Parent pane) {
        workspace.getChildren().clear();
        workspace.getChildren().add(pane);
    }

    public void logout(ActionEvent event) throws IOException, Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new SetStage(stage, "/fxml/login.fxml");
    }

    //Noa's part
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

    public void showLanguageScreen() {
        languageSubScreen.setVisible(true);
    }

    public void hideLanguageScreen() {
        languageSubScreen.setVisible(false);
    }
}
