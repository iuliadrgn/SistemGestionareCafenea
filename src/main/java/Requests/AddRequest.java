package Requests;

import exceptions.Requests.RequestAlreadyExistsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.Request.RequestService;

import java.io.IOException;

public class AddRequest {
    public TextField NameField;
    public TextField NumberField;
    public ChoiceBox<String> UrgentField;
    public Text RequestMessage;

    public void initialize() {UrgentField.getItems().addAll("Da","Nu");
    }
    public void Okay() {
        String name=NameField.getText();
        String number=NumberField.getText();
        if(name==null|| name.length()==0)
        {
            RequestMessage.setText("Complete the name field");
        }
        else
        if(UrgentField.getValue()==null)
        {
            RequestMessage.setText("Complete the choicebox!");
        }
        else
        if(number==null||number.length()==0)
        {
            RequestMessage.setText("Complete the number field");
        }
        else
            try {
                RequestService.addRequest(NameField.getText(), NumberField.getText(), UrgentField.getValue());
                RequestMessage.setText("Request created successfully!");
            } catch (RequestAlreadyExistsException e) {
                RequestMessage.setText(e.getMessage());
            }


    }

    public void Back(ActionEvent actionEvent) {
        try{
            Parent log_in= FXMLLoader.load(getClass().getResource("/SeeRequestsAngajat.fxml"));
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
}
