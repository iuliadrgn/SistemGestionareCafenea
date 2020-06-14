package exceptions;

public class ContractAlreadyExistsException extends Exception {
    private String product;

    public ContractAlreadyExistsException(String product) {
        super(String.format("Contract already exists!", product));
        this.product = product;
    }

    public String getProduct() {
        return product;
    }
}
