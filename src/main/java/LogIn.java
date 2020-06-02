import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;


public class LogIn {
    @FXML
    public Text LogInMessage;
    @FXML
    public TextField UsernameField;
    @FXML
    public PasswordField PasswordField;

    public void PressButton(ActionEvent actionEvent) {
        String username=UsernameField.getText();
        String password=PasswordField.getText();
        if(username==null||username.isEmpty())
        {
            LogInMessage.setText("Invalid e-mail adress");
            return;
        }
        if(password==null||password.isEmpty())
        {
            LogInMessage.setText("Invalid Password");
            return;
        }
       if(Objects.equals(username,"admin")&& Objects.equals(password,"admin"))
       { LogInMessage.setText("Logged in as Administrator");
       return;}
       else
        if(Objects.equals(username,"angajat")&& Objects.equals(password,"angajat"))
        { LogInMessage.setText("Logged in as Angajat");
        return;}
        else
        if(Objects.equals(username,"furnizor")&& Objects.equals(password,"furnizor"))
        { LogInMessage.setText("Logged in as Furnizor");
        return;
        }

        LogInMessage.setText("Invalid credentials");

    }

}
