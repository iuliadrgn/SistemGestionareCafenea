package controllers.Offers;

import Offers.SeeOffersAngajat;
import Offers.SeeOffersFurnizor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import models.Offer;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import services.FileSystem.FileSystemService;
import services.Offer.OfferService;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class SeeOffersFurnizorTest extends ApplicationTest {
    private ObservableList<Offer> elem;

    private SeeOffersAngajat controller;
    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".FisOnline";
        FileSystemService.initApplicationHomeDirIfNeeded();
        OfferService.loadOffersFromFile();
    }
    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        OfferService.loadOffersFromFile();
        controller = new SeeOffersAngajat();
        controller.product = new TableColumn<>();
        controller.number = new TableColumn<>();
        controller.state = new TableColumn<>();
        controller.numef = new TableColumn<>();
        controller.TableView= new javafx.scene.control.TableView<>();
        elem= FXCollections.observableArrayList(OfferService.getOffers());
    }
    @After
    public void tearDown() {
        controller = null;
    }
    @Test
    public void testPeople() throws IOException {
        controller.getPeople();
        assertNotNull(controller.getOffers());
        assertEquals(elem, controller.getOffers());
    }
}
