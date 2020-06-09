package models;

public class Offer {
    private String product;
    private String price;
    private String number;
    private String state;

    public Offer() {
    }

    public Offer(String product,String price, String number, String state) {
        this.product = product;
        this.price = price;
        this.number = number;
        this.state = state;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product =product;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price =price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        models.Offer ofr = (models.Offer) o;

        if (!product.equals(ofr.product)) return false;
        if (!price.equals(ofr.price)) return false;
        if (!number.equals(ofr.number)) return false;
        return state.equals(ofr.state);
    }

    @Override
    public int hashCode() {
        int result =product.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + state.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "product='" + product + '\'' +
                "price='" + price + '\'' +
                ", number='" + number + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
