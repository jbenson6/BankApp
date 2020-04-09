package bankApp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {
    private User user;
    @FXML
    private Label username;
    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Label password;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    void initData(User user) {
        this.user = user;
        username.setText(user.getUsername());
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        password.setText(user.getPassword());
    }

}
