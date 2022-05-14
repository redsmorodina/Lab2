import java.awt.*;
import java.util.ArrayList;

public class Group {
    private String name;
    private String description;
    private ArrayList <Goods> goods;

    public Group(String name, String description, ArrayList <Goods> goods) {
        this.name = name;
        this.description = description;
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", goods=" + goods +
                '}';
    }

    public ArrayList<Goods> getGoods() {return goods;}
    public void setGoods(ArrayList goods) {this.goods = goods;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
}
