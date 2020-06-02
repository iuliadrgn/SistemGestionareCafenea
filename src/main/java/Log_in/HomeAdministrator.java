package Log_in;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomeAdministrator {


    public void LogOut(ActionEvent actionEvent) {
        try{
            Parent log_in= FXMLLoader.load(getClass().getResource("/login.fxml"));
            Stage stage= new Stage();
            stage.setTitle("Sistem Gestionare Cafenea");
            Scene scene=new Scene(log_in,500,300);
            stage.setScene(scene);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void registerAorF(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("RegisterbyAdm.fxml")));
            stage.setTitle("Sistem Gestionare Cafenea");
            stage.setScene(new Scene(root, 500, 300));
            stage.show();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
