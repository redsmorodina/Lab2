import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static ArrayList<Goods> goodsArrayList;
    static ArrayList<Group> groupArrayList;

    public static void main(String[] args) throws IOException {
        goodsArrayList = new ArrayList<>();
        groupArrayList = new ArrayList<>();
        groupArrayList.add(new Group("Cereals", "A cereal is any grass cultivated for the edible components of its grain", new ArrayList<>()));
        addGoods(new Goods(groupArrayList.get(0), "buckwheat", "Buckwheat is a plant cultivated for its grain-like seeds and as a cover crop.", "Factory 1", 140, 50));
        addGoods(new Goods(groupArrayList.get(0), "rice", "Rice is the seed of the grass species Oryza sativa or less commonly Oryza glaberrima", "Factory 2", 130, 55));
        addGoods(new Goods(groupArrayList.get(0), "oatmeal", "Oatmeal is a preparation of oats", "Factory 1", 120, 60));

        groupArrayList.add(new Group("Fruits", "a fruit is the seed-bearing structure in flowering plants that is formed from the ovary after flowering", new ArrayList<>()));
        addGoods(new Goods(groupArrayList.get(1), "Apple", "An apple is an edible fruit produced by an apple tree (Malus domestica).","Garden 1", 89, 15));
        addGoods(new Goods(groupArrayList.get(1), "Banana", "A banana is an elongated, edible fruit ", "Garden 2", 107, 50));


        MenuForm a = new MenuForm();
        a.setBounds(200,100,900,700);
        a.setVisible(true);
    }

    /**
     * we will add new goods
     * @param goods - goods to add
     */
    public static void addGoods(Goods goods) {
        goodsArrayList.add(goods);
        groupArrayList.get(groupArrayList.indexOf(goods.getGroup())).getGoods().add(goods);
//STRING VALUE OF
    }

    /**
     * we will delete given goods
     * @param goods - goods to delete
     */
   static void deleteGoods(Goods goods) {
        goodsArrayList.remove(goods);
        groupArrayList.get(groupArrayList.indexOf(goods.getGroup())).getGoods().remove(goods);

    }

    /**
     * we will make a new goods
     * @return new Goods
     * @throws IOException
     */
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


    /**
     * we will chak the name on unique
     * @param k 0 - group, 1 - goods
     * @param name - name to check
     * @return - true - unique, false - not unique
     */
    public static boolean checkTheNameOnUnique(int k, String name) {
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

    /**
     * we will choose a group from given list
     * @return - choosen group
     */
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

    /**
     * We will edit a goods
     * @param goods - goods to edit
     * @param group - group to set
     * @param name - name to set
     * @param descr - descr to set
     * @param prod - producer to set
     * @param quant - quantity to set
     * @param price - price to set
     * @throws IOException
     */
     static void editGoods(Goods goods, Group group, String name, String descr, String prod, int quant, double price) throws IOException {
        Group newGroup = group;
        if (!goods.getGroup().getName().equals(newGroup.getName())) {
            groupArrayList.get(groupArrayList.indexOf(goods.getGroup())).getGoods().remove(goods);
            groupArrayList.get(groupArrayList.indexOf(newGroup)).getGoods().add(goods);
        }
        goods.setGroup(newGroup);

        goods.setPrice(price);
        goods.setQuantity(quant);
        goods.setDescription(descr);
        goods.setProducer(prod);
        goods.setName(name);
    }

    static void addGroup(Group group) {
        groupArrayList.add(group);
    }

    private static Group makeNewGroup() throws IOException {
        String name = DataInput.getString("Name:");
        while (!checkTheNameOnUnique(0, name)) {
            name = DataInput.getString("Name:");
        }
        String description = DataInput.getString("Description:");
        return new Group(name, description, new ArrayList<Goods>());
    }

    static void removeGroup(Group group) {
       ArrayList <Goods> a = new ArrayList<>();
        for(int i=0; i<goodsArrayList.size(); i++){
            if(goodsArrayList.get(i).getGroup().getName().equals(group.getName())){
                a.add(goodsArrayList.get(i));
            }
        }
        goodsArrayList.removeAll(a);
        groupArrayList.remove(group);
    }

    static void editGroup(Group group, String name, String des) throws IOException {
        group.setName(name);
        group.setDescription(des);

    }

    /**
     * we will get statistic info from the all groups
     * @return arraylist with info
     */
    public static ArrayList<String> statisticInfoFromAllGroups() {
        ArrayList<String> answ = new ArrayList<>();
        for (int i = 0; i < groupArrayList.size(); i++) {
            answ.addAll(statisticInfoFromGroup(groupArrayList.get(i)));
        }
        return answ;
    }

    /**
     * we will get statistic info from the group
     * @param a - group to get info
     * @return arraylist with info
     */
    public static ArrayList<String> statisticInfoFromGroup(Group a) {
        ArrayList<String> answ = new ArrayList<>();
        answ.add("\nName: " + a.getName());
        answ.add("\nDescription: "+a.getDescription());
        answ.add(statisticInfoGroupPrice(a));
        if (groupArrayList.get(groupArrayList.indexOf(a)).getGoods().size() == 0) {
            answ.add("\nГрупа порожня");
        } else {
            for (int i = 0; i < groupArrayList.get(groupArrayList.indexOf(a)).getGoods().size(); i++) {
                answ.add("\n" + groupArrayList.get(groupArrayList.indexOf(a)).getGoods().get(i).toString());
            }
        }
        answ.add("\n   ");
        return answ;
    }

    /**
     * we will get string with statistic price info about hte storage
     * @return string with that info
     */
    public static String statisticInfoGeneralPrice() {
        return "Загальна вартість товару на складі " + getPriceAllProduct();

    }

    /**
     * we will get arraylist with statistic price about the given group
     * @param a group
     * @return arraylist with info
     */
    public static String statisticInfoGroupPrice(Group a) {
        return     "Вартість товару на складі у групі " + a.getName() + countPriceGroup(a);

    }

    /**
     * we will get a price of all goods in the storage
     * @return price of the all product
     */
    private static double getPriceAllProduct() {
        double answ = 0;
        for (int i = 0; i < groupArrayList.size(); i++) {
            answ = answ + countPriceGroup(groupArrayList.get(i));
        }
        return answ;
    }

    /**
     * we will count a price of all goods in group
     * @param group - group to count a price
     * @return price
     */
    private static double countPriceGroup(Group group) {
        double answ = 0;
        for (int i = 0; i < groupArrayList.get(groupArrayList.indexOf(group)).getGoods().size(); i++) {
            Goods a = (Goods) groupArrayList.get(groupArrayList.indexOf(group)).getGoods().get(i);
            answ = answ + a.getPrice() * a.getQuantity();
        }
        return answ;
    }

    public static ArrayList<Goods> findGoodsByName(String name){
        ArrayList<Goods> result = new ArrayList<>();
        for (Goods good:goodsArrayList) {
            if(good.getName().contains(name)) result.add(good);
        }
        return result;
    }

    public static ArrayList<Goods> findGoodsByProducer(String producer){
        ArrayList<Goods> result = new ArrayList<>();
        for (Goods good:goodsArrayList) {
            if(good.getProducer().contains(producer)) result.add(good);
        }
        return result;
    }

    public static ArrayList<Goods> findGoodsByNameAndProducer(String name,String producer){
        ArrayList<Goods> result = new ArrayList<>();
        for (Goods good:goodsArrayList) {
            if(good.getName().contains(name)&&good.getProducer().contains(producer)) result.add(good);
        }
        return result;
    }

    public static void exportToFiles() {
        try {
            File groupObj = new File("D:\\all groups.txt");
            FileWriter writerGroup = new FileWriter(groupObj.getAbsolutePath());
            for (Group group : groupArrayList) {
                writerGroup.write(group.getName() + "\n");
                File goodObj = new File("D:\\" + group.getName() + ".txt");
                FileWriter writerGoods = new FileWriter(goodObj.getAbsolutePath());
                for (Goods good : group.getGoods()) {
                    writerGoods.write(good + "\n");
                }
                writerGoods.close();
            }
            writerGroup.close();
        }catch (IOException e){
            new Error("Something went wrong with the file.");
        }
    }

    /**
     * to make the interface of arrive or sell goods
     * @param num number to add (>0 if arrive, <0 if sell)
     * @param good what good should we edit
     */
    public static void addGoodsQuantity(int num, Goods good){
        int countedQuantity=num+ good.getQuantity();
        if(countedQuantity<0){
            new Error("You can`t sell more than you have.");
        }else if(num!=0){
            if(num>0) new Message("On the warehouse arrived "+num+" pcs of "+good.getName());
            else new Message("You sold "+(-num)+" pcs of "+good.getName());
            good.setQuantity(countedQuantity);
        }else{
            new Error("Number can`t be 0.");
        }
    }
}
