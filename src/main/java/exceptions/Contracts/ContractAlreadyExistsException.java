package exceptions.Contracts;

import static java.lang.String.*;

public class ContractAlreadyExistsException extends Exception {
    private String product;

    public ContractAlreadyExistsException(String product) {
        super(format("Contract already exists!", product));
        this.product = product;
    }

    public String getProduct() {
        return product;
    }
}
