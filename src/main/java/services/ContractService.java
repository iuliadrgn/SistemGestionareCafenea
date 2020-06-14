package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.ContractAlreadyExistsException;
import exceptions.CouldNotWriteContractsException;

import models.Contract;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


import java.util.List;
import java.util.Objects;

public class ContractService {
    private static List<Contract> contracts;
    private static final Path CONTRACT_PATH = FileSystemService.getPathToFile("contracts", "contracts.json");

    public static void loadContractsFromFile() throws IOException {

        if (!Files.exists(CONTRACT_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(ContractService.class.getClassLoader().getResource("contracts.json")), CONTRACT_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        contracts = objectMapper.readValue(CONTRACT_PATH.toFile(), new TypeReference<List<Contract>>() {
        });

    }

    public static void addContract(String product, String price, String number, String state, String numef,String data) throws ContractAlreadyExistsException {
        checkContractDoesNotAlreadyExist(product);

        contracts.add(new Contract(product,price,number,state,numef,data));
        persistContracts();
    }

    private static void checkContractDoesNotAlreadyExist(String product) throws ContractAlreadyExistsException {
        for (Contract co: contracts) {
            System.out.println("Contract: " + co);
            if (Objects.equals(product, co.getProduct()))
                throw new ContractAlreadyExistsException(product);
        }
    }


    private static void persistContracts(){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(CONTRACT_PATH.toFile(), contracts);
        } catch (IOException e) {
            throw new CouldNotWriteContractsException();
        }
    }
}
