package Log_in.Login;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;


public class LogIn {
    private static List<User> users;
    private static final Path USERS_PATH=FileSystemService.getPathToFile("config", "Register/users.json");
    @FXML
    public Text LogInMessage;
    @FXML
    public TextField UsernameField;
    @FXML
    public PasswordField PasswordField;

    public void PressButton() throws IOException {
        String username=UsernameField.getText();
        String password=PasswordField.getText();
        if(username==null||username.isEmpty())
        {
            LogInMessage.setText("Invalid username!");
        }
        else
        if(password==null||password.isEmpty())
        {
            LogInMessage.setText("Invalid password!");
        }
        else
        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("Register/users.json")), USERS_PATH.toFile());
        }
        else
            LogInMessage.setText("Invalid credentials!");

        ObjectMapper objectMapper = new ObjectMapper();
        //LogInMessage.setText("ok");
        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {});
        for(User user :users ){
            String psw= Criptare.decrypt(user.getPassword(),user.getUsername());
            if(Objects.equals(username,user.getUsername()) && Objects.equals(password,psw)){
                if(Objects.equals(user.getRole(),"Admin")){
                    try{
                        Stage stage=(Stage) LogInMessage.getScene().getWindow();
                        Parent AdmHome= FXMLLoader.load(getClass().getResource("/HomeUsers/HomeAdministrator.fxml"));
                        Scene scene=new Scene(AdmHome,600,400);
                        stage.setScene(scene);
                    }catch(IOException e){
                        e.printStackTrace();
                    }


                }
                if(Objects.equals(user.getRole(),"Angajat")){
                    try{
                        Stage stage=(Stage) LogInMessage.getScene().getWindow();
                        Parent AdmHome= FXMLLoader.load(getClass().getResource("/HomeUsers/HomeAngajat.fxml"));
                        Scene scene=new Scene(AdmHome,600,400);
                        stage.setScene(scene);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
                if(Objects.equals(user.getRole(),"Furnizor")){
                    try{
                        Stage stage=(Stage) LogInMessage.getScene().getWindow();
                        Parent AdmHome= FXMLLoader.load(getClass().getResource("/HomeUsers/HomeFurnizor.fxml"));
                        Scene scene=new Scene(AdmHome,600,400);
                        stage.setScene(scene);
                    }catch(IOException e){
                        e.printStackTrace();
                    }


                }


            }

        }

    }

    public void Register(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Register/Register.fxml")));
        stage.setTitle("Sistem Gestionare Cafenea");
        stage.setScene(new Scene(root, 500, 300));
        stage.show();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
