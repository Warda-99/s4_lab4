package sample;

public class Product {
    private String name = "Chleb";
    private Double price = 2.5;
    private Double weight = 0.5;

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product:\n name = " + name + ",\n price = " + price + " zł,\n weight = " + weight + " kg.";
    }
}
