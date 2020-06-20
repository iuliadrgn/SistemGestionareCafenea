package controllers;
import Requests.CreateRequest;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import services.FileSystem.FileSystemService;
import services.Request.RequestService;
import services.User.UserService;

import static junit.framework.TestCase.assertEquals;

public class CreateRequestTest extends ApplicationTest {
    private CreateRequest controller;

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
        controller = new CreateRequest();
        controller.NameField = new TextField();
        controller.NumberField = new PasswordField();
        controller.UrgentField = new ChoiceBox();
        controller.RequestMessage = new Text();
        controller.NameField.setText("Test1");
        controller.NumberField.setText("Test1");
        controller.UrgentField.setValue("Yes");
    }
    @After
    public void tearDown() {
        controller = null;
    }
    @Test
    public void testHandleAddUserActionCode() {
        controller.Okay();
        assertEquals(1, RequestService.getRequests().size());
        assertEquals("Request created successfully!", controller.RequestMessage.getText());
    }
    @Test
    public void testAddSameUserTwice() {
        controller.Okay();
        controller.Okay();
        assertEquals("Request already exists!", controller.RequestMessage.getText());
    }
    @Test
    public void testHandleAddNullUsernameActionCode() {
        controller.NameField.setText("");
        controller.Okay();
        assertEquals("Complete the product field!", controller.RequestMessage.getText());
    }
    @Test
    public void testHandleAddNullPasswordActionCode() {
        controller.NumberField.setText("");
        controller.Okay();
        assertEquals("Complete the number field!", controller.RequestMessage.getText());
    }
    @Test
    public void testHandleAddNullChoiceboxActionCode() {
        controller.UrgentField.setValue(null);
        controller.Okay();
        assertEquals("Complete the choicebox!", controller.RequestMessage.getText());
    }
}
