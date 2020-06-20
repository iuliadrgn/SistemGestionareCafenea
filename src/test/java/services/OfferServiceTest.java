package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.Offers.OfferAlreadyExistsException;
import models.Offer;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import services.FileSystem.FileSystemService;
import services.Offer.OfferService;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;

public class OfferServiceTest {
    @BeforeClass
    public static void setupClass() {
        FileSystemService.APPLICATION_FOLDER = ".FisOnline";
        FileSystemService.initApplicationHomeDirIfNeeded();
    }

    @Before
    public void setUp() throws IOException {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
    }

    @Test
    public void testCopyDefaultFileIfNotExists() throws Exception {
        OfferService.loadOffersFromFile();
        assertTrue(Files.exists(OfferService.OFFER_PATH));
    }

    @Test
    public void testLoadOffersFromFile() throws Exception {
        OfferService.loadOffersFromFile();
        assertNotNull(OfferService.offers);
        Assert.assertEquals(0, OfferService.offers.size());
    }
    @Test
    public void testAddOneOffer() throws Exception {
        OfferService.loadOffersFromFile();
        OfferService.addOffer("test", "testPass", "test1user","test","Test");
        assertNotNull(OfferService.offers);
        assertEquals(1, OfferService.offers.size());
    }
    @Test
    public void testAddTwoOffers() throws Exception {
        OfferService.loadOffersFromFile();
        OfferService.addOffer("test1", "testPass1", "test2user","test1","Test1");
        OfferService.addOffer("test2", "testPass2", "test2user","test1","Test2");
        assertNotNull(OfferService.offers);
        assertEquals(2, OfferService.offers.size());
    }
    @Test(expected = OfferAlreadyExistsException.class)
    public void testAddOfferAlreadyExists() throws Exception {
        OfferService.loadOffersFromFile();
        OfferService.addOffer("test1", "testPass1", "test2user","test1","Test1");
        assertNotNull(OfferService.offers);
        OfferService.checkOfferDoesNotAlreadyExist("test1");
    }
    @Test
    public void testAddOneOfferIsPersisted() throws Exception {
        OfferService.loadOffersFromFile();
        OfferService.addOffer("test1", "testPass1", "test2user","test1","Test1");
        List<Offer> offers = new ObjectMapper().readValue(OfferService.OFFER_PATH.toFile(), new TypeReference<List<Offer>>() {
        });
        assertNotNull(offers);
        assertEquals(1, offers.size());
    }
    @Test
    public void testAddTwoOffersArePersisted() throws Exception {
        OfferService.loadOffersFromFile();
        OfferService.addOffer("test1", "testPass1", "test2user","test1","Test1");
        OfferService.addOffer("test2", "testPass2", "test2user","test1","Test2");
        List<Offer> offers = new ObjectMapper().readValue(OfferService.OFFER_PATH.toFile(), new TypeReference<List<Offer>>() {
        });
        assertNotNull(offers);
        assertEquals(2, offers.size());
    }
}
