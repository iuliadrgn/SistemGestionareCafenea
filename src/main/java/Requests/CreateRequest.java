package Requests;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import services.RequestService;

import java.io.IOException;

public class CreateRequest {
    public TextField NameField;
    public TextField NumberField;
    public ChoiceBox<String> UrgentField;
    public Text RequestMessage;

    public void initialize() {UrgentField.getItems().addAll("Da","Nu");
    }
    public void Okay() throws IOException {
        RequestService.addRequest(NameField.getText(), NumberField.getText(), UrgentField.getValue());

    }
}
