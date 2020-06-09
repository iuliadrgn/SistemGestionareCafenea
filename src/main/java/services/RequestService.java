package services;


import exceptions.CouldNotWriteRequestsException;
import exceptions.RequestAlreadyExistsException;
import models.Request;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class RequestService {
    private static List<Request> requests;
    private static final Path REQUEST_PATH = FileSystemService.getPathToFile("req", "requests.json");

    public static void loadRequestFromFile() throws IOException {

        if (!Files.exists(REQUEST_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(RequestService.class.getClassLoader().getResource("requests.json")), REQUEST_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        requests = objectMapper.readValue(REQUEST_PATH.toFile(), new TypeReference<List<Request>>() {
        });

    }

    public static void addRequest(String name, String number, String urgent) throws RequestAlreadyExistsException {
        checkRequestDoesNotAlreadyExist(name);
        requests.add(new Request(name,number,urgent));
        persistRequests();
    }
    private static void checkRequestDoesNotAlreadyExist(String name) throws RequestAlreadyExistsException {
        for (Request re: requests) {
            System.out.println("Request: " + re);
            if (Objects.equals(name, re.getName()))
                throw new RequestAlreadyExistsException(name);
        }
    }

    private static void persistRequests() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(REQUEST_PATH.toFile(), requests);
        } catch (IOException e) {
            throw new CouldNotWriteRequestsException();
        }
    }




}
