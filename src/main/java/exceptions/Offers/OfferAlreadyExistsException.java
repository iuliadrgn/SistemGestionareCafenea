package exceptions.Offers;

public class OfferAlreadyExistsException extends Exception{
    private String product;

    public OfferAlreadyExistsException(String product) {
        super(String.format("Offer already exists!", product));
        this.product = product;
    }

    public String getProduct() {
        return product;
    }
}
