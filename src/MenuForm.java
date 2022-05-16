import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

    private String text;
    private ArrayList<Goods> found;

    public MenuForm() {
        super("Storage program");
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
        JLabel k = new JLabel("STORAGE");
        k.setFont(new Font("Arial", Font.PLAIN, 25));
        mainPanel.add(k, SwingConstants.CENTER);
        mainPanel.add(new JLabel(" "), SwingConstants.CENTER);


        groupButton = new JButton("Group");
        groupButton.setFont(new Font("Arial", Font.PLAIN, 25));
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
        goodsButton.setFont(new Font("Arial", Font.PLAIN, 25));
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
        supplyButton.setFont(new Font("Arial", Font.PLAIN, 25));
        supplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                remove(mainPanel);
                initSupply(frame);
            }
        });
        mainPanel.add(supplyButton);


        findButton = new JButton("Find");
        findButton.setFont(new Font("Arial", Font.PLAIN, 25));
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                remove(mainPanel);
                initFind(frame);
            }
        });
        mainPanel.add(findButton);


        statisticButton = new JButton("Statistic information");
        statisticButton.setFont(new Font("Arial", Font.PLAIN, 25));
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
        fileButton.setFont(new Font("Arial", Font.PLAIN, 25));
        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int option = fileChooser.showOpenDialog(frame);
                if(option == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    String path=file.getAbsolutePath().replaceAll("\\\\","?;");
                    path=path.replaceAll("\\?;", "\\\\\\\\");
                    Main.exportToFiles(path);
                    new Message("Information was exported on a disk D.");
                }else{
                   new Error("Open command canceled.");
                }
            }
        });
        mainPanel.add(fileButton);
    }

    /**
     * we create panel with supply page
     * @param frame .
     */
    private void initSupply(Frame frame) {
        frame.setSize(1000,800);
        JPanel supplyPanel = new JPanel(new GridLayout(8, 2));
        add(supplyPanel);
        supplyPanel.add(new JLabel("find goods:."),SwingConstants.CENTER);
        supplyPanel.add(new JLabel(""), SwingConstants.CENTER);

        supplyPanel.add(new JLabel("Name:"));
        JTextField nameText = new JTextField();
        nameText.setFont(new Font("Arial", Font.PLAIN, 25));
        supplyPanel.add(nameText);

        supplyPanel.add(new JLabel("Producer:"));
        JTextField producerText = new JTextField();
        producerText.setFont(new Font("Arial", Font.PLAIN, 25));
        supplyPanel.add(producerText);

        JTextArea label = new JTextArea();
        label.setLineWrap(true);
        label.setWrapStyleWord(true);
        label.setEditable(false);
        label.setCursor(null);
        label.setOpaque(false);
        label.setFocusable(false);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setBounds(20,0,400,100);
        JScrollPane jScrollPane = new JScrollPane(label);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        supplyPanel.add(jScrollPane);

        JButton search = new JButton("Search");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                String producer = producerText.getText();
                if (name.equals("") && producer.equals("")) {
                    new Error("You have written nothing");
                } else {
                    if (name.equals("")) {
                        found = Main.findGoodsByProducer(producer);
                    } else if (producer.equals("")) {
                        found = Main.findGoodsByName(name);
                    } else {
                        found = Main.findGoodsByNameAndProducer(name, producer);
                    }
                    text="";
                    if(found.size()==0)  text="We didn`t find any group.";
                    else{
                        for(int i=0;i<found.size();i++){
                            text+=i+" - "+found.get(i)+"\n";
                        }
                    }
                    label.setText(text);
                }
            }
        });
        supplyPanel.add(search);

        supplyPanel.add(new JLabel("Number of :"));
        JTextField numberText = new JTextField();
        numberText.setFont(new Font("Arial", Font.PLAIN, 25));
        supplyPanel.add(numberText);

        supplyPanel.add(new JLabel("How many:"));
        JTextField quantityText = new JTextField();
        quantityText.setFont(new Font("Arial", Font.PLAIN, 25));
        supplyPanel.add(quantityText);

        JButton arrived = new JButton("Arrived");
        arrived.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int num = Integer.parseInt(numberText.getText());
                    int quantity = Integer.parseInt(quantityText.getText());
                    if (label.getText().equals("") ||num<0||num>=label.getLineCount()||quantity<0){
                        new Error("You have entered something wrong.");
                    }else{
                        Main.addGoodsQuantity(quantity,found.get(num));
                    }
                }catch(NumberFormatException ex){
                    new Error("You have entered something wrong.");
                }
            }
        });
        supplyPanel.add(arrived);

        JButton sold = new JButton("Sold");
        sold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int num = Integer.parseInt(numberText.getText());
                    int quantity = Integer.parseInt(quantityText.getText());
                    if (label.getText().equals("") ||num<0||num>=label.getLineCount()||quantity<0){
                        new Error("You have entered something wrong.");
                    }else{
                        Main.addGoodsQuantity(-quantity,found.get(num));
                    }
                }catch(NumberFormatException ex){
                    new Error("You have entered something wrong.");
                }
            }
        });
        supplyPanel.add(sold);

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supplyPanel.setVisible(false);
                remove(supplyPanel);
                init(frame);
            }
        });
        supplyPanel.add(back);
    }

    /**
     * we create panel with find page
     * @param frame .
     */
    private void initFind(Frame frame) {
        JPanel findPanel = new JPanel(new GridLayout(5, 2));
        add(findPanel);
        findPanel.add(new JLabel("Please enter the information about goods."),SwingConstants.CENTER);
        findPanel.add(new JLabel(""), SwingConstants.CENTER);

        findPanel.add(new JLabel("Name:"));
        JTextField nameText = new JTextField();
        nameText.setFont(new Font("Arial", Font.PLAIN, 25));
        findPanel.add(nameText);

        findPanel.add(new JLabel("Producer:"));
        JTextField producerText = new JTextField();
        producerText.setFont(new Font("Arial", Font.PLAIN, 25));
        findPanel.add(producerText);

        JButton search = new JButton("Search");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                String producer = producerText.getText();
                ArrayList<Goods> found;
                if (name.equals("") && producer.equals("")) {
                    new Error("You have written nothing");
                } else {
                    if (name.equals("")) {
                        found = Main.findGoodsByProducer(producer);
                    } else if (producer.equals("")) {
                        found = Main.findGoodsByName(name);
                    } else {
                        found = Main.findGoodsByNameAndProducer(name, producer);
                    }
                    findPanel.setVisible(false);
                    remove(findPanel);
                    initFindResults(frame,found);
                }
            }
        });
        findPanel.add(search);


        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findPanel.setVisible(false);
                remove(findPanel);
                init(frame);
            }
        });
        findPanel.add(back);
    }

    /**
     * we create panel with results of find page
     * @param frame .
     */
    private void initFindResults(Frame frame, ArrayList<Goods> found) {
        JPanel findResultsPanelMain = new JPanel(new GridLayout(4, 1));
        add(findResultsPanelMain);
        findResultsPanelMain.add(new JLabel("Found information:"));

        String text="";
        if(found.size()==0)  text="We didn`t find any group.";
        else{
            for(Goods good:found){
                text+=good+"\n";
            }
        }
        JTextArea label = new JTextArea(text);
        label.setLineWrap(true);
        label.setWrapStyleWord(true);
        label.setEditable(false);
        label.setCursor(null);
        label.setOpaque(false);
        label.setFocusable(false);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setBounds(20,0,400,100);
        JScrollPane jScrollPane = new JScrollPane(label);

        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        findResultsPanelMain.add(jScrollPane);

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findResultsPanelMain.setVisible(false);
                remove(findResultsPanelMain);
                initFind(frame);
            }
        });
        findResultsPanelMain.add(back);


        JButton main = new JButton("Go to main");
        main.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findResultsPanelMain.setVisible(false);
                remove(findResultsPanelMain);
                init(frame);
            }
        });
        findResultsPanelMain.add(main);
    }

    /**
     * we will create statistic info main panel
     * @param frame
     */
    private void initStatistic(Frame frame) {
        JPanel statisticPanelMain = new JPanel(new GridLayout(4, 1));
        add(statisticPanelMain);
        JLabel m  = new JLabel("Choose method to show");
m.setFont(new Font("Arial", Font.PLAIN, 25));
statisticPanelMain.add(m);

        JButton allGoods = new JButton("About all goods");
        allGoods.setFont(new Font("Arial", Font.PLAIN, 25));
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
        aboutGroup.setFont(new Font("Arial", Font.PLAIN, 25));
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
        back.setFont(new Font("Arial", Font.PLAIN, 25));
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
        JLabel G = new JLabel("Statistic information about all goods");
        G.setFont(new Font("Arial", Font.PLAIN, 25));
        statisticAllPanel.add(G);
        ArrayList<String> information = Main.statisticInfoFromAllGroups();
        JPanel info = new JPanel(new GridLayout(information.size() + 1, 1));

        JScrollPane y = new JScrollPane(info);

        y.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        y.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        statisticAllPanel.add(y);
        info.add(new JLabel(Main.statisticInfoGeneralPrice()));
        for (int i = 0; i < information.size(); i++) {
            info.add(new JLabel(information.get(i)));
        }
        JButton back = new JButton("Back");
        back.setFont(new Font("Arial", Font.PLAIN, 25));
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
        JLabel p = new JLabel("GROUP MENU");
p.setFont(new Font("Arial", Font.PLAIN, 25));
groupPanel.add(p);

        JButton addButtonGroup = new JButton("Add");
        addButtonGroup.setFont(new Font("Arial", Font.PLAIN, 25));
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
        editButtonGroup.setFont(new Font("Arial", Font.PLAIN, 25));
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
        deleteButtonGroup.setFont(new Font("Arial", Font.PLAIN, 25));
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
        backButton.setFont(new Font("Arial", Font.PLAIN, 25));
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


    /**
     * we will create group deleting panel
     * @param frame - frame
     */
    private void initGroupDelete(Frame frame) {
        JPanel goodsEditPanel = new JPanel(new GridLayout(4, 1));
        add(goodsEditPanel);
        JLabel h =new JLabel("Choose a group");
h.setFont(new Font("Arial", Font.PLAIN, 25));
goodsEditPanel.add(h);
        String[] groupNameArray = new String[Main.groupArrayList.size()];
        for (int i = 0; i < Main.groupArrayList.size(); i++) {
            groupNameArray[i] = Main.groupArrayList.get(i).getName();
        }

        JComboBox group = new JComboBox(groupNameArray);
        goodsEditPanel.add(group);

        JButton next = new JButton("Delete");
        next.setFont(new Font("Arial", Font.PLAIN, 25));
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
                new Message("Done");
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


    /**
     * we will find group to edit
     * @param frame
     */
    private void initGroupEdit1(Frame frame) {
            JPanel goodsEditPanel = new JPanel(new GridLayout(4, 1));
            add(goodsEditPanel);
            JLabel h = new JLabel("Choose a group");
h.setFont(new Font("Arial", Font.PLAIN, 25));
goodsEditPanel.add(h);
            String[] groupNameArray = new String[Main.groupArrayList.size()];
            for (int i = 0; i < Main.groupArrayList.size(); i++) {
                groupNameArray[i] = Main.groupArrayList.get(i).getName();
            }

            JComboBox group = new JComboBox(groupNameArray);
            goodsEditPanel.add(group);

            JButton next = new JButton("Next");
            next.setFont(new Font("Arial", Font.PLAIN, 25));
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

    /**
     * we will edit group
     * @param frame - frame
     * @param a - group to edit
     */
    private void initGroupEdit2(Frame frame, Group a) {
        JPanel groupAddPanel = new JPanel(new GridLayout(3, 2));
        add(groupAddPanel);
        JLabel KK = new JLabel("Name:");
        KK.setFont(new Font("Arial", Font.PLAIN, 25));
        groupAddPanel.add(KK);
        JTextField nameText = new JTextField();
        nameText.setFont(new Font("Arial", Font.PLAIN, 25));
        groupAddPanel.add(nameText);

        JLabel fu = new JLabel("Description");
        fu.setFont(new Font("Arial", Font.PLAIN, 25));
        groupAddPanel.add(fu);
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
                    new Message("Done");
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
     * we will create a group adding panel
     * @param frame - frame
     */
    private void initGroupAdd(Frame frame) {
        JPanel groupAddPanel = new JPanel(new GridLayout(4, 2));
        add(groupAddPanel);
        JLabel i = new JLabel("ADD A GROUP");
        i.setFont(new Font("Arial", Font.PLAIN, 25));
        groupAddPanel.add(i);
        groupAddPanel.add(new JLabel("   "));

        JLabel u = new JLabel("Name:");
        u.setFont(new Font("Arial", Font.PLAIN, 25));
        groupAddPanel.add(u);
        JTextField nameText = new JTextField();
        nameText.setFont(new Font("Arial", Font.PLAIN, 25));
        groupAddPanel.add(nameText);

        JLabel d = new JLabel("Description");
        d.setFont(new Font("Arial", Font.PLAIN, 25));
        groupAddPanel.add(d);
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
                    new Message("Done");
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
        JLabel f =new JLabel("GOODS MENU");
f.setFont(new Font("Arial", Font.PLAIN, 25));
goodsPanel.add(f);

        JButton addButtonGoods = new JButton("Add");
        addButtonGoods.setFont(new Font("Arial", Font.PLAIN, 25));
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
        editButtonGoods.setFont(new Font("Arial", Font.PLAIN, 25));
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
        deleteButtonGoods.setFont(new Font("Arial", Font.PLAIN, 25));
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
        backButton.setFont(new Font("Arial", Font.PLAIN, 25));
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
       JLabel hhh = new JLabel("Choose a group");
hhh.setFont(new Font("Arial", Font.PLAIN, 25));
goodsEditPanel.add(hhh);
        String[] groupNameArray = new String[Main.groupArrayList.size()];
        for (int i = 0; i < Main.groupArrayList.size(); i++) {
            groupNameArray[i] = Main.groupArrayList.get(i).getName();
        }

        JComboBox group = new JComboBox(groupNameArray);
        goodsEditPanel.add(group);

        JButton next = new JButton("Next");
        next.setFont(new Font("Arial", Font.PLAIN, 25));
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
        if(mode!=3) {

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
        else{
            JButton back = new JButton("Back");
            back.setFont(new Font("Arial", Font.PLAIN, 25));
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    goodsEditPanel.setVisible(false);
                    remove(goodsEditPanel);
                    initStatistic(frame);
                }
            });
            goodsEditPanel.add(back);
        }
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
        JLabel a = new JLabel("Choose a goods");
a.setFont(new Font("Arial", Font.PLAIN, 25));
goodsEditPanel.add(a);
        ArrayList<Goods> goodsFromGroup = group.getGoods();


        String[] goodsNameArray = new String[goodsFromGroup.size()];
        for (int i = 0; i < goodsFromGroup.size(); i++) {
            goodsNameArray[i] = goodsFromGroup.get(i).getName();
        }

        JComboBox goods = new JComboBox(goodsNameArray);
        goodsEditPanel.add(goods);

        String text;
        if(mode ==1){
            text = "Next";
        }
        else{
            text = "Delete";
        }

        JButton next = new JButton(text);
        next.setFont(new Font("Arial", Font.PLAIN, 25));
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
                    new Message("Done");
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

    /**
     * we will show static info for the group
     * @param frame - frame
     * @param a - group to show statistic
     */
    private void initStatisticGroup(Frame frame, Group a) {

        JPanel statisticAllPanel = new JPanel(new GridLayout(3, 1));
        add(statisticAllPanel);
        JLabel editLeee = new JLabel("Statistic information about "+a.getName());
        editLeee.setFont(new Font("Arial", Font.PLAIN, 25));
        statisticAllPanel.add(editLeee);
        ArrayList<String> information = Main.statisticInfoFromGroup(a);
        JPanel info = new JPanel(new GridLayout(information.size() + 1, 1));

        JScrollPane y = new JScrollPane(info);

        y.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        y.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        statisticAllPanel.add(y);
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
        JLabel editL = new JLabel("Edit a goods");
        editL.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsEditPanel.add(editL);
        goodsEditPanel.add(new JLabel("   "));

        JLabel groupL = new JLabel("Group");
        groupL.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsEditPanel.add(groupL);
        String[] groupNameArray = new String[Main.groupArrayList.size()];
        for (int i = 0; i < Main.groupArrayList.size(); i++) {
            groupNameArray[i] = Main.groupArrayList.get(i).getName();
        }

        JComboBox group = new JComboBox(groupNameArray);
        goodsEditPanel.add(group);

        JLabel editL1 = new JLabel("Name");
        editL1.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsEditPanel.add(editL1);
        JTextField nameText = new JTextField();
        nameText.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsEditPanel.add(nameText);

        JLabel editL2 = new JLabel("Description");
        editL2.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsEditPanel.add(editL2);
        JTextField descrText = new JTextField();
        descrText.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsEditPanel.add(descrText);

        JLabel editL3 = new JLabel("Producer");
        editL3.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsEditPanel.add(editL3);
        JTextField prodText = new JTextField();
        prodText.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsEditPanel.add(prodText);

        JLabel editL44 = new JLabel("Quantity");
        editL44.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsEditPanel.add(editL44);
        JSpinner quantity = new JSpinner(new SpinnerNumberModel(a.getQuantity(), 0, 1000000, 1));

        quantity.setSize(700, 300);
        quantity.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsEditPanel.add(quantity);

        JLabel editLww = new JLabel("Price");
        editLww.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsEditPanel.add(editLww);
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
                        new Message("Done");
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
     * we will make a panel to add a goods
     * @param frame - frame
     */
    private void initGoodsAdd(Frame frame) {
        JPanel goodsAddPanel = new JPanel(new GridLayout(8, 2));
        add(goodsAddPanel);
        //
        JLabel addLabel = new JLabel("ADD A GOODS");
        addLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        goodsAddPanel.add(addLabel);
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
                    new Message("Done");
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