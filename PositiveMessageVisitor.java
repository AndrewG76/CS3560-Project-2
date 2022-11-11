import java.util.*;

public class PositiveMessageVisitor implements Visitor{
    private int positiveMessageCount;
    private int totalMessageCount;
    private double positiveRatio;
    private List<String> positiveWords;
    
    public PositiveMessageVisitor(){
        positiveMessageCount = 0;
        totalMessageCount = 0;

        positiveWords.add("cool");
        positiveWords.add("good");
    }

    public double getPositiveRatio(){
        if(totalMessageCount == 0){
            return 0.0;
        }
        else{
            positiveRatio = totalMessageCount/positiveMessageCount;
        return positiveRatio;
        }
    }

    public void atUser(User inputUser){
        List<String> userTweets = inputUser.getTweets();
        for(int i = 0; i < userTweets.size(); i++){
            for(int j = 0; j < positiveWords.size(); j++){
                System.out.println(userTweets.get(i) + " " + positiveWords.get(j));
                if(userTweets.get(i).toLowerCase().indexOf(positiveWords.get(j).toLowerCase()) != -1){
                    positiveMessageCount++;
                    break; //We break here because otherwise, it might say we have two positive messages when only one was sent but has multiple 'good' words.
                }
            }
            totalMessageCount++;
        }
    }

    public void atGroup(UserGroup inputGroup){
        
    }
}
