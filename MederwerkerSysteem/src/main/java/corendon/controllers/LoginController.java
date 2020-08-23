package corendon.controllers;

import corendon.queries.MyJDBC;
import corendon.queries.Operators;
import corendon.utilities.SetStage;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    private static final String DEFAULT_TEXT_EMAIL = "E-mail";
    private static final String DEFAULT_TEXT_PASSWORD = "Password";
    private static final String DEFAULT_TEXT_LOGIN = "Login";
    
    public static Operators currentUser;
    
    @FXML
    private TextField usernameInputField, passwordInputField;

    @FXML
    private Label emailLabel, passwordLabel, errorLabel;

    @FXML
    private Button loginBtn;
    
    MyJDBC myJDBC = new MyJDBC("fys");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emailLabel.setText(DEFAULT_TEXT_EMAIL);
        passwordLabel.setText(DEFAULT_TEXT_PASSWORD);
        loginBtn.setText(DEFAULT_TEXT_LOGIN);
        errorLabel.setTextFill(Color.web("#E74C3C"));
        loginBtn.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: #fff");
    }

    @FXML
    private void login(ActionEvent event) throws IOException, Exception {
        String query = "SELECT Count(*) FROM users "
                + "WHERE email = '" + usernameInputField.getText() + "' && password = '" + passwordInputField.getText() + "';";

        String errorMessage = "Email or password is incorrect!";
        if (Integer.parseInt(myJDBC.executeStringQuery(query)) == 1) {
            this.currentUser = Operators.getByCredentials(usernameInputField.getText(), passwordInputField.getText());
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            new SetStage(stage, "/fxml/HomeLayout.fxml");
        }
        else {
            errorLabel.setText(errorMessage);
        }
    }
}
