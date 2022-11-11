import javax.swing.*;
import java.awt.event.*;

public class UserView {
    private JFrame frame;
    private JTextField followUserID;
    private JTextField tweetText;
    private DefaultListModel<String> followingList;
    private DefaultListModel<String> tweetList;
    private User user;

    public UserView(User inputUser){
        user = inputUser;
        initialize(inputUser.getID() + "' user view");
        frame.setVisible(true);
    }

    private void initialize(String inputTitle){
        frame = new JFrame(inputTitle);
        frame.setBounds(50, 50, 50, 50);
        frame.getContentPane().setLayout(null);

        tweetText = new JTextField();
        tweetText.setBounds(100, 100, 50, 50);
        frame.getContentPane().add(tweetText);
        tweetText.setColumns(10);

        JButton followUserButton = new JButton("Follow User");
        followUserButton.setBounds(150, 150, 50, 50);
        frame.getContentPane().add(followUserButton);
        followUserButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                user.followUser((followUserID.getText()));
            }
        });

        followingList = new DefaultListModel<String>();
        JList<String> followingUserDisplay = new JList<String>(followingList);
        followingUserDisplay.setBounds(200, 200, 50, 50);
        frame.getContentPane().add(followingUserDisplay);
        followingList.addElement("Currently Following: ");

        followUserID = new JTextField();
        followUserID.setBounds(250, 250, 50, 50);
        frame.getContentPane().add(followUserID);
        followUserID.setColumns(10);

        JButton tweetButton = new JButton("Post Tweet");
        tweetButton.setBounds(300, 300, 50, 50);
        frame.getContentPane().add(tweetButton);

        tweetList = new DefaultListModel<String>();
        JList<String> tweetDisplay = new JList<String>(tweetList);
        tweetDisplay.setBounds(350, 350, 50, 50);
        frame.getContentPane().add(tweetDisplay);
    }

    public void updateFollowers(String inputUserID){
        followingList.addElement(" - " + inputUserID);
    }

    public void updateTweetList(String inputMessage){
        tweetList.addElement(inputMessage);
    }

}
