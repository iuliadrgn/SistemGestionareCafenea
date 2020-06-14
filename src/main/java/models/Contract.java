package models;




public class Contract {
    private String product;
    private String price;
    private String number;
    private String state;
    private String numef;
    private String data;


    public Contract() {
    }

    public Contract(String product, String price, String number, String state, String numef, String data) {
        this.product = product;
        this.price = price;
        this.number = number;
        this.state = state;
        this.numef=numef;
        this.data=data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        models.Contract con = (models.Contract) o;

        if (!product.equals(con.product)) return false;
        if (!price.equals(con.price)) return false;
        if (!number.equals(con.number)) return false;
        if (!numef.equals(con.numef)) return false;
        if (!data.equals(con.data)) return false;
        return state.equals(con.state);
    }

    @Override
    public int hashCode() {
        int result =product.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + numef.hashCode();
        result = 31 * result + data.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "product='" + product + '\'' +
                "price='" + price + '\'' +
                ", number='" + number + '\'' +
                ", state='" + state + '\'' +
                ", numef='" + numef + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
