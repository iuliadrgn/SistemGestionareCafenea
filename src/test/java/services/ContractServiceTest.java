package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.Contracts.ContractAlreadyExistsException;
import models.Contract;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import services.Contract.ContractService;
import services.FileSystem.FileSystemService;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.*;

public class ContractServiceTest {
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
        ContractService.loadContractsFromFile();
        assertTrue(Files.exists(ContractService.CONTRACT_PATH));
    }

    @Test
    public void testLoadContractsFromFile() throws Exception {
        ContractService.loadContractsFromFile();
        assertNotNull(ContractService.contracts);
        Assert.assertEquals(0, ContractService.contracts.size());
    }

    @Test
    public void testAddOneContract() throws Exception {
        ContractService.loadContractsFromFile();
        ContractService.addContract("test", "testPass", "test1user","test1","Test1","11/11/1111");
        assertNotNull(ContractService.contracts);
        assertEquals(1, ContractService.contracts.size());
    }
    @Test
    public void testAddTwoContracts() throws Exception {
        ContractService.loadContractsFromFile();
        ContractService.addContract("test1", "testPass1", "test1user","test1","Test1","11/11/1111");
        ContractService.addContract("test2", "testPass2", "test2user","test1","Test1","11/11/1111");
        assertNotNull(ContractService.contracts);
        assertEquals(2, ContractService.contracts.size());
    }
    @Test(expected = ContractAlreadyExistsException.class)
    public void testAddContractAlreadyExists() throws Exception {
        ContractService.loadContractsFromFile();
        ContractService.addContract("test1", "testPass1", "test1user","test1","Test1","11/11/1111");
        assertNotNull(ContractService.contracts);
        ContractService.checkContractDoesNotAlreadyExist("test1");
    }
    @Test
    public void testAddOneContractIsPersisted() throws Exception {
        ContractService.loadContractsFromFile();
        ContractService.addContract("test1", "testPass1", "test1user","test1","Test1","11/11/1111");
        List<Contract> contracts= new ObjectMapper().readValue(ContractService.CONTRACT_PATH.toFile(), new TypeReference<List<Contract>>() {
        });
        assertNotNull(ContractService.contracts);
        assertEquals(1, contracts.size());
    }
    @Test
    public void testAddTwoContractsArePersisted() throws Exception {
        ContractService.loadContractsFromFile();
        ContractService.addContract("test1", "testPass1", "test1user","test1","Test1","11/11/1111");
        ContractService.addContract("test2", "testPass2", "test2user","test1","Test1","11/11/1111");
        List<Contract> contracts= new ObjectMapper().readValue(ContractService.CONTRACT_PATH.toFile(), new TypeReference<List<Contract>>() {
        });
        assertNotNull(ContractService.contracts);
        assertEquals(2, contracts.size());
    }


}
