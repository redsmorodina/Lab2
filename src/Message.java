import javax.swing.*;

public class Message extends JOptionPane {

    JFrame f;
    Message(String label){
        f=new JFrame();
        JOptionPane.showMessageDialog(f,label,"Message",JOptionPane.PLAIN_MESSAGE);
    }

}
