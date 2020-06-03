package Change_Password;

import javafx.event.ActionEvent;
import javafx.scene.Node;

public class ChangedSuccessfully {
    public void CloseWindow(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
