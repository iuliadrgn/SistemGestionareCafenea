package Register;

import exceptions.Users.UsernameAlreadyExistsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.User.UserService;

import java.io.IOException;

public class Register {
    @FXML
     Text registrationMessage;
    @FXML
     PasswordField passwordField;
    @FXML
     TextField usernameField;
    @FXML
     ChoiceBox<String> role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Admin");
    }


    public void handleRegisterAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username == null || username.length() == 0) {
            registrationMessage.setText("Invalid e-mail adress");
        } else if (role.getValue() == null) {
            registrationMessage.setText("Complete the choicebox!");
        } else if (password == null || password.length() == 0) {
            registrationMessage.setText("Invalid Password");
        } else
            try {

                UserService.addUser(usernameField.getText(), passwordField.getText(), role.getValue());
                registrationMessage.setText("Account created successfully!");
            } catch (UsernameAlreadyExistsException e) {
                registrationMessage.setText(e.getMessage());
            }
    }

    public void backButton(ActionEvent actionEvent) {
        try {
            Parent log_in = FXMLLoader.load(getClass().getResource("/Login/login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Sistem Gestionare Cafenea");
            Scene scene = new Scene(log_in, 500, 300);
            stage.setScene(scene);
            stage.show();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
