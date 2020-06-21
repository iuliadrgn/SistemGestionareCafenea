package services.Request;


import exceptions.Requests.CouldNotWriteRequestsException;
import exceptions.Requests.RequestAlreadyExistsException;
import models.Request;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.User;
import org.apache.commons.io.FileUtils;
import services.FileSystem.FileSystemService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class RequestService {
    public static List<Request> requests;
    public static final Path REQUEST_PATH = FileSystemService.getPathToFile("req", "Requests/requests.json");

    public static void loadRequestFromFile() throws IOException {

        if (!Files.exists(REQUEST_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(RequestService.class.getClassLoader().getResource("Requests/requests.json")), REQUEST_PATH.toFile());
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
    public static void checkRequestDoesNotAlreadyExist(String name) throws RequestAlreadyExistsException {
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

    public static List<Request> getRequests() {
        return requests;
    }
}
