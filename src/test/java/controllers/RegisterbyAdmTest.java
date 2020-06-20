package controllers;

import Register.RegisterbyAdm;
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

public class RegisterbyAdmTest extends ApplicationTest{

        public static final String TEST_USER = "testUser";
        public static final String TEST_PASSWORD = "testPassword";
        private RegisterbyAdm controller;

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
            controller = new RegisterbyAdm();
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
            controller.RegisterSomeone();
            assertEquals(1, UserService.getUsers().size());
            assertEquals("Account created successfully!", controller.registrationMessage.getText());
        }
        @Test
        public void testAddSameUserTwice() {
            controller.RegisterSomeone();
            controller.RegisterSomeone();
            assertEquals("An account with the username " + TEST_USER + " already exists!", controller.registrationMessage.getText());
        }
        @Test
        public void testHandleAddNullUsernameActionCode() {
            controller.usernameField.setText("");
            controller.RegisterSomeone();
            assertEquals("Invalid username!", controller.registrationMessage.getText());
        }
        @Test
        public void testHandleAddNullPasswordActionCode() {
            controller.passwordField.setText("");
            controller.RegisterSomeone();
            assertEquals("Invalid password!", controller.registrationMessage.getText());
        }
        @Test
        public void testHandleAddNullChoiceboxActionCode() {
            controller.role.setValue(null);
            controller.RegisterSomeone();
            assertEquals("Complete the choicebox!", controller.registrationMessage.getText());
        }
}
