import java.util.*;
import javax.swing.*;

public class User extends Observable implements Component{
    private String id;

    List<User> followerList;
    List<User> followingList;
    List<String> tweets;

    public User(String inputID){
        id = inputID;
        followerList = new ArrayList<User>();
        followingList = new ArrayList<User>();
        tweets = new ArrayList<String>();
    }

    public void accept(Visitor v){
        v.atUser(this);
    }

    public void follow(User inputUser){
        inputUser.addFollower(this);
        followingList.add(inputUser);
        addObserver(new TweetObserver());
    }

    public List<User> getFollowingList(){
        return followingList;
    }

    public List<User> getFollowerList(){
        return followerList;
    }

    public String getLastTweet(){
        return tweets.get(tweets.size() - 1);
    }

    public List<String> getTweets(){
        return tweets;
    }

    public String getID(){
        return id;
    }

    public void setID(String inputID){
        id = inputID;
    }

    public void addFollower(User inputUser){
        followerList.add(inputUser);
    }

    public void addTweet(String inputTweet){
        tweets.add(inputTweet);
    }

    public void sendTweet(String inputTweet){
        addTweet(inputTweet);
        setChanged();
        notifyObservers();
    }

}
