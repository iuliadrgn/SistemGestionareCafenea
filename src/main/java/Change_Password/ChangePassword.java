package Change_Password;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ChangePassword {
    @FXML
    public Text ChangeMessage;
    @FXML
    public TextField UsernameField;
    @FXML
    public javafx.scene.control.PasswordField OldPasswordField;
    @FXML
    public javafx.scene.control.PasswordField NewPasswordField;

    public void Change(ActionEvent actionEvent)  {
        String username = UsernameField.getText();
        String oldpassword = OldPasswordField.getText();
        String newpassword = NewPasswordField.getText();
        if (username == null || username.isEmpty()) {
            ChangeMessage.setText("Invalid e-mail");
        } if (oldpassword == null || oldpassword.isEmpty()) {
            ChangeMessage.setText("Invalid password");
        }
        if (newpassword == null || newpassword.isEmpty()) {
            ChangeMessage.setText("Introduce password");
        }
        else if(username.equals("admin")||username.equals("angajat")||username.equals("furnizor"))
            return;
        else ChangeMessage.setText("Invalid Credentials");
    }
}
