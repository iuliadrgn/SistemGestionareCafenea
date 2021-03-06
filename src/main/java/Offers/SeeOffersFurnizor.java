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
import javafx.scene.control.cell.TextFieldTableCell;
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

public class SeeOffersFurnizor implements Initializable {

    private static List<Offer> offers;
    private static final Path OFFER_PATH = FileSystemService.getPathToFile("ofr", "Offers/offers.json");
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
            Parent log_in= FXMLLoader.load(getClass().getResource("/HomeUsers/HomeFurnizor.fxml"));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        product.setCellValueFactory(new PropertyValueFactory<>("product"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));

        try {
            TableView.setItems(getPeople());
        } catch (IOException e) {
            e.printStackTrace();
        }
        TableView.setEditable(true);
        product.setCellFactory((TextFieldTableCell.forTableColumn()));
        number.setCellFactory((TextFieldTableCell.forTableColumn()));
        price.setCellFactory((TextFieldTableCell.forTableColumn()));
        state.setCellFactory((TextFieldTableCell.forTableColumn()));
    }
    private ObservableList<Offer> getPeople() throws IOException {
        ObservableList<Offer> oferte= FXCollections.observableArrayList();
        if (!Files.exists(OFFER_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(OfferService.class.getClassLoader().getResource("Offers/offers.json")), OFFER_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        offers = objectMapper.readValue(OFFER_PATH.toFile(), new TypeReference<List<Offer>>() {
        });

        oferte.addAll(offers);
        return oferte;

    }

    public void Delete(ActionEvent actionEvent) {
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

    public void Edit(ActionEvent actionEvent) {
        offers=TableView.getItems();
        persistOffers();
    }
    public void editProduct(TableColumn.CellEditEvent<Offer, String> requestStringCellEditEvent) {
        Offer ofr=TableView.getSelectionModel().getSelectedItem();
        ofr.setProduct(requestStringCellEditEvent.getNewValue());
    }

    public void editNumber(TableColumn.CellEditEvent<Offer, String> requestStringCellEditEvent) {
        Offer ofr=TableView.getSelectionModel().getSelectedItem();
        ofr.setNumber(requestStringCellEditEvent.getNewValue());
    }

    public void editPrice(TableColumn.CellEditEvent<Offer, String> requestStringCellEditEvent) {
        Offer ofr=TableView.getSelectionModel().getSelectedItem();
        ofr.setPrice(requestStringCellEditEvent.getNewValue());
    }
    public void editState(TableColumn.CellEditEvent<Offer, String> requestStringCellEditEvent) {
        Offer ofr=TableView.getSelectionModel().getSelectedItem();
        ofr.setState(requestStringCellEditEvent.getNewValue());
    }

    public void Add(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Offers/AddOffer.fxml")));
            stage.setTitle("Sistem Gestionare Cafenea");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
