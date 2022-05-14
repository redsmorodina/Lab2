import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuForm extends JFrame{
    JPanel mainPanel;
    JPanel groupPanel;
    JPanel goodsPanel;
    private JButton groupButton;
    private JButton goodsButton;
    private JButton findButton;
    private JButton supplyButton;
    private JButton statisticButton;
    private JButton fileButton;
    private JPanel MenuPanel;


    public MenuForm() {
        super("Storage programm");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 900);
        init(this);

    }

    private void init(Frame frame) {
        mainPanel = new JPanel(new GridLayout(3, 3));
        add(mainPanel);
        mainPanel.add(new JLabel(""), SwingConstants.CENTER);
        mainPanel.add(new JLabel("STORAGE"), SwingConstants.CENTER);
        mainPanel.add(new JLabel(""), SwingConstants.CENTER);


        groupButton = new JButton("Group");
        groupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                remove(mainPanel);
                initGroup(frame);
            }
        });
        mainPanel.add(groupButton);


        goodsButton = new JButton("Goods");
        goodsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                remove(mainPanel);
                initGoods(frame);
            }
        });
        mainPanel.add(goodsButton);


        supplyButton = new JButton("Supply");
        supplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        mainPanel.add(supplyButton);


        findButton = new JButton("Find");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        mainPanel.add(findButton);


        statisticButton = new JButton("Statistic information");
        statisticButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                remove(mainPanel);
                initStatistic(frame);
            }
        });
        mainPanel.add(statisticButton);


        fileButton = new JButton("File");
        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        mainPanel.add(fileButton);
    }

    private void initStatistic(Frame frame) {
        JPanel statisticPanelMain = new JPanel(new GridLayout(4, 1));
        add(statisticPanelMain);
        statisticPanelMain.add(new JLabel("Choose method to show"));


        JButton allGoods = new JButton("About all goods");
        allGoods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statisticPanelMain.setVisible(false);
                remove(statisticPanelMain);
                initStatisticAllGoods(frame);
            }
        });
        statisticPanelMain.add(allGoods);


        JButton aboutGroup = new JButton("About the chosen theme");
        aboutGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        statisticPanelMain.add(aboutGroup);


        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statisticPanelMain.setVisible(false);
                remove(statisticPanelMain);
                init(frame);
            }
        });
        statisticPanelMain.add(back);
    }

    private void initStatisticAllGoods(Frame frame) {
        JPanel statisticAllPanel = new JPanel(new GridLayout(3, 1));
        add(statisticAllPanel);
        statisticAllPanel.add(new JLabel("Statistic information about all goods"));
        ArrayList<String> information = Main.statisticInfoFromAllGroups();
        JPanel info = new JPanel(new GridLayout(information.size()+1, 1));

        JScrollPane y = new JScrollPane(info);

        y.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        y.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        statisticAllPanel.add(y);
       // statisticAllPanel.add(y);
        //statisticAllPanel.add(info);
        info.add(new JLabel(Main.statisticInfoGeneralPrice()));
        for(int i=0; i<information.size(); i++){
            info.add(new JLabel(information.get(i)));
        }
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statisticAllPanel.setVisible(false);
                remove(statisticAllPanel);
                initStatistic(frame);
            }
        });
        statisticAllPanel.add(back);
    }

    private void initGroup(Frame frame) {
        groupPanel = new JPanel(new GridLayout(5, 1));
        add(groupPanel);
        groupPanel.add(new JLabel("GROUP MENU"));


        JButton addButtonGroup = new JButton("Add");
        addButtonGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                groupPanel.setVisible(false);
                remove(groupPanel);
                initGroupAdd(frame);
            }
        });
        groupPanel.add(addButtonGroup);


        JButton editButtonGroup = new JButton("Edit");
        editButtonGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Main.groupArrayList.size()<=0){
new Error("There is no group. Please, add a group before editing.");
                }
                else{

                }
            }
        });
        groupPanel.add(editButtonGroup);


        JButton deleteButtonGroup = new JButton("Delete");
        deleteButtonGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Main.groupArrayList.size()<=0){
                    new Error("There is no group. Please, add a group before deleting.");
                }
                else{

                }
            }
        });
        groupPanel.add(deleteButtonGroup);


        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                groupPanel.setVisible(false);
                remove(groupPanel);
                init(frame);
            }
        });
        groupPanel.add(backButton);
    }

    private void initGroupAdd(Frame frame) {
JPanel groupAddPanel = new JPanel(new GridLayout(4, 2));
add(groupAddPanel);
groupAddPanel.add(new JLabel("ADD A GROUP"));
groupAddPanel.add(new JLabel("   "));

groupAddPanel.add(new JLabel("Name:"));
JTextField nameText = new JTextField();
nameText.setFont(new Font("Arial", Font.PLAIN, 25));
groupAddPanel.add(nameText);

groupAddPanel.add(new JLabel("Description"));
JTextField descrText = new JTextField();
descrText.setFont(new Font("Arial", Font.PLAIN, 25));
groupAddPanel.add(descrText);

JButton addButton = new JButton("Add");
addButton.setFont(new Font("Arial", Font.PLAIN, 25));
addButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {

    }
});
groupAddPanel.add(addButton);

