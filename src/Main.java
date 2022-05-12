import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static ArrayList<Goods> goodsArrayList;
    static ArrayList<Group> groupArrayList;

    public static void main(String[] args) throws IOException {
        goodsArrayList = new ArrayList<>();
        groupArrayList = new ArrayList<>();

    }


    private static void addGoods(Goods goods) {
        goodsArrayList.add(goods);
        groupArrayList.get(groupArrayList.indexOf(goods.getGroup())).getGoods().add(goods);
//STRING VALUE OF
    }

    private static void deleteGoods(Goods goods) {
        goodsArrayList.remove(goods);
        groupArrayList.get(groupArrayList.indexOf(goods.getGroup())).getGoods().remove(goods);

    }

    private static Goods makeNewGoods() throws IOException {
        Group group = chooseAGroup();
        String name = DataInput.getString("Name:");
        while (!checkTheNameOnUnique(1, name)) {
            name = DataInput.getString("Name:");
        }
        String describtion = DataInput.getString("Description");
        String producer = DataInput.getString("Producer");
        int quantity = DataInput.getInt("Quantity");
        double price = DataInput.getDouble("Price");
        return new Goods(group, name, describtion, producer, quantity, price);
    }

    //0 - group, 1 - goods
    private static boolean checkTheNameOnUnique(int k, String name) {
        if (k == 0) {
            for (int i = 0; i < groupArrayList.size(); i++) {
                if (groupArrayList.get(i).getName().equalsIgnoreCase(name)) {
                    return false;
                }
            }
            return true;
        } else if (k == 1) {
            for (int i = 0; i < goodsArrayList.size(); i++) {
                if (goodsArrayList.get(i).getName().equalsIgnoreCase(name)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static Group chooseAGroup() {
        if (groupArrayList.size() > 0) {
            for (int i = 0; i < groupArrayList.size(); i++) {
                System.out.println((i + 1) + " - " + groupArrayList.get(i));
            }
            int k = DataInput.getInt("Number");
            return groupArrayList.get(k - 1);
        }
        return null;
    }


    private static void editGoods(Goods goods) throws IOException {
        Group newGroup = chooseAGroup();
        if (!goods.getGroup().getName().equals(newGroup.getName())) {

            groupArrayList.get(groupArrayList.indexOf(goods.getGroup())).getGoods().remove(goods);
            groupArrayList.get(groupArrayList.indexOf(newGroup)).getGoods().add(goods);
        }
        goods.setGroup(newGroup);

        goods.setPrice(DataInput.getDouble("Price"));
        goods.setQuantity(DataInput.getInt("Quantity"));
        goods.setDescription(DataInput.getString("Description"));
        goods.setProducer(DataInput.getString("Producer"));
        goods.setName(DataInput.getString("Name"));
    }

    private static void addGroup(Group group) {
        groupArrayList.add(group);
    }

    private static Group makeNewGroup() throws IOException {
        String name = DataInput.getString("Name:");
        String description = DataInput.getString("Description:");
        return new Group(name, description, new ArrayList<Goods>());
    }

    private static void removeGroup(Group group) {
        groupArrayList.remove(group);
    }

    private static void editGroup(Group group) throws IOException {
        group.setName(DataInput.getString("Name"));
        group.setDescription(DataInput.getString("Description"));
    }

    private static ArrayList<String> statisticInfoFromAllGroups() {
        ArrayList<String> answ = new ArrayList<>();
        for (int i = 0; i < groupArrayList.size(); i++) {
            answ.addAll(statisticInfoFromGroup(groupArrayList.get(i)));

        }
        return answ;
    }

    private static ArrayList<String> statisticInfoFromGroup(Group a) {
        ArrayList<String> answ = new ArrayList<>();
        answ.add("\n" + a.getName());
        if (groupArrayList.get(groupArrayList.indexOf(a)).getGoods().size() == 0) {
            answ.add("\nГрупа порожня");
        } else {
            for (int i = 0; i < groupArrayList.get(groupArrayList.indexOf(a)).getGoods().size(); i++) {
                answ.add("\n" + groupArrayList.get(groupArrayList.indexOf(a)).getGoods().get(i).toString());
            }
        }
        return answ;
    }


    private static ArrayList<String> statisticInfoGeneralPrice() {
        ArrayList<String> answ = new ArrayList();
        answ.add("Загальна вартість товару на складі " + statisticInfoGeneralPrice());
        return answ;
    }

    private static ArrayList<String> statisticInfoGroupPrice(Group a) {
        ArrayList<String> answ = new ArrayList();
        answ.add("Вартість товару на складі у групі " + a.getName() + countPriceGroup(a));
        return answ;
    }

    private static double getPriceAllProduct() {
        double answ = 0;
        for (int i = 0; i < groupArrayList.size(); i++) {
            answ = answ + countPriceGroup(groupArrayList.get(i));
        }
        return answ;
    }

    private static double countPriceGroup(Group group) {
        double answ = 0;
        for (int i = 0; i < groupArrayList.get(groupArrayList.indexOf(group)).getGoods().size(); i++) {
            Goods a = (Goods) groupArrayList.get(groupArrayList.indexOf(group)).getGoods().get(i);
            answ = answ + a.getPrice() * a.getQuantity();
        }
        return answ;
    }

    private static ArrayList<Goods> findGoodsByName(String name){
        ArrayList<Goods> result = new ArrayList<>();
        for (Goods good:goodsArrayList) {
            if(good.getName().contains(name)) result.add(good);
        }
        return result;
    }

    private static ArrayList<Goods> findGoodsByProducer(String producer){
        ArrayList<Goods> result = new ArrayList<>();
        for (Goods good:goodsArrayList) {
            if(good.getProducer().contains(producer)) result.add(good);
        }
        return result;
    }

    private static ArrayList<Goods> findGoodsByNameAndProducer(String name,String producer){
        ArrayList<Goods> result = new ArrayList<>();
        for (Goods good:goodsArrayList) {
            if(good.getName().contains(name)&&good.getProducer().contains(producer)) result.add(good);
        }
        return result;
    }
}
