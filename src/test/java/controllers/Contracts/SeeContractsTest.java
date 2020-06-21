package controllers.Contracts;

import Contracts.SeeContracts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import models.Contract;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import services.Contract.ContractService;
import services.FileSystem.FileSystemService;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class SeeContractsTest extends ApplicationTest {
    private ObservableList<Contract> elem;

    private SeeContracts controller;
    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".FisOnline";
        FileSystemService.initApplicationHomeDirIfNeeded();
        ContractService.loadContractsFromFile();
    }
    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        ContractService.loadContractsFromFile();
        controller = new SeeContracts();
        controller.product = new TableColumn<>();
        controller.number = new TableColumn<>();
        controller.state = new TableColumn<>();
        controller.numef = new TableColumn<>();
        controller.data = new TableColumn<>();
        controller.TableView= new javafx.scene.control.TableView<>();
        elem= FXCollections.observableArrayList(ContractService.getContracts());
    }
    @After
    public void tearDown() {
        controller = null;
    }
    @Test
    public void testPeople() throws IOException {
        controller.getPeople();
        assertNotNull(controller.getContracts());
        assertEquals(elem, controller.getContracts());
    }

}
