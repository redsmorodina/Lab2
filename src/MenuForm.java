import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MenuForm extends JFrame {
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

    /**
     * We will create main panel
     * @param frame - frame
     */
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

    /**
     * we will create statistic info main panel
     * @param frame
     */
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


        JButton aboutGroup = new JButton("About the chosen group");
        aboutGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statisticPanelMain.setVisible(false);
                remove(statisticPanelMain);
                initGoodsEdit(frame, 3);
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

    /**
     * we will make a panel with statistic info about all goods
     * @param frame
     */
    private void initStatisticAllGoods(Frame frame) {
        JPanel statisticAllPanel = new JPanel(new GridLayout(3, 1));
        add(statisticAllPanel);
        statisticAllPanel.add(new JLabel("Statistic information about all goods"));
        ArrayList<String> information = Main.statisticInfoFromAllGroups();
        JPanel info = new JPanel(new GridLayout(information.size() + 1, 1));

        JScrollPane y = new JScrollPane(info);

        y.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        y.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        statisticAllPanel.add(y);
        // statisticAllPanel.add(y);
        //statisticAllPanel.add(info);
        info.add(new JLabel(Main.statisticInfoGeneralPrice()));
        for (int i = 0; i < information.size(); i++) {
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

    /**
     * we will create panel with add|delete|edit mode for group
     * @param frame - frame
     */
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
                if (Main.groupArrayList.size() <= 0) {
                    new Error("There is no group. Please, add a group before editing.");
                } else {
groupPanel.setVisible(false);
remove(groupPanel);
initGroupEdit1(frame);
                }
            }
        });
        groupPanel.add(editButtonGroup);


        JButton deleteButtonGroup = new JButton("Delete");
        deleteButtonGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Main.groupArrayList.size() <= 0) {
                    new Error("There is no group. Please, add a group before deleting.");
                } else {
                    groupPanel.setVisible(false);
                    remove(groupPanel);
                    initGroupDelete(frame);
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



    private void initGroupDelete(Frame frame) {
        JPanel goodsEditPanel = new JPanel(new GridLayout(4, 1));
        add(goodsEditPanel);
        goodsEditPanel.add(new JLabel("Choose a group"));

        String[] groupNameArray = new String[Main.groupArrayList.size()];
        for (int i = 0; i < Main.groupArrayList.size(); i++) {
            groupNameArray[i] = Main.groupArrayList.get(i).getName();
        }

        JComboBox group = new JComboBox(groupNameArray);
        goodsEditPanel.add(group);

        JButton next = new JButton("Delete");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goodsEditPanel.setVisible(false);
                remove(goodsEditPanel);
                Group a = null;
                String groupName = (String) group.getSelectedItem();
                for (int i = 0; i < Main.groupArrayList.size(); i++) {
                    if (groupName.equals(Main.groupArrayList.get(i).getName())) {
                        a = Main.groupArrayList.get(i);
                        break;
                    }
                }
                Main.removeGroup(a);
                goodsEditPanel.setVisible(false);
                remove(goodsEditPanel);
                initGroup(frame);
            }
        });
        goodsEditPanel.add(next);

        JButton back = new JButton("Back");
        back.setFont(new Font("Arial", Font.PLAIN, 25));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goodsEditPanel.setVisible(false);
                remove(goodsEditPanel);
                initGroup(frame);
            }
        });
        goodsEditPanel.add(back);
    }




    private void initGroupEdit1(Frame frame) {
            JPanel goodsEditPanel = new JPanel(new GridLayout(4, 1));
            add(goodsEditPanel);
            goodsEditPanel.add(new JLabel("Choose a group"));

            String[] groupNameArray = new String[Main.groupArrayList.size()];
            for (int i = 0; i < Main.groupArrayList.size(); i++) {
                groupNameArray[i] = Main.groupArrayList.get(i).getName();
            }

            JComboBox group = new JComboBox(groupNameArray);
            goodsEditPanel.add(group);

            JButton next = new JButton("Next");
            next.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    goodsEditPanel.setVisible(false);
                    remove(goodsEditPanel);
                    Group a = null;
                    String groupName = (String) group.getSelectedItem();
                    for (int i = 0; i < Main.groupArrayList.size(); i++) {
                        if (groupName.equals(Main.groupArrayList.get(i).getName())) {
                            a = Main.groupArrayList.get(i);
                            break;
                        }
                    }
                    initGroupEdit2(frame, a);
                }
            });
            goodsEditPanel.add(next);

            JButton back = new JButton("Back");
            back.setFont(new Font("Arial", Font.PLAIN, 25));
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    goodsEditPanel.setVisible(false);
                    remove(goodsEditPanel);
                    initGroup(frame);
                }
            });
            goodsEditPanel.add(back);
        }

    private void initGroupEdit2(Frame frame, Group a) {
        JPanel groupAddPanel = new JPanel(new GridLayout(4, 2));
        add(groupAddPanel);
        groupAddPanel.add(new JLabel("Name:"));
        JTextField nameText = new JTextField();
        nameText.setFont(new Font("Arial", Font.PLAIN, 25));
        groupAddPanel.add(nameText);

        groupAddPanel.add(new JLabel("Description"));
        JTextField descrText = new JTextField();
        descrText.setFont(new Font("Arial", Font.PLAIN, 25));
        groupAddPanel.add(descrText);



        JButton addButton = new JButton("OK");
        addButton.setFont(new Font("Arial", Font.PLAIN, 25));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Main.checkTheNameOnUnique(1, nameText.getText()) && !nameText.getText().equals(a.getName())) {
                    new Error("Please, change the group name. This group name has already been used");
                } else {

                    String n;
                    if(nameText.getText().equals("")){
                        n = a.getName();
                    }
                    else{
                        n = nameText.getText();
                    }

                    String d;
                    if(descrText.getText().equals("")){
                        d = a.getDescription();
                    }
                    else{
                        d = descrText.getText();
                    }


                    try {
                        Main.editGroup(a,  n, d);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    groupAddPanel.setVisible(false);
                    remove(groupAddPanel);
                    initGoods(frame);
                }
            }
        });
        groupAddPanel.add(addButton);
    }


    /**
     * we will create a group adding panel
     * @param frame - frame
     */
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




                if (nameText.getText().equals("") || descrText.getText().equals("")) {
                    new Error("Please, fill all fields");

                } else if (!Main.checkTheNameOnUnique(0, nameText.getText())) {
                    new Error("Please, change the group name. This group name has already been used");
                } else {

                    Main.addGroup(new Group(nameText.getText(), descrText.getText(), new ArrayList<Goods>()));
                    groupAddPanel.setVisible(false);
                    remove(groupAddPanel);
                    initGroup(frame);
                }



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

    /**
     * we will create main edit|add|delete panel for goods
     * @param frame - frame
     */
    private void initGoods(Frame frame) {
        JPanel goodsPanel = new JPanel(new GridLayout(5, 1));
        add(goodsPanel);
        goodsPanel.add(new JLabel("GOODS MENU"));


        JButton addButtonGoods = new JButton("Add");
        addButtonGoods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Main.groupArrayList.size() <= 0) {
                    new Error("There is no group. Please, add a group before adding a goods.");
                } else {
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
                if (Main.groupArrayList.size() <= 0) {
                    new Error("There is no group. Please, add a group before editing.");
                } else if (Main.goodsArrayList.size() <= 0) {
                    new Error("There is no goods. Please, add a goods before editing.");
                } else {
                    goodsPanel.setVisible(false);
                    remove(goodsPanel);


                    initGoodsEdit(frame, 1);
                }
            }
        });
        goodsPanel.add(editButtonGoods);


        JButton deleteButtonGoods = new JButton("Delete");
        deleteButtonGoods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Main.groupArrayList.size() <= 0) {
                    new Error("There is no group. Please, add a group before deleting.");
                } else if (Main.goodsArrayList.size() <= 0) {
                    new Error("There is no goods. Please, add a goods before deleting.");
                } else {
                    goodsPanel.setVisible(false);
                    remove(goodsPanel);
                    initGoodsEdit(frame, 2);
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

    /**
     * we will create panel for choosing a group
     * @param frame - frame
     * @param mode - 1 - edit, 2 - delete, 3 - statistic
     */
    private void initGoodsEdit(Frame frame, int mode ) {
        JPanel goodsEditPanel = new JPanel(new GridLayout(4, 1));
        add(goodsEditPanel);
        goodsEditPanel.add(new JLabel("Choose a group"));

        String[] groupNameArray = new String[Main.groupArrayList.size()];
        for (int i = 0; i < Main.groupArrayList.size(); i++) {
            groupNameArray[i] = Main.groupArrayList.get(i).getName();
        }

        JComboBox group = new JComboBox(groupNameArray);
        goodsEditPanel.add(group);

        JButton next = new JButton("Next");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goodsEditPanel.setVisible(false);
                remove(goodsEditPanel);
                Group a = null;
                String groupName = (String) group.getSelectedItem();
                for (int i = 0; i < Main.groupArrayList.size(); i++) {
                    if (groupName.equals(Main.groupArrayList.get(i).getName())) {
                        a = Main.groupArrayList.get(i);
                        break;
                    }
                }
                if(mode==1 || mode==2)
                initGoodsEdit2(frame, a, mode);
                else
                    initStatisticGroup(frame, a);

            }
        });
        goodsEditPanel.add(next);

        JButton back = new JButton("Back");
        back.setFont(new Font("Arial", Font.PLAIN, 25));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goodsEditPanel.setVisible(false);
                remove(goodsEditPanel);
                initGoods(frame);
            }
        });
        goodsEditPanel.add(back);
    }

    /**
     * we will choose a goods from the list
     * @param frame - frame
     * @param group - group to find goods
     * @param mode - 1 - edit, 2 - delete
     */
    private void initGoodsEdit2(Frame frame, Group group, int mode) {
        JPanel goodsEditPanel = new JPanel(new GridLayout(4, 1));
        add(goodsEditPanel);
        goodsEditPanel.add(new JLabel("Choose a goods"));

        ArrayList<Goods> goodsFromGroup = group.getGoods();


        String[] goodsNameArray = new String[goodsFromGroup.size()];
        for (int i = 0; i < goodsFromGroup.size(); i++) {
            goodsNameArray[i] = goodsFromGroup.get(i).getName();
        }

        JComboBox goods = new JComboBox(goodsNameArray);
        goodsEditPanel.add(goods);

        JButton next = new JButton("Next");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goodsEditPanel.setVisible(false);
                remove(goodsEditPanel);
                Goods a = null;
                String goodsName = (String) goods.getSelectedItem();
                for (int i = 0; i < Main.goodsArrayList.size(); i++) {
                    if (goodsName.equals(Main.goodsArrayList.get(i).getName())) {
                        a = Main.goodsArrayList.get(i);
                        break;
                    }
                }
                if(mode==1)
                initGoodsEdit3(frame, a);
                else if(mode==2) {
                    Main.deleteGoods(a);
                    initGoods(frame);
                }

            }
        });
        goodsEditPanel.add(next);

        JButton back = new JButton("Back");
        back.setFont(new Font("Arial", Font.PLAIN, 25));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goodsEditPanel.setVisible(false);
                remove(goodsEditPanel);
                initGoodsEdit(frame, 1);
            }
        });
        goodsEditPanel.add(back);
    }

    private void initStatisticGroup(Frame frame, Group a) {

        JPanel statisticAllPanel = new JPanel(new GridLayout(3, 1));
        add(statisticAllPanel);
        statisticAllPanel.add(new JLabel("Statistic information about "+a.getName()));
        ArrayList<String> information = Main.statisticInfoFromGroup(a);
        JPanel info = new JPanel(new GridLayout(information.size() + 1, 1));

        JScrollPane y = new JScrollPane(info);

        y.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        y.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        statisticAllPanel.add(y);
        // statisticAllPanel.add(y);
        //statisticAllPanel.add(info);
        info.add(new JLabel(Main.statisticInfoGeneralPrice()));
        for (int i = 0; i < information.size(); i++) {
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

    /**
     * We will make goods edit panel
     * @param frame - frame
     * @param a - goods to edit
     */
    private void initGoodsEdit3(Frame frame, Goods a) {
        JPanel goodsEditPanel = new JPanel(new GridLayout(8, 2));
        add(goodsEditPanel);

        goodsEditPanel.add(new JLabel("Edit a goods"));
        goodsEditPanel.add(new JLabel("   "));

        goodsEditPanel.add(new JLabel("Group"));
        String[] groupNameArray = new String[Main.groupArrayList.size()];
        for (int i = 0; i < Main.groupArrayList.size(); i++) {
            groupNameArray[i] = Main.groupArrayList.get(i).getName();
        }

        JComboBox group = new JComboBox(groupNameArray);
        goodsEditPanel.add(group);

        goodsEditPanel.add(new JLabel("Name:"));
        JTextField nameText = new JTextField();
        nameText.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsEditPanel.add(nameText);

        goodsEditPanel.add(new JLabel("Description"));
        JTextField descrText = new JTextField();
        descrText.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsEditPanel.add(descrText);

        goodsEditPanel.add(new JLabel("Producer"));
        JTextField prodText = new JTextField();
        prodText.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsEditPanel.add(prodText);

        goodsEditPanel.add(new JLabel("Quantity"));
        JSpinner quantity = new JSpinner(new SpinnerNumberModel(a.getQuantity(), 0, 1000000, 1));
        
        quantity.setSize(700, 300);
        quantity.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsEditPanel.add(quantity);
        goodsEditPanel.add(new JLabel("Price"));
        JSpinner price = new JSpinner(new SpinnerNumberModel(a.getPrice(), 0, 1000000, 0.1));
        price.setSize(700, 300);
        price.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsEditPanel.add(price);

        JButton addButton = new JButton("OK");
        addButton.setFont(new Font("Arial", Font.PLAIN, 25));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Main.checkTheNameOnUnique(1, nameText.getText()) && !nameText.getText().equals(a.getName())) {
                    new Error("Please, change the goods name. This group name has already been used");
                } else {
                    String groupGoods = (String) group.getSelectedItem();
                    Group ab = null;
                    for (int i = 0; i < Main.groupArrayList.size(); i++) {
                        if (groupGoods.equals(Main.groupArrayList.get(i).getName())) {
                            ab = Main.groupArrayList.get(i);
                            break;
                        }
                    }
                    int q = (int) quantity.getValue();
                    double p = (double) price.getValue();
                    String n;
                    if(nameText.getText().equals("")){
                        n = a.getName();
                    }
                    else{
                        n = nameText.getText();
                    }

                    String d;
                    if(descrText.getText().equals("")){
                        d = a.getDescription();
                    }
                    else{
                        d = descrText.getText();
                    }

                    String pr;
                    if(prodText.getText().equals("")){
                        pr = a.getProducer();
                    }
                    else{
                        pr = prodText.getText();
                    }



                    try {
                        Main.editGoods(a, ab, n, d, pr, q, p);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    goodsEditPanel.setVisible(false);
                    remove(goodsEditPanel);
                    initGoods(frame);
                }
            }
        });
        goodsEditPanel.add(addButton);
    }

    /**
     * we will make a panel to add a goods
     * @param frame - frame
     */
    private void initGoodsAdd(Frame frame) {
        JPanel goodsAddPanel = new JPanel(new GridLayout(8, 2));
        add(goodsAddPanel);
        goodsAddPanel.add(new JLabel("ADD A GOODS"));
        goodsAddPanel.add(new JLabel("   "));

        goodsAddPanel.add(new JLabel("Goods"));
        String[] groupNameArray = new String[Main.groupArrayList.size()];
        for (int i = 0; i < Main.groupArrayList.size(); i++) {
            groupNameArray[i] = Main.groupArrayList.get(i).getName();
        }

        JComboBox group = new JComboBox(groupNameArray);
        goodsAddPanel.add(group);

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
                if (nameText.getText().equals("") || descrText.getText().equals("") || prodText.getText().equals("")) {
                    new Error("Please, fill all fields");

                } else if (!Main.checkTheNameOnUnique(1, nameText.getText())) {
                    new Error("Please, change the goods name. This goods name has already been used");
                } else {
                    String groupGoods = (String) group.getSelectedItem();
                    Group a = null;
                    for (int i = 0; i < Main.groupArrayList.size(); i++) {
                        if (groupGoods.equals(Main.groupArrayList.get(i).getName())) {
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