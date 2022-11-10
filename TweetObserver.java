import java.util.*;

public class TweetObserver implements Observer{
    private String tweet;

    public void update(Observable s, Object argument){
        if(s instanceof User){
            System.out.println("Tweet added!");
            System.out.println(((User) s).getID() + ": " + ((User) s).getLastTweet());
        }
    }
}
