package Requests;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Request;

import java.io.IOException;

public class SeeRequestsAngajat {
    @FXML
    public javafx.scene.control.TableView<Request> TableView;
    @FXML
    public TableColumn<Request,String> name;
    @FXML
    public TableColumn<Request,String> number;
    @FXML
    public TableColumn<Request,String> urgent;


    public void Back(ActionEvent actionEvent) {
        try{
            Parent log_in= FXMLLoader.load(getClass().getResource("/HomeAngajat.fxml"));
            Stage stage= new Stage();
            stage.setTitle("Sistem Gestionare Cafenea");
            Scene scene=new Scene(log_in,700,500);
            stage.setScene(scene);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
