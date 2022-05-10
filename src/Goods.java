import java.util.Objects;

public class Goods {
    private Group group;
    private String name;
    private String description;
    private String producer;
    private int quantity;
    private double price;

    public Goods(Group group, String name, String description, String producer, int quantity, double price) {
        this.group = group;
        this.name = name;
        this.description = description;
        this.producer = producer;
        this.quantity = quantity;
        this.price = price;
    }


    @Override
    public String toString() {
        return "Goods{" +
                "group='" + group.getName() + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", producer='" + producer + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
