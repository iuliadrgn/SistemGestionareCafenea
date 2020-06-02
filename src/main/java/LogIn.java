import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
       { try {
           Stage stage = (Stage) LogInMessage.getScene().getWindow();
           Parent AdmHome = FXMLLoader.load(getClass().getResource("/HomeAdministrator.fxml"));
           Scene scene = new Scene(AdmHome, 700, 500);
           stage.setScene(scene);
       } catch (IOException e) {
           e.printStackTrace();
       }
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
