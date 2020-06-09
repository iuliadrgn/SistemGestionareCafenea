package Offers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.OfferService;


import java.io.IOException;

public class CreateOffer {
    public TextField product;
    public TextField price;
    public TextField number;
    public ChoiceBox<String> state;




    public void initialize() {state.getItems().addAll("Fresh","Frozen");
    }

    public void ok(ActionEvent actionEvent) throws IOException {
        OfferService.addOffer(product.getText(), price.getText(), number.getText(), state.getValue());
    }

    public void Back(ActionEvent actionEvent) {
        try{
            Parent log_in= FXMLLoader.load(getClass().getResource("/HomeFurnizor.fxml"));
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
