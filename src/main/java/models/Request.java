package models;

public class Request {
    private String name;
    private String number;
    private String urgent;

    public Request() {
    }

    public Request(String name, String number, String urgent) {
        this.name = name;
        this.number = number;
        this.urgent = urgent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name =name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUrgent() {
        return urgent;
    }

    public void setUrgent(String urgent) {
        this.urgent = urgent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        models.Request req = (models.Request) o;

        if (!name.equals(req.name)) return false;
        if (!number.equals(req.number)) return false;
        return urgent.equals(req.urgent);
    }

    @Override
    public int hashCode() {
        int result =name.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + urgent.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", urgent='" + urgent + '\'' +
                '}';
    }
}
