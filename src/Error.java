import javax.swing.*;

public class Error extends JOptionPane {

    JFrame f;
    Error(String label){
        f=new JFrame();
        JOptionPane.showMessageDialog(f,label,"Alert",JOptionPane.WARNING_MESSAGE);
    }

}
