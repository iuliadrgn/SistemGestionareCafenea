import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Contract;

import java.io.IOException;

public class SeeContracts {
    public javafx.scene.control.TableView<Contract> TableView;
    public TableColumn<Contract,String> product;
    public TableColumn<Contract,String> price;
    public TableColumn<Contract,String> number;
    public TableColumn<Contract,String> state;
    public TableColumn<Contract,String> numef;
    public TableColumn<Contract,String> data;

    public void Back(ActionEvent actionEvent) {
        try{
            Parent log_in= FXMLLoader.load(getClass().getResource("/HomeAdministrator.fxml"));
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
