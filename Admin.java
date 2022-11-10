import java.awt.*;
import javax.swing.*;

public class Admin {
    private static Admin instance = null;

    private JFrame frame;
    private JTextField newUserID;
    private JTextField newGroupID;
    private JPanel rootDisplay;
    private UserGroup root;

    private Admin(){
        root = new UserGroup("root");
        initFrame(800,600);
    }

    public static Admin getInstance(){
        if(instance == null){
            synchronized(Admin.class){ //Synchronized allows for the usage of multi-threading processes for live, up-to-date announcements like with the Tweets
                if(instance == null){
                    instance = new Admin();
                }
            }
        }
        return instance;
    }

    private void initFrame(int inputWidth, int inputHeight){
        frame = new JFrame("Admin Control Panel");
        frame.setVisible((true));
        frame.setSize(inputWidth, inputHeight);
    }

    private void addRootView(){
        rootDisplay = new JPanel(new BorderLayout());
        frame.add(rootDisplay);
    }
}
