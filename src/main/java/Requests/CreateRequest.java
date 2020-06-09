package Requests;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import services.RequestService;

import java.io.IOException;

import static services.RequestService.*;

public class CreateRequest {
    public TextField NameField;
    public TextField NumberField;
    public ChoiceBox<String> UrgentField;
    public Text RequestMessage;

    public void initialize() {UrgentField.getItems().addAll("Da","Nu");
    }
    public void Okay() throws IOException {
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
        else {
            addRequest(NameField.getText(), NumberField.getText(), UrgentField.getValue());
            RequestMessage.setText("Created Successfully");
        }

    }
}
