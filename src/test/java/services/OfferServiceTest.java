package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.OfferAlreadyExistsException;
import exceptions.UsernameAlreadyExistsException;
import models.Offer;
import models.User;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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

}
