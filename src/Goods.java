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

    /**
     * creating to string wih main info
     * @return string with main
     */
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

    /**
     * we will get group
     * @return group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * we will set a group
     * @param group - group to set
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * we will get name
     * @return name of a goods
     */
    public String getName() {
        return name;
    }

    /**
     * we will set name
     * @param name - name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * we will get description
     * @return description of the goods
     */
    public String getDescription() {
        return description;
    }

    /**
     * we will set description
     * @param description - description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * we will get producer
     * @return - goods producer
     */
    public String getProducer() {
        return producer;
    }

    /**
     * we will set producer
     * @param producer - producer to set
     */
    public void setProducer(String producer) {
        this.producer = producer;
    }

    /**
     * we will get quantity
     * @return quantity of a goods
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * we will set quantity
     * @param quantity - quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * we will get price
     * @return - price
     */
    public double getPrice() {
        return price;
    }

    /**
     * we will set prive
     * @param price - price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
