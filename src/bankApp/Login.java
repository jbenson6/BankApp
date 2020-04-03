package bankApp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label signInErrorMessage;

    public void signUp() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
            username.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void signIn() {
        try {
            if(OracleAccess.signIn(username.getText(), password.getText())){
                Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                username.getScene().setRoot(root);
            } else {
                signInErrorMessage.setVisible(true);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
