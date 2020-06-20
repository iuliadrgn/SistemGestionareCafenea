package Offers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.Offers.CouldNotWriteOffersException;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Offer;
import org.apache.commons.io.FileUtils;
import services.FileSystem.FileSystemService;
import services.Offer.OfferService;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class SeeOffersAngajat implements Initializable {
    public static Offer ofr;
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
    @FXML
    private TableColumn<Offer,String> numef;

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

    public void Respinge(ActionEvent actionEvent) {
        Offer ofr=TableView.getSelectionModel().getSelectedItem();

        offers.remove(ofr);
        persistOffers();

        TableView.getItems().removeAll(TableView.getSelectionModel().getSelectedItem());
    }

    private void persistOffers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(OFFER_PATH.toFile(), offers);
        } catch (IOException e) {
            throw new CouldNotWriteOffersException();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        product.setCellValueFactory(new PropertyValueFactory<>("product"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        numef.setCellValueFactory(new PropertyValueFactory<>("numef"));

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

        oferte.addAll(offers);
        return oferte;

    }

    public void Accept(ActionEvent actionEvent) {
        ofr=TableView.getSelectionModel().getSelectedItem();
        try{

            Parent log_in= FXMLLoader.load(getClass().getResource("/CreateContract.fxml"));
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

    public static Offer metoda(){
        return ofr;


    }
}
