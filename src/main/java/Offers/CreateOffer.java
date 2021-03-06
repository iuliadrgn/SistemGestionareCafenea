package Offers;

import exceptions.Offers.OfferAlreadyExistsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.Offer.OfferService;


import java.io.IOException;

public class CreateOffer {
    public TextField product;
    public TextField price;
    public TextField number;
    public ChoiceBox<String> state;
    public TextField numef;
    public Text OfferMessage;


    public void initialize() {state.getItems().addAll("Fresh","Frozen");
    }

    public void ok()  {
        String prd=product.getText();
        String prc=price.getText();
        String nr=number.getText();
        String num=numef.getText();
        if(prd==null|| prd.length()==0)
        {
            OfferMessage.setText("Complete the product field");

        }
        else
        if(prc==null|| prc.length()==0)
        {
            OfferMessage.setText("Complete the price field");

        }
        else
        if(nr==null||nr.length()==0)
        {
            OfferMessage.setText("Complete the number field");
        }
        else
        if(state.getValue()==null)
        {
            OfferMessage.setText("Complete the choicebox!");

        }
        else
        if(num==null||num.length()==0)
        {
            OfferMessage.setText("Complete the numef field");
        }
        else  try {
            OfferService.addOffer(product.getText(),price.getText(), number.getText(), state.getValue(),numef.getText());
            OfferMessage.setText("Offer created successfully!");
        } catch (OfferAlreadyExistsException e) {
            OfferMessage.setText(e.getMessage());
        }
    }

    public void Back(ActionEvent actionEvent) {
        try{
            Parent log_in= FXMLLoader.load(getClass().getResource("/Requests/SeeRequestsFurnizor.fxml"));
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
