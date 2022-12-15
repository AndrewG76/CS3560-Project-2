import java.util.*;

public class User extends Observable implements Component{
    private String id;

    private List<User> followerList;
    private List<User> followingList;
    private List<String> tweets;
    private List<String> newsFeed;
    private UserView userView;
    private UserGroup parentGroup;
    private final long creationTime;
    private long lastUpdateTime;

    //This constructor initializes the aspects of what makes a "user"
    public User(String inputID, UserGroup inputParent){
        id = inputID;
        followerList = new ArrayList<User>();
        followingList = new ArrayList<User>();
        newsFeed = new ArrayList<String>();
        tweets = new ArrayList<String>();
        this.parentGroup = inputParent;
        follow(this); //Also, we want the user technically following themselves because that's how they update their feed with their own messages
        creationTime = System.currentTimeMillis();
        lastUpdateTime = System.currentTimeMillis();
    }

    @Override
    public void accept(Visitor inputVisitor){
        inputVisitor.atUser(this);
    }

    //Doing this adds an observer
    public void follow(User inputUser){
        inputUser.addFollower(this);
        followingList.add(inputUser);
        inputUser.addObserver(new TweetObserver(this));
    }

    public String getID(){
        return id;
    }

    public List<User> getFollowerList(){
        return followerList;
    }

    public List<User> getFollowingList(){
        return followingList;
    }

    public List<String> getTweets(){
        return tweets;
    }

    public UserGroup getParent(){
        return parentGroup;
    }

    public String getLastTweet(){
        return tweets.get(tweets.size() - 1);
    }

    public List<String> getNewsFeed(){
        return newsFeed;
    }

    public void addFollower(User inputUser){
        followerList.add(inputUser);
    }

    public void addTweet(String inputTweet){ //This one can be confused with the other method, this is just for general archiving/backend purposes
        tweets.add(inputTweet);
    }

    public void sendTweet(String inputTweet){ //And this one is when we use the entire functionality of sending out a tweet with notifying and all
        addTweet(inputTweet);
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString(){
        return "[User] " + id; //Displays the name on the scroll panel and clarifies user identity
    }

    public void openUserView(){ //This function exists to create the user's control panel and then show it off after clicking open user view from the admin control panel
        userView = new UserView(this);
    }
    
    //This takes advantage of the Visitor class to search through all users and return the user with the matching ID, following them and updating the current user's followingList
    public void followUser(String inputUserToFollow){
        UserGroup root = getParent().getRoot();
        UserFinderVisitor userFinderVisitor = new UserFinderVisitor(inputUserToFollow);
        root.accept(userFinderVisitor);
        follow((userFinderVisitor.getTarget()));
        userView.updateFollowers(inputUserToFollow);
    }

    //This method will be used to update the feed and then the newsfeed archives the messages just for consistency
    public void updateTweetFeed(String inputMessage){
        userView.updateTweetList(inputMessage);
        newsFeed.add(inputMessage);
        lastUpdateTime = System.currentTimeMillis();
    }

    @Override
    public long getCreationTime(){
        return creationTime;
    }

    public long getLastUpdateTime(){
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long inputTime){
        lastUpdateTime = inputTime;
    }
}
