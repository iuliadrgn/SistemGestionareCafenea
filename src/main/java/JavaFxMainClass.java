import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.UserService;

import java.io.IOException;

public class JavaFxMainClass extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        UserService.loadUsersFromFile();
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        primaryStage.setTitle("Sistem Gestionare Cafenea");
        primaryStage.setScene(new Scene(root,500,300));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
