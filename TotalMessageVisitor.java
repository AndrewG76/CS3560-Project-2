public class TotalMessageVisitor implements Visitor{
    private int messageCount;

    public TotalMessageVisitor(){
        messageCount = 0;
    }

    public int MessageCount(){
        return messageCount;
    }

    public void atUser(User inputUser){
        messageCount += inputUser.getTweets().size();
    }

    public void atGroup(UserGroup inputUser){
        
    }
}