JButton back = new JButton("Back");
back.setFont(new Font("Arial", Font.PLAIN, 25));
back.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        groupAddPanel.setVisible(false);
        remove(groupAddPanel);
        initGroup(frame);
    }
});
groupAddPanel.add(back);
    }

    private void initGoods(Frame frame) {
       JPanel goodsPanel = new JPanel(new GridLayout(5, 1));
        add(goodsPanel);
        goodsPanel.add(new JLabel("GOODS MENU"));


        JButton addButtonGoods = new JButton("Add");
        addButtonGoods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Main.groupArrayList.size()<=0){
                    new Error("There is no group. Please, add a group before adding a goods.");
                }
                else{
goodsPanel.setVisible(false);
remove(goodsPanel);
initGoodsAdd(frame);
                }
            }
        });
        goodsPanel.add(addButtonGoods);


        JButton editButtonGoods = new JButton("Edit");
        editButtonGoods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Main.groupArrayList.size()<=0){
                    new Error("There is no group. Please, add a group before editing.");
                }
                else if (Main.goodsArrayList.size()<=0){
                    new Error("There is no goods. Please, add a goods before editing.");
                }
                else{

                }
            }
        });
        goodsPanel.add(editButtonGoods);


        JButton deleteButtonGoods = new JButton("Delete");
        deleteButtonGoods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Main.groupArrayList.size()<=0){
                    new Error("There is no group. Please, add a group before deleting.");
                }
                else if (Main.goodsArrayList.size()<=0){
                    new Error("There is no goods. Please, add a goods before deleting.");
                }
                else{

                }
            }
        });
        goodsPanel.add(deleteButtonGoods);


        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goodsPanel.setVisible(false);
                remove(goodsPanel);
                init(frame);
            }
        });
        goodsPanel.add(backButton);
    }

    private void initGoodsAdd(Frame frame) {
        JPanel goodsAddPanel = new JPanel(new GridLayout(8, 2));
        add(goodsAddPanel);
        goodsAddPanel.add(new JLabel("ADD A GOODS"));
        goodsAddPanel.add(new JLabel("   "));

        goodsAddPanel.add(new JLabel("Goods"));
        String [] groupNameArray = new String[Main.groupArrayList.size()];
        for(int i=0; i<Main.groupArrayList.size(); i++){
           groupNameArray[i]=Main.groupArrayList.get(i).getName();
        }

        //String groupGoods;

        JComboBox group = new JComboBox(groupNameArray);
        goodsAddPanel.add(group);
        /*
        group.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                groupGoods = group.getName();
            }
        });


        String
        System.out.println(groupGoods);


         */
        goodsAddPanel.add(new JLabel("Name:"));
        JTextField nameText = new JTextField();
        nameText.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsAddPanel.add(nameText);

        goodsAddPanel.add(new JLabel("Description"));
        JTextField descrText = new JTextField();
        descrText.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsAddPanel.add(descrText);

        goodsAddPanel.add(new JLabel("Producer"));
        JTextField prodText = new JTextField();
        prodText.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsAddPanel.add(prodText);

        goodsAddPanel.add(new JLabel("Quantity"));
        JSpinner quantity = new JSpinner(new SpinnerNumberModel(0, 0, 1000000, 1));
        quantity.setSize(700, 300);
        quantity.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsAddPanel.add(quantity);

        goodsAddPanel.add(new JLabel("Price"));
        JSpinner price = new JSpinner(new SpinnerNumberModel(10, 0, 1000000, 0.1));
        price.setSize(700, 300);
        price.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsAddPanel.add(price);

        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Arial", Font.PLAIN, 25));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
if(nameText.getText().equals("") || descrText.getText().equals("")|| prodText.getText().equals("")){
    new Error("Please, fill all fields");
}
else{
    String groupGoods = (String) group.getSelectedItem();
    Group a = null;
    for(int i=0; i<Main.groupArrayList.size(); i++){
        if(groupGoods.equals(Main.groupArrayList.get(i).getName())){
            a = Main.groupArrayList.get(i);
            break;
        }
    }
    int q = (int) quantity.getValue();
    double p = (double) price.getValue();
    Main.addGoods(new Goods(a, nameText.getText(), descrText.getText(), prodText.getText(), q, p));
    goodsAddPanel.setVisible(false);
    remove(goodsAddPanel);
    initGoods(frame);
}
            }
        });
        goodsAddPanel.add(addButton);

        JButton back = new JButton("Back");
        back.setFont(new Font("Arial", Font.PLAIN, 25));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goodsAddPanel.setVisible(false);
                remove(goodsAddPanel);
                initGoods(frame);
            }
        });
        goodsAddPanel.add(back);
    }


}
