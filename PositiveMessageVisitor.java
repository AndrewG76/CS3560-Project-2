import java.util.*;

public class PositiveMessageVisitor implements Visitor{
    private int positiveMessageCount;
    private int totalMessageCount;
    private double positiveRatio;
    private List<String> positiveWords;
    
    //We initialize the positive words according to the example
    public PositiveMessageVisitor(){
        positiveMessageCount = 0;
        totalMessageCount = 0;
        positiveWords = new ArrayList<String>();
        positiveWords.add("cool");
        positiveWords.add("good");
    }

    public double getPositiveRatio(){
        if(totalMessageCount == 0){
            return 0.0;
        }
        else{
            positiveRatio = ((double) positiveMessageCount/ (double) totalMessageCount) * 100.0;
            return positiveRatio;
        }
    }

    //Here, this implementation of atUser searches all the messages for positive words and adds it to the positive counter
    public void atUser(User inputUser){
        List<String> userTweets = inputUser.getTweets();
        for(String tweet : userTweets){
            for(String positiveWord : positiveWords){
                    if(tweet.toLowerCase().indexOf(positiveWord.toLowerCase()) != -1){
                    positiveMessageCount++;
                    break; //We break here because otherwise, it might say we have two positive messages when only one was sent but has multiple 'good' words.
                }
            }
            totalMessageCount++;
        }
    }

    public void atGroup(UserGroup inputGroup){
        //Because we're searching the individual users' tweets for positive words, we don't have anything happen at the groups
    }
}
