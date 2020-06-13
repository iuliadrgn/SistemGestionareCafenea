package Log_in;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomeFurnizor {
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

    public void ChangePassword(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("ChangePassword.fxml")));
            stage.setTitle("Sistem Gestionare Cafenea");
            stage.setScene(new Scene(root, 400, 200));
            stage.show();
            //((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void CreateOffer(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("CreateOffer.fxml")));
            stage.setTitle("Sistem Gestionare Cafenea");
            stage.setScene(new Scene(root, 500, 300));
            stage.show();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void SeeOffers(ActionEvent actionEvent) {
    }
}
