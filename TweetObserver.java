import java.util.*;

public class TweetObserver implements Observer{
    User user;

    public TweetObserver(User inputUser){
        user = inputUser;
    }

    //Our observer implementation here is needed to update user news feeds whenever a tweet is sent out
    public void update(Observable s, Object argument){
        if(s instanceof User){
            user.updateTweetFeed(((User) s).getID() + ": " + ((User) s).getLastTweet());
        }
    }
}
