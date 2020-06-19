package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.OfferAlreadyExistsException;
import exceptions.RequestAlreadyExistsException;
import models.Offer;
import models.Request;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.*;

public class RequestServiceTest {
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
        RequestService.loadRequestFromFile();
        assertTrue(Files.exists(RequestService.REQUEST_PATH));
    }

    @Test
    public void testLoadRequestsFromFile() throws Exception {
        RequestService.loadRequestFromFile();
        assertNotNull(RequestService.requests);
        Assert.assertEquals(0, RequestService.requests.size());
    }
}
