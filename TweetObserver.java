import java.util.*;

public class TweetObserver implements Observer{
    User user;

    public TweetObserver(User inputUser){
        user = inputUser;
    }

    public void update(Observable s, Object argument){
        if(s instanceof User){
            user.updateTweetFeed(((User) s).getID() + ": " + ((User) s).getLastTweet());
        }
    }
}
