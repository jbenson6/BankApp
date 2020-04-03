package bankApp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;

public class SignUp implements Initializable {
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private PasswordField confirmPassword;
    @FXML private Label usernameInUse;
    @FXML private Label passwordMatchInvalid;

    public void signUp() throws SQLException, ClassNotFoundException {
        if(password.getText().equals(confirmPassword.getText())) {
            if (OracleAccess.addUser(firstName.getText(), lastName.getText(), username.getText(), password.getText())) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                    username.getScene().setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                usernameInUse.setVisible(true);
            }
        } else{
            passwordMatchInvalid.setVisible(true);
            System.out.println("Confirm Password");
        }
    }
    public void cancel(){
            try{
                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                username.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
