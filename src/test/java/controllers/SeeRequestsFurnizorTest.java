package controllers;

import Requests.SeeRequestsAngajat;
import Requests.SeeRequestsFurnizor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import models.Request;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import services.FileSystem.FileSystemService;
import services.Request.RequestService;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class SeeRequestsFurnizorTest extends ApplicationTest {
    private ObservableList<Request> elem;

    private SeeRequestsFurnizor controller;
    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".FisOnline";
        FileSystemService.initApplicationHomeDirIfNeeded();
        RequestService.loadRequestFromFile();
    }
    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        RequestService.loadRequestFromFile();
        controller = new SeeRequestsFurnizor();
        controller.name = new TableColumn<>();
        controller.number = new TableColumn<>();
        controller.urgent = new TableColumn<>();
        controller.TableView= new javafx.scene.control.TableView<>();
        elem= FXCollections.observableArrayList(RequestService.getRequests());
    }
    @After
    public void tearDown() {
        controller = null;
    }
    @Test
    public void testPeople() throws IOException {
        controller.getPeople();
        assertNotNull(controller.getRequests());
        assertEquals(elem, controller.getRequests());
    }
}
