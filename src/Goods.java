public class Goods {
    String name;
    String description;
    String producer;
    int quantity;
    int price;

    public Goods(String name, String description, String producer, int quantity, int price) {
        this.name = name;
        this.description = description;
        this.producer = producer;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", producer='" + producer + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
