package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.UsernameAlreadyExistsException;
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

public class UserServiceTest {
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
        UserService.loadUsersFromFile();
        assertTrue(Files.exists(UserService.USERS_PATH));
    }

    @Test
    public void testLoadUsersFromFile() throws Exception {
        UserService.loadUsersFromFile();
        assertNotNull(UserService.users);
        Assert.assertEquals(0, UserService.users.size());
    }
    @Test
    public void testAddOneUser() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test", "testPass", "test1user");
        assertNotNull(UserService.users);
        assertEquals(1, UserService.users.size());
    }
}
