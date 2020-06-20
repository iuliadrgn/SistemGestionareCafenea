package controllers;

import Register.Register;
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
import services.User.UserService;

import static junit.framework.TestCase.assertEquals;

public class RegisterTest extends ApplicationTest {
    public static final String TEST_USER = "testUser";
    public static final String TEST_PASSWORD = "testPassword";
    private Register controller;
    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".FisOnline";
        FileSystemService.initApplicationHomeDirIfNeeded();
        UserService.loadUsersFromFile();
    }
    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        UserService.loadUsersFromFile();
        controller = new Register();
        controller.usernameField = new TextField();
        controller.passwordField = new PasswordField();
        controller.role = new ChoiceBox();
        controller.registrationMessage = new Text();
        controller.passwordField.setText(TEST_PASSWORD);
        controller.usernameField.setText(TEST_USER);
        controller.role.setValue("Admin");
    }
    @After
    public void tearDown() {
        controller = null;
    }
    @Test
    public void testHandleAddUserActionCode() {
        controller.handleRegisterAction();
        assertEquals(1, UserService.getUsers().size());
        assertEquals("Account created successfully!", controller.registrationMessage.getText());
    }
    @Test
    public void testAddSameUserTwice() {
        controller.handleRegisterAction();
        controller.handleRegisterAction();
        assertEquals("An account with the username " + TEST_USER + " already exists!", controller.registrationMessage.getText());
    }
    @Test
    public void testHandleAddNullUsernameActionCode() {
        controller.usernameField.setText("");
        controller.handleRegisterAction();
        assertEquals("Invalid username!", controller.registrationMessage.getText());
    }
    @Test
    public void testHandleAddNullPasswordActionCode() {
        controller.passwordField.setText("");
        controller.handleRegisterAction();
        assertEquals("Invalid password!", controller.registrationMessage.getText());
    }
    @Test
    public void testHandleAddNullChoiceboxActionCode() {
        controller.role.setValue(null);
        controller.handleRegisterAction();
        assertEquals("Complete the choicebox!", controller.registrationMessage.getText());
    }
}
