package exceptions;

public class RequestAlreadyExistsException extends Exception {
    private String name;

    public RequestAlreadyExistsException(String name) {
        super(String.format("Request already exists!", name));
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
