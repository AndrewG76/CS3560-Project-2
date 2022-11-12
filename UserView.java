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
        initialize(inputUser.getID() + " user view"); //Allows the window name to be the name of the user previously selected
        frame.setVisible(true);
    }

    //Creates all the elements to interact with
    private void initialize(String inputTitle){
        frame = new JFrame(inputTitle);
        frame.setBounds(50, 50, 522, 430);
        frame.getContentPane().setLayout(null);

        //Standard implementations of ActionListeners to grab user inputs and create a presentable working system
        followUserID = new JTextField();
        followUserID.setBounds(10, 10, 240, 50);
        frame.getContentPane().add(followUserID);
        followUserID.setColumns(10);
        
        JButton followUserButton = new JButton("Follow User");
        followUserButton.setBounds(260, 10, 240, 50);
        frame.getContentPane().add(followUserButton);
        followUserButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                user.followUser((followUserID.getText()));
            }
        });

        tweetText = new JTextField();
        tweetText.setBounds(10, 200, 290, 50);
        frame.getContentPane().add(tweetText);
        tweetText.setColumns(10);

        followingList = new DefaultListModel<String>();
        JList<String> followingUserDisplay = new JList<String>(followingList);
        followingUserDisplay.setBounds(10, 70, 490, 120);
        frame.getContentPane().add(followingUserDisplay);
        followingList.addElement("Currently Following");

        JButton tweetButton = new JButton("Post Tweet");
        tweetButton.setBounds(310, 200, 190, 50);
        frame.getContentPane().add(tweetButton);
        tweetButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                user.sendTweet(tweetText.getText());
            }
        });

        tweetList = new DefaultListModel<String>();
        JList<String> tweetDisplay = new JList<String>(tweetList);
        tweetDisplay.setBounds(10, 260, 490, 120);
        frame.getContentPane().add(tweetDisplay);
        
        JLabel labelMessages = new JLabel();
        labelMessages.setBounds(10, 260, 490, 120);
        frame.getContentPane().add(labelMessages);

        repopulateFollowers();
        repopulateTweets();
    }

    public void updateFollowers(String inputUserID){
        followingList.addElement(" - " + inputUserID);
    }

    public void updateTweetList(String inputMessage){
        tweetList.addElement(inputMessage);
    }

    //These next few functions exist in the off chance that a user closes their view but still wants to see all the updates that took place while they were gone
    private void repopulateTweets(){
        for(String tweet : user.getNewsFeed()){
            updateTweetList(tweet);
        }
    }

    private void repopulateFollowers(){
        for(User following : user.getFollowingList()){
            if(following.getID().equals(user.getID()) != true){
                updateFollowers(following.getID());
            }
        }
    }

}
