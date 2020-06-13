package models;

public class Offer {
    private String product;
    private String price;
    private String number;
    private String state;



    private String numef;

    public Offer() {
    }

    public Offer(String product,String price, String number, String state,String numef) {
        this.product = product;
        this.price = price;
        this.number = number;
        this.state = state;
        this.numef=numef;
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

    public String getNumef() {
        return numef;
    }

    public void setNumef(String numef) {
        this.numef = numef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        models.Offer ofr = (models.Offer) o;

        if (!product.equals(ofr.product)) return false;
        if (!price.equals(ofr.price)) return false;
        if (!number.equals(ofr.number)) return false;
        if (!numef.equals(ofr.numef)) return false;
        return state.equals(ofr.state);
    }

    @Override
    public int hashCode() {
        int result =product.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + numef.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "product='" + product + '\'' +
                "price='" + price + '\'' +
                ", number='" + number + '\'' +
                ", state='" + state + '\'' +
                ", numef='" + numef + '\'' +
                '}';
    }
}
