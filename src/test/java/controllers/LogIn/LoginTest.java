package controllers.LogIn;

import Log_in.Login.LogIn;
import Register.Register;
import exceptions.Users.UsernameAlreadyExistsException;
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

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class LoginTest extends ApplicationTest {
    public static final String TEST_USER = "test1";
    public static final String TEST_PASSWORD = "test1";
    private LogIn controller;
    private Register controller_reg;
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
        controller_reg = new Register();
        controller_reg.usernameField = new TextField();
        controller_reg.passwordField = new PasswordField();
        controller_reg.role = new ChoiceBox();
        controller_reg.registrationMessage = new Text();
        controller_reg.passwordField.setText(TEST_PASSWORD);
        controller_reg.usernameField.setText(TEST_USER);

        controller = new LogIn();
        controller.UsernameField = new TextField();
        controller.PasswordField = new PasswordField();
        controller.LogInMessage = new Text();
        controller.PasswordField.setText(TEST_PASSWORD);
        controller.UsernameField.setText(TEST_USER);
    }
    @After
    public void tearDown() {
        controller = null;
        controller_reg=null;
    }
    @Test
    public void testPressButton() throws IOException {
        controller.PasswordField.setText("a");
        controller.UsernameField.setText("a");
        controller.PressButton();
        assertEquals("Invalid credentials!", controller.LogInMessage.getText());
    }
    @Test
    public void testPressButton2() throws IOException {
        controller.PasswordField.setText("");
        controller.UsernameField.setText(TEST_USER);
        controller.PressButton();
        assertEquals("Invalid password!", controller.LogInMessage.getText());
    }
    @Test
    public void testPressButton3() throws IOException {
        controller.PasswordField.setText(TEST_PASSWORD);
        controller.UsernameField.setText("");
        controller.PressButton();
        assertEquals("Invalid username!", controller.LogInMessage.getText());
    }
    @Test(expected = NullPointerException.class)
    public void testPressButton4() throws IOException, UsernameAlreadyExistsException {
        UserService.addUser(TEST_USER,TEST_PASSWORD,"Admin");

        controller.PressButton();
        assertEquals("", controller.LogInMessage.getText());
    }
}
