package Contracts;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Contract;
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

public class SeeContracts implements Initializable {
    public static Offer ofr;
    private static List<Contract> contracts;
    private static final Path CONTRACT_PATH = FileSystemService.getPathToFile("contracts", "Contracts/contracts.json");
    @FXML
    public javafx.scene.control.TableView<Contract> TableView;
    @FXML
    public TableColumn<Contract,String> product;
    @FXML
    public TableColumn<Contract,String> price;
    @FXML
    public TableColumn<Contract,String> number;
    @FXML
    public TableColumn<Contract,String> state;
    @FXML
    public TableColumn<Contract,String> numef;
    @FXML
    public TableColumn<Contract,String> data;

    public void Back(ActionEvent actionEvent) {
        try{
            Parent log_in= FXMLLoader.load(getClass().getResource("/HomeUsers/HomeAdministrator.fxml"));
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
        numef.setCellValueFactory(new PropertyValueFactory<>("numef"));
        data.setCellValueFactory(new PropertyValueFactory<>("data"));


        try {
            TableView.setItems(getPeople());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<Contract> getPeople() throws IOException {
        ObservableList<Contract> contracte= FXCollections.observableArrayList();
        if (!Files.exists(CONTRACT_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(OfferService.class.getClassLoader().getResource("Contracts/contracts.json")), CONTRACT_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        contracts = objectMapper.readValue(CONTRACT_PATH.toFile(), new TypeReference<List<Contract>>() {
        });

        contracte.addAll(contracts);
        return contracte;

    }
    public List<Contract> getContracts(){
        return contracts ;
    }
}
