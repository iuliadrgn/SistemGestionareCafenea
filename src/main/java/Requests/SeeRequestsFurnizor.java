package Requests;

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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import models.Request;
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

public class SeeRequestsFurnizor implements Initializable {

    private static final Path REQUEST_PATH = FileSystemService.getPathToFile("req", "requests.json");
    @FXML
    public javafx.scene.control.TableView<Request> TableView;
    @FXML
    public TableColumn<Request,String> name;
    @FXML
    public TableColumn<Request,String> number;
    @FXML
    public TableColumn<Request,String> urgent;

    public void Back(ActionEvent actionEvent) {
        try{
            Parent log_in= FXMLLoader.load(getClass().getResource("/HomeFurnizor.fxml"));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        urgent.setCellValueFactory(new PropertyValueFactory<>("urgent"));



        try {
            TableView.setItems(getPeople());
        } catch (IOException e) {
            e.printStackTrace();
        }
        TableView.setEditable(true);
        name.setCellFactory((TextFieldTableCell.forTableColumn()));
        number.setCellFactory((TextFieldTableCell.forTableColumn()));
        urgent.setCellFactory((TextFieldTableCell.forTableColumn()));

    }

    private ObservableList<Request> getPeople() throws IOException {
        ObservableList<Request> cereri= FXCollections.observableArrayList();
        if (!Files.exists(REQUEST_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(OfferService.class.getClassLoader().getResource("requests.json")), REQUEST_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        List<Request> requests = objectMapper.readValue(REQUEST_PATH.toFile(), new TypeReference<List<Request>>() {
        });

        cereri.addAll(requests);
        return cereri;
    }
}
