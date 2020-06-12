package Offers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Offer;
import org.apache.commons.io.FileUtils;
import services.FileSystemService;
import services.OfferService;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class SeeOffersAngajat implements Initializable {
    private static List<Offer> offers;
    private static final Path OFFER_PATH = FileSystemService.getPathToFile("ofr", "offers.json");
    @FXML
    private javafx.scene.control.TableView<Offer> TableView;

    @FXML
    private TableColumn<Offer,String> product;

    @FXML
    private TableColumn<Offer,String> price;
    @FXML
    private TableColumn<Offer,String> number;
    @FXML
    private TableColumn<Offer,String> state;
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

    public void OK(ActionEvent actionEvent) {
    }

    public void Respinge(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        product.setCellValueFactory(new PropertyValueFactory<Offer, String>("product"));
        price.setCellValueFactory(new PropertyValueFactory<Offer, String> ("price"));
        number.setCellValueFactory(new PropertyValueFactory<Offer, String> ("number"));
        state.setCellValueFactory(new PropertyValueFactory<Offer, String> ("state"));

        try {
            TableView.setItems(getPeople());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ObservableList<Offer> getPeople() throws IOException {
        ObservableList<Offer> oferte= FXCollections.observableArrayList();
        if (!Files.exists(OFFER_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(OfferService.class.getClassLoader().getResource("offers.json")), OFFER_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        offers = objectMapper.readValue(OFFER_PATH.toFile(), new TypeReference<List<Offer>>() {
        });

        for(Offer i : offers){
            oferte.add(i);
        }
        return oferte;

    }
}
