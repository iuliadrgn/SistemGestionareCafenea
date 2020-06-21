import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.Contract.ContractService;
import services.Offer.OfferService;
import services.Request.RequestService;
import services.User.UserService;

import java.io.IOException;

public class JavaFxMainClass extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {

        UserService.loadUsersFromFile();
        RequestService.loadRequestFromFile();
        OfferService.loadOffersFromFile();
        ContractService.loadContractsFromFile();

        Parent root = FXMLLoader.load(getClass().getResource("/Login/login.fxml"));
        primaryStage.setTitle("Sistem Gestionare Cafenea");
        primaryStage.setScene(new Scene(root,500,300));
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }

}
