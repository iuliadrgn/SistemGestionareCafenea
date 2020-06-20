package Change_Password;

import exceptions.Users.CouldNotWriteUsersException;
import models.User;
import services.PasswordEncrypt.Criptare;
import services.FileSystem.FileSystemService;
import services.User.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class ChangePassword {
    private static List<User> users;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "Register/users.json");
    @FXML
    public Text ChangeMessage;
    @FXML
    public TextField UsernameField;
    @FXML
    public javafx.scene.control.PasswordField OldPasswordField;
    @FXML
    public javafx.scene.control.PasswordField NewPasswordField;

    public void Change(ActionEvent actionEvent) throws IOException {
        String username = UsernameField.getText();
        String oldpassword = OldPasswordField.getText();
        String newpassword = NewPasswordField.getText();
        if (username == null || username.isEmpty()) {
            ChangeMessage.setText("Invalid username!");
            return;
        }
        else
            if (oldpassword == null || oldpassword.isEmpty()) {
            ChangeMessage.setText("Invalid password!");return;
        } else
        if(newpassword==null||newpassword.isEmpty()){
            ChangeMessage.setText("Introduce password!");return;
        }
        else
        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("Register/users.json")), USERS_PATH.toFile());
        }
        else
            ChangeMessage.setText("Invalid credentials!");

        ObjectMapper objectMapper = new ObjectMapper();
        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
        for (User user : users) {
            String psw= Criptare.decrypt(user.getPassword(),user.getUsername());
            if (Objects.equals(username, user.getUsername()) && Objects.equals(oldpassword, psw)) {
                user.setPassword(Criptare.encrypt(newpassword,username));
                persistUsers();
                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Password/ChangedSuccessfully.fxml")));
                    stage.setTitle("Sistem Gestionare Cafenea");
                    stage.setScene(new Scene(root, 200, 100));
                    stage.show();
                }catch(IOException e){
                    e.printStackTrace();
                }
                return;
            }
        }
    }

    private void persistUsers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }
}
