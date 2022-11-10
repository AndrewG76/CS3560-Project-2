import java.util.*;

public class TweetObserver implements Observer{
    public void update(Observable s, Object argument){
        if(s instanceof User){
            ((User) s).updateTweetView(((User) s).getID() + ": " + ((User) s).getLastTweet());
        }
    }
}
