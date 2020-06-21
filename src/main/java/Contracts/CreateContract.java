package Contracts;

import Offers.SeeOffersAngajat;
import exceptions.Contracts.ContractAlreadyExistsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Offer;
import services.Contract.ContractService;


import java.io.IOException;


public class CreateContract {

    public TextField product;
    public TextField price;
    public TextField number;
    public TextField state;
    public TextField numef;
    public DatePicker data;
    public Text ContractMessage;

public void initialize(){
Offer o= SeeOffersAngajat.metoda();
product.setText(o.getProduct());
price.setText(o.getPrice());
number.setText(o.getNumber());
numef.setText(o.getNumef());
state.setText(o.getState());
}


    public void Ok() {
        if(data.getValue()==null)
        {ContractMessage.setText("Complete the date!"); }
        else
try {
    ContractService.addContract(product.getText(), price.getText(), number.getText(), state.getText(), numef.getText(), data.getValue().toString());
    ContractMessage.setText("Contract created successfully!");
}catch(ContractAlreadyExistsException e) {
            ContractMessage.setText(e.getMessage());}

    }


    public void Back(ActionEvent actionEvent) {
        try{
            Parent log_in= FXMLLoader.load(getClass().getResource("/Offers/SeeOffersAngajat.fxml"));
            Stage stage= new Stage();
            stage.setTitle("Sistem Gestionare Cafenea");
            Scene scene=new Scene(log_in,600,400);
            stage.setScene(scene);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
