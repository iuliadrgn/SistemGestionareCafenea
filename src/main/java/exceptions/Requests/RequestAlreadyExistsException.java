package exceptions.Requests;

import static java.lang.String.format;

public class RequestAlreadyExistsException extends Exception {
    private String name;

    public RequestAlreadyExistsException(String name) {
        super(format("Request already exists!", name));
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
