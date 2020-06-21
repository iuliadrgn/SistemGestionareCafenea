package controllers.Contracts;

import Contracts.CreateContract;
import Offers.CreateOffer;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import services.Contract.ContractService;
import services.FileSystem.FileSystemService;

import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;

public class CreateContractTest extends ApplicationTest {
    private CreateContract controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".FisOnline";
        FileSystemService.initApplicationHomeDirIfNeeded();
        ContractService.loadContractsFromFile();
    }
    @Before
    public void setUp() throws Exception{
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        ContractService.loadContractsFromFile();
        controller = new CreateContract();
        controller.product = new TextField();
        controller.price = new TextField();
        controller.number = new TextField();
        controller.numef = new TextField();
        controller.state= new TextField();
        controller.data=new DatePicker();
        controller.ContractMessage = new Text();
        controller.product.setText("Test1");
        controller.price.setText("Test2");
        controller.number.setText("Test3");
        controller.numef.setText("Test3");
        controller.state.setText("Frozen");
        controller.data.setValue(LocalDate.of(2020,11,10));
    }
    @After
    public void tearDown() {
        controller = null;
    }
    @Test
    public void testHandleAddUserActionCode() {
        controller.Ok();
        assertEquals(1, ContractService.getContracts().size());
        assertEquals("Contract created successfully!", controller.ContractMessage.getText());
    }
    @Test
    public void testAddSameUserTwice() {
        controller.Ok();
        controller.Ok();
        assertEquals("Contract already exists!", controller.ContractMessage.getText());
    }
    @Test
    public void testHandleAddNullDateActionCode() {
        controller.data.setValue(null);
        controller.Ok();
        assertEquals("Complete the date!", controller.ContractMessage.getText());
    }
}
