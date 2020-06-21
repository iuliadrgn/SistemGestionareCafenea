package controllers;
import Offers.CreateOffer;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import services.FileSystem.FileSystemService;
import services.Offer.OfferService;

import static junit.framework.TestCase.assertEquals;

public class CreateOfferTest extends ApplicationTest {
    private CreateOffer controller;

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
        controller = new CreateOffer();
        controller.product = new TextField();
        controller.price = new TextField();
        controller.number = new TextField();
        controller.numef = new TextField();
        controller.state= new ChoiceBox();
        controller.OfferMessage = new Text();
        controller.product.setText("Test1");
        controller.price.setText("Test2");
        controller.number.setText("Test3");
        controller.numef.setText("Test3");
        controller.state.setValue("Frozen");
    }
    @After
    public void tearDown() {
        controller = null;
    }
    @Test
    public void testHandleAddUserActionCode() {
        controller.ok();
        assertEquals(1, OfferService.getOffers().size());
        assertEquals("Offer created successfully!", controller.OfferMessage.getText());
    }
    @Test
    public void testAddSameUserTwice() {
        controller.ok();
        controller.ok();
        assertEquals("Offer already exists!", controller.OfferMessage.getText());
    }
    @Test
    public void testHandleAddNullProductActionCode() {
        controller.product.setText("");
        controller.ok();
        assertEquals("Complete the product field", controller.OfferMessage.getText());
    }
    @Test
    public void testHandleAddNullPriceActionCode() {
        controller.price.setText("");
        controller.ok();
        assertEquals("Complete the price field", controller.OfferMessage.getText());
    }
    @Test
    public void testHandleAddNullNumberActionCode() {
        controller.number.setText("");
        controller.ok();
        assertEquals("Complete the number field", controller.OfferMessage.getText());
    }
    @Test
    public void testHandleAddNullChoiceboxActionCode() {
        controller.state.setValue(null);
        controller.ok();
        assertEquals("Complete the choicebox!", controller.OfferMessage.getText());
    }
    @Test
    public void testHandleAddNullNumefActionCode() {
        controller.numef.setText("");
        controller.ok();
        assertEquals("Complete the numef field", controller.OfferMessage.getText());
    }
}
