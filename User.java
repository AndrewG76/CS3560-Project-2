import java.util.*;

public class User extends Observable implements Component{
    private String id;

    private List<User> followerList;
    private List<User> followingList;
    private List<String> tweets;
    private List<String> newsFeed;
    private UserView userView;
    private UserGroup parentGroup;

    public User(String inputID, UserGroup inputParent){
        id = inputID;
        followerList = new ArrayList<User>();
        followingList = new ArrayList<User>();
        newsFeed = new ArrayList<String>();
        tweets = new ArrayList<String>();
        this.parentGroup = inputParent;
        follow(this);
    }

    @Override
    public void accept(Visitor inputVisitor){
        inputVisitor.atUser(this);
    }

    public void follow(User inputUser){
        inputUser.addFollower(this);
        followingList.add(inputUser);
        inputUser.addObserver(new TweetObserver(this));
    }

    public String getID(){
        return id;
    }

    public List<User> getFollowingList(){
        return followingList;
    }

    public String getLastTweet(){
        return tweets.get(tweets.size() - 1);
    }

    public List<User> getFollowerList(){
        return followerList;
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

    public List<String> getTweets(){
        return tweets;
    }

    @Override
    public String toString(){
        return "[User] " + id;
    }

    public void setID(String inputID){
        id = inputID;
    }

    public void openUserView(){
        userView = new UserView(this);
    }

    public UserGroup getParent(){
        return parentGroup;
    }

    public void followUser(String inputUserToFollow){
        UserGroup root = getParent().getRoot();
        UserFinderVisitor userFinderVisitor = new UserFinderVisitor(inputUserToFollow);
        root.accept(userFinderVisitor);
        follow((userFinderVisitor.getTarget()));
        userView.updateFollowers(inputUserToFollow);
    }
    
    public List<String> getNewsFeed(){
        return newsFeed;
    }

    public void updateTweetFeed(String inputMessage){
        userView.updateTweetList(inputMessage);
        newsFeed.add(inputMessage);
    }
}
